package com.lichkin.application.apis.api10011.S.n00;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysDeptR;
import com.lichkin.framework.db.enums.LikeType;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.utils.LKCodeUtils;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysDeptEntity;
import com.lichkin.springframework.services.LKApiBusGetListService;

@Service("SysDeptS00Service")
public class S extends LKApiBusGetListService<I, SysDeptEntity, SysDeptEntity> {

	@Override
	protected void initSQL(I sin, ApiKeyValues<I> params, QuerySQL sql) {
		// 主表
		sql.eq(SysDeptR.usingStatus, LKUsingStatusEnum.USING);

		// 关联表

		// 字典表
//		int i = 0;

		// 筛选条件（必填项）
//		addConditionId(sql, SysDeptR.id, params.getId());
//		addConditionLocale(sql, SysDeptR.locale, params.getLocale());
		addConditionCompId(true, sql, SysDeptR.compId, params.getCompId(), params.getBusCompId());
		addConditionUsingStatus(true, params.getCompId(), sql, SysDeptR.usingStatus, params.getUsingStatus(), LKUsingStatusEnum.STAND_BY, LKUsingStatusEnum.USING);

		// 筛选条件（业务项）
		if (StringUtils.isNotBlank(sin.getDeptName())) {
			sql.like(SysDeptR.deptName, LikeType.ALL, sin.getDeptName());
		}

		// 排序条件
		sql.addOrders(new Order(SysDeptR.deptCode));
	}


	@Override
	protected List<SysDeptEntity> afterQuery(I sin, ApiKeyValues<I> params, List<SysDeptEntity> list) {
		if (StringUtils.isNotBlank(sin.getDeptName())) {
			List<String> codeList = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				codeList.add(list.get(i).getDeptCode());
			}

			codeList.addAll(LKCodeUtils.parentsCode(codeList, false));

			// 主表
			QuerySQL sql = new QuerySQL(false, SysDeptEntity.class, true);

			// 关联表

			// 字典表
//			int i = 0;

			// 筛选条件（必填项）
//			addConditionId(sql, SysDeptR.id, params.getId());
//			addConditionLocale(sql, SysDeptR.locale, params.getLocale());
			addConditionCompId(true, sql, SysDeptR.compId, params.getCompId(), params.getBusCompId());
			addConditionUsingStatus(true, params.getCompId(), sql, SysDeptR.usingStatus, params.getUsingStatus(), LKUsingStatusEnum.STAND_BY, LKUsingStatusEnum.USING);

			// 筛选条件（业务项）
			sql.in(SysDeptR.deptCode, codeList);

			// 排序条件
			sql.addOrders(new Order(SysDeptR.deptCode));

			return dao.getList(sql, SysDeptEntity.class);
		}
		return list;
	}

}
