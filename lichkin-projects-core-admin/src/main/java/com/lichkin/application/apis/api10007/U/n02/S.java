package com.lichkin.application.apis.api10007.U.n02;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.framework.utils.security.md5.LKMD5Encrypter;
import com.lichkin.springframework.entities.impl.SysAdminLoginEntity;
import com.lichkin.springframework.services.LKApiBusUpdateService;

@Service("SysAdminLoginU02Service")
public class S extends LKApiBusUpdateService<I, SysAdminLoginEntity> {

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


	@Value("${com.lichkin.api.admin.login.defaultPwd:88888888}")
	private String defaultPwd;


	@Override
	protected void beforeSaveMainTable(I sin, SysAdminLoginEntity exist) {
		exist.setPwd(LKMD5Encrypter.encrypt(LKMD5Encrypter.encrypt(defaultPwd)));
	}


	@Override
	protected String[] excludeFieldNames(I sin, SysAdminLoginEntity exist) {
		return new String[] { "id", "compId" };
	}

}
