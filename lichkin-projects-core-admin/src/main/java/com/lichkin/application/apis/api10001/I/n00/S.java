package com.lichkin.application.apis.api10001.I.n00;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichkin.application.services.bus.impl.SysCompBusService;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.framework.defines.exceptions.LKRuntimeException;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysCompEntity;
import com.lichkin.springframework.services.LKApiBusInsertService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service("SysCompI00Service")
public class S extends LKApiBusInsertService<I, SysCompEntity> {

	@Autowired
	private SysCompBusService busService;


	@Getter
	@RequiredArgsConstructor
	enum ErrorCodes implements LKCodeEnum {

		SysComp_EXIST(20000),

		SysComp_parent_code_can_not_modify_when_restore(20000),

		SysComp_comp_key_can_not_modify_when_restore(20000),

		SysComp_token_can_not_modify_when_restore(20000),

		;

		private final Integer code;

	}


	@Override
	protected List<SysCompEntity> findExist(I sin, ApiKeyValues<I> params) {
		return busService.findExist(params, sin.getParentCode(), sin.getCompName(), sin.getCompKey(), sin.getToken());
	}


	@Override
	protected boolean forceCheck(I sin, ApiKeyValues<I> params) {
		return false;
	}


	@Override
	protected LKCodeEnum existErrorCode(I sin, ApiKeyValues<I> params) {
		return ErrorCodes.SysComp_EXIST;
	}


	@Override
	protected void beforeRestore(I sin, ApiKeyValues<I> params, SysCompEntity entity, SysCompEntity exist) {
		if (!exist.getParentCode().equals(entity.getParentCode())) {
			throw new LKRuntimeException(ErrorCodes.SysComp_parent_code_can_not_modify_when_restore).withParam("#parentCode", exist.getParentCode());
		}
		entity.setCompCode(exist.getCompCode());
		if (!exist.getCompKey().equals(entity.getCompKey())) {
			throw new LKRuntimeException(ErrorCodes.SysComp_comp_key_can_not_modify_when_restore).withParam("#compKey", exist.getCompKey());
		}
		if (!exist.getToken().equals(entity.getToken())) {
			throw new LKRuntimeException(ErrorCodes.SysComp_token_can_not_modify_when_restore).withParam("#token", exist.getToken());
		}
	}


	@Override
	protected void beforeAddNew(I sin, ApiKeyValues<I> params, SysCompEntity entity) {
		entity.setCompCode(busService.analysisCompCode(sin.getParentCode()));
	}

}
