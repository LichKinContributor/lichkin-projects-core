package com.lichkin.application.apis.api10007.U.n00;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichkin.application.services.bus.impl.SysAdminLoginBusService;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysAdminLoginEntity;
import com.lichkin.springframework.services.LKApiBusUpdateService;

@Service("SysAdminLoginU00Service")
public class S extends LKApiBusUpdateService<I, SysAdminLoginEntity> {

	@Autowired
	private SysAdminLoginBusService busService;


	@Override
	protected void beforeSaveMain(I sin, ApiKeyValues<I> params, SysAdminLoginEntity entity) {
		entity.setPwd(busService.analysisPwd());
	}


	@Override
	protected void clearSubs(I sin, ApiKeyValues<I> params, SysAdminLoginEntity entity, String id) {
		busService.clearAdminLoginRole(id);
	}


	@Override
	protected void addSubs(I sin, ApiKeyValues<I> params, SysAdminLoginEntity entity, String id) {
		busService.addAdminLoginRole(id, sin.getRoleIds());
	}

}
