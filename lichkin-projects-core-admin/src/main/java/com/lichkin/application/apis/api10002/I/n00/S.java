package com.lichkin.application.apis.api10002.I.n00;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichkin.application.services.bus.impl.SysCategoryBusService;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.springframework.entities.impl.SysCategoryEntity;
import com.lichkin.springframework.services.LKApiBusInsertService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service("SysCategoryI00Service")
public class S extends LKApiBusInsertService<I, SysCategoryEntity> {

	@Autowired
	private SysCategoryBusService busService;


	@Getter
	@RequiredArgsConstructor
	enum ErrorCodes implements LKCodeEnum {

		SysCategory_EXIST(100000),

		;

		private final Integer code;

	}


	@Override
	protected List<SysCategoryEntity> findExist(I sin, String locale, String compId, String loginId) {
		return busService.findExist(null, getLocale(locale, sin.getLocale()), sin.getCategoryCode(), sin.getCategoryName());
	}


	@Override
	protected boolean forceCheck(I sin, String locale, String compId, String loginId) {
		return true;
	}


	@Override
	protected LKCodeEnum existErrorCode(I sin, String locale, String compId, String loginId) {
		return ErrorCodes.SysCategory_EXIST;
	}


	@Override
	protected void beforeAddNew(I sin, String locale, String compId, String loginId, SysCategoryEntity entity) {
		entity.setLocale(getLocale(locale, sin.getLocale()));
	}


	@Override
	protected void afterSaveMain(I sin, String locale, String compId, String loginId, SysCategoryEntity entity, String id) {
		busService.cacheCategory();
	}

}
