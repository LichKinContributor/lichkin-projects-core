package com.lichkin.application.services.bus.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.Condition;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysDeptR;
import com.lichkin.framework.db.beans.SysEmployeeLoginCompR;
import com.lichkin.framework.db.beans.SysEmployeeLoginDeptR;
import com.lichkin.framework.db.beans.SysEmployeeLoginR;
import com.lichkin.framework.db.beans.eq;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.springframework.entities.impl.SysDeptEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeLoginCompEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeLoginDeptEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeLoginEntity;
import com.lichkin.springframework.services.LKDBService;

@Service
public class SysEmployeeBusService extends LKDBService {

	public List<SysEmployeeLoginCompEntity> findExist(String id, String compId, String cellphone, String email, String userCard, String jobNumber) {
		QuerySQL sql = new QuerySQL(false, SysEmployeeLoginCompEntity.class);

		if (StringUtils.isNotBlank(id)) {
			sql.neq(SysEmployeeLoginCompR.id, id);
		}

		sql.eq(SysEmployeeLoginCompR.compId, compId);

		sql.where(new Condition(true,

				new Condition(false, new eq(SysEmployeeLoginCompR.cellphone, cellphone)),

				new Condition(false, new eq(SysEmployeeLoginCompR.email, email)),

				new Condition(false, new eq(SysEmployeeLoginCompR.userCard, userCard)),

				new Condition(false, new eq(SysEmployeeLoginCompR.jobNumber, jobNumber))

		));

		return dao.getList(sql, SysEmployeeLoginCompEntity.class);
	}


	public SysEmployeeLoginEntity findEmployeeLogin(String cellphone) {
		QuerySQL sqlObj = new QuerySQL(false, SysEmployeeLoginEntity.class);
		sqlObj.eq(SysEmployeeLoginR.cellphone, cellphone);
		return dao.getOne(sqlObj, SysEmployeeLoginEntity.class);
	}


	public void clearEmployeeLoginDept(String id, String compId) {
		QuerySQL sql = new QuerySQL(false, SysEmployeeLoginDeptEntity.class);

		sql.innerJoin(SysDeptEntity.class,

				new Condition(SysEmployeeLoginDeptR.deptId, SysDeptR.id),

				new Condition(new eq(SysEmployeeLoginDeptR.loginId, id)),

				new Condition(new eq(SysDeptR.compId, compId))

		);

		List<SysEmployeeLoginDeptEntity> listSysEmployeeLoginDept = dao.getList(sql, SysEmployeeLoginDeptEntity.class);
		if (CollectionUtils.isNotEmpty(listSysEmployeeLoginDept)) {
			dao.removeList(listSysEmployeeLoginDept);
		}
	}


	public void addEmployeeLoginDept(String id, final String deptId) {
		if (StringUtils.isNotBlank(deptId)) {
			String[] deptIdAry = deptId.split(LKFrameworkStatics.SPLITOR);
			final List<SysEmployeeLoginDeptEntity> loginDeptList = new ArrayList<>();
			for (String element : deptIdAry) {
				SysEmployeeLoginDeptEntity loginDept = new SysEmployeeLoginDeptEntity();
				loginDept.setLoginId(id);
				loginDept.setDeptId(element);
				loginDeptList.add(loginDept);
			}
			dao.persistList(loginDeptList);
		}
	}

}
