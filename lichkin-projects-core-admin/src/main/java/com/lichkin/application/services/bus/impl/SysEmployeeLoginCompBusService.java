package com.lichkin.application.services.bus.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.Condition;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysEmployeeLoginCompR;
import com.lichkin.framework.db.beans.eq;
import com.lichkin.springframework.entities.impl.SysEmployeeLoginCompEntity;
import com.lichkin.springframework.services.LKDBService;

@Service
public class SysEmployeeLoginCompBusService extends LKDBService {

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

}
