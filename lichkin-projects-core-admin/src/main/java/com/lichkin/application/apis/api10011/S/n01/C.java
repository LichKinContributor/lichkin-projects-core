package com.lichkin.application.apis.api10011.S.n01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.web.annotations.LKApiType;
import com.lichkin.framework.web.enums.ApiType;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.controllers.LKApiVVController;
import com.lichkin.springframework.services.LKApiVoidService;

@RestController("SysDeptS01Controller")
@RequestMapping(value = LKFrameworkStatics.WEB_MAPPING_API + "/SysDept/S01")
@LKApiType(apiType = ApiType.COMPANY_BUSINESS)
public class C extends LKApiVVController<I> {

	@Autowired
	private S service;


	@Override
	protected LKApiVoidService<I> getService(I cin, ApiKeyValues<I> params) {
		return service;
	}

}
