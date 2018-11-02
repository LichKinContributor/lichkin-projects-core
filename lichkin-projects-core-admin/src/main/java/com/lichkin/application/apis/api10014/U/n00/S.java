package com.lichkin.application.apis.api10014.U.n00;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichkin.application.services.bus.impl.SysEmployeeBusService;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.springframework.entities.impl.SysEmployeeEntity;
import com.lichkin.springframework.services.LKApiBusUpdateService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service("SysEmployeeU00Service")
public class S extends LKApiBusUpdateService<I, SysEmployeeEntity> {

	@Autowired
	private SysEmployeeBusService busService;


	@Getter
	@RequiredArgsConstructor
	enum ErrorCodes implements LKCodeEnum {

		SysEmployee_EXIST(100000),

		;

		private final Integer code;

	}


	@Override
	protected boolean needCheckExist(I sin, String locale, String compId, String loginId, SysEmployeeEntity entity, String id) {
		if (!entity.getCellphone().equals(sin.getCellphone())) {
			return true;
		}
		String emailSaved = entity.getEmail();
		String emailIn = sin.getEmail();
		if (((emailSaved == null) && (emailIn != null)) || ((emailSaved != null) && ((emailIn == null) || !emailSaved.equals(emailIn)))) {
			return true;
		}
		String userCardSaved = entity.getUserCard();
		String userCardIn = sin.getUserCard();
		if (((userCardSaved == null) && (userCardIn != null)) || ((userCardSaved != null) && ((userCardIn == null) || !userCardSaved.equals(userCardIn)))) {
			return true;
		}
		return false;
	}


	@Override
	protected List<SysEmployeeEntity> findExist(I sin, String locale, String compId, String loginId, SysEmployeeEntity entity, String id) {
		return busService.findExist(id, compId, null, sin.getCellphone(), sin.getEmail(), sin.getUserCard(), entity.getJobNumber());
	}


	@Override
	protected LKCodeEnum existErrorCode(I sin, String locale, String compId, String loginId) {
		return ErrorCodes.SysEmployee_EXIST;
	}


	@Override
	protected void beforeSaveMain(I sin, String locale, String compId, String loginId, SysEmployeeEntity entity) {
		entity.setBirthday(busService.analysisBirthday(entity.getUserCard()));
	}


	@Override
	protected void clearSubs(I sin, String locale, String compId, String loginId, SysEmployeeEntity entity, String id) {
		busService.clearEmployeeDept(id);
	}


	@Override
	protected void addSubs(I sin, String locale, String compId, String loginId, SysEmployeeEntity entity, String id) {
		busService.addEmployeeDept(id, sin.getDeptIds());
	}

}
