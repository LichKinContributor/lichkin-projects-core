package com.lichkin.application.apis.ModifyPassword;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lichkin.framework.defines.entities.I_Login;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.framework.defines.exceptions.LKRuntimeException;
import com.lichkin.framework.utils.security.md5.LKMD5Encrypter;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.services.LKApiServiceImpl;
import com.lichkin.springframework.services.LKApiVoidService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service(Statics.SERVICE_NAME)
public class S extends LKApiServiceImpl<I, Void> implements LKApiVoidService<I> {

	@Getter
	@RequiredArgsConstructor
	enum ErrorCodes implements LKCodeEnum {

		old_pwd_incorrect(20007),

		new_pwd_is_same_to_saved(20010),

		;

		private final Integer code;

	}


	@Transactional
	@Override
	public void handle(I sin, ApiKeyValues<I> params) throws LKException {
		I_Login login = params.getLogin();

		String pwdSaved = login.getPwd();
		if (!pwdSaved.equals(LKMD5Encrypter.encrypt(sin.getPwdOld()))) {
			throw new LKRuntimeException(ErrorCodes.old_pwd_incorrect);
		}

		String pwdNew = sin.getPwdNew();
		if (pwdSaved.equals(pwdNew)) {
			throw new LKRuntimeException(ErrorCodes.new_pwd_is_same_to_saved);
		}

		login.setPwd(LKMD5Encrypter.encrypt(pwdNew));
		dao.mergeOne(login);
	}

}
