package com.lichkin.application.services.extend.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysAdminLoginR;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.defines.exceptions.LKRuntimeException;
import com.lichkin.springframework.entities.impl.SysAdminLoginEntity;
import com.lichkin.springframework.services.LoginService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
public class XAdminLoginService extends LoginService {

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
	public SysAdminLoginEntity findUserLoginByToken(String token) {
		if (StringUtils.isBlank(token)) {
			throw new LKRuntimeException(ErrorCodes.INVALIDED_TOKEN);
		}

		QuerySQL sql = new QuerySQL(false, SysAdminLoginEntity.class);

		sql.neq(SysAdminLoginR.usingStatus, LKUsingStatusEnum.DEPRECATED);
		sql.eq(SysAdminLoginR.token, token);

		SysAdminLoginEntity userLogin = dao.getOne(sql, SysAdminLoginEntity.class);
		if (userLogin == null) {
			throw new LKRuntimeException(ErrorCodes.ACCOUNT_INEXIST);
		}
		return userLogin;
	}

}
