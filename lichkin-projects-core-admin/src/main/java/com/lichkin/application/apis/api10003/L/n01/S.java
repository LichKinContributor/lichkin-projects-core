package com.lichkin.application.apis.api10003.L.n01;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.Condition;
import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysCategoryR;
import com.lichkin.framework.db.beans.SysDictionaryR;
import com.lichkin.framework.db.beans.eq;
import com.lichkin.framework.db.enums.LikeType;
import com.lichkin.framework.defines.enums.impl.CategoryAuthTypeEnum;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysCategoryEntity;
import com.lichkin.springframework.entities.impl.SysDictionaryEntity;
import com.lichkin.springframework.services.LKApiBusGetListService;

@Service("SysDictionaryL01Service")
public class S extends LKApiBusGetListService<I, O, SysDictionaryEntity> {

	@Override
	protected void initSQL(I sin, ApiKeyValues<I> params, QuerySQL sql) {
		// 主表
		sql.select(SysDictionaryR.id);
//		sql.select(SysDictionaryR.insertTime);
//		sql.select(SysDictionaryR.usingStatus);// LKUsingStatusEnum
		sql.select(SysDictionaryR.sortId);
		sql.select(SysDictionaryR.dictName);
		sql.select(SysDictionaryR.dictCode);
		sql.select(SysDictionaryR.categoryCode);
		sql.select(SysDictionaryR.locale);

		// 关联表
		sql.innerJoin(SysCategoryEntity.class, new Condition(SysCategoryR.categoryCode, SysDictionaryR.categoryCode), new Condition(true, new eq(SysCategoryR.locale, params.getLocale())));
		sql.select(SysCategoryR.categoryName);

		// 字典表
//		int i = 0;
//		LKDictUtils.x(sql, SysDictionaryR.y, i++);

		// 筛选条件（必填项）
//		addConditionId(sql, SysDictionaryR.id, params.getId());
//		addConditionLocale(sql, SysDictionaryR.locale, params.getLocale());
		addConditionCompId(true, sql, SysDictionaryR.compId, params.getCompId(), params.getBusCompId());
		addConditionUsingStatus(true, params.getCompId(), sql, SysDictionaryR.usingStatus, params.getUsingStatus(), LKUsingStatusEnum.STAND_BY, LKUsingStatusEnum.USING);

		// 筛选条件（业务项）
		sql.eq(SysCategoryR.authType, CategoryAuthTypeEnum.R_2_C);

		String categoryName = sin.getCategoryName();
		if (StringUtils.isNotBlank(categoryName)) {
			sql.like(SysCategoryR.categoryName, LikeType.ALL, categoryName);
		}

		String categoryCode = sin.getCategoryCode();
		if (StringUtils.isNotBlank(categoryCode)) {
			sql.like(SysCategoryR.categoryCode, LikeType.ALL, categoryCode);
		}

		String dictName = sin.getDictName();
		if (StringUtils.isNotBlank(dictName)) {
			sql.like(SysDictionaryR.dictName, LikeType.ALL, dictName);
		}

		String dictCode = sin.getDictCode();
		if (StringUtils.isNotBlank(dictCode)) {
			sql.like(SysDictionaryR.dictCode, LikeType.ALL, dictCode);
		}

		// 排序条件
		sql.addOrders(new Order(SysDictionaryR.categoryCode), new Order(SysDictionaryR.sortId), new Order(SysDictionaryR.id, false));
	}

}
