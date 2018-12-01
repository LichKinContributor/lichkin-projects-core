package com.lichkin.application.apis.ROOT.GetDeptTree;

import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysDeptR;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysDeptEntity;
import com.lichkin.springframework.services.LKApiBusGetListService;

@Service(Statics.SERVICE_NAME)
public class S extends LKApiBusGetListService<I, SysDeptEntity, SysDeptEntity> {

	@Override
	protected void initSQL(I sin, ApiKeyValues<I> params, QuerySQL sql) {
		sql.eq(SysDeptR.compId, sin.getCompId());
		sql.eq(SysDeptR.usingStatus, LKUsingStatusEnum.USING);
		sql.addOrders(new Order(SysDeptR.deptCode));
	}

}
