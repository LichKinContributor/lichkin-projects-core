package com.lichkin.application.apis.api10007.O.n00;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lichkin.framework.beans.impl.LKRequestIDBean;
import com.lichkin.framework.db.beans.Condition;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysAdminLoginRoleR;
import com.lichkin.framework.db.beans.SysRoleR;
import com.lichkin.framework.db.beans.eq;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.utils.LKBeanUtils;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysAdminLoginEntity;
import com.lichkin.springframework.entities.impl.SysAdminLoginRoleEntity;
import com.lichkin.springframework.entities.impl.SysRoleEntity;
import com.lichkin.springframework.services.LKApiBusGetOneService;

@Service("SysAdminLoginO00Service")
public class S extends LKApiBusGetOneService<LKRequestIDBean, O, SysAdminLoginEntity> {

	@Override
	protected void setOtherValues(SysAdminLoginEntity entity, String id, LKRequestIDBean sin, ApiKeyValues<LKRequestIDBean> params, O out) {
		LKBeanUtils.setStringValues(findListRoleByLoginId(id), out,

				new String[] { "id", "roleName" },

				new String[] { "roleIds", "roleNames" },

				new String[] { LKFrameworkStatics.SPLITOR, "," }

		);
	}


	private List<SysRoleEntity> findListRoleByLoginId(String loginId) {
		QuerySQL sql = new QuerySQL(false, SysRoleEntity.class);

		sql.selectTable(SysRoleEntity.class);
		sql.innerJoin(SysAdminLoginRoleEntity.class,

				new Condition(SysAdminLoginRoleR.roleId, SysRoleR.id),

				new Condition(new eq(SysAdminLoginRoleR.loginId, loginId))

		);

		return dao.getList(sql, SysRoleEntity.class);
	}

}
