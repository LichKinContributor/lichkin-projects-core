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

		SysDictionary_EXIST(100000),

		;

		private final Integer code;

	}


	@Override
	public boolean needCheckExist(I sin, SysDictionaryEntity exist) {
		return !exist.getDictName().equals(sin.getDictName());
	}


	@Override
	public List<SysDictionaryEntity> findExist(I sin, SysDictionaryEntity exist) {
		return busService.findExist(sin.getId(), sin.getCompId(), sin.getLocale(), exist.getCategoryCode(), sin.getDictName(), exist.getDictCode());
	}


	@Override
	public LKCodeEnum existErrorCode(I sin) {
		return ErrorCodes.SysDictionary_EXIST;
	}

}
