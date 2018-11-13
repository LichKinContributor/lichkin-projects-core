package com.lichkin.application.apis.api10002.L.n01;

import java.util.List;

import com.lichkin.application.cache.Category;
import com.lichkin.application.cache.LKSysCatagoryCache;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.framework.web.annotations.LKApiType;
import com.lichkin.framework.web.enums.ApiType;
import com.lichkin.springframework.controllers.LKApiY0Controller;

@LKApiType(apiType = ApiType.OPEN)
public abstract class CSuper extends LKApiY0Controller<I, List<Category>> {

	@Override
	protected List<Category> doInvoke(I cin) throws LKException {
		return LKSysCatagoryCache.getList(cin.getDatas().getLocale());
	}

}
