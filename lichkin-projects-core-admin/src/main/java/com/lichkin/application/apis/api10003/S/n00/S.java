package com.lichkin.application.apis.api10003.S.n00;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysDictionaryEntity;
import com.lichkin.springframework.services.LKApiBusService;
import com.lichkin.springframework.services.LKApiVoidService;

@Service("SysDictionaryS00Service")
public class S extends LKApiBusService<I, Void, SysDictionaryEntity> implements LKApiVoidService<I> {

	@Transactional
	@Override
	public void handle(I sin, ApiKeyValues<I> params) throws LKException {
		dao.deleteOneOrMoreById(SysDictionaryEntity.class, sin.getId());
	}

}
