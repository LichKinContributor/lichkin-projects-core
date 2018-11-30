package com.lichkin.application.apis.api10007.O.n00;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lichkin.framework.beans.impl.LKRequestIDBean;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.web.annotations.LKApiType;
import com.lichkin.framework.web.enums.ApiType;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.controllers.LKApiBusGetOneController;
import com.lichkin.springframework.entities.impl.SysAdminLoginEntity;
import com.lichkin.springframework.services.LKApiBusGetOneService;

@RestController("SysAdminLoginO00Controller")
@RequestMapping(value = LKFrameworkStatics.WEB_MAPPING_API + "/SysAdminLogin/O")
@LKApiType(apiType = ApiType.COMPANY_BUSINESS)
public class C extends LKApiBusGetOneController<LKRequestIDBean, O, SysAdminLoginEntity> {

	@Autowired
	private S service;


	@Override
	protected LKApiBusGetOneService<LKRequestIDBean, O, SysAdminLoginEntity> getService(LKRequestIDBean cin, ApiKeyValues<LKRequestIDBean> params) {
		return service;
	}

}
