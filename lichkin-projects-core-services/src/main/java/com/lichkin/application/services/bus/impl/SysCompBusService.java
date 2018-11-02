package com.lichkin.application.services.bus.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.Condition;
import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysCompR;
import com.lichkin.framework.db.beans.eq;
import com.lichkin.framework.defines.beans.impl.LKPageBean;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.framework.defines.exceptions.LKRuntimeException;
import com.lichkin.framework.utils.LKCodeUtils;
import com.lichkin.springframework.entities.impl.SysCompEntity;
import com.lichkin.springframework.services.LKDBService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
public class SysCompBusService extends LKDBService {

	@Getter
	@RequiredArgsConstructor
	enum ErrorCodes implements LKCodeEnum {

		SysComp_LEVEL_OUT(100000),

		;

		private final Integer code;

	}


	public List<SysCompEntity> findExist(String id, String parentCode, String compName, String compKey) {
		QuerySQL sql = new QuerySQL(false, SysCompEntity.class);

		if (StringUtils.isNotBlank(id)) {
			sql.neq(SysCompR.id, id);
		}

		sql.eq(SysCompR.parentCode, parentCode);
		sql.where(

				new Condition(

						new Condition(null, new eq(SysCompR.compName, compName)),

						new Condition(false, new eq(SysCompR.compKey, compKey))

				)

		);

		return dao.getList(sql, SysCompEntity.class);
	}


	public String analysisCompCode(String parentCode) {
		QuerySQL sql = new QuerySQL(false, SysCompEntity.class);
		sql.eq(SysCompR.parentCode, parentCode);
		sql.setPage(new LKPageBean(1));
		sql.addOrders(new Order(SysCompR.compCode, false));
		Page<SysCompEntity> page = dao.getPage(sql, SysCompEntity.class);
		if (CollectionUtils.isNotEmpty(page.getContent())) {
			return LKCodeUtils.nextCode(page.getContent().get(0).getCompCode());
		}
		if (LKCodeUtils.currentLevel(parentCode) == 8) {
			throw new LKRuntimeException(ErrorCodes.SysComp_LEVEL_OUT);
		}
		return LKCodeUtils.createCode(parentCode);
	}

}
