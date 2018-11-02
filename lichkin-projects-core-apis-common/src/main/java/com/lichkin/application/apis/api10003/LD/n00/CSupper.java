package com.lichkin.application.apis.api10003.LD.n00;

import org.springframework.beans.factory.annotation.Autowired;

import com.lichkin.framework.web.annotations.LKApiType;
import com.lichkin.framework.web.enums.ApiType;
import com.lichkin.springframework.controllers.LKApiBusGetDroplistController;
import com.lichkin.springframework.services.LKApiBusGetDroplistService;

@LKApiType(apiType = ApiType.COMPANY_BUSINESS)
public abstract class CSupper extends LKApiBusGetDroplistController<I> {

	@Autowired
	private S service;


	@Override
	protected LKApiBusGetDroplistService<I> getService(I cin) {
		return service;
	}

}
