package com.lichkin.application.apis.api10014.I.n00;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichkin.application.services.bus.impl.SysEmployeeBusService;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.framework.utils.LKBeanUtils;
import com.lichkin.springframework.entities.impl.SysEmployeeEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeLoginCompEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeLoginEntity;
import com.lichkin.springframework.services.LKApiBusInsertService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service("SysEmployeeLoginCompI00Service")
public class S extends LKApiBusInsertService<I, SysEmployeeLoginCompEntity> {

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
	public boolean needCheckExist(I sin) {
		return true;
	}


	@Override
	public List<SysEmployeeLoginCompEntity> findExist(I sin) {
		return busService.findExist(null, sin.getCompId(), sin.getCellphone(), sin.getEmail(), sin.getUserCard(), sin.getJobNumber());
	}


	@Override
	public LKCodeEnum existErrorCode(I sin) {
		return ErrorCodes.SysEmployeeLoginComp_EXIST;
	}


	@Override
	protected void beforeSaveMainTable(I sin, SysEmployeeLoginCompEntity entity, SysEmployeeLoginCompEntity exist) {
		if (StringUtils.isNotBlank(sin.getUserCard())) {
			entity.setBirthday(sin.getUserCard().substring(6, 10) + "-" + sin.getUserCard().substring(10, 12) + "-" + sin.getUserCard().substring(12, 14));
		}

		SysEmployeeLoginEntity employeeLogin = busService.findEmployeeLogin(sin.getCellphone());
		if (employeeLogin == null) {
			// 员工个人信息表
			SysEmployeeEntity employee = LKBeanUtils.newInstance(true, sin, SysEmployeeEntity.class);
			employee.setBirthday(entity.getBirthday());
			dao.persistOne(employee);

			// 登录表
			employeeLogin = LKBeanUtils.newInstance(true, sin, SysEmployeeLoginEntity.class);
			employeeLogin.setUserId(employee.getId());
			employeeLogin.setLoginName(employee.getCellphone());
			employeeLogin.setCellphone(employee.getCellphone());
			dao.persistOne(employeeLogin);
		}
		entity.setLoginId(employeeLogin.getId());
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
		return new String[] { "id", "loginId" };
	}

}
