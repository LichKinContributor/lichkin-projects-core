package com.lichkin.application.apis.api10014.I.n00;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichkin.application.services.bus.impl.SysEmployeeBusService;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysEmployeeEntity;
import com.lichkin.springframework.services.LKApiBusInsertService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service("SysEmployeeI00Service")
public class S extends LKApiBusInsertService<I, SysEmployeeEntity> {

	@Autowired
	private SysEmployeeBusService busService;


	@Getter
	@RequiredArgsConstructor
	enum ErrorCodes implements LKCodeEnum {

		SysEmployee_EXIST(20000),

		;

		private final Integer code;

	}


	@Override
	protected List<SysEmployeeEntity> findExist(I sin, ApiKeyValues<I> params) {
		return busService.findExist(params, sin.getCellphone(), sin.getEmail(), busService.analysisUserCard(sin.getUserCard()), sin.getJobNumber());
	}


	@Override
	protected boolean forceCheck(I sin, ApiKeyValues<I> params) {
		return true;
	}


	@Override
	protected LKCodeEnum existErrorCode(I sin, ApiKeyValues<I> params) {
		return ErrorCodes.SysEmployee_EXIST;
	}


	@Override
	protected void beforeAddNew(I sin, ApiKeyValues<I> params, SysEmployeeEntity entity) {
		entity.setUserCard(busService.analysisUserCard(sin.getUserCard()));
	}


	@Override
	protected void beforeSaveMain(I sin, ApiKeyValues<I> params, SysEmployeeEntity entity) {
		entity.setBirthday(busService.analysisBirthday(sin.getUserCard()));
		// 框架会将当前登录ID设置值，此登录ID不能设置
		entity.setLoginId(null);
	}


	@Override
	protected void addSubs(I sin, ApiKeyValues<I> params, SysEmployeeEntity entity, String id) {
		busService.addEmployeeDept(id, sin.getDeptId());
	}

}
