package com.lichkin.application.controllers.pages.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lichkin.application.services.extend.impl.XCompService;
import com.lichkin.application.services.extend.impl.XUserLoginService;
import com.lichkin.springframework.entities.impl.SysCompEntity;
import com.lichkin.springframework.entities.impl.SysDeptEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeEntity;
import com.lichkin.springframework.entities.impl.SysUserLoginEntity;
import com.lichkin.springframework.web.LKSession;

@Controller
public class SSOPageController extends SSOPagesController {

	@Autowired
	private XUserLoginService userLoginService;

	@Autowired
	private XCompService compService;


	@Override
	protected String getPlatform() {
		return "employee";
	}


	@Override
	protected void handle(String compToken, String token) {
		SysUserLoginEntity userLogin = userLoginService.findUserLoginByToken(true, token);
		SysEmployeeEntity employee = userLoginService.findEmployeeByUserLoginAndCompToken(true, userLogin, compToken);
		SysCompEntity comp = compService.findCompByToken(true, compToken);
		SysDeptEntity dept = userLoginService.findDeptByLoginIdAndCompId(true, employee.getId(), comp.getId());
		LKSession.setUser(session, employee);
		LKSession.setLogin(session, userLogin);
		LKSession.setComp(session, comp);
		LKSession.setDept(session, dept);
	}

}
