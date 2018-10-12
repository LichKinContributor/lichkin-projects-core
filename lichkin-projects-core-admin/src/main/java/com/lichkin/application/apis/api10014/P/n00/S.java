package com.lichkin.application.apis.api10014.P.n00;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lichkin.application.utils.LKDictUtils;
import com.lichkin.framework.db.beans.Condition;
import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysDeptR;
import com.lichkin.framework.db.beans.SysEmployeeLoginCompR;
import com.lichkin.framework.db.beans.SysEmployeeLoginDeptR;
import com.lichkin.framework.db.enums.LikeType;
import com.lichkin.framework.defines.enums.impl.LKGenderEnum;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.springframework.entities.impl.SysDeptEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeLoginCompEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeLoginDeptEntity;
import com.lichkin.springframework.services.LKApiBusGetPageService;

@Service("SysEmployeeLoginCompP00Service")
public class S extends LKApiBusGetPageService<I, O, SysEmployeeLoginCompEntity> {

	@Override
	protected void initSQL(I sin, QuerySQL sql) {
		String compId = sin.getCompId();

		// 主表
		sql.select(SysEmployeeLoginCompR.id);
//		sql.select(SysEmployeeLoginCompR.insertTime);
//		sql.select(SysEmployeeLoginCompR.usingStatus);// LKUsingStatusEnum
		sql.select(SysEmployeeLoginCompR.entryDate);
//		sql.select(SysEmployeeLoginCompR.jobTitle);
		sql.select(SysEmployeeLoginCompR.jobNumber);
//		sql.select(SysEmployeeLoginCompR.nation);
//		sql.select(SysEmployeeLoginCompR.maritalStatus);
//		sql.select(SysEmployeeLoginCompR.education);
//		sql.select(SysEmployeeLoginCompR.degree);
//		sql.select(SysEmployeeLoginCompR.birthplace);
//		sql.select(SysEmployeeLoginCompR.gender);// LKGenderEnum
//		sql.select(SysEmployeeLoginCompR.birthday);
		sql.select(SysEmployeeLoginCompR.userCard);
		sql.select(SysEmployeeLoginCompR.email);
		sql.select(SysEmployeeLoginCompR.cellphone);
		sql.select(SysEmployeeLoginCompR.userName);
//		sql.select(SysEmployeeLoginCompR.photo);
//		sql.select(SysEmployeeLoginCompR.loginId);

		// 关联表
		sql.innerJoin(SysEmployeeLoginDeptEntity.class, new Condition(SysEmployeeLoginDeptR.loginId, SysEmployeeLoginCompR.loginId));
		sql.innerJoin(SysDeptEntity.class, new Condition(SysDeptR.id, SysEmployeeLoginDeptR.deptId));
		sql.select(SysDeptR.deptName);

		// 字典表
		int i = 0;
		LKDictUtils.usingStatus4Employee(sql, SysEmployeeLoginCompR.usingStatus, i++);
		LKDictUtils.jobTitle(sql, compId, SysEmployeeLoginCompR.jobTitle, i++);
		LKDictUtils.nation(sql, SysEmployeeLoginCompR.nation, i++);
		LKDictUtils.maritalStatus(sql, SysEmployeeLoginCompR.maritalStatus, i++);
		LKDictUtils.education(sql, SysEmployeeLoginCompR.education, i++);
		LKDictUtils.degree(sql, SysEmployeeLoginCompR.degree, i++);
		LKDictUtils.gender(sql, SysEmployeeLoginCompR.gender, i++);

		// 筛选条件（必填项）
		sql.eq(SysEmployeeLoginCompR.compId, compId);
		sql.neq(SysEmployeeLoginCompR.usingStatus, LKUsingStatusEnum.DEPRECATED);

		// 筛选条件（业务项）
		String userName = sin.getUserName();
		if (StringUtils.isNotBlank(userName)) {
			sql.like(SysEmployeeLoginCompR.userName, LikeType.ALL, userName);
		}

		String userCard = sin.getUserCard();
		if (StringUtils.isNotBlank(userCard)) {
			sql.like(SysEmployeeLoginCompR.userCard, LikeType.ALL, userCard);
		}

		String cellphone = sin.getCellphone();
		if (StringUtils.isNotBlank(cellphone)) {
			sql.like(SysEmployeeLoginCompR.cellphone, LikeType.RIGHT, cellphone);
		}

		String email = sin.getEmail();
		if (StringUtils.isNotBlank(email)) {
			sql.like(SysEmployeeLoginCompR.email, LikeType.ALL, email);
		}

		LKGenderEnum gender = sin.getGender();
		if (gender != null) {
			sql.eq(SysEmployeeLoginCompR.gender, gender);
		}

		LKUsingStatusEnum usingStatus = sin.getUsingStatus();
		if (usingStatus != null) {
			sql.eq(SysEmployeeLoginCompR.usingStatus, usingStatus);
		}

		// 排序条件
		sql.addOrders(new Order(SysEmployeeLoginCompR.jobNumber), new Order(SysEmployeeLoginCompR.userName), new Order(SysEmployeeLoginCompR.id, false));
	}

}
