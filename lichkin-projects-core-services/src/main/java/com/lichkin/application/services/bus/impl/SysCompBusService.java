package com.lichkin.application.services.bus.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.Condition;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysCompR;
import com.lichkin.framework.db.beans.eq;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.springframework.entities.impl.SysCompEntity;
import com.lichkin.springframework.services.LKCodeService;
import com.lichkin.springframework.services.LKDBService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
public class SysCompBusService extends LKDBService {

	@Getter
	@RequiredArgsConstructor
	enum ErrorCodes implements LKCodeEnum {

		SysComp_LEVEL_OUT(20000),

		;

		private final Integer code;

	}


	public List<SysCompEntity> findExist(String id, String parentCode, String compName, String compKey, String token) {
		QuerySQL sql = new QuerySQL(false, SysCompEntity.class);

		if (StringUtils.isNotBlank(id)) {
			sql.neq(SysCompR.id, id);
		}

		sql.eq(SysCompR.parentCode, parentCode);
		sql.where(

				new Condition(

						new Condition(null, new eq(SysCompR.compName, compName)),

						new Condition(false, new eq(SysCompR.compKey, compKey)),

						new Condition(false, new eq(SysCompR.token, token))

				)

		);

		return dao.getList(sql, SysCompEntity.class);
	}


	@Autowired
	private LKCodeService codeService;


	public String analysisCompCode(String parentCode) {
		return codeService.analysisCode(SysCompEntity.class, null, parentCode, "compCode", 0, SysCompR.parentCode, SysCompR.compCode, ErrorCodes.SysComp_LEVEL_OUT);
	}

}
