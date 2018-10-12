package com.lichkin.application.apis.api10007.US.n00;

import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.SysAdminLoginR;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.springframework.entities.impl.SysAdminLoginEntity;
import com.lichkin.springframework.services.LKApiBusUpdateUsingStatusService;

@Service("SysAdminLoginUS00Service")
public class S extends LKApiBusUpdateUsingStatusService<I, SysAdminLoginEntity> {

	@Override
	protected int getIdColumnResId() {
		return SysAdminLoginR.id;
	}


	@Override
	protected void beforeSaveMainTable(I in, SysAdminLoginEntity entity) {
		if (entity.getUsingStatus().equals(LKUsingStatusEnum.LOCKED)) {
			entity.setLockTime("");
		}
	}

}
