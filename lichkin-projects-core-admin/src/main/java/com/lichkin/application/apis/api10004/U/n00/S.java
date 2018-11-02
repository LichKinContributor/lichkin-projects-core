package com.lichkin.application.apis.api10004.U.n00;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichkin.application.services.bus.impl.SysMenuBusService;
import com.lichkin.springframework.entities.impl.SysMenuEntity;
import com.lichkin.springframework.services.LKApiBusUpdateWithoutCheckerService;

@Service("SysMenuU00Service")
public class S extends LKApiBusUpdateWithoutCheckerService<I, SysMenuEntity> {

	@Autowired
	private SysMenuBusService busService;


	@Override
	protected void beforeSaveMain(I sin, String locale, String compId, String loginId, SysMenuEntity entity) {
		entity.setUrl(busService.analysisUrl(sin.getUrl()));
	}

}
