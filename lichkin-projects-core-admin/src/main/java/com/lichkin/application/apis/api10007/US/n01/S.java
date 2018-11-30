package com.lichkin.application.apis.api10007.US.n01;

import org.springframework.stereotype.Service;

import com.lichkin.framework.beans.impl.LKRequestIDsBean;
import com.lichkin.framework.db.beans.SysAdminLoginR;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.framework.defines.exceptions.LKRuntimeException;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysAdminLoginEntity;
import com.lichkin.springframework.services.LKApiBusUpdateUsingStatusService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service("SysAdminLoginUS01Service")
public class S extends LKApiBusUpdateUsingStatusService<LKRequestIDsBean, SysAdminLoginEntity> {

	@Getter
	@RequiredArgsConstructor
	enum ErrorCodes implements LKCodeEnum {

		SysAdminLogin_only_LOCKED_can_USING(20000),

		;

		private final Integer code;

	}


	@Override
	protected int getIdColumnResId() {
		return SysAdminLoginR.id;
	}


	@Override
	protected void beforeSaveMain(LKRequestIDsBean sin, ApiKeyValues<LKRequestIDsBean> params, SysAdminLoginEntity entity, String id) {
		switch (entity.getUsingStatus()) {
			case LOCKED: {
			}
			break;
			default:
				throw new LKRuntimeException(ErrorCodes.SysAdminLogin_only_LOCKED_can_USING);
		}
	}

}
