package com.lichkin.application.apis.api10011.I.n00;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lichkin.application.services.impl.SysAdminOperLogService;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.web.annotations.LKApiType;
import com.lichkin.framework.web.enums.ApiType;
import com.lichkin.springframework.controllers.LKApiBusInsertController;
import com.lichkin.springframework.entities.impl.SysDeptEntity;
import com.lichkin.springframework.services.LKApiBusInsertService;
import com.lichkin.springframework.services.OperLogService;

@RestController("SysDeptI00Controller")
@RequestMapping(value = LKFrameworkStatics.WEB_MAPPING_API_WEB_ADMIN + "/SysDept/I")
@LKApiType(apiType = ApiType.COMPANY_BUSINESS)
public class C extends LKApiBusInsertController<I, SysDeptEntity> {

	@Autowired
	private S service;


	@Override
	protected LKApiBusInsertService<I, SysDeptEntity> getService(I cin) {
		return service;
	}


	@Autowired
	private SysAdminOperLogService logService;


	@Override
	public OperLogService getLogService() {
		return logService;
	}

}
