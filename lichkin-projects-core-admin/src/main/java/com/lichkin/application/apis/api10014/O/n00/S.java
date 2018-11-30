package com.lichkin.application.apis.api10014.O.n00;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.Condition;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysDeptR;
import com.lichkin.framework.db.beans.SysEmployeeDeptR;
import com.lichkin.framework.db.beans.eq;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.utils.LKBeanUtils;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysDeptEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeDeptEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeEntity;
import com.lichkin.springframework.services.LKApiBusGetOneService;

@Service("SysEmployeeO00Service")
public class S extends LKApiBusGetOneService<I, O, SysEmployeeEntity> {

	@Override
	protected void setOtherValues(SysEmployeeEntity entity, String id, I sin, ApiKeyValues<I> params, O out) {
		LKBeanUtils.setStringValues(findListDeptByEmployeeLoginId(params.getCompId(), id), out,

				new String[] { "id", },

				new String[] { "deptId" },

				new String[] { LKFrameworkStatics.SPLITOR }

		);
	}


	private List<SysDeptEntity> findListDeptByEmployeeLoginId(String compId, String employeeId) {
		QuerySQL sql = new QuerySQL(false, SysDeptEntity.class);

		sql.innerJoin(SysEmployeeDeptEntity.class,

				new Condition(SysEmployeeDeptR.deptId, SysDeptR.id),

				new Condition(new eq(SysEmployeeDeptR.employeeId, employeeId))

		);

		sql.eq(SysDeptR.compId, compId);

		return dao.getList(sql, SysDeptEntity.class);
	}

}
