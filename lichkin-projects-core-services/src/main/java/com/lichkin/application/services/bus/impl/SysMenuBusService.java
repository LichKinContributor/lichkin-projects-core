package com.lichkin.application.services.bus.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.SysMenuR;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.springframework.entities.impl.SysMenuEntity;
import com.lichkin.springframework.services.LKCodeService;
import com.lichkin.springframework.services.LKDBService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
public class SysMenuBusService extends LKDBService {

	@Getter
	@RequiredArgsConstructor
	enum ErrorCodes implements LKCodeEnum {

		SysMenu_LEVEL_OUT(20000),

		;

		private final Integer code;

	}


	public String analysisUrl(String url) {
		return StringUtils.trimToEmpty(url);
	}


	@Autowired
	private LKCodeService codeService;


	public String analysisMenuCode(String parentCode) {
		return codeService.analysisCode(SysMenuEntity.class, null, parentCode, "menuCode", 0, SysMenuR.parentCode, SysMenuR.menuCode, ErrorCodes.SysMenu_LEVEL_OUT);
	}

}
