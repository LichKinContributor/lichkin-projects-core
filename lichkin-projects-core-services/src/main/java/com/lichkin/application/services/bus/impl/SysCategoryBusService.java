package com.lichkin.application.services.bus.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lichkin.application.cache.Category;
import com.lichkin.application.cache.LKSysCatagoryCache;
import com.lichkin.application.utils.LKDictUtils;
import com.lichkin.framework.db.beans.Condition;
import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysCategoryR;
import com.lichkin.framework.db.beans.eq;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysCategoryEntity;
import com.lichkin.springframework.services.LKDBService;

@Service
public class SysCategoryBusService extends LKDBService {

	public List<SysCategoryEntity> findExist(ApiKeyValues<?> params, String locale, String categoryCode, String categoryName) {
		QuerySQL sql = new QuerySQL(false, SysCategoryEntity.class);

		addConditionId(sql, SysCategoryR.id, params.getId());
		addConditionLocale(sql, SysCategoryR.locale, locale);
//		addConditionCompId(true, sql, SysCategoryR.compId, params.getCompId(), params.getBusCompId());
//		addConditionUsingStatus(true, params.getCompId(), sql, SysCategoryR.usingStatus, params.getUsingStatus(), LKUsingStatusEnum.USING);

		sql.where(

				new Condition(

						new Condition(new eq(SysCategoryR.categoryName, categoryName)),

						new Condition(false, new eq(SysCategoryR.categoryCode, categoryCode))

				)

		);

		return dao.getList(sql, SysCategoryEntity.class);
	}


	public void cacheCategory() {
		QuerySQL sql = new QuerySQL(SysCategoryEntity.class);
		// 主表
		sql.select(SysCategoryR.id);
//		sql.select(SysCategoryR.authType);// CategoryAuthTypeEnum
		sql.select(SysCategoryR.sortId);
		sql.select(SysCategoryR.categoryName);
		sql.select(SysCategoryR.categoryCode);
		sql.select(SysCategoryR.locale);

		// 字典表
		int i = 0;
		LKDictUtils.categoryAuthType(sql, SysCategoryR.authType, i++);

		// 排序条件
		sql.addOrders(new Order(SysCategoryR.sortId), new Order(SysCategoryR.id));

		LKSysCatagoryCache.initList(dao.getList(sql, Category.class));
	}

}
