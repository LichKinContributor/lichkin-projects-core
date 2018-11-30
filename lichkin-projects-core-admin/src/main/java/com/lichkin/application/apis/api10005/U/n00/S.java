package com.lichkin.application.apis.api10005.U.n00;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichkin.application.services.bus.impl.SysRoleBusService;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysRoleEntity;
import com.lichkin.springframework.services.LKApiBusUpdateService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service("SysRoleU00Service")
public class S extends LKApiBusUpdateService<I, SysRoleEntity> {

	@Autowired
	private SysRoleBusService busService;


	@Getter
	@RequiredArgsConstructor
	enum ErrorCodes implements LKCodeEnum {

		SysRole_EXIST(20000),

		;

		private final Integer code;

	}


	@Override
	protected boolean needCheckExist(I sin, ApiKeyValues<I> params, SysRoleEntity entity, String id) {
		if (!entity.getRoleName().equals(sin.getRoleName())) {
			return true;
		}
		return false;
	}


	@Override
	protected List<SysRoleEntity> findExist(I sin, ApiKeyValues<I> params, SysRoleEntity entity, String id) {
		return busService.findExist(params, sin.getRoleName());
	}


	@Override
	protected LKCodeEnum existErrorCode(I sin, ApiKeyValues<I> params) {
		return ErrorCodes.SysRole_EXIST;
	}


	@Override
	protected void clearSubs(I sin, ApiKeyValues<I> params, SysRoleEntity entity, String id) {
		busService.clearRoleMenu(id);
	}


	@Override
	protected void addSubs(I sin, ApiKeyValues<I> params, SysRoleEntity entity, String id) {
		busService.addRoleMenu(id, sin.getMenuIds());
	}

}
