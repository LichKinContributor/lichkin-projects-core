package com.lichkin.application.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.Condition;
import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysMenuR;
import com.lichkin.framework.db.beans.eq;
import com.lichkin.framework.db.beans.isNull;
import com.lichkin.springframework.entities.impl.SysMenuEntity;
import com.lichkin.springframework.services.LKDBService;

@Service
public class SysMenuService extends LKDBService {

	public List<SysMenuEntity> findAllAssignableAsSuperAdmin() {
		QuerySQL sql = new QuerySQL(SysMenuEntity.class);

		sql.where(new Condition(new Condition(new eq(SysMenuR.rootOnly, Boolean.FALSE)), new Condition(false, new isNull(SysMenuR.rootOnly))));

		sql.addOrders(new Order(SysMenuR.menuCode));

		return dao.getList(sql, SysMenuEntity.class);
	}

}
