package com.lichkin.application.apis.api10007.US.n02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lichkin.framework.beans.impl.LKRequestIDsBean;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.web.annotations.LKApiType;
import com.lichkin.framework.web.enums.ApiType;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.controllers.LKApiBusUpdateUsingStatusController;
import com.lichkin.springframework.entities.impl.SysAdminLoginEntity;
import com.lichkin.springframework.services.LKApiBusUpdateUsingStatusService;

@RestController("SysAdminLoginUS02Controller")
@RequestMapping(value = LKFrameworkStatics.WEB_MAPPING_API + "/SysAdminLogin/US02")
@LKApiType(apiType = ApiType.COMPANY_BUSINESS)
public class C extends LKApiBusUpdateUsingStatusController<LKRequestIDsBean, SysAdminLoginEntity> {

	@Autowired
	private S service;


	@Override
	protected LKApiBusUpdateUsingStatusService<LKRequestIDsBean, SysAdminLoginEntity> getService(LKRequestIDsBean cin, ApiKeyValues<LKRequestIDsBean> params) {
		return service;
	}

}
