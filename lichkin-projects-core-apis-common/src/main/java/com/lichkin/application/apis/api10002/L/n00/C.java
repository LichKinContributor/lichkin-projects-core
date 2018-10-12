package com.lichkin.application.apis.api10002.L.n00;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lichkin.application.cache.LKSysCatagoryCache;
import com.lichkin.framework.beans.impl.LKRequestBean;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.framework.web.annotations.LKApiType;
import com.lichkin.framework.web.enums.ApiType;
import com.lichkin.springframework.controllers.LKApiY0Controller;
import com.lichkin.springframework.entities.impl.SysCategoryEntity;

@RestController("SysCategoryL00Controller")
@RequestMapping(value = LKFrameworkStatics.WEB_MAPPING_API_WEB_ADMIN + "/SysCategory/L")
@LKApiType(apiType = ApiType.COMPANY_BUSINESS)
public class C extends LKApiY0Controller<LKRequestBean, List<O>> {

	@Override
	protected List<O> doInvoke(LKRequestBean cin) throws LKException {
		List<SysCategoryEntity> listCategory = LKSysCatagoryCache.getList(cin.getLocale(), cin.getCompId());
		if (CollectionUtils.isEmpty(listCategory)) {
			return Collections.emptyList();
		}
		List<O> list = new ArrayList<>(listCategory.size());
		for (SysCategoryEntity category : listCategory) {
			O o = new O();
			o.setCategoryCode(category.getCategoryCode());
			o.setCategoryName(category.getCategoryName());
			list.add(o);
		}
		return list;
	}

}
