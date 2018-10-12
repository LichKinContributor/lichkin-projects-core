package com.lichkin.application.services.extend.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysUserLoginR;
import com.lichkin.framework.defines.enums.impl.LKErrorCodesEnum;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.defines.exceptions.LKRuntimeException;
import com.lichkin.springframework.entities.impl.SysUserEntity;
import com.lichkin.springframework.entities.impl.SysUserLoginEntity;
import com.lichkin.springframework.services.LoginService;

@Service
public class XUserLoginService extends LoginService<SysUserLoginEntity, SysUserEntity> {

	@Override
	public SysUserLoginEntity findUserLoginByToken(boolean throwException, String token) {
		if (StringUtils.isBlank(token)) {
			if (throwException) {
				throw new LKRuntimeException(LKErrorCodesEnum.INVALIDED_TOKEN);
			}
			return null;
		}

		QuerySQL sql = new QuerySQL(false, SysUserLoginEntity.class);

		sql.neq(SysUserLoginR.usingStatus, LKUsingStatusEnum.DEPRECATED);
		sql.eq(SysUserLoginR.token, token);

		SysUserLoginEntity userLogin = dao.getOne(sql, SysUserLoginEntity.class);
		if (userLogin == null) {
			if (throwException) {
				throw new LKRuntimeException(LKErrorCodesEnum.ACCOUNT_INEXIST);
			}
			return null;
		}
		return userLogin;
	}


	@Override
	public SysUserEntity findUserByUserLogin(boolean throwException, SysUserLoginEntity userLogin) {
		if (userLogin == null) {
			if (throwException) {
				throw new LKRuntimeException(LKErrorCodesEnum.INVALIDED_USER_LOGIN);
			}
			return null;
		}

		SysUserEntity user = dao.findOneById(SysUserEntity.class, userLogin.getUserId());
		if (user == null) {
			if (throwException) {
				throw new LKRuntimeException(LKErrorCodesEnum.ACCOUNT_INEXIST);
			}
			return null;
		}
		return user;
	}

}
