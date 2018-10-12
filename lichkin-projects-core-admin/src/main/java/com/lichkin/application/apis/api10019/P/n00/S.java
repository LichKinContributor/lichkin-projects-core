package com.lichkin.application.apis.api10019.P.n00;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lichkin.application.utils.LKDictUtils;
import com.lichkin.framework.db.beans.Condition;
import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysUserLoginR;
import com.lichkin.framework.db.beans.SysUserR;
import com.lichkin.framework.db.enums.LikeType;
import com.lichkin.framework.defines.enums.impl.LKDateTimeTypeEnum;
import com.lichkin.framework.defines.enums.impl.LKGenderEnum;
import com.lichkin.framework.utils.LKDateTimeUtils;
import com.lichkin.springframework.entities.impl.SysUserEntity;
import com.lichkin.springframework.entities.impl.SysUserLoginEntity;
import com.lichkin.springframework.services.LKApiBusGetPageService;

@Service("SysUserP00Service")
public class S extends LKApiBusGetPageService<I, O, SysUserEntity> {

	@Override
	protected void initSQL(I sin, QuerySQL sql) {
//		String compId = sin.getCompId();

		// 主表
		sql.select(SysUserR.id);
		sql.select(SysUserR.insertTime);
//		sql.select(SysUserR.usingStatus);// LKUsingStatusEnum
//		sql.select(SysUserR.gender);// LKGenderEnum
		sql.select(SysUserR.birthday);
//		sql.select(SysUserR.userCard);
		sql.select(SysUserR.userName);

		// 关联表
		sql.innerJoin(SysUserLoginEntity.class, new Condition(SysUserLoginR.userId, SysUserR.id));
		sql.select(SysUserLoginR.loginName);
		sql.select(SysUserLoginR.cellphone);
		sql.select(SysUserLoginR.email);
		sql.select(SysUserLoginR.level);

		// 字典表
		int i = 0;
		LKDictUtils.gender(sql, SysUserR.gender, i++);

		// 筛选条件（必填项）
//		sql.eq(SysUserR.compId, compId);
//		sql.eq(SysUserR.usingStatus, LKUsingStatusEnum.USING);

		// 筛选条件（业务项）
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

		String userName = sin.getUserName();
		if (StringUtils.isNotBlank(userName)) {
			sql.like(SysUserR.userName, LikeType.ALL, userName);
		}

		LKGenderEnum gender = sin.getGender();
		if (gender != null) {
			if (LKGenderEnum.MALE.equals(gender) || LKGenderEnum.FEMALE.equals(gender)) {
				sql.eq(SysUserR.gender, gender);
			} else {
				sql.neq(SysUserR.gender, LKGenderEnum.MALE);
				sql.neq(SysUserR.gender, LKGenderEnum.FEMALE);
			}
		}

		final String startDate = sin.getStartDate();
		if (StringUtils.isNotBlank(startDate)) {
			sql.gte(SysUserLoginR.insertTime, LKDateTimeUtils.toString(LKDateTimeUtils.toDate(startDate, LKDateTimeTypeEnum.DATE_ONLY), LKDateTimeTypeEnum.TIMESTAMP_MIN));
		}

		final String endDate = sin.getEndDate();
		if (StringUtils.isNotBlank(endDate)) {
			sql.lte(SysUserLoginR.insertTime, LKDateTimeUtils.toString(LKDateTimeUtils.toDate(endDate + " 23:59:59.999", LKDateTimeTypeEnum.TIMESTAMP), LKDateTimeTypeEnum.TIMESTAMP_MIN));
		}

		// 排序条件
		sql.addOrders(new Order(SysUserR.id, false));
	}

}
