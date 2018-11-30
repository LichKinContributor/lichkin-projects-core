package com.lichkin.application.apis.api10004.S.n00;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.defines.beans.impl.LKTreeBean;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.framework.utils.LKTreeUtils;
import com.lichkin.framework.web.annotations.LKApiType;
import com.lichkin.framework.web.enums.ApiType;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.controllers.LKApiYYController;
import com.lichkin.springframework.entities.impl.SysMenuEntity;
import com.lichkin.springframework.services.LKApiService;

@RestController("SysMenuS00Controller")
@RequestMapping(value = LKFrameworkStatics.WEB_MAPPING_API + "/SysMenu/T")
@LKApiType(apiType = ApiType.COMPANY_BUSINESS)
public class C extends LKApiYYController<I, List<LKTreeBean>, List<SysMenuEntity>> {

	@Override
	protected boolean saveLog(I cin, ApiKeyValues<I> params) {
		return false;
	}


	@Autowired
	private S service;


	@Override
	protected LKApiService<I, List<SysMenuEntity>> getService(I cin, ApiKeyValues<I> params) {
		return service;
	}


	@Override
	protected List<LKTreeBean> afterInvokeService(I cin, ApiKeyValues<I> params, List<SysMenuEntity> sout) throws LKException {
		return LKTreeUtils.toTree(sout, "id", "menuName", "menuCode", "parentCode");
	}

}
