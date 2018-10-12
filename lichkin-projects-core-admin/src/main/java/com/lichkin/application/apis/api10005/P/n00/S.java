package com.lichkin.application.apis.api10005.P.n00;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysRoleR;
import com.lichkin.framework.db.enums.LikeType;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.springframework.entities.impl.SysRoleEntity;
import com.lichkin.springframework.services.LKApiBusGetPageService;

@Service("SysRoleP00Service")
public class S extends LKApiBusGetPageService<I, O, SysRoleEntity> {

	@Override
	protected void initSQL(I sin, QuerySQL sql) {
		String compId = sin.getCompId();

		// 主表
		sql.select(SysRoleR.id);
//		sql.select(SysRoleR.insertTime);
//		sql.select(SysRoleR.usingStatus);// LKUsingStatusEnum
		sql.select(SysRoleR.description);
		sql.select(SysRoleR.roleName);

		// 关联表
//		sql.innerJoin(BusXEntity.class, new Condition(BusXR.y, SysRoleR.z));
//		sql.select(BusXR.w);

		// 字典表
//		int i = 0;
//		LKDictUtils.x(sql, SysRoleR.y, i++);

		// 筛选条件（必填项）
		sql.eq(SysRoleR.compId, compId);
		sql.eq(SysRoleR.usingStatus, LKUsingStatusEnum.USING);

		// 筛选条件（业务项）
		String roleName = sin.getRoleName();
		if (StringUtils.isNotBlank(roleName)) {
			sql.like(SysRoleR.roleName, LikeType.ALL, roleName);
		}

		// 排序条件
		sql.addOrders(new Order(SysRoleR.id, false));
	}

}
