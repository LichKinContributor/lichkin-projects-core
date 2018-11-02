package com.lichkin.application.apis.api10003.I.n00;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichkin.application.services.bus.impl.SysDictionaryBusService;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.defines.exceptions.LKRuntimeException;
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

		SysDictionary_EXIST(100000),

		SysDictionary_dict_code_can_not_modify_when_restore(100000),

		;

		private final Integer code;

	}


	@Override
	protected List<SysDictionaryEntity> findExist(I sin, String locale, String compId, String loginId) {
		return busService.findExist(null, compId, sin.getCompId(), getLocale(locale, sin.getLocale()), sin.getCategoryCode(), sin.getDictCode(), sin.getDictName());
	}


	@Override
	protected boolean forceCheck(I sin, String locale, String compId, String loginId) {
		return false;
	}


	@Override
	protected LKCodeEnum existErrorCode(I sin, String locale, String compId, String loginId) {
		return ErrorCodes.SysDictionary_EXIST;
	}


	@Override
	protected void beforeRestore(I sin, String locale, String compId, String loginId, SysDictionaryEntity entity, SysDictionaryEntity exist) {
		entity.setUsingStatus(LKUsingStatusEnum.USING);
		entity.setCompId(exist.getCompId());
		entity.setLocale(exist.getLocale());
		entity.setCategoryCode(exist.getCategoryCode());
		if (!exist.getDictCode().equals(entity.getDictCode())) {
			throw new LKRuntimeException(ErrorCodes.SysDictionary_dict_code_can_not_modify_when_restore).withParam("#dictCode", exist.getDictCode());
		}
	}


	@Override
	protected void beforeAddNew(I sin, String locale, String compId, String loginId, SysDictionaryEntity entity) {
		entity.setCompId(getCompId(compId, sin.getCompId()));
		entity.setLocale(getLocale(locale, sin.getLocale()));
	}

}
