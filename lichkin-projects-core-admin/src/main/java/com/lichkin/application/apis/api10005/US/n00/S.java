package com.lichkin.application.apis.api10005.US.n00;

import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.SysRoleR;
import com.lichkin.springframework.entities.impl.SysRoleEntity;
import com.lichkin.springframework.services.LKApiBusUpdateUsingStatusService;

@Service("SysRoleUS00Service")
public class S extends LKApiBusUpdateUsingStatusService<I, SysRoleEntity> {

	@Override
	protected int getIdColumnResId() {
		return SysRoleR.id;
	}

}
