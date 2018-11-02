package com.lichkin.application.apis.api10005.P.n00;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lichkin.application.utils.LKDictUtils;
import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysRoleR;
import com.lichkin.framework.db.enums.LikeType;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.springframework.entities.impl.SysRoleEntity;
import com.lichkin.springframework.services.LKApiBusGetPageService;

@Service("SysRoleP00Service")
public class S extends LKApiBusGetPageService<I, O, SysRoleEntity> {

	@Override
	protected void initSQL(I sin, String locale, String compId, String loginId, QuerySQL sql) {
		// 主表
		sql.select(SysRoleR.id);
		sql.select(SysRoleR.insertTime);
		sql.select(SysRoleR.roleName);
		sql.select(SysRoleR.description);

		// 关联表

		// 字典表
		int i = 0;
		LKDictUtils.usingStatus(sql, SysRoleR.usingStatus, i++);

		// 筛选条件（必填项）
		// 公司ID
		String busCompId = sin.getCompId();
		sql.eq(SysRoleR.compId, LKFrameworkStatics.LichKin.equals(compId) && StringUtils.isNotBlank(busCompId) ? busCompId : compId);
		// 在用状态
		LKUsingStatusEnum usingStatus = sin.getUsingStatus();
		if (usingStatus == null) {
			if (LKFrameworkStatics.LichKin.equals(compId)) {
				sql.neq(SysRoleR.usingStatus, LKUsingStatusEnum.DEPRECATED);
			} else {
				sql.eq(SysRoleR.usingStatus, LKUsingStatusEnum.USING);
			}
		} else {
			sql.eq(SysRoleR.usingStatus, usingStatus);
		}

		// 筛选条件（业务项）
		String roleName = sin.getRoleName();
		if (StringUtils.isNotBlank(roleName)) {
			sql.like(SysRoleR.roleName, LikeType.ALL, roleName);
		}

		// 排序条件
		sql.addOrders(new Order(SysRoleR.id, false));
	}

}
