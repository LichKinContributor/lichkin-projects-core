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
	protected void initSQL(I sin, String locale, String compId, String loginId, QuerySQL sql) {
		// 主表
		sql.select(SysAdminLoginLogR.id);
		sql.select(SysAdminLoginLogR.requestId);
		sql.select(SysAdminLoginLogR.requestTime);
		sql.select(SysAdminLoginLogR.requestIp);

		// 关联表
		sql.leftJoin(SysAdminLoginEntity.class, new Condition(SysAdminLoginR.id, SysAdminLoginLogR.loginId));
		sql.select(SysAdminLoginR.userName);
		sql.select(SysAdminLoginR.email);

		// 字典表
//		int i = 0;

		// 筛选条件（必填项）
		// 公司ID
		String busCompId = sin.getCompId();
//		sql.eq(SysAdminLoginLogR.compId, LKFrameworkStatics.LichKin.equals(compId) && StringUtils.isNotBlank(busCompId) ? busCompId : compId);
		if (LKFrameworkStatics.LichKin.equals(compId)) {
			if (StringUtils.isNotBlank(busCompId)) {
				sql.eq(SysAdminLoginLogR.compId, busCompId);
			}
		} else {
			sql.eq(SysAdminLoginLogR.compId, compId);
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

		String startDate = sin.getStartDate();
		if (StringUtils.isNotBlank(startDate)) {
			sql.gte(SysAdminLoginLogR.requestTime, LKDateTimeUtils.toString(LKDateTimeUtils.toDateTime(startDate, LKDateTimeTypeEnum.DATE_ONLY), LKDateTimeTypeEnum.TIMESTAMP_MIN));
		}

		String endDate = sin.getEndDate();
		if (StringUtils.isNotBlank(endDate)) {
			sql.lt(SysAdminLoginLogR.requestTime, LKDateTimeUtils.toString(LKDateTimeUtils.toDateTime(endDate, LKDateTimeTypeEnum.DATE_ONLY).plusDays(1), LKDateTimeTypeEnum.TIMESTAMP_MIN));
		}

		// 排序条件
		sql.addOrders(new Order(SysAdminLoginLogR.id, false));
	}

}
