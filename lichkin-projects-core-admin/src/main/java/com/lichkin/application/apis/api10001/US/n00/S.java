package com.lichkin.application.apis.api10001.US.n00;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichkin.application.services.bus.impl.SysAdminLoginBusService;
import com.lichkin.framework.beans.impl.LKRequestIDsBean;
import com.lichkin.framework.db.beans.SysCompR;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.framework.defines.exceptions.LKRuntimeException;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysAdminLoginEntity;
import com.lichkin.springframework.entities.impl.SysCompEntity;
import com.lichkin.springframework.services.LKApiBusUpdateUsingStatusService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service("SysCompUS00Service")
public class S extends LKApiBusUpdateUsingStatusService<LKRequestIDsBean, SysCompEntity> {

	@Autowired
	private SysAdminLoginBusService busService;


	@Getter
	@RequiredArgsConstructor
	enum ErrorCodes implements LKCodeEnum {

		SysComp_deleted_company_has_assigned_account(20000),

		;

		private final Integer code;

	}


	@Override
	protected int getIdColumnResId() {
		return SysCompR.id;
	}


	@Override
	protected void beforeSaveMain(LKRequestIDsBean cin, ApiKeyValues<LKRequestIDsBean> params, SysCompEntity entity, String id) {
		List<SysAdminLoginEntity> list = busService.findAdminLoginByCompId(id);
		if (CollectionUtils.isNotEmpty(list)) {
			throw new LKRuntimeException(ErrorCodes.SysComp_deleted_company_has_assigned_account);
		}
	}

}
