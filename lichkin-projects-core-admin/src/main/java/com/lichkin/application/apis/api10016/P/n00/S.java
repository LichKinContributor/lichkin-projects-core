package com.lichkin.application.apis.api10016.P.n00;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lichkin.application.utils.LKDictUtils;
import com.lichkin.framework.db.beans.Condition;
import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysDictionaryR;
import com.lichkin.framework.db.beans.SysEmployeeOperLogR;
import com.lichkin.framework.db.beans.SysEmployeeR;
import com.lichkin.framework.db.enums.LikeType;
import com.lichkin.framework.defines.enums.impl.LKDateTimeTypeEnum;
import com.lichkin.framework.defines.enums.impl.LKOperTypeEnum;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.utils.LKDateTimeUtils;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysEmployeeEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeOperLogEntity;
import com.lichkin.springframework.services.LKApiBusGetPageService;

@Service("SysEmployeeOperLogP00Service")
public class S extends LKApiBusGetPageService<I, O, SysEmployeeOperLogEntity> {

	@Override
	protected void initSQL(I sin, ApiKeyValues<I> params, QuerySQL sql) {
		// 主表
		sql.select(SysEmployeeOperLogR.id);
		sql.select(SysEmployeeOperLogR.requestId);
		sql.select(SysEmployeeOperLogR.requestTime);
		sql.select(SysEmployeeOperLogR.requestIp);

		// 关联表
		sql.innerJoin(SysEmployeeEntity.class, new Condition(SysEmployeeR.id, SysEmployeeOperLogR.loginId));
		sql.select(SysEmployeeR.userName);
		sql.select(SysEmployeeR.cellphone);

		// 字典表
		int i = 0;
		LKDictUtils.employeeBusType(sql, SysEmployeeOperLogR.requestUrl, i++);
		LKDictUtils.operType(sql, SysEmployeeOperLogR.operType, i++);

		// 筛选条件（必填项）
//		addConditionId(sql, SysEmployeeR.id, params.getId());
//		addConditionLocale(sql, SysEmployeeR.locale, params.getLocale());
		addConditionCompId(true, sql, SysEmployeeR.compId, params.getCompId(), params.getBusCompId());
		addConditionUsingStatus(true, params.getCompId(), sql, SysEmployeeR.usingStatus, params.getUsingStatus(), LKUsingStatusEnum.USING);

		// 筛选条件（业务项）
		LKOperTypeEnum operType = sin.getOperType();
		if (operType != null) {
			sql.eq(SysEmployeeOperLogR.operType, operType);
		}

		String busType = sin.getBusType();
		if (StringUtils.isNotBlank(busType)) {
			sql.like(SysDictionaryR.dictName, LikeType.ALL, busType);
		}

		String userName = sin.getUserName();
		if (StringUtils.isNotBlank(userName)) {
			sql.like(SysEmployeeR.userName, LikeType.ALL, userName);
		}

		String cellphone = sin.getCellphone();
		if (StringUtils.isNotBlank(cellphone)) {
			sql.like(SysEmployeeR.cellphone, LikeType.RIGHT, cellphone);
		}

		String startDate = sin.getStartDate();
		if (StringUtils.isNotBlank(startDate)) {
			sql.gte(SysEmployeeOperLogR.requestTime, LKDateTimeUtils.toString(LKDateTimeUtils.toDateTime(startDate, LKDateTimeTypeEnum.DATE_ONLY), LKDateTimeTypeEnum.TIMESTAMP_MIN));
		}

		String endDate = sin.getEndDate();
		if (StringUtils.isNotBlank(endDate)) {
			sql.lt(SysEmployeeOperLogR.requestTime, LKDateTimeUtils.toString(LKDateTimeUtils.toDateTime(endDate, LKDateTimeTypeEnum.DATE_ONLY).plusDays(1), LKDateTimeTypeEnum.TIMESTAMP_MIN));
		}

		// 排序条件
		sql.addOrders(new Order(SysEmployeeOperLogR.id, false));
	}

}
