package com.lichkin.application.apis.api10005.U.n00;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichkin.application.services.bus.impl.SysRoleBusService;
import com.lichkin.framework.defines.enums.LKCodeEnum;
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

		SysRole_EXIST(100000),

		;

		private final Integer code;

	}


	@Override
	public boolean needCheckExist(I sin, SysRoleEntity exist) {
		return !exist.getRoleName().equals(sin.getRoleName());
	}


	@Override
	public List<SysRoleEntity> findExist(I sin, SysRoleEntity exist) {
		return busService.findExist(sin.getId(), sin.getCompId(), sin.getRoleName());
	}


	@Override
	public LKCodeEnum existErrorCode(I sin) {
		return ErrorCodes.SysRole_EXIST;
	}


	@Override
	protected void clearSubTables(I sin, SysRoleEntity exist) {
		busService.clearRoleMenu(exist.getId());
	}


	@Override
	protected void addSubTables(I sin, SysRoleEntity exist) {
		busService.addRoleMenu(exist.getId(), sin.getMenuIds());
	}

}
