package com.lichkin.application.apis.api10001.LD.n00;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysCompR;
import com.lichkin.framework.defines.beans.impl.LKDroplistBean;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysCompEntity;
import com.lichkin.springframework.services.LKApiBusGetDroplistService;

@Service("SysCompLD00Service")
public class S extends LKApiBusGetDroplistService<I> {

	@Override
	public List<LKDroplistBean> handle(I sin, ApiKeyValues<I> params) throws LKException {
		QuerySQL sql = new QuerySQL(SysCompEntity.class);

		// 查询结果
		sql.select(SysCompR.id, "value");
		sql.select(SysCompR.compName, "text");

		// 筛选条件（必填项）
//		sql.eq(SysCompR.compId, compId);
		sql.eq(SysCompR.usingStatus, LKUsingStatusEnum.USING);

		// 筛选条件（业务项）

		// 排序条件
		sql.addOrders(new Order(SysCompR.id, false));

		// 返回结果
		return dao.getList(sql, LKDroplistBean.class);
	}

}
