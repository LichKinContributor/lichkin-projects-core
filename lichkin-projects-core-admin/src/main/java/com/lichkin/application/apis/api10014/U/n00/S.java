package com.lichkin.application.apis.api10014.U.n00;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichkin.application.services.bus.impl.SysEmployeeBusService;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.springframework.entities.impl.SysEmployeeLoginCompEntity;
import com.lichkin.springframework.services.LKApiBusUpdateService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service("SysEmployeeLoginCompU00Service")
public class S extends LKApiBusUpdateService<I, SysEmployeeLoginCompEntity> {

	@Autowired
	private SysEmployeeBusService busService;


	@Getter
	@RequiredArgsConstructor
	enum ErrorCodes implements LKCodeEnum {

		SysEmployeeLoginComp_EXIST(100000),

		;

		private final Integer code;

	}


	@Override
	public boolean needCheckExist(I sin, SysEmployeeLoginCompEntity exist) {
		return !exist.getCellphone().equals(sin.getCellphone())

				|| !exist.getEmail().equals(sin.getEmail())

				|| !exist.getUserCard().equals(sin.getUserCard());
	}


	@Override
	public List<SysEmployeeLoginCompEntity> findExist(I sin, SysEmployeeLoginCompEntity exist) {
		return busService.findExist(sin.getId(), sin.getCompId(), sin.getCellphone(), sin.getEmail(), sin.getUserCard(), exist.getJobNumber());
	}


	@Override
	public LKCodeEnum existErrorCode(I sin) {
		return ErrorCodes.SysEmployeeLoginComp_EXIST;
	}


	@Override
	protected void beforeSaveMainTable(I sin, SysEmployeeLoginCompEntity exist) {
		if (StringUtils.isNotBlank(sin.getUserCard())) {
			sin.setBirthday(sin.getUserCard().substring(6, 10) + "-" + sin.getUserCard().substring(10, 12) + "-" + sin.getUserCard().substring(12, 14));
		}
	}


	@Override
	protected void clearSubTables(I sin, SysEmployeeLoginCompEntity exist) {
		busService.clearEmployeeLoginDept(exist.getLoginId(), exist.getCompId());
	}


	@Override
	protected void addSubTables(I sin, SysEmployeeLoginCompEntity exist) {
		busService.addEmployeeLoginDept(exist.getLoginId(), sin.getDeptId());
	}


	@Override
	protected String[] excludeFieldNames(I sin, SysEmployeeLoginCompEntity exist) {
		return super.excludeFieldNames(sin, exist);
	}

}
