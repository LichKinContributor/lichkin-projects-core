package com.lichkin.application.apis.api10005.I.n00;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichkin.application.services.bus.impl.SysRoleBusService;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.springframework.entities.impl.SysRoleEntity;
import com.lichkin.springframework.services.LKApiBusInsertService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service("SysRoleI00Service")
public class S extends LKApiBusInsertService<I, SysRoleEntity> {

	@Autowired
	private SysRoleBusService busService;


	@Getter
	@RequiredArgsConstructor
	enum ErrorCodes implements LKCodeEnum {

		SysRole_EXIST(100000),

		;

		private final Integer code;

	}


	@Override
	protected List<SysRoleEntity> findExist(I sin, String locale, String compId, String loginId) {
		return busService.findExist(null, compId, sin.getCompId(), sin.getRoleName());
	}


	@Override
	protected boolean forceCheck(I sin, String locale, String compId, String loginId) {
		return true;
	}


	@Override
	protected LKCodeEnum existErrorCode(I sin, String locale, String compId, String loginId) {
		return ErrorCodes.SysRole_EXIST;
	}


	@Override
	protected void beforeAddNew(I sin, String locale, String compId, String loginId, SysRoleEntity entity) {
		entity.setCompId(getCompId(compId, sin.getCompId()));
	}


	@Override
	protected void addSubs(I sin, String locale, String compId, String loginId, SysRoleEntity entity, String id) {
		busService.addRoleMenu(id, sin.getMenuIds());
	}

}
