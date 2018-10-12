package com.lichkin.application.apis.api10007.U.n00;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichkin.application.services.bus.impl.SysAdminLoginBusService;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.springframework.entities.impl.SysAdminLoginEntity;
import com.lichkin.springframework.services.LKApiBusUpdateService;

@Service("SysAdminLoginU00Service")
public class S extends LKApiBusUpdateService<I, SysAdminLoginEntity> {

	@Autowired
	private SysAdminLoginBusService busService;


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
	protected void clearSubTables(I sin, SysAdminLoginEntity exist) {
		busService.clearAdminLoginRole(exist.getId());
	}


	@Override
	protected void addSubTables(I sin, SysAdminLoginEntity exist) {
		busService.addAdminLoginRole(exist.getId(), sin.getRoleIds());
	}

}
