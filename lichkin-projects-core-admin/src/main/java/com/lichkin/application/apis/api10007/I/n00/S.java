package com.lichkin.application.apis.api10007.I.n00;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.lichkin.application.services.bus.impl.SysAdminLoginBusService;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.framework.defines.enums.impl.LKYesNoEnum;
import com.lichkin.framework.utils.security.md5.LKMD5Encrypter;
import com.lichkin.springframework.entities.impl.SysAdminLoginEntity;
import com.lichkin.springframework.entities.impl.SysCompEntity;
import com.lichkin.springframework.services.LKApiBusInsertService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service("SysAdminLoginI00Service")
public class S extends LKApiBusInsertService<I, SysAdminLoginEntity> {

	@Autowired
	private SysAdminLoginBusService busService;


	@Getter
	@RequiredArgsConstructor
	enum ErrorCodes implements LKCodeEnum {

		SysAdminLogin_EXIST(100000),

		SysAdminLoginComp_EXIST(100001),

		;

		private final Integer code;

	}


	@Override
	public boolean needCheckExist(I sin) {
		return true;
	}


	@Override
	public List<SysAdminLoginEntity> findExist(I sin) {
		if (StringUtils.isBlank(sin.getBusCompId())) {
			sin.setEmail(sin.getEmail() + "@" + ((SysCompEntity) sin.getComp()).getCompKey());
			return busService.findExist(null, sin.getEmail());
		} else {
			return busService.findExistCompAdmin(null, sin.getEmail(), sin.getBusCompId());
		}
	}


	@Override
	public LKCodeEnum existErrorCode(I sin) {
		if (StringUtils.isBlank(sin.getBusCompId())) {
			return ErrorCodes.SysAdminLogin_EXIST;
		} else {
			return ErrorCodes.SysAdminLoginComp_EXIST;
		}
	}


	@Value("${com.lichkin.api.admin.login.defaultPwd:88888888}")
	private String defaultPwd;


	@Override
	protected void beforeSaveMainTable(I sin, SysAdminLoginEntity entity, SysAdminLoginEntity exist) {
		entity.setPwd(LKMD5Encrypter.encrypt(LKMD5Encrypter.encrypt(defaultPwd)));
		if (StringUtils.isBlank(sin.getBusCompId())) {
			entity.setSuperAdmin(LKYesNoEnum.NO);
		} else {
			entity.setSuperAdmin(LKYesNoEnum.YES);
			entity.setCompId(sin.getBusCompId());
		}
	}


	@Override
	protected void clearSubTables(I sin, SysAdminLoginEntity exist) {
		busService.clearAdminLoginRole(exist.getId());
	}


	@Override
	protected void addSubTables(I sin, SysAdminLoginEntity exist) {
		busService.addAdminLoginRole(exist.getId(), sin.getRoleIds());
	}

}
