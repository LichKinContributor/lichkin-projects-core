package com.lichkin.application.apis.api10007.U.n01;

import org.springframework.stereotype.Service;

import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.framework.defines.exceptions.LKRuntimeException;
import com.lichkin.framework.utils.security.md5.LKMD5Encrypter;
import com.lichkin.springframework.entities.impl.SysAdminLoginEntity;
import com.lichkin.springframework.services.LKApiBusUpdateWithoutCheckerService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service("SysAdminLoginU01Service")
public class S extends LKApiBusUpdateWithoutCheckerService<I, SysAdminLoginEntity> {

	@Getter
	@RequiredArgsConstructor
	enum ErrorCodes implements LKCodeEnum {

		SysAdminLogin_old_pwd_incorrect(20000)

		;

		private final Integer code;

	}


	@Override
	protected boolean busCheck(I sin, String locale, String compId, String loginId, SysAdminLoginEntity entity, String id) {
		if (!entity.getPwd().equals(LKMD5Encrypter.encrypt(sin.getPwdOld()))) {
			throw new LKRuntimeException(ErrorCodes.SysAdminLogin_old_pwd_incorrect);
		}
		return true;
	}


	@Override
	protected void beforeSaveMain(I sin, String locale, String compId, String loginId, SysAdminLoginEntity entity) {
		entity.setPwd(LKMD5Encrypter.encrypt(sin.getPwdNew()));
	}

}
