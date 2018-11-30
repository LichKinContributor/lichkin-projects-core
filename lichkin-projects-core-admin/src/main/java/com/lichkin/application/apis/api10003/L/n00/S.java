package com.lichkin.application.apis.api10003.L.n00;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lichkin.application.utils.LKDictUtils;
import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysDictionaryR;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysDictionaryEntity;
import com.lichkin.springframework.services.LKApiBusGetListService;

@Service("SysDictionaryL00Service")
public class S extends LKApiBusGetListService<I, O, SysDictionaryEntity> {

	@Override
	protected void initSQL(I sin, ApiKeyValues<I> params, QuerySQL sql) {
		// 主表
		sql.select(SysDictionaryR.id);
		sql.select(SysDictionaryR.insertTime);
		sql.select(SysDictionaryR.dictCode);
		sql.select(SysDictionaryR.dictName);
		sql.select(SysDictionaryR.sortId);

		// 关联表

		// 字典表
//		int i = 0;
		LKDictUtils.usingStatus(sql, SysDictionaryR.usingStatus, 1);

		// 筛选条件（必填项）
//		addConditionId(sql, SysDictionaryR.id, params.getId());
		addConditionLocale(sql, SysDictionaryR.locale, params.getLocale());
		addConditionCompId(true, sql, SysDictionaryR.compId, params.getCompId(), params.getBusCompId());
		addConditionUsingStatus(true, params.getCompId(), sql, SysDictionaryR.usingStatus, params.getUsingStatus(), LKUsingStatusEnum.STAND_BY, LKUsingStatusEnum.USING);

		// 筛选条件（业务项）
		String categoryCode = sin.getCategoryCode();
		if (StringUtils.isNotBlank(categoryCode)) {
			sql.eq(SysDictionaryR.categoryCode, categoryCode);
		}

		// 排序条件
		sql.addOrders(new Order(SysDictionaryR.sortId), new Order(SysDictionaryR.id, false));
	}

}
