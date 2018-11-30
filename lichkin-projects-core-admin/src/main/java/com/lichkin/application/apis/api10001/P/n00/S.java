package com.lichkin.application.apis.api10001.P.n00;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lichkin.application.utils.LKDictUtils;
import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysCompR;
import com.lichkin.framework.db.enums.LikeType;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysCompEntity;
import com.lichkin.springframework.services.LKApiBusGetPageService;

@Service("SysCompP00Service")
public class S extends LKApiBusGetPageService<I, O, SysCompEntity> {

	@Override
	protected void initSQL(I sin, ApiKeyValues<I> params, QuerySQL sql) {
		// 主表
		sql.select(SysCompR.id);
		sql.select(SysCompR.insertTime);
		sql.select(SysCompR.compName);
		sql.select(SysCompR.compKey);
		sql.select(SysCompR.token);
		sql.select(SysCompR.abbreviation);
		sql.select(SysCompR.telephone);
		sql.select(SysCompR.email);
		sql.select(SysCompR.address);
		sql.select(SysCompR.website);
		sql.select(SysCompR.linkmanName);
		sql.select(SysCompR.linkmanCellphone);

		// 关联表

		// 字典表
		int i = 0;
		LKDictUtils.usingStatus(sql, SysCompR.usingStatus, i++);

		// 筛选条件（必填项）
//		addConditionId(sql, SysCompR.id, params.getId());
//		addConditionLocale(sql, SysCompR.locale, params.getLocale());
//		addConditionCompId(true, sql, SysCompR.compId, params.getCompId(), params.getBusCompId());
		addConditionUsingStatus(true, params.getCompId(), sql, SysCompR.usingStatus, params.getUsingStatus(), LKUsingStatusEnum.USING);

		// 筛选条件（业务项）
		String compName = sin.getCompName();
		if (StringUtils.isNotBlank(compName)) {
			sql.like(SysCompR.compName, LikeType.ALL, compName);
		}

		String email = sin.getEmail();
		if (StringUtils.isNotBlank(email)) {
			sql.like(SysCompR.email, LikeType.ALL, email);
		}

		String linkmanName = sin.getLinkmanName();
		if (StringUtils.isNotBlank(linkmanName)) {
			sql.like(SysCompR.linkmanName, LikeType.ALL, linkmanName);
		}

		String linkmanCellphone = sin.getLinkmanCellphone();
		if (StringUtils.isNotBlank(linkmanCellphone)) {
			sql.like(SysCompR.linkmanCellphone, LikeType.RIGHT, linkmanCellphone);
		}

		// 排序条件
		sql.addOrders(new Order(SysCompR.id, false));
	}

}
