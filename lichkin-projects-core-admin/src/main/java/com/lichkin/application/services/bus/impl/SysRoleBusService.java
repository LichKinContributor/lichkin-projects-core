package com.lichkin.application.services.bus.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysRoleMenuR;
import com.lichkin.framework.db.beans.SysRoleR;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.springframework.entities.impl.SysRoleEntity;
import com.lichkin.springframework.entities.impl.SysRoleMenuEntity;
import com.lichkin.springframework.services.LKDBService;

@Service
public class SysRoleBusService extends LKDBService {

	public List<SysRoleEntity> findExist(String id, String compId, String roleName) {
		QuerySQL sql = new QuerySQL(false, SysRoleEntity.class);

		if (StringUtils.isNotBlank(id)) {
			sql.neq(SysRoleR.id, id);
		}

		sql.eq(SysRoleR.compId, compId);

		sql.eq(SysRoleR.roleName, roleName);

		return dao.getList(sql, SysRoleEntity.class);
	}


	public void clearRoleMenu(String id) {
		QuerySQL sql = new QuerySQL(false, SysRoleMenuEntity.class);

		sql.eq(SysRoleMenuR.roleId, id);

		List<SysRoleMenuEntity> listSysRoleMenu = dao.getList(sql, SysRoleMenuEntity.class);

		if (CollectionUtils.isNotEmpty(listSysRoleMenu)) {
			dao.removeList(listSysRoleMenu);
		}
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
