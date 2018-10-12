package com.lichkin.application.apis.api10014.U.n00;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lichkin.application.services.impl.SysAdminOperLogService;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.web.annotations.LKApiType;
import com.lichkin.framework.web.enums.ApiType;
import com.lichkin.springframework.controllers.LKApiBusUpdateController;
import com.lichkin.springframework.entities.impl.SysEmployeeLoginCompEntity;
import com.lichkin.springframework.services.LKApiBusUpdateService;
import com.lichkin.springframework.services.OperLogService;

@RestController("SysEmployeeLoginCompU00Controller")
@RequestMapping(value = LKFrameworkStatics.WEB_MAPPING_API_WEB_ADMIN + "/SysEmployeeLoginComp/U")
@LKApiType(apiType = ApiType.COMPANY_BUSINESS)
public class C extends LKApiBusUpdateController<I, SysEmployeeLoginCompEntity> {

	@Autowired
	private S service;


	@Override
	protected LKApiBusUpdateService<I, SysEmployeeLoginCompEntity> getService(I cin) {
		return service;
	}


	@Autowired
	private SysAdminOperLogService logService;


	@Override
	public OperLogService getLogService() {
		return logService;
	}

}
