package com.lichkin.application.services.bus.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.DeleteSQL;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysRoleMenuR;
import com.lichkin.framework.db.beans.SysRoleR;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysRoleEntity;
import com.lichkin.springframework.entities.impl.SysRoleMenuEntity;
import com.lichkin.springframework.services.LKDBService;

@Service
public class SysRoleBusService extends LKDBService {

	public List<SysRoleEntity> findExist(ApiKeyValues<?> params, String roleName) {
		QuerySQL sql = new QuerySQL(false, SysRoleEntity.class);

		addConditionId(sql, SysRoleR.id, params.getId());
//		addConditionLocale(sql, SysRoleR.locale, params.getLocale());
		addConditionCompId(true, sql, SysRoleR.compId, params.getCompId(), params.getBusCompId());
//		addConditionUsingStatus(true, params.getCompId(), sql, SysRoleR.usingStatus, params.getUsingStatus(), LKUsingStatusEnum.USING);

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
