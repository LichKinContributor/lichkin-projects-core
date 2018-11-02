package com.lichkin.application.apis.api10003.US.n00;

import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.SysDictionaryR;
import com.lichkin.springframework.entities.impl.SysDictionaryEntity;
import com.lichkin.springframework.services.LKApiBusUpdateUsingStatusService;

@Service("SysDictionaryUS00Service")
public class S extends LKApiBusUpdateUsingStatusService<I, SysDictionaryEntity> {

	@Override
	protected int getIdColumnResId() {
		return SysDictionaryR.id;
	}

}
