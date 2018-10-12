package com.lichkin.application.apis.api10009.P.n00;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.Condition;
import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysAdminLoginLogR;
import com.lichkin.framework.db.beans.SysAdminLoginR;
import com.lichkin.framework.db.enums.LikeType;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.defines.enums.impl.LKDateTimeTypeEnum;
import com.lichkin.framework.utils.LKDateTimeUtils;
import com.lichkin.springframework.entities.impl.SysAdminLoginEntity;
import com.lichkin.springframework.entities.impl.SysAdminLoginLogEntity;
import com.lichkin.springframework.services.LKApiBusGetPageService;

@Service("SysAdminLoginLogP00Service")
public class S extends LKApiBusGetPageService<I, O, SysAdminLoginLogEntity> {

	@Override
	protected void initSQL(I sin, QuerySQL sql) {
		String compId = sin.getCompId();

		// 主表
		sql.select(SysAdminLoginLogR.id);
//		sql.select(SysAdminLoginLogR.requestDatas);
		sql.select(SysAdminLoginLogR.requestIp);
		sql.select(SysAdminLoginLogR.requestTime);
		sql.select(SysAdminLoginLogR.requestId);
//		sql.select(SysAdminLoginLogR.loginId);

		// 关联表
		sql.leftJoin(SysAdminLoginEntity.class, new Condition(SysAdminLoginR.id, SysAdminLoginLogR.loginId));
		sql.select(SysAdminLoginR.userName);
		sql.select(SysAdminLoginR.email);

		// 字典表
//		int i = 0;
//		LKDictUtils.x(sql, SysAdminLoginLogR.y, i++);

		// 筛选条件（必填项）
		if (!compId.equals(LKFrameworkStatics.LichKin)) {
			sql.eq(SysAdminLoginLogR.compId, compId);
		}
//		sql.eq(SysAdminLoginLogR.usingStatus, LKUsingStatusEnum.USING);

		// 筛选条件（业务项）
		String userName = sin.getUserName();
		if (StringUtils.isNotBlank(userName)) {
			sql.like(SysAdminLoginR.userName, LikeType.ALL, userName);
		}

		String email = sin.getEmail();
		if (StringUtils.isNotBlank(email)) {
			sql.like(SysAdminLoginR.email, LikeType.ALL, email);
		}

		final String startDate = sin.getStartDate();
		if (StringUtils.isNotBlank(startDate)) {
			sql.gte(SysAdminLoginLogR.requestTime, LKDateTimeUtils.toString(LKDateTimeUtils.toDate(startDate, LKDateTimeTypeEnum.DATE_ONLY), LKDateTimeTypeEnum.TIMESTAMP_MIN));
		}

		final String endDate = sin.getEndDate();
		if (StringUtils.isNotBlank(endDate)) {
			sql.lte(SysAdminLoginLogR.requestTime, LKDateTimeUtils.toString(LKDateTimeUtils.toDate(endDate + " 23:59:59.999", LKDateTimeTypeEnum.TIMESTAMP), LKDateTimeTypeEnum.TIMESTAMP_MIN));
		}

		// 排序条件
		sql.addOrders(new Order(SysAdminLoginLogR.id, false));
	}

}
