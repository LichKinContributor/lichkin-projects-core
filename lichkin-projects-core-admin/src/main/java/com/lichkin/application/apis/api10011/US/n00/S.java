package com.lichkin.application.apis.api10011.US.n00;

import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.SysDeptR;
import com.lichkin.springframework.entities.impl.SysDeptEntity;
import com.lichkin.springframework.services.LKApiBusUpdateUsingStatusService;

@Service("SysDeptUS00Service")
public class S extends LKApiBusUpdateUsingStatusService<I, SysDeptEntity> {

	@Override
	protected int getIdColumnResId() {
		return SysDeptR.id;
	}

}
