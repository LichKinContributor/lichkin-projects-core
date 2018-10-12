package com.lichkin.application.apis.api10011.U.n00;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichkin.application.services.bus.impl.SysDeptBusService;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.springframework.entities.impl.SysDeptEntity;
import com.lichkin.springframework.services.LKApiBusUpdateService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service("SysDeptU00Service")
public class S extends LKApiBusUpdateService<I, SysDeptEntity> {

	@Autowired
	private SysDeptBusService busService;


	@Getter
	@RequiredArgsConstructor
	enum ErrorCodes implements LKCodeEnum {

		SysDept_EXIST(100000),

		;

		private final Integer code;

	}


	@Override
	public boolean needCheckExist(I sin, SysDeptEntity exist) {
		return !exist.getDeptName().equals(sin.getDeptName());
	}


	@Override
	public List<SysDeptEntity> findExist(I sin, SysDeptEntity exist) {
		return busService.findExist(sin.getId(), exist.getParentCode(), sin.getDeptName());
	}


	@Override
	public LKCodeEnum existErrorCode(I sin) {
		return ErrorCodes.SysDept_EXIST;
	}

}
