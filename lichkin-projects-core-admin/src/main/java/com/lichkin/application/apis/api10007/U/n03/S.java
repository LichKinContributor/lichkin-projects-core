package com.lichkin.application.apis.api10007.U.n03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichkin.application.services.bus.impl.SysAdminLoginBusService;
import com.lichkin.springframework.entities.impl.SysAdminLoginEntity;
import com.lichkin.springframework.services.LKApiBusUpdateWithoutCheckerService;

@Service("SysAdminLoginU03Service")
public class S extends LKApiBusUpdateWithoutCheckerService<I, SysAdminLoginEntity> {

	@Autowired
	private SysAdminLoginBusService busService;


	@Override
	protected void clearSubs(I sin, String locale, String compId, String loginId, SysAdminLoginEntity entity, String id) {
		busService.clearAdminLoginRole(id);
	}


	@Override
	protected void addSubs(I sin, String locale, String compId, String loginId, SysAdminLoginEntity entity, String id) {
		busService.addAdminLoginRole(id, sin.getRoleIds());
	}

}
