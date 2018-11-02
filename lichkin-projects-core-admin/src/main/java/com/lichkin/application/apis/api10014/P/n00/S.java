package com.lichkin.application.apis.api10014.P.n00;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lichkin.application.utils.LKDictUtils;
import com.lichkin.framework.db.beans.Condition;
import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysDeptR;
import com.lichkin.framework.db.beans.SysEmployeeDeptR;
import com.lichkin.framework.db.beans.SysEmployeeR;
import com.lichkin.framework.db.enums.LikeType;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.defines.enums.impl.LKGenderEnum;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.springframework.entities.impl.SysDeptEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeDeptEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeEntity;
import com.lichkin.springframework.services.LKApiBusGetPageService;

@Service("SysEmployeeP00Service")
public class S extends LKApiBusGetPageService<I, O, SysEmployeeEntity> {

	@Override
	protected void initSQL(I sin, String locale, String compId, String loginId, QuerySQL sql) {
		// 主表
		sql.select(SysEmployeeR.id);
		sql.select(SysEmployeeR.insertTime);
		sql.select(SysEmployeeR.userName);
		sql.select(SysEmployeeR.cellphone);
		sql.select(SysEmployeeR.email);
		sql.select(SysEmployeeR.userCard);
		sql.select(SysEmployeeR.jobNumber);
		sql.select(SysEmployeeR.entryDate);

		// 关联表
		sql.innerJoin(SysEmployeeDeptEntity.class, new Condition(SysEmployeeDeptR.employeeId, SysEmployeeR.id));
		sql.innerJoin(SysDeptEntity.class, new Condition(SysDeptR.id, SysEmployeeDeptR.deptId));
		sql.select(SysDeptR.id, "deptId");
		sql.select(SysDeptR.fullName);

		// 字典表
		int i = 0;
		LKDictUtils.usingStatus4Employee(sql, SysEmployeeR.usingStatus, i++);
		LKDictUtils.gender(sql, SysEmployeeR.gender, i++);
		LKDictUtils.degree(sql, SysEmployeeR.degree, i++);
		LKDictUtils.education(sql, SysEmployeeR.education, i++);
		LKDictUtils.maritalStatus(sql, SysEmployeeR.maritalStatus, i++);
		LKDictUtils.nation(sql, SysEmployeeR.nation, i++);
		LKDictUtils.jobTitle(sql, compId, SysEmployeeR.jobTitle, i++);

		// 筛选条件（必填项）
		// 公司ID
		String busCompId = sin.getCompId();
		sql.eq(SysEmployeeR.compId, LKFrameworkStatics.LichKin.equals(compId) && StringUtils.isNotBlank(busCompId) ? busCompId : compId);
		// 在用状态
		LKUsingStatusEnum usingStatus = sin.getUsingStatus();
		if (usingStatus == null) {
			if (LKFrameworkStatics.LichKin.equals(compId)) {
				sql.neq(SysEmployeeR.usingStatus, LKUsingStatusEnum.DEPRECATED);
			} else {
				sql.eq(SysEmployeeR.usingStatus, LKUsingStatusEnum.USING);
			}
		} else {
			sql.eq(SysEmployeeR.usingStatus, usingStatus);
		}

		// 筛选条件（业务项）
		String userName = sin.getUserName();
		if (StringUtils.isNotBlank(userName)) {
			sql.like(SysEmployeeR.userName, LikeType.ALL, userName);
		}

		String cellphone = sin.getCellphone();
		if (StringUtils.isNotBlank(cellphone)) {
			sql.like(SysEmployeeR.cellphone, LikeType.RIGHT, cellphone);
		}

		String email = sin.getEmail();
		if (StringUtils.isNotBlank(email)) {
			sql.like(SysEmployeeR.email, LikeType.ALL, email);
		}

		String userCard = sin.getUserCard();
		if (StringUtils.isNotBlank(userCard)) {
			sql.like(SysEmployeeR.userCard, LikeType.ALL, userCard);
		}

		LKGenderEnum gender = sin.getGender();
		if (gender != null) {
			sql.eq(SysEmployeeR.gender, gender);
		}

		String deptIds = sin.getDeptIds();
		if (StringUtils.isNotBlank(deptIds)) {
			sql.in(SysDeptR.id, deptIds);
		}

		String includes = sin.getIncludes();
		if (StringUtils.isNotBlank(includes)) {
			sql.in(SysEmployeeR.id, includes);
		}

		String excludes = sin.getExcludes();
		if (StringUtils.isNotBlank(excludes)) {
			sql.notIn(SysEmployeeR.id, excludes);
		}

		// 排序条件
		sql.addOrders(new Order(SysEmployeeR.jobNumber), new Order(SysEmployeeR.userName), new Order(SysEmployeeR.id, false));
	}

}
