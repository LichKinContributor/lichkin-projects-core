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
	public boolean needCheckExist(I sin) {
		return true;
	}


	@Override
	public List<SysRoleEntity> findExist(I sin) {
		return busService.findExist(null, sin.getCompId(), sin.getRoleName());
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
