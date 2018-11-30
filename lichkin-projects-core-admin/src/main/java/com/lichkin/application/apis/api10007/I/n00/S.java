package com.lichkin.application.apis.api10007.I.n00;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichkin.application.services.bus.impl.SysAdminLoginBusService;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.framework.defines.exceptions.LKRuntimeException;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysAdminLoginEntity;
import com.lichkin.springframework.services.LKApiBusInsertService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service("SysAdminLoginI00Service")
public class S extends LKApiBusInsertService<I, SysAdminLoginEntity> {

	@Autowired
	private SysAdminLoginBusService busService;


	@Getter
	@RequiredArgsConstructor
	enum ErrorCodes implements LKCodeEnum {

		SysAdminLogin_EXIST(20000),

		SysAdminLoginComp_EXIST(20000),

		SysAdminLoginComp_email_can_not_modify_when_restore(20000),

		;

		private final Integer code;

	}


	@Override
	protected List<SysAdminLoginEntity> findExist(I sin, ApiKeyValues<I> params) {
		return busService.findExist(params, busService.analysisEmail(sin.getEmail(), params.getCompId(), params.getComp().getCompKey()));
	}


	@Override
	protected boolean forceCheck(I sin, ApiKeyValues<I> params) {
		return false;
	}


	@Override
	protected LKCodeEnum existErrorCode(I sin, ApiKeyValues<I> params) {
		return LKFrameworkStatics.LichKin.equals(params.getCompId()) ? ErrorCodes.SysAdminLoginComp_EXIST : ErrorCodes.SysAdminLogin_EXIST;
	}


	@Override
	protected void beforeRestore(I sin, ApiKeyValues<I> params, SysAdminLoginEntity entity, SysAdminLoginEntity exist) {
		if (LKFrameworkStatics.LichKin.equals(params.getCompId())) {
			if (!exist.getEmail().equals(entity.getEmail())) {
				throw new LKRuntimeException(ErrorCodes.SysAdminLoginComp_email_can_not_modify_when_restore).withParam("#email", exist.getEmail());
			}
		}
		entity.setEmail(exist.getEmail());
		entity.setLevel(exist.getLevel());
		entity.setSuperAdmin(exist.getSuperAdmin());
	}


	@Override
	protected void beforeAddNew(I sin, ApiKeyValues<I> params, SysAdminLoginEntity entity) {
		entity.setEmail(busService.analysisEmail(sin.getEmail(), params.getCompId(), params.getComp().getCompKey()));
		entity.setSuperAdmin(busService.analysisSuperAdmin(params.getCompId()));
	}


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
