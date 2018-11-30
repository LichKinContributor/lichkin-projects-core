package com.lichkin.application.apis.api10007.U.n02;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.lichkin.framework.utils.security.md5.LKMD5Encrypter;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysAdminLoginEntity;
import com.lichkin.springframework.services.LKApiBusUpdateWithoutCheckerService;

@Service("SysAdminLoginU02Service")
public class S extends LKApiBusUpdateWithoutCheckerService<I, SysAdminLoginEntity> {

	@Value("${com.lichkin.api.admin.login.defaultPwd:88888888}")
	private String defaultPwd;


	@Override
	protected void beforeSaveMain(I sin, ApiKeyValues<I> params, SysAdminLoginEntity entity) {
		entity.setPwd(LKMD5Encrypter.encrypt(LKMD5Encrypter.encrypt(defaultPwd)));
	}

}
