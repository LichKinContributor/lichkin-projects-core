package com.lichkin.application.services.extend.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysUserLoginR;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.defines.exceptions.LKRuntimeException;
import com.lichkin.springframework.entities.impl.SysUserLoginEntity;
import com.lichkin.springframework.services.LoginService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
public class XUserLoginService extends LoginService {

	@Getter
	@RequiredArgsConstructor
	enum ErrorCodes implements LKCodeEnum {

		/** 登录信息已失效 */
		INVALIDED_TOKEN(29000),

		/** 账号不存在 */
		ACCOUNT_INEXIST(29001),

		;

		private final Integer code;

	}


	@Override
	public SysUserLoginEntity findUserLoginByToken(String token) {
		if (StringUtils.isBlank(token)) {
			throw new LKRuntimeException(ErrorCodes.INVALIDED_TOKEN);
		}

		QuerySQL sql = new QuerySQL(false, SysUserLoginEntity.class);

		sql.neq(SysUserLoginR.usingStatus, LKUsingStatusEnum.DEPRECATED);
		sql.eq(SysUserLoginR.token, token);

		SysUserLoginEntity userLogin = dao.getOne(sql, SysUserLoginEntity.class);
		if (userLogin == null) {
			throw new LKRuntimeException(ErrorCodes.ACCOUNT_INEXIST);
		}
		return userLogin;
	}

}
