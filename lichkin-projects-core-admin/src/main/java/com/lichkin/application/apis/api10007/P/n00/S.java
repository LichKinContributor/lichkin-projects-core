package com.lichkin.application.apis.api10007.P.n00;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lichkin.application.utils.LKDictUtils;
import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysAdminLoginR;
import com.lichkin.framework.db.enums.LikeType;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysAdminLoginEntity;
import com.lichkin.springframework.services.LKApiBusGetPageService;

@Service("SysAdminLoginP00Service")
public class S extends LKApiBusGetPageService<I, O, SysAdminLoginEntity> {

	@Override
	protected void initSQL(I sin, ApiKeyValues<I> params, QuerySQL sql) {
		// 主表
		sql.select(SysAdminLoginR.id);
		sql.select(SysAdminLoginR.insertTime);
		sql.select(SysAdminLoginR.userName);
		sql.select(SysAdminLoginR.email);
		sql.select(SysAdminLoginR.level);

		// 关联表

		// 字典表
		int i = 0;
		LKDictUtils.usingStatus(sql, SysAdminLoginR.usingStatus, i++);
		LKDictUtils.gender(sql, SysAdminLoginR.gender, i++);

		// 筛选条件（必填项）
//		addConditionId(sql, SysAdminLoginR.id, params.getId());
//		addConditionLocale(sql, SysAdminLoginR.locale, params.getLocale());
		addConditionCompId(true, sql, SysAdminLoginR.compId, params.getCompId(), params.getBusCompId());
//		addConditionUsingStatus(true, params.getCompId(), sql, SysAdminLoginR.usingStatus, params.getUsingStatus(), LKUsingStatusEnum.USING);
		LKUsingStatusEnum usingStatus = sin.getUsingStatus();
		if (usingStatus == null) {
			if (LKFrameworkStatics.LichKin.equals(params.getCompId())) {
				sql.neq(SysAdminLoginR.usingStatus, LKUsingStatusEnum.DEPRECATED);
			} else {
				sql.neq(SysAdminLoginR.usingStatus, LKUsingStatusEnum.DEPRECATED);
			}
		} else {
			sql.eq(SysAdminLoginR.usingStatus, usingStatus);
		}

		// 筛选条件（业务项）
		String userName = sin.getUserName();
		if (StringUtils.isNotBlank(userName)) {
			sql.like(SysAdminLoginR.userName, LikeType.ALL, userName);
		}

		String email = sin.getEmail();
		if (StringUtils.isNotBlank(email)) {
			sql.like(SysAdminLoginR.email, LikeType.ALL, email);
		}

		if (LKFrameworkStatics.LichKin.equals(params.getCompId())) {
			sql.eq(SysAdminLoginR.superAdmin, Boolean.TRUE);
		} else {
			sql.eq(SysAdminLoginR.superAdmin, Boolean.FALSE);
		}

		// 排序条件
		sql.addOrders(new Order(SysAdminLoginR.id, false));
	}

}
