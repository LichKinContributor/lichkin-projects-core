package com.lichkin.application.apis.ModifyCellphone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.framework.web.annotations.LKApiType;
import com.lichkin.framework.web.enums.ApiType;
import com.lichkin.springframework.controllers.LKApiVVController;
import com.lichkin.springframework.services.LKApiVoidService;

@RestController(Statics.CONTROLLER_NAME + "WEB")
@RequestMapping(value = LKFrameworkStatics.WEB_MAPPING_API_APP_EMPLOYEE + Statics.SUB_URL)
@LKApiType(apiType = ApiType.PERSONAL_BUSINESS)
public class CWeb extends LKApiVVController<I, I> {

	@Autowired
	private S service;


	@Override
	protected LKApiVoidService<I> getService(I cin) {
		return service;
	}


	@Override
	protected I beforeInvokeService(I cin) throws LKException {
		return cin;
	}

}
