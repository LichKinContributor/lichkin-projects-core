package com.lichkin.application.apis.api10001.U.n00;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.springframework.entities.impl.SysCompEntity;
import com.lichkin.springframework.services.LKApiBusUpdateService;

@Service("SysCompU00Service")
public class S extends LKApiBusUpdateService<I, SysCompEntity> {

	@Override
	public boolean needCheckExist(I sin, SysCompEntity exist) {
		return false;
	}


	@Override
	public List<SysCompEntity> findExist(I sin, SysCompEntity exist) {
		return null;
	}


	@Override
	public LKCodeEnum existErrorCode(I sin) {
		return null;
	}

}
