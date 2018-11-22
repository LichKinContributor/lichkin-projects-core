package com.lichkin.application.apis.api10003.S.n00;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.framework.web.annotations.LKApiType;
import com.lichkin.framework.web.enums.ApiType;
import com.lichkin.springframework.controllers.LKApiVVController;
import com.lichkin.springframework.services.LKApiVoidService;

@RestController("SysDictionaryS00Controller")
@RequestMapping(value = LKFrameworkStatics.WEB_MAPPING_API_WEB + "/SysDictionary/S")
@LKApiType(apiType = ApiType.COMPANY_BUSINESS)
public class C extends LKApiVVController<I, I> {

	@Autowired
	private S service;


	@Override
	protected I beforeInvokeService(I cin) throws LKException {
		return cin;
	}


	@Override
	protected LKApiVoidService<I> getService(I cin) {
		return service;
	}

}
