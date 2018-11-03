package com.lichkin.application.apis.api10007.I.n00;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichkin.application.services.bus.impl.SysAdminLoginBusService;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
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

		;

		private final Integer code;

	}


	@Override
	protected List<SysAdminLoginEntity> findExist(I sin, String locale, String compId, String loginId) {
		return busService.findExist(null, compId, sin.getCompId(), busService.analysisEmail(sin.getEmail(), compId, sin.getDatas().getComp().getCompKey()));
	}


	@Override
	protected boolean forceCheck(I sin, String locale, String compId, String loginId) {
		return false;
	}


	@Override
	protected LKCodeEnum existErrorCode(I sin, String locale, String compId, String loginId) {
		return LKFrameworkStatics.LichKin.equals(compId) ? ErrorCodes.SysAdminLoginComp_EXIST : ErrorCodes.SysAdminLogin_EXIST;
	}


	@Override
	protected void beforeRestore(I sin, String locale, String compId, String loginId, SysAdminLoginEntity entity, SysAdminLoginEntity exist) {
		entity.setUsingStatus(LKUsingStatusEnum.USING);
		entity.setCompId(exist.getCompId());
		entity.setEmail(exist.getEmail());
		entity.setLevel(exist.getLevel());
		entity.setSuperAdmin(exist.getSuperAdmin());
	}


	@Override
	protected void beforeAddNew(I sin, String locale, String compId, String loginId, SysAdminLoginEntity entity) {
		entity.setCompId(getCompId(compId, sin.getCompId()));
		entity.setEmail(busService.analysisEmail(sin.getEmail(), compId, sin.getDatas().getComp().getCompKey()));
		entity.setSuperAdmin(busService.analysisSuperAdmin(compId));
	}


	@Override
	protected void beforeSaveMain(I sin, String locale, String compId, String loginId, SysAdminLoginEntity entity) {
		entity.setPwd(busService.analysisPwd());
	}


	@Override
	protected void clearSubs(I sin, String locale, String compId, String loginId, SysAdminLoginEntity entity, String id) {
		busService.clearAdminLoginRole(id);
	}


	@Override
	protected void addSubs(I sin, String locale, String compId, String loginId, SysAdminLoginEntity entity, String id) {
		busService.addAdminLoginRole(id, sin.getRoleIds());
	}

}
