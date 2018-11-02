package com.lichkin.application.apis.api10020.P.n00;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lichkin.application.utils.LKDictUtils;
import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysUserLoginR;
import com.lichkin.framework.db.enums.LikeType;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.defines.enums.impl.LKDateTimeTypeEnum;
import com.lichkin.framework.defines.enums.impl.LKGenderEnum;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.utils.LKDateTimeUtils;
import com.lichkin.springframework.entities.impl.SysUserLoginEntity;
import com.lichkin.springframework.services.LKApiBusGetPageService;

@Service("SysUserLoginP00Service")
public class S extends LKApiBusGetPageService<I, O, SysUserLoginEntity> {

	@Override
	protected void initSQL(I sin, String locale, String compId, String loginId, QuerySQL sql) {
		// 主表
		sql.select(SysUserLoginR.id);
		sql.select(SysUserLoginR.insertTime);
		sql.select(SysUserLoginR.userName);
		sql.select(SysUserLoginR.loginName);
		sql.select(SysUserLoginR.cellphone);
		sql.select(SysUserLoginR.email);
		sql.select(SysUserLoginR.level);

		// 关联表

		// 字典表
		int i = 0;
		LKDictUtils.usingStatus(sql, SysUserLoginR.usingStatus, i++);
		LKDictUtils.gender(sql, SysUserLoginR.gender, i++);

		// 筛选条件（必填项）
		// 在用状态
		LKUsingStatusEnum usingStatus = sin.getUsingStatus();
		if (usingStatus == null) {
			if (LKFrameworkStatics.LichKin.equals(compId)) {
				sql.neq(SysUserLoginR.usingStatus, LKUsingStatusEnum.DEPRECATED);
			} else {
				sql.eq(SysUserLoginR.usingStatus, LKUsingStatusEnum.USING);
			}
		} else {
			sql.eq(SysUserLoginR.usingStatus, usingStatus);
		}

		// 筛选条件（业务项）

		String userName = sin.getUserName();
		if (StringUtils.isNotBlank(userName)) {
			sql.like(SysUserLoginR.userName, LikeType.ALL, userName);
		}

		LKGenderEnum gender = sin.getGender();
		if (gender != null) {
			if (LKGenderEnum.MALE.equals(gender) || LKGenderEnum.FEMALE.equals(gender)) {
				sql.eq(SysUserLoginR.gender, gender);
			} else {
				sql.neq(SysUserLoginR.gender, LKGenderEnum.MALE);
				sql.neq(SysUserLoginR.gender, LKGenderEnum.FEMALE);
			}
		}

		String loginName = sin.getLoginName();
		if (StringUtils.isNotBlank(loginName)) {
			sql.like(SysUserLoginR.loginName, LikeType.ALL, loginName);
		}

		String cellphone = sin.getCellphone();
		if (StringUtils.isNotBlank(cellphone)) {
			sql.like(SysUserLoginR.cellphone, LikeType.RIGHT, cellphone);
		}

		String email = sin.getEmail();
		if (StringUtils.isNotBlank(email)) {
			sql.like(SysUserLoginR.email, LikeType.ALL, email);
		}

		String startDate = sin.getStartDate();
		if (StringUtils.isNotBlank(startDate)) {
			sql.gte(SysUserLoginR.insertTime, LKDateTimeUtils.toString(LKDateTimeUtils.toDateTime(startDate, LKDateTimeTypeEnum.DATE_ONLY), LKDateTimeTypeEnum.TIMESTAMP_MIN));
		}

		String endDate = sin.getEndDate();
		if (StringUtils.isNotBlank(endDate)) {
			sql.lt(SysUserLoginR.insertTime, LKDateTimeUtils.toString(LKDateTimeUtils.toDateTime(endDate, LKDateTimeTypeEnum.DATE_ONLY).plusDays(1), LKDateTimeTypeEnum.TIMESTAMP_MIN));
		}

		// 排序条件
		sql.addOrders(new Order(SysUserLoginR.id, false));
	}

}
