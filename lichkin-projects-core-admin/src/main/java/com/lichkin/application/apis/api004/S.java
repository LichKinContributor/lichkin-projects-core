package com.lichkin.application.apis.api004;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.framework.defines.exceptions.LKRuntimeException;
import com.lichkin.framework.utils.security.md5.LKMD5Encrypter;
import com.lichkin.springframework.entities.impl.SysAdminLoginEntity;
import com.lichkin.springframework.services.LKApiServiceImpl;
import com.lichkin.springframework.services.LKApiVoidService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service("ModifyPwdService")
public class S extends LKApiServiceImpl<I, Void> implements LKApiVoidService<I> {

	@Getter
	@RequiredArgsConstructor
	enum ErrorCodes implements LKCodeEnum {

		SysAdminLogin_old_pwd_incorrect(20000)

		;

		private final Integer code;

	}


	@Transactional
	@Override
	public void handle(I sin, String locale, String compId, String loginId) throws LKException {
		SysAdminLoginEntity exist = (SysAdminLoginEntity) sin.getDatas().getLogin();
		if (!exist.getPwd().equals(LKMD5Encrypter.encrypt(sin.getPwdOld()))) {
			throw new LKRuntimeException(ErrorCodes.SysAdminLogin_old_pwd_incorrect);
		}
		exist.setPwd(LKMD5Encrypter.encrypt(sin.getPwdNew()));
		dao.mergeOne(exist);
	}

}
