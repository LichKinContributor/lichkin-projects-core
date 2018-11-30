package com.lichkin.application.apis.api10021.P.n00;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lichkin.application.utils.LKDictUtils;
import com.lichkin.framework.db.beans.Condition;
import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysDictionaryR;
import com.lichkin.framework.db.beans.SysUserLoginR;
import com.lichkin.framework.db.beans.SysUserOperLogR;
import com.lichkin.framework.db.enums.LikeType;
import com.lichkin.framework.defines.enums.impl.LKDateTimeTypeEnum;
import com.lichkin.framework.defines.enums.impl.LKOperTypeEnum;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.utils.LKDateTimeUtils;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysUserLoginEntity;
import com.lichkin.springframework.entities.impl.SysUserOperLogEntity;
import com.lichkin.springframework.services.LKApiBusGetPageService;

@Service("SysUserOperLogP00Service")
public class S extends LKApiBusGetPageService<I, O, SysUserOperLogEntity> {

	@Override
	protected void initSQL(I sin, ApiKeyValues<I> params, QuerySQL sql) {
		// 主表
		sql.select(SysUserOperLogR.id);
		sql.select(SysUserOperLogR.requestId);
		sql.select(SysUserOperLogR.requestTime);
		sql.select(SysUserOperLogR.requestIp);

		// 关联表
		sql.innerJoin(SysUserLoginEntity.class, new Condition(SysUserLoginR.id, SysUserOperLogR.loginId));
		sql.select(SysUserLoginR.loginName);
		sql.select(SysUserLoginR.cellphone);

		// 字典表
		int i = 0;
		LKDictUtils.userBusType(sql, SysUserOperLogR.requestUrl, i++);
		LKDictUtils.operType(sql, SysUserOperLogR.operType, i++);

		// 筛选条件（必填项）
//		addConditionId(sql, SysUserLoginR.id, params.getId());
//		addConditionLocale(sql, SysUserLoginR.locale, params.getLocale());
//		addConditionCompId(true, sql, SysUserLoginR.compId, params.getCompId(), params.getBusCompId());
		addConditionUsingStatus(true, params.getCompId(), sql, SysUserLoginR.usingStatus, params.getUsingStatus(), LKUsingStatusEnum.USING);

		// 筛选条件（业务项）
		LKOperTypeEnum operType = sin.getOperType();
		if (operType != null) {
			sql.eq(SysUserOperLogR.operType, operType);
		}

		String busType = sin.getBusType();
		if (StringUtils.isNotBlank(busType)) {
			sql.like(SysDictionaryR.dictName, LikeType.ALL, busType);
		}

		String loginName = sin.getLoginName();
		if (StringUtils.isNotBlank(loginName)) {
			sql.like(SysUserLoginR.loginName, LikeType.ALL, loginName);
		}

		String cellphone = sin.getCellphone();
		if (StringUtils.isNotBlank(cellphone)) {
			sql.like(SysUserLoginR.cellphone, LikeType.RIGHT, cellphone);
		}

		String startDate = sin.getStartDate();
		if (StringUtils.isNotBlank(startDate)) {
			sql.gte(SysUserOperLogR.requestTime, LKDateTimeUtils.toString(LKDateTimeUtils.toDateTime(startDate, LKDateTimeTypeEnum.DATE_ONLY), LKDateTimeTypeEnum.TIMESTAMP_MIN));
		}

		String endDate = sin.getEndDate();
		if (StringUtils.isNotBlank(endDate)) {
			sql.lt(SysUserOperLogR.requestTime, LKDateTimeUtils.toString(LKDateTimeUtils.toDateTime(endDate, LKDateTimeTypeEnum.DATE_ONLY).plusDays(1), LKDateTimeTypeEnum.TIMESTAMP_MIN));
		}

		// 排序条件
		sql.addOrders(new Order(SysUserOperLogR.id, false));
	}

}
