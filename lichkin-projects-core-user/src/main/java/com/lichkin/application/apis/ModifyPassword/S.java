package com.lichkin.application.apis.ModifyPassword;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.framework.defines.exceptions.LKRuntimeException;
import com.lichkin.framework.utils.security.md5.LKMD5Encrypter;
import com.lichkin.springframework.entities.impl.SysUserLoginEntity;
import com.lichkin.springframework.services.LKApiServiceImpl;
import com.lichkin.springframework.services.LKApiVoidService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service(Statics.SERVICE_NAME)
public class S extends LKApiServiceImpl<I, Void> implements LKApiVoidService<I> {

	@Getter
	@RequiredArgsConstructor
	enum ErrorCodes implements LKCodeEnum {

		app_old_pwd_incorrect(20007),

		;

		private final Integer code;

	}


	@Transactional
	@Override
	public void handle(I sin, String locale, String compId, String loginId) throws LKException {
		SysUserLoginEntity login = (SysUserLoginEntity) sin.getDatas().getLogin();

		String pwdOld = LKMD5Encrypter.encrypt(sin.getPwdOld());
		if (!login.getPwd().equals(pwdOld)) {
			throw new LKRuntimeException(ErrorCodes.app_old_pwd_incorrect);
		}

		String pwdNew = LKMD5Encrypter.encrypt(sin.getPwdNew());
		if (!login.getPwd().equals(pwdNew)) {
			login.setPwd(pwdNew);
			dao.mergeOne(login);
		}
	}

}
