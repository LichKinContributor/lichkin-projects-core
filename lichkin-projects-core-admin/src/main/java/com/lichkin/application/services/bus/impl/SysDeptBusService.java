package com.lichkin.application.services.bus.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysDeptR;
import com.lichkin.springframework.entities.impl.SysDeptEntity;
import com.lichkin.springframework.services.LKDBService;

@Service
public class SysDeptBusService extends LKDBService {

	public List<SysDeptEntity> findExist(String id, String parentCode, String deptName) {
		QuerySQL sql = new QuerySQL(false, SysDeptEntity.class);

		if (StringUtils.isNotBlank(id)) {
			sql.neq(SysDeptR.id, id);
		}

		sql.eq(SysDeptR.parentCode, parentCode);
		sql.eq(SysDeptR.deptName, deptName);

		return dao.getList(sql, SysDeptEntity.class);
	}

}
