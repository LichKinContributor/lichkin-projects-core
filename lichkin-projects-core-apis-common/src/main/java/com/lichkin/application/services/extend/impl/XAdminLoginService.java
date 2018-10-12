package com.lichkin.application.services.extend.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysAdminLoginR;
import com.lichkin.framework.defines.enums.impl.LKErrorCodesEnum;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.defines.exceptions.LKRuntimeException;
import com.lichkin.springframework.entities.impl.SysAdminLoginEntity;
import com.lichkin.springframework.services.LoginService;

@Service
public class XAdminLoginService extends LoginService<SysAdminLoginEntity, SysAdminLoginEntity> {

	@Override
	public SysAdminLoginEntity findUserLoginByToken(boolean throwException, String token) {
		if (StringUtils.isBlank(token)) {
			if (throwException) {
				throw new LKRuntimeException(LKErrorCodesEnum.INVALIDED_TOKEN);
			}
			return null;
		}

		QuerySQL sql = new QuerySQL(false, SysAdminLoginEntity.class);

		sql.neq(SysAdminLoginR.usingStatus, LKUsingStatusEnum.DEPRECATED);
		sql.eq(SysAdminLoginR.token, token);

		SysAdminLoginEntity userLogin = dao.getOne(sql, SysAdminLoginEntity.class);
		if (userLogin == null) {
			if (throwException) {
				throw new LKRuntimeException(LKErrorCodesEnum.ACCOUNT_INEXIST);
			}
			return null;
		}
		return userLogin;
	}


	@Override
	public SysAdminLoginEntity findUserByUserLogin(boolean throwException, SysAdminLoginEntity userLogin) {
		if (userLogin == null) {
			if (throwException) {
				throw new LKRuntimeException(LKErrorCodesEnum.INVALIDED_USER_LOGIN);
			}
			return null;
		}
		return userLogin;
	}

}
