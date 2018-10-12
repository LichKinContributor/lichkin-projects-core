package com.lichkin.application.services.bus.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.Condition;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysDictionaryR;
import com.lichkin.framework.db.beans.eq;
import com.lichkin.springframework.entities.impl.SysDictionaryEntity;
import com.lichkin.springframework.services.LKDBService;

@Service
public class SysDictionaryBusService extends LKDBService {

	public List<SysDictionaryEntity> findExist(String id, String compId, String locale, String categoryCode, String dictName, String dictCode) {
		QuerySQL sql = new QuerySQL(false, SysDictionaryEntity.class);

		if (StringUtils.isNotBlank(id)) {
			sql.neq(SysDictionaryR.id, id);
		}

		sql.eq(SysDictionaryR.compId, compId);
		sql.eq(SysDictionaryR.locale, locale);
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
