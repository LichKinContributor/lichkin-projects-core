package com.lichkin.application.apis.api10003.L.n00;

import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysDictionaryR;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.springframework.entities.impl.SysDictionaryEntity;
import com.lichkin.springframework.services.LKApiBusGetListService;

@Service("SysDictionaryL00Service")
public class S extends LKApiBusGetListService<I, O, SysDictionaryEntity> {

	@Override
	protected void initSQL(I sin, QuerySQL sql) {
		String compId = sin.getCompId();

		// 主表
		sql.select(SysDictionaryR.id);
//		sql.select(SysDictionaryR.insertTime);
//		sql.select(SysDictionaryR.usingStatus);// LKUsingStatusEnum
		sql.select(SysDictionaryR.orderId);
		sql.select(SysDictionaryR.dictName);
		sql.select(SysDictionaryR.dictCode);
//		sql.select(SysDictionaryR.categoryCode);
//		sql.select(SysDictionaryR.locale);

		// 关联表
//		sql.innerJoin(BusXEntity.class, new Condition(BusXR.y, SysDictionaryR.z));
//		sql.select(BusXR.w);

		// 字典表
//		int i = 0;
//		LKDictUtils.x(sql, SysDictionaryR.y, i++);

		// 筛选条件（必填项）
		sql.eq(SysDictionaryR.compId, compId);
		sql.eq(SysDictionaryR.usingStatus, LKUsingStatusEnum.USING);

		// 筛选条件（业务项）
		sql.eq(SysDictionaryR.locale, sin.getLocale());
		sql.eq(SysDictionaryR.categoryCode, sin.getCategoryCode());

		// 排序条件
		sql.addOrders(new Order(SysDictionaryR.orderId), new Order(SysDictionaryR.id, false));
	}

}
