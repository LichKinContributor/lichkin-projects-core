package com.lichkin.application.apis.api10007.P.n00;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lichkin.application.utils.LKDictUtils;
import com.lichkin.framework.db.beans.Condition;
import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysAdminLoginR;
import com.lichkin.framework.db.beans.eq;
import com.lichkin.framework.db.enums.LikeType;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.defines.enums.impl.LKYesNoEnum;
import com.lichkin.springframework.entities.impl.SysAdminLoginEntity;
import com.lichkin.springframework.services.LKApiBusGetPageService;

@Service("SysAdminLoginP00Service")
public class S extends LKApiBusGetPageService<I, O, SysAdminLoginEntity> {

	@Override
	protected void initSQL(I sin, QuerySQL sql) {
		String compId = sin.getCompId();

		// 主表
		sql.select(SysAdminLoginR.id);
//		sql.select(SysAdminLoginR.insertTime);
//		sql.select(SysAdminLoginR.usingStatus);// LKUsingStatusEnum
//		sql.select(SysAdminLoginR.superAdmin);// LKYesNoEnum
//		sql.select(SysAdminLoginR.lockTime);
//		sql.select(SysAdminLoginR.token);
		sql.select(SysAdminLoginR.level);
//		sql.select(SysAdminLoginR.errorTimes);
//		sql.select(SysAdminLoginR.securityCode);
//		sql.select(SysAdminLoginR.pwd);
		sql.select(SysAdminLoginR.email);
//		sql.select(SysAdminLoginR.photo);
//		sql.select(SysAdminLoginR.gender);// LKGenderEnum
		sql.select(SysAdminLoginR.userName);

		// 关联表
//		sql.innerJoin(BusXEntity.class, new Condition(BusXR.y, SysAdminLoginR.z));
//		sql.select(BusXR.w);

		// 字典表
		int i = 0;
		LKDictUtils.usingStatus(sql, SysAdminLoginR.usingStatus, i++);
		LKDictUtils.gender(sql, SysAdminLoginR.gender, i++);

		// 筛选条件（必填项）
		sql.eq(SysAdminLoginR.compId, compId);
//		sql.eq(SysAdminLoginR.usingStatus, LKUsingStatusEnum.USING);
		sql.where(new Condition(

				new Condition(null, new eq(SysAdminLoginR.usingStatus, LKUsingStatusEnum.USING)),

				new Condition(false, new eq(SysAdminLoginR.usingStatus, LKUsingStatusEnum.LOCKED))

		));

		// 筛选条件（业务项）
		sql.eq(SysAdminLoginR.superAdmin, LKYesNoEnum.NO);

		String userName = sin.getUserName();
		if (StringUtils.isNotBlank(userName)) {
			sql.like(SysAdminLoginR.userName, LikeType.ALL, userName);
		}

		String email = sin.getEmail();
		if (StringUtils.isNotBlank(email)) {
			sql.like(SysAdminLoginR.email, LikeType.ALL, email);
		}

		if (sin.getUsingStatus() != null) {
			sql.eq(SysAdminLoginR.usingStatus, sin.getUsingStatus());
		}

		// 排序条件
		sql.addOrders(new Order(SysAdminLoginR.id, false));
	}

}
