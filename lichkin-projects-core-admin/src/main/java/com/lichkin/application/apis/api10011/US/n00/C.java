package com.lichkin.application.apis.api10011.US.n00;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lichkin.framework.beans.impl.LKRequestIDsBean;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.web.annotations.LKApiType;
import com.lichkin.framework.web.enums.ApiType;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.controllers.LKApiBusUpdateUsingStatusController;
import com.lichkin.springframework.entities.impl.SysDeptEntity;
import com.lichkin.springframework.services.LKApiBusUpdateUsingStatusService;

@RestController("SysDeptUS00Controller")
@RequestMapping(value = LKFrameworkStatics.WEB_MAPPING_API + "/SysDept/US")
@LKApiType(apiType = ApiType.COMPANY_BUSINESS)
public class C extends LKApiBusUpdateUsingStatusController<LKRequestIDsBean, SysDeptEntity> {

	@Autowired
	private S service;


	@Override
	protected LKApiBusUpdateUsingStatusService<LKRequestIDsBean, SysDeptEntity> getService(LKRequestIDsBean cin, ApiKeyValues<LKRequestIDsBean> params) {
		return service;
	}

}
