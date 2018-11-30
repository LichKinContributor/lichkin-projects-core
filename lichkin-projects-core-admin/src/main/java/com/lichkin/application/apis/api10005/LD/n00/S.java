package com.lichkin.application.apis.api10005.LD.n00;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysRoleR;
import com.lichkin.framework.defines.beans.impl.LKDroplistBean;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysRoleEntity;
import com.lichkin.springframework.services.LKApiBusGetDroplistService;

@Service("SysRoleLD00Service")
public class S extends LKApiBusGetDroplistService<I> {

	@Override
	public List<LKDroplistBean> handle(I sin, ApiKeyValues<I> params) throws LKException {
		QuerySQL sql = new QuerySQL(SysRoleEntity.class);

		// 查询结果
		sql.select(SysRoleR.id, "value");
		sql.select(SysRoleR.roleName, "text");

		// 筛选条件（必填项）
//		addConditionId(sql, SysRoleR.id, params.getId());
//		addConditionLocale(sql, SysRoleR.locale, params.getLocale());
		addConditionCompId(true, sql, SysRoleR.compId, params.getCompId(), params.getBusCompId());
		addConditionUsingStatus(true, params.getCompId(), sql, SysRoleR.usingStatus, params.getUsingStatus(), LKUsingStatusEnum.STAND_BY, LKUsingStatusEnum.USING);

		// 筛选条件（业务项）

		// 排序条件
		sql.addOrders(new Order(SysRoleR.id, false));

		// 返回结果
		return dao.getList(sql, LKDroplistBean.class);
	}

}
