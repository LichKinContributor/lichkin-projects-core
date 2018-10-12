package com.lichkin.application.apis.api10014.O.n00;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.Condition;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysDeptR;
import com.lichkin.framework.db.beans.SysEmployeeLoginDeptR;
import com.lichkin.framework.db.beans.eq;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.utils.LKBeanUtils;
import com.lichkin.springframework.entities.impl.SysDeptEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeLoginCompEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeLoginDeptEntity;
import com.lichkin.springframework.services.LKApiBusGetOneService;

@Service("SysEmployeeLoginCompO00Service")
public class S extends LKApiBusGetOneService<I, O, SysEmployeeLoginCompEntity> {

	@Override
	protected void setOtherValues(SysEmployeeLoginCompEntity entity, I in, O out) {
		LKBeanUtils.setStringValues(findListDeptByEmployeeLoginId(entity.getLoginId(), entity.getCompId()), out,

				new String[] { "id", },

				new String[] { "deptId" },

				new String[] { LKFrameworkStatics.SPLITOR }

		);
	}


	private List<SysDeptEntity> findListDeptByEmployeeLoginId(String employeeLoginId, String compId) {
		QuerySQL sql = new QuerySQL(false, SysDeptEntity.class);

		sql.innerJoin(SysEmployeeLoginDeptEntity.class,

				new Condition(SysEmployeeLoginDeptR.deptId, SysDeptR.id),

				new Condition(new eq(SysEmployeeLoginDeptR.loginId, employeeLoginId))

		);

		sql.eq(SysDeptR.compId, compId);

		return dao.getList(sql, SysDeptEntity.class);
	}

}
