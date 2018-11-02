package com.lichkin.application.apis.api10014.US.n00;

import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.SysEmployeeR;
import com.lichkin.springframework.entities.impl.SysEmployeeEntity;
import com.lichkin.springframework.services.LKApiBusUpdateUsingStatusService;

@Service("SysEmployeeUS00Service")
public class S extends LKApiBusUpdateUsingStatusService<I, SysEmployeeEntity> {

	@Override
	protected int getIdColumnResId() {
		return SysEmployeeR.id;
	}

}
