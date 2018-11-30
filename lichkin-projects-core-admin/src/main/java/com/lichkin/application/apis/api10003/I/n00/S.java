package com.lichkin.application.apis.api10003.I.n00;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichkin.application.services.bus.impl.SysDictionaryBusService;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.framework.defines.exceptions.LKRuntimeException;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysDictionaryEntity;
import com.lichkin.springframework.services.LKApiBusInsertService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service("SysDictionaryI00Service")
public class S extends LKApiBusInsertService<I, SysDictionaryEntity> {

	@Autowired
	private SysDictionaryBusService busService;


	@Getter
	@RequiredArgsConstructor
	enum ErrorCodes implements LKCodeEnum {

		SysDictionary_EXIST(20000),

		SysDictionary_dict_code_can_not_modify_when_restore(20000),

		;

		private final Integer code;

	}


	@Override
	protected List<SysDictionaryEntity> findExist(I sin, ApiKeyValues<I> params) {
		return busService.findExist(params, sin.getCategoryCode(), sin.getDictCode(), sin.getDictName());
	}


	@Override
	protected boolean forceCheck(I sin, ApiKeyValues<I> params) {
		return false;
	}


	@Override
	protected LKCodeEnum existErrorCode(I sin, ApiKeyValues<I> params) {
		return ErrorCodes.SysDictionary_EXIST;
	}


	@Override
	protected void beforeRestore(I sin, ApiKeyValues<I> params, SysDictionaryEntity entity, SysDictionaryEntity exist) {
		entity.setCategoryCode(exist.getCategoryCode());
		if (!exist.getDictCode().equals(entity.getDictCode())) {
			throw new LKRuntimeException(ErrorCodes.SysDictionary_dict_code_can_not_modify_when_restore).withParam("#dictCode", exist.getDictCode());
		}
	}

}
