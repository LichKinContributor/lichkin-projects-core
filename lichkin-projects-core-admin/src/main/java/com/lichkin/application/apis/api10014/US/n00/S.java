package com.lichkin.application.apis.api10014.US.n00;

import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.SysEmployeeLoginCompR;
import com.lichkin.springframework.entities.impl.SysEmployeeLoginCompEntity;
import com.lichkin.springframework.services.LKApiBusUpdateUsingStatusService;

@Service("SysEmployeeLoginCompUS00Service")
public class S extends LKApiBusUpdateUsingStatusService<I, SysEmployeeLoginCompEntity> {

	@Override
	protected int getIdColumnResId() {
		return SysEmployeeLoginCompR.id;
	}

}
