package com.lichkin.application.apis.api10005.D.n00;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.web.annotations.LKApiType;
import com.lichkin.framework.web.enums.ApiType;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.controllers.LKApiBusDeleteController;
import com.lichkin.springframework.entities.impl.SysRoleEntity;
import com.lichkin.springframework.services.LKApiBusDeleteService;

@RestController("SysRoleD00Controller")
@RequestMapping(value = LKFrameworkStatics.WEB_MAPPING_API + "/SysRole/D")
@LKApiType(apiType = ApiType.COMPANY_BUSINESS)
public class C extends LKApiBusDeleteController<I, SysRoleEntity> {

	@Autowired
	private S service;


	@Override
	protected LKApiBusDeleteService<I, SysRoleEntity> getService(I cin, ApiKeyValues<I> params) {
		return service;
	}

}
