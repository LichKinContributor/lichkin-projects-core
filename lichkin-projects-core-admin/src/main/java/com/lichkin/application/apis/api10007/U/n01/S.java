package com.lichkin.application.apis.api10007.U.n01;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.framework.defines.exceptions.LKRuntimeException;
import com.lichkin.framework.utils.security.md5.LKMD5Encrypter;
import com.lichkin.springframework.entities.impl.SysAdminLoginEntity;
import com.lichkin.springframework.services.LKApiBusUpdateService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service("SysAdminLoginU01Service")
public class S extends LKApiBusUpdateService<I, SysAdminLoginEntity> {

	@Getter
	@RequiredArgsConstructor
	enum ErrorCodes implements LKCodeEnum {

		SysAdminLogin_old_pwd_incorrect(100002)

		;

		private final Integer code;

	}


	@Override
	public boolean needCheckExist(I sin, SysAdminLoginEntity exist) {
		return false;
	}


	@Override
	public List<SysAdminLoginEntity> findExist(I sin, SysAdminLoginEntity exist) {
		return null;
	}


	@Override
	public LKCodeEnum existErrorCode(I sin) {
		return null;
	}


	@Override
	protected void beforeSaveMainTable(I sin, SysAdminLoginEntity exist) {
		if (!exist.getPwd().equals(LKMD5Encrypter.encrypt(sin.getPwdOld()))) {
			throw new LKRuntimeException(ErrorCodes.SysAdminLogin_old_pwd_incorrect);
		}
		exist.setPwd(LKMD5Encrypter.encrypt(sin.getPwdNew()));
	}

}
