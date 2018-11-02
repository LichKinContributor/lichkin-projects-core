package com.lichkin.application.apis.api10010.P.n00;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lichkin.application.utils.LKDictUtils;
import com.lichkin.framework.db.beans.Condition;
import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysAdminLoginR;
import com.lichkin.framework.db.beans.SysAdminOperLogR;
import com.lichkin.framework.db.beans.SysDictionaryR;
import com.lichkin.framework.db.enums.LikeType;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.defines.enums.impl.LKDateTimeTypeEnum;
import com.lichkin.framework.defines.enums.impl.LKOperTypeEnum;
import com.lichkin.framework.utils.LKDateTimeUtils;
import com.lichkin.springframework.entities.impl.SysAdminLoginEntity;
import com.lichkin.springframework.entities.impl.SysAdminOperLogEntity;
import com.lichkin.springframework.services.LKApiBusGetPageService;

@Service("SysAdminOperLogP00Service")
public class S extends LKApiBusGetPageService<I, O, SysAdminOperLogEntity> {

	@Override
	protected void initSQL(I sin, String locale, String compId, String loginId, QuerySQL sql) {
		// 主表
		sql.select(SysAdminOperLogR.id);
		sql.select(SysAdminOperLogR.requestId);
		sql.select(SysAdminOperLogR.requestTime);
		sql.select(SysAdminOperLogR.requestIp);

		// 关联表
		sql.innerJoin(SysAdminLoginEntity.class, new Condition(SysAdminLoginR.id, SysAdminOperLogR.loginId));
		sql.select(SysAdminLoginR.userName);
		sql.select(SysAdminLoginR.email);

		// 字典表
		int i = 0;
		LKDictUtils.adminBusType(sql, SysAdminOperLogR.busType, i++);
		LKDictUtils.operType(sql, SysAdminOperLogR.operType, i++);

		// 筛选条件（必填项）
		// 公司ID
		String busCompId = sin.getCompId();
//		sql.eq(SysAdminOperLogR.compId, LKFrameworkStatics.LichKin.equals(compId) && StringUtils.isNotBlank(busCompId) ? busCompId : compId);
		if (LKFrameworkStatics.LichKin.equals(compId)) {
			if (StringUtils.isNotBlank(busCompId)) {
				sql.eq(SysAdminOperLogR.compId, busCompId);
			}
		} else {
			sql.eq(SysAdminOperLogR.compId, compId);
		}

		// 筛选条件（业务项）
		LKOperTypeEnum operType = sin.getOperType();
		if (operType != null) {
			sql.eq(SysAdminOperLogR.operType, operType);
		}

		String busType = sin.getBusType();
		if (StringUtils.isNotBlank(busType)) {
			sql.like(SysDictionaryR.dictName, LikeType.ALL, busType);
		}

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
			sql.gte(SysAdminOperLogR.requestTime, LKDateTimeUtils.toString(LKDateTimeUtils.toDateTime(startDate, LKDateTimeTypeEnum.DATE_ONLY), LKDateTimeTypeEnum.TIMESTAMP_MIN));
		}

		String endDate = sin.getEndDate();
		if (StringUtils.isNotBlank(endDate)) {
			sql.lt(SysAdminOperLogR.requestTime, LKDateTimeUtils.toString(LKDateTimeUtils.toDateTime(endDate, LKDateTimeTypeEnum.DATE_ONLY).plusDays(1), LKDateTimeTypeEnum.TIMESTAMP_MIN));
		}

		// 排序条件
		sql.addOrders(new Order(SysAdminOperLogR.id, false));
	}

}
