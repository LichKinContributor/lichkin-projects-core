package com.lichkin.application.apis.api10005.LD.n00;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysRoleR;
import com.lichkin.framework.defines.beans.impl.LKDroplistBean;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.springframework.entities.impl.SysRoleEntity;
import com.lichkin.springframework.services.LKApiBusGetDroplistService;

@Service("SysRoleLD00Service")
public class S extends LKApiBusGetDroplistService<I> {

	@Override
	public List<LKDroplistBean> handle(I sin) throws LKException {
		QuerySQL sql = new QuerySQL(SysRoleEntity.class);

		sql.select(SysRoleR.id, "value");
		sql.select(SysRoleR.roleName, "text");

		sql.eq(SysRoleR.usingStatus, LKUsingStatusEnum.USING);
		sql.eq(SysRoleR.compId, sin.getCompId());

		sql.addOrders(new Order(SysRoleR.id, false));

		return dao.getList(sql, LKDroplistBean.class);
	}

}
