package com.lichkin.application.services.bus.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.Condition;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysAdminLoginR;
import com.lichkin.framework.db.beans.SysAdminLoginRoleR;
import com.lichkin.framework.db.beans.eq;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.utils.security.md5.LKMD5Encrypter;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysAdminLoginEntity;
import com.lichkin.springframework.entities.impl.SysAdminLoginRoleEntity;
import com.lichkin.springframework.services.LKDBService;

@Service
public class SysAdminLoginBusService extends LKDBService {

	public List<SysAdminLoginEntity> findExist(ApiKeyValues<?> params, String email) {
		QuerySQL sql = new QuerySQL(false, SysAdminLoginEntity.class);

		addConditionId(sql, SysAdminLoginR.id, params.getId());
//		addConditionLocale(sql, SysAdminLoginR.locale, params.getLocale());
//		addConditionCompId(true, sql, SysAdminLoginR.compId, params.getCompId(), params.getBusCompId());
//		addConditionUsingStatus(true, params.getCompId(), sql, SysAdminLoginR.usingStatus, params.getUsingStatus(), LKUsingStatusEnum.USING);

		if (LKFrameworkStatics.LichKin.equals(params.getCompId())) {
			sql.where(

					new Condition(new eq(SysAdminLoginR.email, email)),

					new Condition(false,

							new Condition(null, new eq(SysAdminLoginR.compId, params.getBusCompId())),

							new Condition(true, new eq(SysAdminLoginR.superAdmin, Boolean.TRUE))

					)

			);
		} else {
			sql.eq(SysAdminLoginR.email, email);
		}

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


	@Value("${com.lichkin.api.admin.login.defaultPwd:88888888}")
	private String defaultPwd;


	public String analysisPwd() {
		return LKMD5Encrypter.encrypt(LKMD5Encrypter.encrypt(defaultPwd));
	}


	public Boolean analysisSuperAdmin(String compId) {
		return LKFrameworkStatics.LichKin.equals(compId);
	}


	public String analysisEmail(String email, String compId, String compKey) {
		return LKFrameworkStatics.LichKin.equals(compId) ? email : (email.contains("@") ? email : email + "@" + compKey);
	}

}
