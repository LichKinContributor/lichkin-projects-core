package com.lichkin.application.apis.api10002.U.n00;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichkin.application.services.bus.impl.SysCategoryBusService;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysCategoryEntity;
import com.lichkin.springframework.services.LKApiBusUpdateService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service("SysCategoryU00Service")
public class S extends LKApiBusUpdateService<I, SysCategoryEntity> {

	@Autowired
	private SysCategoryBusService busService;


	@Getter
	@RequiredArgsConstructor
	enum ErrorCodes implements LKCodeEnum {

		SysCategory_EXIST(20000),

		;

		private final Integer code;

	}


	@Override
	protected boolean needCheckExist(I sin, ApiKeyValues<I> params, SysCategoryEntity entity, String id) {
		if (!entity.getCategoryName().equals(sin.getCategoryName())) {
			return true;
		}
		return false;
	}


	@Override
	protected List<SysCategoryEntity> findExist(I sin, ApiKeyValues<I> params, SysCategoryEntity entity, String id) {
		return busService.findExist(params, entity.getLocale(), entity.getCategoryCode(), sin.getCategoryName());
	}


	@Override
	protected LKCodeEnum existErrorCode(I sin, ApiKeyValues<I> params) {
		return ErrorCodes.SysCategory_EXIST;
	}


	@Override
	protected void afterSaveMain(I sin, ApiKeyValues<I> params, SysCategoryEntity entity, String id) {
		busService.cacheCategory();
	}

}
