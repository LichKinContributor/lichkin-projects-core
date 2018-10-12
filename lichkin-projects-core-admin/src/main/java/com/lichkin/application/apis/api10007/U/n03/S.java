package com.lichkin.application.apis.api10007.U.n03;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichkin.application.services.bus.impl.SysAdminLoginBusService;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.springframework.entities.impl.SysAdminLoginEntity;
import com.lichkin.springframework.services.LKApiBusUpdateService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service("SysAdminLoginU03Service")
public class S extends LKApiBusUpdateService<I, SysAdminLoginEntity> {

	@Autowired
	private SysAdminLoginBusService busService;


	@Getter
	@RequiredArgsConstructor
	enum ErrorCodes implements LKCodeEnum {

		SysAdminLogin_EXIST(100000),

		;

		private final Integer code;

	}


	@Override
	public boolean needCheckExist(I sin, SysAdminLoginEntity exist) {
		return !exist.getEmail().equals(sin.getEmail());
	}


	@Override
	public List<SysAdminLoginEntity> findExist(I sin, SysAdminLoginEntity exist) {
		return busService.findExist(sin.getId(), sin.getEmail());
	}


	@Override
	public LKCodeEnum existErrorCode(I sin) {
		return ErrorCodes.SysAdminLogin_EXIST;
	}


	@Override
	protected void clearSubTables(I sin, SysAdminLoginEntity exist) {
		busService.clearAdminLoginRole(exist.getId());
	}


	@Override
	protected void addSubTables(I sin, SysAdminLoginEntity exist) {
		busService.addAdminLoginRole(exist.getId(), sin.getRoleIds());
	}


	@Override
	protected String[] excludeFieldNames(I sin, SysAdminLoginEntity exist) {
		return new String[] { "id", "compId" };
	}

}
