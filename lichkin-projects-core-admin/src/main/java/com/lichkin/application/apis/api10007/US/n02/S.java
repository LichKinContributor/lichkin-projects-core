package com.lichkin.application.apis.api10007.US.n02;

import org.springframework.stereotype.Service;

import com.lichkin.framework.beans.impl.LKRequestIDsBean;
import com.lichkin.framework.db.beans.DeleteSQL;
import com.lichkin.framework.db.beans.SysAdminLoginR;
import com.lichkin.framework.db.beans.SysAdminLoginRoleR;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysAdminLoginEntity;
import com.lichkin.springframework.entities.impl.SysAdminLoginRoleEntity;
import com.lichkin.springframework.services.LKApiBusUpdateUsingStatusService;

@Service("SysAdminLoginUS02Service")
public class S extends LKApiBusUpdateUsingStatusService<LKRequestIDsBean, SysAdminLoginEntity> {

	@Override
	protected int getIdColumnResId() {
		return SysAdminLoginR.id;
	}


	@Override
	protected void beforeSaveMain(LKRequestIDsBean sin, ApiKeyValues<LKRequestIDsBean> params, SysAdminLoginEntity entity, String id) {
		DeleteSQL sql = new DeleteSQL(SysAdminLoginRoleEntity.class);
		sql.eq(SysAdminLoginRoleR.loginId, sin.getId());
		dao.delete(sql);
	}

}
