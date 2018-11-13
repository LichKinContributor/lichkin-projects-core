package com.lichkin.application.apis.api10007.U.n01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.framework.web.annotations.LKApiType;
import com.lichkin.framework.web.enums.ApiType;
import com.lichkin.springframework.controllers.LKApiBusUpdateController;
import com.lichkin.springframework.entities.impl.SysAdminLoginEntity;
import com.lichkin.springframework.services.LKApiBusUpdateWithoutCheckerService;

@RestController("SysAdminLoginU01Controller")
@RequestMapping(value = LKFrameworkStatics.WEB_MAPPING_API_WEB_ADMIN + "/SysAdminLogin/U01")
@LKApiType(apiType = ApiType.COMPANY_BUSINESS)
public class C extends LKApiBusUpdateController<I, SysAdminLoginEntity> {

	@Autowired
	private S service;


	@Override
	protected LKApiBusUpdateWithoutCheckerService<I, SysAdminLoginEntity> getService(I cin) {
		return service;
	}


	@Deprecated
	@Override
	protected I beforeInvokeService(I cin) throws LKException {
		cin.setId(cin.getDatas().getLoginId());
		return cin;
	}


	@Override
	public String getSubOperBusType(I cin) {
		return "ModifyPwd";
	}

}
