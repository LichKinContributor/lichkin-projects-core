package com.lichkin.application.apis.AccountLogin;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
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
import com.lichkin.framework.defines.exceptions.LKRuntimeException;
import com.lichkin.framework.utils.LKDateTimeUtils;
import com.lichkin.framework.utils.LKRandomUtils;
import com.lichkin.framework.utils.security.md5.LKMD5Encrypter;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysEmployeeEntity;
import com.lichkin.springframework.entities.impl.SysUserLoginEntity;
import com.lichkin.springframework.services.LKApiService;
import com.lichkin.springframework.services.LKApiServiceImpl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service(Statics.SERVICE_NAME)
public class S extends LKApiServiceImpl<I, O> implements LKApiService<I, O> {

	@Value("${com.lichkin.api.user.login.lockTimeoutMinutes:1440}")
	private int lockTimeoutMinutes;

	@Value("${com.lichkin.api.user.login.maxErrorTimes:5}")
	private int maxErrorTimes;

	/** 接口服务器URL根路径 */
	@Value("${com.lichkin.apis.server.rootUrl}")
	private String apisServerRootUrl;


	@Getter
	@RequiredArgsConstructor
	enum ErrorCodes implements LKCodeEnum {

		app_account_inexistence(20003),

		app_account_locked(20004),

		app_pwd_incorrect(20005),

		/** 不是员工 */
		YOU_ARE_NOT_A_EMPLOYEE(29003),

		/** 不能绑定多个员工 */
		CAN_NOT_BIND_AN_OTHER_EMPLOYEE(20011),

		;

		private final Integer code;

	}


	@Transactional
	@Override
	public O handle(I sin, ApiKeyValues<I> params) throws LKException {
		SysUserLoginEntity userLogin = findUserLoginByLoginName(sin.getLoginName());
		if (userLogin == null) {
			throw new LKRuntimeException(ErrorCodes.app_account_inexistence);
		}

		if (LKUsingStatusEnum.LOCKED.equals(userLogin.getUsingStatus())) {
			int remainingTime = (int) (lockTimeoutMinutes - ((DateTime.now().getMillis() - LKDateTimeUtils.toDateTime(userLogin.getLockTime()).getMillis()) / 60000));
			if (remainingTime > 0) {
				throw new LKRuntimeException(ErrorCodes.app_account_locked).withParam("#remainingTime", remainingTime);
			}
			userLogin.setErrorTimes((byte) 0);
			userLogin.setUsingStatus(LKUsingStatusEnum.USING);
		}

		if (!userLogin.getPwd().equals(LKMD5Encrypter.encrypt(sin.getPwd()))) {
			userLogin.setErrorTimes((byte) (userLogin.getErrorTimes() + 1));
			if (userLogin.getErrorTimes() >= maxErrorTimes) {
				userLogin.setUsingStatus(LKUsingStatusEnum.LOCKED);
				userLogin.setLockTime(LKDateTimeUtils.now());
				dao.mergeOne(userLogin);
				throw new LKException(ErrorCodes.app_account_locked).withParam("#remainingTime", lockTimeoutMinutes);
			} else {
				dao.mergeOne(userLogin);
				throw new LKException(ErrorCodes.app_pwd_incorrect).withParam("#chances", maxErrorTimes - userLogin.getErrorTimes());
			}
		} else {
			userLogin.setToken(LKRandomUtils.create64());
			dao.mergeOne(userLogin);
		}

		if (Platform.PLATFORM.equals(LKPlatform.EMPLOYEE)) {
			// 首先查找绑定过的用户
			QuerySQL sql = new QuerySQL(false, SysEmployeeEntity.class);
			sql.eq(SysEmployeeR.compId, params.getCompId());
			sql.eq(SysEmployeeR.loginId, userLogin.getId());
			SysEmployeeEntity employee = dao.getOne(sql, SysEmployeeEntity.class);
			if (employee == null) {
				// 验证是否为员工
				sql = new QuerySQL(false, SysEmployeeEntity.class);
				sql.eq(SysEmployeeR.compId, params.getCompId());
				sql.eq(SysEmployeeR.cellphone, userLogin.getCellphone());
				employee = dao.getOne(sql, SysEmployeeEntity.class);
				if (employee == null) {// 不是员工
					throw new LKException(ErrorCodes.YOU_ARE_NOT_A_EMPLOYEE);
				}

				if (StringUtils.isBlank(employee.getLoginId())) {
					// 将员工与登录信息绑定
					employee.setLoginId(userLogin.getId());
					dao.mergeOne(employee);
					// 绑定过的用户从员工补充用户信息
					if (initUserInfoFromEmployee(userLogin, employee)) {
						dao.mergeOne(userLogin);
					}
				} else {// 通过登录ID未找到，通过手机号找到，但却绑定过登录ID，将会绑定多个员工。
					throw new LKException(ErrorCodes.CAN_NOT_BIND_AN_OTHER_EMPLOYEE);
				}
			} else {
				// 绑定过的用户从员工补充用户信息
				if (initUserInfoFromEmployee(userLogin, employee)) {
					dao.mergeOne(userLogin);
				}
			}
		}

		O out = new O();
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
	 * @return 是否被修改了值
	 */
	private boolean initUserInfoFromEmployee(SysUserLoginEntity userLogin, SysEmployeeEntity employee) {
		boolean changed = false;
		if (StringUtils.isBlank(userLogin.getUserName()) && StringUtils.isNotBlank(employee.getUserName())) {
			changed = true;
			userLogin.setUserName(employee.getUserName());
		}

		if ((LKGenderEnum.UNKNOWN.equals(userLogin.getGender())) && (LKGenderEnum.MALE.equals(employee.getGender()) || LKGenderEnum.FEMALE.equals(employee.getGender()))) {
			changed = true;
			userLogin.setGender(employee.getGender());
		}

		if (StringUtils.isBlank(userLogin.getEmail()) && StringUtils.isNotBlank(employee.getEmail())) {
			changed = true;
			userLogin.setEmail(employee.getEmail());
		}

		if (StringUtils.isBlank(userLogin.getUserCard()) && StringUtils.isNotBlank(employee.getUserCard())) {
			changed = true;
			userLogin.setUserCard(employee.getUserCard());
		}

		return changed;
	}


	/**
	 * 按照登录名获取登录信息
	 * @param loginName 登录名/手机号码/身份证号/邮箱
	 * @return 登录信息
	 */
	private SysUserLoginEntity findUserLoginByLoginName(String loginName) {
		if (StringUtils.isBlank(loginName)) {
			return null;
		}
		QuerySQL sql = new QuerySQL(false, SysUserLoginEntity.class);
		sql.neq(SysUserLoginR.usingStatus, LKUsingStatusEnum.DEPRECATED);
		sql.where(

				new Condition(true,

						new Condition(false, new eq(SysUserLoginR.loginName, loginName)),

						new Condition(false, new eq(SysUserLoginR.userCard, loginName)),

						new Condition(false, new eq(SysUserLoginR.cellphone, loginName)),

						new Condition(false, new eq(SysUserLoginR.email, loginName))

				)

		);

		return dao.getOne(sql, SysUserLoginEntity.class);
	}

}
