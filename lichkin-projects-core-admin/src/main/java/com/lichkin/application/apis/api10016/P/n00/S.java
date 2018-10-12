package com.lichkin.application.apis.api10016.P.n00;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lichkin.application.utils.LKDictUtils;
import com.lichkin.framework.db.beans.Condition;
import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysEmployeeLoginR;
import com.lichkin.framework.db.beans.SysEmployeeOperLogR;
import com.lichkin.framework.db.enums.LikeType;
import com.lichkin.framework.defines.enums.impl.LKDateTimeTypeEnum;
import com.lichkin.framework.defines.enums.impl.LKOperTypeEnum;
import com.lichkin.framework.utils.LKDateTimeUtils;
import com.lichkin.springframework.entities.impl.SysEmployeeLoginEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeOperLogEntity;
import com.lichkin.springframework.services.LKApiBusGetPageService;

@Service("SysEmployeeOperLogP00Service")
public class S extends LKApiBusGetPageService<I, O, SysEmployeeOperLogEntity> {

	@Override
	protected void initSQL(I sin, QuerySQL sql) {
		String compId = sin.getCompId();

		// 主表
		sql.select(SysEmployeeOperLogR.id);
//		sql.select(SysEmployeeOperLogR.busType);
//		sql.select(SysEmployeeOperLogR.operType);// LKOperTypeEnum
//		sql.select(SysEmployeeOperLogR.requestDatas);
//		sql.select(SysEmployeeOperLogR.requestUrl);
		sql.select(SysEmployeeOperLogR.requestIp);
		sql.select(SysEmployeeOperLogR.requestTime);
		sql.select(SysEmployeeOperLogR.requestId);
//		sql.select(SysEmployeeOperLogR.loginId);

		// 关联表
		sql.innerJoin(SysEmployeeLoginEntity.class, new Condition(SysEmployeeLoginR.id, SysEmployeeOperLogR.loginId));
		sql.select(SysEmployeeLoginR.loginName);
		sql.select(SysEmployeeLoginR.cellphone);

		// 字典表
		int i = 0;
		LKDictUtils.operType(sql, SysEmployeeOperLogR.operType, i++);
		LKDictUtils.employeeBusType(sql, SysEmployeeOperLogR.busType, i++);

		// 筛选条件（必填项）
		sql.eq(SysEmployeeOperLogR.compId, compId);
//		sql.eq(SysEmployeeOperLogR.usingStatus, LKUsingStatusEnum.USING);

		// 筛选条件（业务项）
		LKOperTypeEnum operType = sin.getOperType();
		if (operType != null) {
			sql.eq(SysEmployeeOperLogR.operType, operType);
		}

		String busType = sin.getBusType();
		if (StringUtils.isNotBlank(busType)) {
			sql.eq(SysEmployeeOperLogR.busType, busType);
		}

		String loginName = sin.getLoginName();
		if (StringUtils.isNotBlank(loginName)) {
			sql.like(SysEmployeeLoginR.loginName, LikeType.ALL, loginName);
		}

		String cellphone = sin.getCellphone();
		if (StringUtils.isNotBlank(cellphone)) {
			sql.like(SysEmployeeLoginR.cellphone, LikeType.ALL, cellphone);
		}

		final String startDate = sin.getStartDate();
		if (StringUtils.isNotBlank(startDate)) {
			sql.gte(SysEmployeeOperLogR.requestTime, LKDateTimeUtils.toString(LKDateTimeUtils.toDate(startDate, LKDateTimeTypeEnum.DATE_ONLY), LKDateTimeTypeEnum.TIMESTAMP_MIN));
		}

		final String endDate = sin.getEndDate();
		if (StringUtils.isNotBlank(endDate)) {
			sql.lte(SysEmployeeOperLogR.requestTime, LKDateTimeUtils.toString(LKDateTimeUtils.toDate(endDate + " 23:59:59.999", LKDateTimeTypeEnum.TIMESTAMP), LKDateTimeTypeEnum.TIMESTAMP_MIN));
		}

		// 排序条件
		sql.addOrders(new Order(SysEmployeeOperLogR.id, false));
	}

}
