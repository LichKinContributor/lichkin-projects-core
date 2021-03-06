package com.lichkin.application.apis.api10011.U.n00;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichkin.application.services.bus.impl.SysDeptBusService;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysDeptEntity;
import com.lichkin.springframework.services.LKApiBusUpdateService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service("SysDeptU00Service")
public class S extends LKApiBusUpdateService<I, SysDeptEntity> {

	@Autowired
	private SysDeptBusService busService;


	@Getter
	@RequiredArgsConstructor
	enum ErrorCodes implements LKCodeEnum {

		SysDept_EXIST(20000),

		;

		private final Integer code;

	}


	@Override
	protected boolean needCheckExist(I sin, ApiKeyValues<I> params, SysDeptEntity entity, String id) {
		if (!entity.getDeptName().equals(sin.getDeptName())) {
			return true;
		}
		return false;
	}


	@Override
	protected List<SysDeptEntity> findExist(I sin, ApiKeyValues<I> params, SysDeptEntity entity, String id) {
		return busService.findExist(params, entity.getParentCode(), sin.getDeptName());
	}


	@Override
	protected LKCodeEnum existErrorCode(I sin, ApiKeyValues<I> params) {
		return ErrorCodes.SysDept_EXIST;
	}


	@Override
	protected void beforeSaveMain(I sin, ApiKeyValues<I> params, SysDeptEntity entity) {
//		entity.setFullName(busService.analysisFullName();
	}


	@Override
	protected void beforeCopyProperties(I sin, ApiKeyValues<I> params, SysDeptEntity entity) {
		entity.setFullName(busService.analysisFullName(entity.getId(), entity.getDeptCode(), entity.getDeptName(), sin.getDeptName(), entity.getFullName()));
	}

}
