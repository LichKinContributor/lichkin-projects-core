package com.lichkin.application.services.bus.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.Condition;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysAdminLoginR;
import com.lichkin.framework.db.beans.SysAdminLoginRoleR;
import com.lichkin.framework.db.beans.eq;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.defines.enums.impl.LKYesNoEnum;
import com.lichkin.springframework.entities.impl.SysAdminLoginEntity;
import com.lichkin.springframework.entities.impl.SysAdminLoginRoleEntity;
import com.lichkin.springframework.services.LKDBService;

@Service
public class SysAdminLoginBusService extends LKDBService {

	public List<SysAdminLoginEntity> findExist(String id, String email) {
		QuerySQL sql = new QuerySQL(false, SysAdminLoginEntity.class);

		if (StringUtils.isNotBlank(id)) {
			sql.neq(SysAdminLoginR.id, id);
		}

		sql.eq(SysAdminLoginR.email, email);

		return dao.getList(sql, SysAdminLoginEntity.class);
	}


	public List<SysAdminLoginEntity> findExistCompAdmin(String id, String email, String compId) {
		QuerySQL sql = new QuerySQL(false, SysAdminLoginEntity.class);

		if (StringUtils.isNotBlank(id)) {
			sql.neq(SysAdminLoginR.id, id);
		}

		sql.where(

				new Condition(new eq(SysAdminLoginR.email, email)),

				new Condition(false,

						new Condition(null, new eq(SysAdminLoginR.compId, compId)),

						new Condition(true, new eq(SysAdminLoginR.superAdmin, LKYesNoEnum.YES))

				)

		);

		return dao.getList(sql, SysAdminLoginEntity.class);
	}


	public void clearAdminLoginRole(String id) {
		QuerySQL sql = new QuerySQL(false, SysAdminLoginRoleEntity.class);

		sql.eq(SysAdminLoginRoleR.loginId, id);

		List<SysAdminLoginRoleEntity> listSysAdminLoginRole = dao.getList(sql, SysAdminLoginRoleEntity.class);

		if (CollectionUtils.isNotEmpty(listSysAdminLoginRole)) {
			dao.removeList(listSysAdminLoginRole);
		}
	}


	public void addAdminLoginRole(String id, String roleIdsStr) {
		final String[] roleIds = roleIdsStr.split(LKFrameworkStatics.SPLITOR);
		if (ArrayUtils.isNotEmpty(roleIds)) {
			List<SysAdminLoginRoleEntity> list = new ArrayList<>(roleIds.length);
			for (final String roleId : roleIds) {
				list.add(new SysAdminLoginRoleEntity(id, roleId));
			}
			dao.persistList(list);
		}
	}

}
