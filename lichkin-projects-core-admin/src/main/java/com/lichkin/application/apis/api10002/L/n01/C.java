package com.lichkin.application.apis.api10002.L.n01;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lichkin.application.cache.Category;
import com.lichkin.application.cache.LKSysCatagoryCache;
import com.lichkin.framework.beans.impl.LKRequestBean;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.framework.web.annotations.LKApiType;
import com.lichkin.framework.web.enums.ApiType;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.controllers.LKApiY0Controller;

@RestController("SysCategoryL01Controller")
@RequestMapping(value = LKFrameworkStatics.WEB_MAPPING_API + "/SysCategory/L01")
@LKApiType(apiType = ApiType.OPEN)
public class C extends LKApiY0Controller<LKRequestBean, List<Category>> {

	@Override
	protected boolean saveLog(LKRequestBean cin, ApiKeyValues<LKRequestBean> params) {
		return false;
	}


	@Deprecated
	@Override
	protected List<Category> doInvoke(LKRequestBean cin, ApiKeyValues<LKRequestBean> params) throws LKException {
		return LKSysCatagoryCache.getList(params.getLocale());
	}

}
