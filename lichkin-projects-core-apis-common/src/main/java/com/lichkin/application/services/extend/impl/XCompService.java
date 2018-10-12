package com.lichkin.application.services.extend.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lichkin.framework.defines.enums.impl.LKErrorCodesEnum;
import com.lichkin.framework.defines.exceptions.LKRuntimeException;
import com.lichkin.springframework.entities.impl.SysCompEntity;
import com.lichkin.springframework.services.CompService;

@Service
public class XCompService extends CompService {

	@Override
	public SysCompEntity findCompById(boolean throwException, String compId) {
		if (StringUtils.isBlank(compId)) {
			if (throwException) {
				throw new LKRuntimeException(LKErrorCodesEnum.ACCOUNT_INEXIST);
			}
			return null;
		}

		SysCompEntity comp = dao.findOneById(SysCompEntity.class, compId);
		if (comp == null) {
			if (throwException) {
				throw new LKRuntimeException(LKErrorCodesEnum.COMP_INEXIST);
			}
			return null;
		}
		return comp;
	}

}
