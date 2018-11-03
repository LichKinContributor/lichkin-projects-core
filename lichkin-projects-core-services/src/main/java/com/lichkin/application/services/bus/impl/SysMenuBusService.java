package com.lichkin.application.services.bus.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysMenuR;
import com.lichkin.framework.defines.beans.impl.LKPageBean;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.framework.defines.exceptions.LKRuntimeException;
import com.lichkin.framework.utils.LKCodeUtils;
import com.lichkin.springframework.entities.impl.SysMenuEntity;
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


	public String analysisMenuCode(String parentCode) {
		QuerySQL sql = new QuerySQL(false, SysMenuEntity.class);
		sql.eq(SysMenuR.parentCode, parentCode);
		sql.setPage(new LKPageBean(1));
		sql.addOrders(new Order(SysMenuR.menuCode, false));
		Page<SysMenuEntity> page = dao.getPage(sql, SysMenuEntity.class);
		if (CollectionUtils.isNotEmpty(page.getContent())) {
			return LKCodeUtils.nextCode(page.getContent().get(0).getMenuCode());
		}
		if (LKCodeUtils.currentLevel(parentCode) == 8) {
			throw new LKRuntimeException(ErrorCodes.SysMenu_LEVEL_OUT);
		}
		return LKCodeUtils.createCode(parentCode);
	}

}
