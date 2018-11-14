package com.lichkin.application.apis.FastLogin;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lichkin.defines.CoreStatics;
import com.lichkin.framework.db.beans.Condition;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysUserLoginR;
import com.lichkin.framework.db.beans.eq;
import com.lichkin.framework.defines.enums.impl.LKGenderEnum;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.framework.utils.LKDateTimeUtils;
import com.lichkin.framework.utils.LKRandomUtils;
import com.lichkin.springframework.entities.impl.SysUserLoginEntity;
import com.lichkin.springframework.services.LKApiService;
import com.lichkin.springframework.services.LKApiServiceImpl;

@Service(Statics.SERVICE_NAME)
public class S extends LKApiServiceImpl<I, O> implements LKApiService<I, O> {

	/** 接口服务器URL根路径 */
	@Value("${com.lichkin.apis.server.rootUrl}")
	private String apisServerRootUrl;

	@Autowired
	private com.lichkin.application.apis.ValidateSmsSecurityCode.S validateSmsSecurityCodeService;


	@Transactional
	@Override
	public O handle(I sin, String locale, String compId, String loginId) throws LKException {
		String cellphone = sin.getCellphone();

		// 校验验证码
		validateSmsSecurityCodeService.validateSms(cellphone, sin.getSecurityCode());

		// 获取登录信息
		SysUserLoginEntity userLogin = findUserLoginByCellphone(cellphone);

		boolean isLogin = userLogin != null;
		if (isLogin) {// 登录
			// 设置Token
			userLogin.setToken(LKRandomUtils.create64());

			// 修改数据
			dao.mergeOne(userLogin);
		} else {// 注册
			userLogin = new SysUserLoginEntity();

			// 设置Token
			userLogin.setToken(LKRandomUtils.create64());

			// 设置其它值
			userLogin.setUserName("");
			userLogin.setUserCard("");
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

			// 新增数据
			dao.persistOne(userLogin);
		}

		O out = new O();
		out.setLogin(isLogin);
		out.setToken(userLogin.getToken());
		out.setLevel(userLogin.getLevel());
		out.setLoginName(userLogin.getLoginName());
		out.setPhoto(userLogin.getPhoto());
		out.setSecurityCenterUrl(apisServerRootUrl + CoreStatics.SECURITY_CENTER_URL);
		out.setApiServerRootUrl(apisServerRootUrl);
		return out;
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
