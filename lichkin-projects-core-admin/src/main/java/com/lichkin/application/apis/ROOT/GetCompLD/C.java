package com.lichkin.application.apis.ROOT.GetCompLD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lichkin.framework.beans.impl.LKRequestBean;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.web.annotations.LKApiType;
import com.lichkin.framework.web.enums.ApiType;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.controllers.LKApiBusGetDroplistController;
import com.lichkin.springframework.services.LKApiBusGetDroplistService;

@RestController(Statics.CONTROLLER_NAME)
@RequestMapping(value = LKFrameworkStatics.WEB_MAPPING_API + Statics.SUB_URL)
@LKApiType(apiType = ApiType.COMPANY_BUSINESS)
public class C extends LKApiBusGetDroplistController<LKRequestBean> {

	@Autowired
	private S service;


	@Override
	protected LKApiBusGetDroplistService<LKRequestBean> getService(LKRequestBean cin, ApiKeyValues<LKRequestBean> params) {
		return service;
	}

}
