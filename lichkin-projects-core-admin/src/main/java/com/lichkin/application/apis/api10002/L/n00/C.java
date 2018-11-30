package com.lichkin.application.apis.api10002.L.n00;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lichkin.application.cache.Category;
import com.lichkin.application.cache.LKSysCatagoryCache;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.framework.web.annotations.LKApiType;
import com.lichkin.framework.web.enums.ApiType;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.controllers.LKApiY0Controller;

@RestController("SysCategoryL00Controller")
@RequestMapping(value = LKFrameworkStatics.WEB_MAPPING_API + "/SysCategory/L")
@LKApiType(apiType = ApiType.COMPANY_QUERY)
public class C extends LKApiY0Controller<I, List<Category>> {

	@Override
	protected boolean saveLog(I cin, ApiKeyValues<I> params) {
		return false;
	}


	@Deprecated
	@Override
	protected List<Category> doInvoke(I cin, ApiKeyValues<I> params) throws LKException {
		return LKSysCatagoryCache.getList(cin.isShowFull(), LKFrameworkStatics.LichKin.equals(params.getCompId()) ? cin.getLocale() : params.getLocale(), params.getCompId(), cin.getCategoryCode(), cin.getCategoryName(), cin.getAuthType());
	}

}
