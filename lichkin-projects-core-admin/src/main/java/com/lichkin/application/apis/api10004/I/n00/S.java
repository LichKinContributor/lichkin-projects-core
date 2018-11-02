package com.lichkin.application.apis.api10004.I.n00;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichkin.application.services.bus.impl.SysMenuBusService;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.springframework.entities.impl.SysMenuEntity;
import com.lichkin.springframework.services.LKApiBusInsertWithoutCheckerService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service("SysMenuI00Service")
public class S extends LKApiBusInsertWithoutCheckerService<I, SysMenuEntity> {

	@Autowired
	private SysMenuBusService busService;


	@Getter
	@RequiredArgsConstructor
	enum ErrorCodes implements LKCodeEnum {

		;

		private final Integer code;

	}


	@Override
	protected void beforeAddNew(I sin, String locale, String compId, String loginId, SysMenuEntity entity) {
		entity.setMenuCode(busService.analysisMenuCode(sin.getParentCode()));
	}


	@Override
	protected void beforeSaveMain(I sin, String locale, String compId, String loginId, SysMenuEntity entity) {
		entity.setUrl(busService.analysisUrl(sin.getUrl()));
	}

}
