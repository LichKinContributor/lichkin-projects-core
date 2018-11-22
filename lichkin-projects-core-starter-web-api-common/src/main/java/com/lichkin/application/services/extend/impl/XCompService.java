package com.lichkin.application.services.extend.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysAdminLoginR;
import com.lichkin.framework.db.beans.SysCompR;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.defines.exceptions.LKRuntimeException;
import com.lichkin.springframework.entities.impl.SysCompEntity;
import com.lichkin.springframework.services.LKDBService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
public class XCompService extends LKDBService {

	@Getter
	@RequiredArgsConstructor
	enum ErrorCodes implements LKCodeEnum {

		/** 公司信息有误 */
		INVALIDED_COMP_TOKEN(29002),

		/** 没有所属公司 */
		COMP_INEXIST(29007),

		;

		private final Integer code;

	}


	public SysCompEntity findCompByToken(String compToken) {
		if (StringUtils.isBlank(compToken)) {
			throw new LKRuntimeException(ErrorCodes.INVALIDED_COMP_TOKEN);
		}

		QuerySQL sql = new QuerySQL(SysCompEntity.class);

		sql.neq(SysAdminLoginR.usingStatus, LKUsingStatusEnum.DEPRECATED);
		sql.eq(SysCompR.token, compToken);

		SysCompEntity comp = dao.getOne(sql, SysCompEntity.class);
		if (comp == null) {
			throw new LKRuntimeException(ErrorCodes.COMP_INEXIST);
		}
		return comp;
	}

}
