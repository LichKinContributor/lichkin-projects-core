package com.lichkin.application.services.bus.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.DeleteSQL;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysRoleMenuR;
import com.lichkin.framework.db.beans.SysRoleR;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.springframework.entities.impl.SysRoleEntity;
import com.lichkin.springframework.entities.impl.SysRoleMenuEntity;
import com.lichkin.springframework.services.LKDBService;

@Service
public class SysRoleBusService extends LKDBService {

	public List<SysRoleEntity> findExist(String id, String compId, String busCompId, String roleName) {
		QuerySQL sql = new QuerySQL(false, SysRoleEntity.class);

		if (StringUtils.isNotBlank(id)) {
			sql.neq(SysRoleR.id, id);
		}

		addConditionCompId(true, sql, SysRoleR.compId, compId, busCompId);

		sql.eq(SysRoleR.roleName, roleName);

		return dao.getList(sql, SysRoleEntity.class);
	}


	public void clearRoleMenu(String id) {
		DeleteSQL sql = new DeleteSQL(SysRoleMenuEntity.class);

		sql.eq(SysRoleMenuR.roleId, id);

		dao.delete(sql);
	}


	public void addRoleMenu(String id, String menuIdsStr) {
		final String[] menuIds = menuIdsStr.split(LKFrameworkStatics.SPLITOR);
		if (ArrayUtils.isNotEmpty(menuIds)) {
			List<SysRoleMenuEntity> list = new ArrayList<>(menuIds.length);
			for (final String menuId : menuIds) {
				list.add(new SysRoleMenuEntity(id, menuId));
			}
			dao.persistList(list);
		}
	}

}
