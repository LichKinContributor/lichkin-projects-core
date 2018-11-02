package com.lichkin.application.apis.api10003.L.n02;

import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.Condition;
import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysCompR;
import com.lichkin.framework.db.beans.SysDictionaryR;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.springframework.entities.impl.SysCompEntity;
import com.lichkin.springframework.entities.impl.SysDictionaryEntity;
import com.lichkin.springframework.services.LKApiBusGetListService;

@Service("SysDictionaryL02Service")
public class S extends LKApiBusGetListService<I, O, SysDictionaryEntity> {

	@Override
	protected void initSQL(I sin, String locale, String compId, String loginId, QuerySQL sql) {
		// 主表
		sql.select(SysDictionaryR.id);
//		sql.select(SysDictionaryR.insertTime);
//		sql.select(SysDictionaryR.usingStatus);// LKUsingStatusEnum
//		sql.select(SysDictionaryR.sortId);
//		sql.select(SysDictionaryR.dictName);
//		sql.select(SysDictionaryR.dictCode);
//		sql.select(SysDictionaryR.categoryCode);
//		sql.select(SysDictionaryR.locale);

		// 关联表
		sql.innerJoin(SysCompEntity.class, new Condition(SysCompR.id, SysDictionaryR.compId));
		sql.select(SysCompR.compName);

		// 字典表
//		int i = 0;
//		LKDictUtils.x(sql, SysDictionaryR.y, i++);

		// 筛选条件（必填项）
//		sql.eq(SysDictionaryR.compId, compId);
		sql.eq(SysDictionaryR.usingStatus, LKUsingStatusEnum.USING);

		// 筛选条件（业务项）
		sql.eq(SysDictionaryR.locale, locale);
		sql.eq(SysDictionaryR.categoryCode, sin.getCategoryCode());
		sql.eq(SysDictionaryR.dictCode, sin.getDictCode());

		// 排序条件
		sql.addOrders(new Order(SysDictionaryR.sortId), new Order(SysDictionaryR.id, false));
	}

}
