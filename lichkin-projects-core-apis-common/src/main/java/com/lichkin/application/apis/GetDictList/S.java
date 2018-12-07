package com.lichkin.application.apis.GetDictList;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lichkin.application.cache.Category;
import com.lichkin.application.cache.LKSysCatagoryCache;
import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysDictionaryR;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysDictionaryEntity;
import com.lichkin.springframework.services.LKApiBusGetListService;

@Service(Statics.SERVICE_NAME)
public class S extends LKApiBusGetListService<I, SysDictionaryEntity, SysDictionaryEntity> {

	@Override
	protected List<SysDictionaryEntity> beforeQuery(I cin, ApiKeyValues<I> params) {
		String categoryCode = cin.getCategoryCode();
		Category category = LKSysCatagoryCache.get(params.getLocale(), categoryCode);
		if (StringUtils.isBlank(categoryCode) || "LOCALE".equals(categoryCode) || (category == null)) {
			return emptyList();
		}
		params.putObject("category", category);
		return null;
	}


	@Override
	protected void initSQL(I cin, ApiKeyValues<I> params, QuerySQL sql) {
		sql.eq(SysDictionaryR.usingStatus, LKUsingStatusEnum.USING);
		sql.eq(SysDictionaryR.locale, params.getLocale());
		sql.eq(SysDictionaryR.categoryCode, cin.getCategoryCode());

		switch (((Category) params.getObject("category")).getAuthTypeDictCode()) {
			case "ENUM":
			case "ROOT":
				sql.eq(SysDictionaryR.compId, LKFrameworkStatics.LichKin);
			break;
			case "R_2_C":
			case "COMP":
			case "COMMON":
				sql.eq(SysDictionaryR.compId, params.getCompId());
			break;
		}

		String includes = cin.getIncludes();
		if (StringUtils.isNotBlank(includes)) {
			sql.in(SysDictionaryR.dictCode, includes);
		}

		String excludes = cin.getExcludes();
		if (StringUtils.isNotBlank(excludes)) {
			sql.notIn(SysDictionaryR.dictCode, excludes);
		}

		sql.addOrders(new Order(SysDictionaryR.sortId), new Order(SysDictionaryR.id));
	}

}
