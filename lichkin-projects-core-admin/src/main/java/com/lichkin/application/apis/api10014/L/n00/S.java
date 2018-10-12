package com.lichkin.application.apis.api10014.L.n00;

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
import com.lichkin.springframework.services.LKApiBusGetListService;

@Service("SysEmployeeLoginCompL00Service")
public class S extends LKApiBusGetListService<I, O, SysEmployeeLoginCompEntity> {

	@Override
	protected void initSQL(I sin, QuerySQL sql) {
		String compId = sin.getCompId();

		// 主表
//		sql.select(SysEmployeeLoginCompR.id);
//		sql.select(SysEmployeeLoginCompR.insertTime);
//		sql.select(SysEmployeeLoginCompR.usingStatus);// LKUsingStatusEnum
//		sql.select(SysEmployeeLoginCompR.entryDate);
//		sql.select(SysEmployeeLoginCompR.jobTitle);
		sql.select(SysEmployeeLoginCompR.jobNumber);
//		sql.select(SysEmployeeLoginCompR.nation);
//		sql.select(SysEmployeeLoginCompR.maritalStatus);
//		sql.select(SysEmployeeLoginCompR.education);
//		sql.select(SysEmployeeLoginCompR.degree);
//		sql.select(SysEmployeeLoginCompR.birthplace);
//		sql.select(SysEmployeeLoginCompR.gender);// LKGenderEnum
//		sql.select(SysEmployeeLoginCompR.birthday);
//		sql.select(SysEmployeeLoginCompR.userCard);
//		sql.select(SysEmployeeLoginCompR.email);
//		sql.select(SysEmployeeLoginCompR.cellphone);
		sql.select(SysEmployeeLoginCompR.userName);
//		sql.select(SysEmployeeLoginCompR.photo);
		sql.select(SysEmployeeLoginCompR.loginId, "id");

		// 关联表
		sql.innerJoin(SysEmployeeLoginDeptEntity.class, new Condition(SysEmployeeLoginDeptR.loginId, SysEmployeeLoginCompR.loginId));
		sql.innerJoin(SysDeptEntity.class, new Condition(SysDeptR.id, SysEmployeeLoginDeptR.deptId));
		sql.select(SysDeptR.id, "deptId");
		sql.select(SysDeptR.deptName);

		// 字典表
		int i = 0;
		LKDictUtils.gender(sql, SysEmployeeLoginCompR.gender, i++);

		// 筛选条件（必填项）
		sql.eq(SysEmployeeLoginCompR.compId, compId);
		sql.eq(SysEmployeeLoginCompR.usingStatus, LKUsingStatusEnum.USING);

		// 筛选条件（业务项）
		final String userName = sin.getUserName();
		if (StringUtils.isNotBlank(userName)) {
			sql.like(SysEmployeeLoginCompR.userName, LikeType.ALL, userName);
		}

		LKGenderEnum gender = sin.getGender();
		if (gender != null) {
			sql.eq(SysEmployeeLoginCompR.gender, gender);
		}

		String includes = sin.getIncludes();
		if (StringUtils.isNotBlank(includes)) {
			sql.in(SysEmployeeLoginCompR.loginId, includes);
		}

		String excludes = sin.getExcludes();
		if (StringUtils.isNotBlank(excludes)) {
			sql.notIn(SysEmployeeLoginCompR.loginId, excludes);
		}

		String deptIds = sin.getDeptIds();
		if (StringUtils.isNotBlank(deptIds)) {
			sql.in(SysDeptR.id, deptIds);
		}

		// 排序条件
		sql.addOrders(new Order(SysEmployeeLoginCompR.id, false));
	}

}
