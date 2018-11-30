package com.lichkin.application.apis.api10011.I.n00;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichkin.application.services.bus.impl.SysDeptBusService;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.framework.defines.exceptions.LKRuntimeException;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysDeptEntity;
import com.lichkin.springframework.services.LKApiBusInsertService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service("SysDeptI00Service")
public class S extends LKApiBusInsertService<I, SysDeptEntity> {

	@Autowired
	private SysDeptBusService busService;


	@Getter
	@RequiredArgsConstructor
	enum ErrorCodes implements LKCodeEnum {

		SysDept_EXIST(20000),

		SysDept_parent_code_can_not_modify_when_restore(20000),

		;

		private final Integer code;

	}


	@Override
	protected List<SysDeptEntity> findExist(I sin, ApiKeyValues<I> params) {
		return busService.findExist(params, sin.getParentCode(), sin.getDeptName());
	}


	@Override
	protected boolean forceCheck(I sin, ApiKeyValues<I> params) {
		return false;
	}


	@Override
	protected LKCodeEnum existErrorCode(I sin, ApiKeyValues<I> params) {
		return ErrorCodes.SysDept_EXIST;
	}


	@Override
	protected void beforeRestore(I sin, ApiKeyValues<I> params, SysDeptEntity entity, SysDeptEntity exist) {
		if (!exist.getParentCode().equals(entity.getParentCode())) {
			throw new LKRuntimeException(ErrorCodes.SysDept_parent_code_can_not_modify_when_restore).withParam("#parentCode", exist.getParentCode());
		}
		entity.setDeptCode(exist.getDeptCode());
		entity.setFullName(exist.getFullName());
	}


	@Override
	protected void beforeAddNew(I sin, ApiKeyValues<I> params, SysDeptEntity entity) {
		entity.setDeptCode(busService.analysisDeptCode(params.getCompId(), sin.getParentCode()));
		entity.setFullName(busService.analysisFullName(params.getCompId(), entity.getDeptCode(), entity.getDeptName()));
	}

}
