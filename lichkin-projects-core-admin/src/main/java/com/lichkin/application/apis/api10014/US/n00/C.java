package com.lichkin.application.apis.api10014.US.n00;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lichkin.framework.beans.impl.LKRequestIDsBean;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.web.annotations.LKApiType;
import com.lichkin.framework.web.enums.ApiType;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.controllers.LKApiBusUpdateUsingStatusController;
import com.lichkin.springframework.entities.impl.SysEmployeeEntity;
import com.lichkin.springframework.services.LKApiBusUpdateUsingStatusService;

@RestController("SysEmployeeUS00Controller")
@RequestMapping(value = LKFrameworkStatics.WEB_MAPPING_API + "/SysEmployee/US")
@LKApiType(apiType = ApiType.COMPANY_BUSINESS)
public class C extends LKApiBusUpdateUsingStatusController<LKRequestIDsBean, SysEmployeeEntity> {

	@Autowired
	private S service;


	@Override
	protected LKApiBusUpdateUsingStatusService<LKRequestIDsBean, SysEmployeeEntity> getService(LKRequestIDsBean cin, ApiKeyValues<LKRequestIDsBean> params) {
		return service;
	}

}
