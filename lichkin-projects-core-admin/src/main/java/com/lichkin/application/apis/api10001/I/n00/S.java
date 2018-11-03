package com.lichkin.application.apis.api10001.I.n00;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichkin.application.services.bus.impl.SysCompBusService;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.defines.exceptions.LKRuntimeException;
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

		;

		private final Integer code;

	}


	@Override
	protected List<SysCompEntity> findExist(I sin, String locale, String compId, String loginId) {
		return busService.findExist(null, sin.getParentCode(), sin.getCompName(), sin.getCompKey());
	}


	@Override
	protected boolean forceCheck(I sin, String locale, String compId, String loginId) {
		return false;
	}


	@Override
	protected LKCodeEnum existErrorCode(I sin, String locale, String compId, String loginId) {
		return ErrorCodes.SysComp_EXIST;
	}


	@Override
	protected void beforeRestore(I sin, String locale, String compId, String loginId, SysCompEntity entity, SysCompEntity exist) {
		entity.setUsingStatus(LKUsingStatusEnum.USING);
		if (!exist.getParentCode().equals(entity.getParentCode())) {
			throw new LKRuntimeException(ErrorCodes.SysComp_parent_code_can_not_modify_when_restore).withParam("#parentCode", exist.getParentCode());
		}
		entity.setCompCode(exist.getCompCode());
		if (!exist.getCompKey().equals(entity.getCompKey())) {
			throw new LKRuntimeException(ErrorCodes.SysComp_comp_key_can_not_modify_when_restore).withParam("#compKey", exist.getCompKey());
		}
	}


	@Override
	protected void beforeAddNew(I sin, String locale, String compId, String loginId, SysCompEntity entity) {
		entity.setCompCode(busService.analysisCompCode(sin.getParentCode()));
	}

}
