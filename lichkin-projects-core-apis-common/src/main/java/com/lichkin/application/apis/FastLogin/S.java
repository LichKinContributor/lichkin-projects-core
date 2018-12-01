package com.lichkin.application.apis.FastLogin;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lichkin.defines.CoreStatics;
import com.lichkin.framework.db.beans.Condition;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysEmployeeR;
import com.lichkin.framework.db.beans.SysUserLoginR;
import com.lichkin.framework.db.beans.eq;
import com.lichkin.framework.defines.Platform;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.framework.defines.enums.LKPlatform;
import com.lichkin.framework.defines.enums.impl.LKGenderEnum;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.framework.utils.LKDateTimeUtils;
import com.lichkin.framework.utils.LKRandomUtils;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysEmployeeEntity;
import com.lichkin.springframework.entities.impl.SysUserLoginEntity;
import com.lichkin.springframework.services.LKApiService;
import com.lichkin.springframework.services.LKApiServiceImpl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service(Statics.SERVICE_NAME)
public class S extends LKApiServiceImpl<I, O> implements LKApiService<I, O> {

	@Getter
	@RequiredArgsConstructor
	enum ErrorCodes implements LKCodeEnum {

		/** 不是员工 */
		YOU_ARE_NOT_A_EMPLOYEE(29003),

		;

		private final Integer code;

	}


	/** 接口服务器URL根路径 */
	@Value("${com.lichkin.apis.server.rootUrl}")
	private String apisServerRootUrl;

	@Autowired
	private com.lichkin.application.apis.ValidateSmsSecurityCode.S validateSmsSecurityCodeService;


	@Transactional
	@Override
	public O handle(I sin, ApiKeyValues<I> params) throws LKException {
		String cellphone = sin.getCellphone();

		// 校验验证码
		validateSmsSecurityCodeService.validateSms(cellphone, sin.getSecurityCode());

		SysEmployeeEntity employee = null;
		if (Platform.PLATFORM.equals(LKPlatform.EMPLOYEE)) {
			// 验证是否为员工
			QuerySQL sql = new QuerySQL(false, SysEmployeeEntity.class);
			sql.eq(SysEmployeeR.compId, params.getCompId());
			sql.eq(SysEmployeeR.cellphone, cellphone);
			employee = dao.getOne(sql, SysEmployeeEntity.class);
			if (employee == null) {
				throw new LKException(ErrorCodes.YOU_ARE_NOT_A_EMPLOYEE);
			}
		}

		// 获取登录信息
		SysUserLoginEntity userLogin = findUserLoginByCellphone(cellphone);

		boolean isLogin = userLogin != null;
		if (isLogin) {// 登录
			// 设置Token
			userLogin.setToken(LKRandomUtils.create64());

			if (Platform.PLATFORM.equals(LKPlatform.EMPLOYEE)) {
				// 从员工补充用户信息
				initUserInfoFromEmployee(userLogin, employee);
			}

			// 修改数据
			dao.mergeOne(userLogin);

			if (Platform.PLATFORM.equals(LKPlatform.EMPLOYEE) && StringUtils.isBlank(employee.getLoginId())) {
				// 将员工与登录信息绑定
				employee.setLoginId(userLogin.getId());
				dao.mergeOne(employee);
			}
		} else {// 注册
			userLogin = new SysUserLoginEntity();

			// 设置Token
			userLogin.setToken(LKRandomUtils.create64());

			// 设置其它值
			userLogin.setUserName("");
			userLogin.setGender(LKGenderEnum.UNKNOWN);
			userLogin.setLoginName(cellphone);
			userLogin.setCellphone(cellphone);
			userLogin.setEmail("");
			userLogin.setUserCard("");
			userLogin.setPwd("");
			userLogin.setSecurityCode("");
			userLogin.setErrorTimes((byte) 0);
			userLogin.setLevel((byte) 1);
			userLogin.setLockTime(LKDateTimeUtils.toString(LKDateTimeUtils.toDateTime("19700101000000000")));

			if (Platform.PLATFORM.equals(LKPlatform.EMPLOYEE)) {
				// 从员工补充用户信息
				initUserInfoFromEmployee(userLogin, employee);
			}

			// 新增数据
			dao.persistOne(userLogin);

			if (Platform.PLATFORM.equals(LKPlatform.EMPLOYEE)) {
				// 将员工与登录信息绑定
				employee.setLoginId(userLogin.getId());
				dao.mergeOne(employee);
			}
		}

		O out = new O();
		out.setLogin(isLogin);
		out.setToken(userLogin.getToken());
		out.setLevel(userLogin.getLevel());
		out.setLoginName(userLogin.getLoginName());
		out.setPhoto(userLogin.getPhoto());
		out.setSecurityCenterUrl(apisServerRootUrl + CoreStatics.SSO_URL + CoreStatics.SECURITY_CENTER_URL);
		out.setApiServerRootUrl(apisServerRootUrl);
		return out;
	}


	/**
	 * 从员工补充用户信息
	 * @param employee 员工信息
	 * @param userLogin 用户信息
	 */
	private void initUserInfoFromEmployee(SysUserLoginEntity userLogin, SysEmployeeEntity employee) {
		if (StringUtils.isBlank(userLogin.getUserName()) && StringUtils.isNotBlank(employee.getUserName())) {
			userLogin.setUserName(employee.getUserName());
		}

		if ((LKGenderEnum.UNKNOWN.equals(userLogin.getGender())) && (LKGenderEnum.MALE.equals(employee.getGender()) || LKGenderEnum.FEMALE.equals(employee.getGender()))) {
			userLogin.setGender(employee.getGender());
		}

		if (StringUtils.isBlank(userLogin.getEmail()) && StringUtils.isNotBlank(employee.getEmail())) {
			userLogin.setEmail(employee.getEmail());
		}

		if (StringUtils.isBlank(userLogin.getUserCard()) && StringUtils.isNotBlank(employee.getUserCard())) {
			userLogin.setUserCard(employee.getUserCard());
		}
	}


	/**
	 * 按照手机号码获取登录信息
	 * @param cellphone 手机号码
	 * @return 登录信息
	 */
	private SysUserLoginEntity findUserLoginByCellphone(String cellphone) {
		if (StringUtils.isBlank(cellphone)) {
			return null;
		}
		QuerySQL sql = new QuerySQL(false, SysUserLoginEntity.class);
		sql.neq(SysUserLoginR.usingStatus, LKUsingStatusEnum.DEPRECATED);
		sql.where(

				new Condition(true,

						new Condition(false, new eq(SysUserLoginR.loginName, cellphone)),

						new Condition(false, new eq(SysUserLoginR.cellphone, cellphone))

				)

		);

		return dao.getOne(sql, SysUserLoginEntity.class);
	}

}
