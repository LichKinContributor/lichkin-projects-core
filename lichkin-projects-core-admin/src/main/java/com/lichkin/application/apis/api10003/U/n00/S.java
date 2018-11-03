package com.lichkin.application.apis.api10003.U.n00;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichkin.application.services.bus.impl.SysDictionaryBusService;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.springframework.entities.impl.SysDictionaryEntity;
import com.lichkin.springframework.services.LKApiBusUpdateService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service("SysDictionaryU00Service")
public class S extends LKApiBusUpdateService<I, SysDictionaryEntity> {

	@Autowired
	private SysDictionaryBusService busService;


	@Getter
	@RequiredArgsConstructor
	enum ErrorCodes implements LKCodeEnum {

		SysDictionary_EXIST(20000),

		;

		private final Integer code;

	}


	@Override
	protected boolean needCheckExist(I sin, String locale, String compId, String loginId, SysDictionaryEntity entity, String id) {
		if (!entity.getDictName().equals(sin.getDictName())) {
			return true;
		}
		return false;
	}


	@Override
	protected List<SysDictionaryEntity> findExist(I sin, String locale, String compId, String loginId, SysDictionaryEntity entity, String id) {
		return busService.findExist(id, compId, null, entity.getLocale(), entity.getCategoryCode(), entity.getDictCode(), sin.getDictName());
	}


	@Override
	protected LKCodeEnum existErrorCode(I sin, String locale, String compId, String loginId) {
		return ErrorCodes.SysDictionary_EXIST;
	}

}
