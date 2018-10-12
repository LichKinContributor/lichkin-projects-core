package com.lichkin.application.apis.api10007.U.n02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lichkin.application.services.impl.SysAdminOperLogService;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.web.annotations.LKApiType;
import com.lichkin.framework.web.enums.ApiType;
import com.lichkin.springframework.controllers.LKApiBusUpdateController;
import com.lichkin.springframework.entities.impl.SysAdminLoginEntity;
import com.lichkin.springframework.services.LKApiBusUpdateService;
import com.lichkin.springframework.services.OperLogService;

@RestController("SysAdminLoginU02Controller")
@RequestMapping(value = LKFrameworkStatics.WEB_MAPPING_API_WEB_ADMIN + "/SysAdminLogin/U02")
@LKApiType(apiType = ApiType.COMPANY_BUSINESS)
public class C extends LKApiBusUpdateController<I, SysAdminLoginEntity> {

	@Autowired
	private S service;


	@Override
	protected LKApiBusUpdateService<I, SysAdminLoginEntity> getService(I cin) {
		return service;
	}


	@Autowired
	private SysAdminOperLogService logService;


	@Override
	public OperLogService getLogService() {
		return logService;
	}


	@Override
	public String getSubOperBusType(I cin) {
		return "_ResetPwd";
	}

}
