package com.lichkin.application.apis.api10001.US.n00;

import org.springframework.stereotype.Service;

import com.lichkin.framework.beans.impl.LKRequestIDsBean;
import com.lichkin.framework.db.beans.SysCompR;
import com.lichkin.springframework.entities.impl.SysCompEntity;
import com.lichkin.springframework.services.LKApiBusUpdateUsingStatusService;

@Service("SysCompUS00Service")
public class S extends LKApiBusUpdateUsingStatusService<LKRequestIDsBean, SysCompEntity> {

	@Override
	protected int getIdColumnResId() {
		return SysCompR.id;
	}

}
