package com.lichkin.application.apis.api10003.I.n00;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichkin.application.services.bus.impl.SysDictionaryBusService;
import com.lichkin.framework.defines.enums.LKCodeEnum;
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

		;

		private final Integer code;

	}


	@Override
	public boolean needCheckExist(I sin) {
		return true;
	}


	@Override
	public List<SysDictionaryEntity> findExist(I sin) {
		return busService.findExist(null, sin.getCompId(), sin.getLocale(), sin.getCategoryCode(), sin.getDictName(), sin.getDictCode());
	}


	@Override
	public LKCodeEnum existErrorCode(I sin) {
		return ErrorCodes.SysDictionary_EXIST;
	}


	@Override
	protected void beforeSaveMainTable(I sin, SysDictionaryEntity entity, SysDictionaryEntity exist) {
		if (exist != null) {
			entity.setDictCode(exist.getDictCode());
		}
	}

}
