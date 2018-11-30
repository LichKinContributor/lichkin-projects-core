package com.lichkin.application.services.bus.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.Condition;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysDictionaryR;
import com.lichkin.framework.db.beans.eq;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysDictionaryEntity;
import com.lichkin.springframework.services.LKDBService;

@Service
public class SysDictionaryBusService extends LKDBService {

	public List<SysDictionaryEntity> findExist(ApiKeyValues<?> params, String categoryCode, String dictCode, String dictName) {
		QuerySQL sql = new QuerySQL(false, SysDictionaryEntity.class);

		addConditionId(sql, SysDictionaryR.id, params.getId());
		addConditionLocale(sql, SysDictionaryR.locale, params.getLocale());
		addConditionCompId(true, sql, SysDictionaryR.compId, params.getCompId(), params.getBusCompId());

		sql.eq(SysDictionaryR.categoryCode, categoryCode);

		sql.where(

				new Condition(

						new Condition(new eq(SysDictionaryR.dictCode, dictCode)),

						new Condition(false, new eq(SysDictionaryR.dictName, dictName))

				)

		);

		return dao.getList(sql, SysDictionaryEntity.class);
	}

}
