package com.lichkin.application.controllers.pages.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lichkin.application.services.extend.impl.XCompService;
import com.lichkin.application.services.extend.impl.XEmployeeLoginService;
import com.lichkin.springframework.entities.impl.SysCompEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeEntity;
import com.lichkin.springframework.entities.impl.SysUserLoginEntity;
import com.lichkin.springframework.web.LKSession;

@Controller
public class SSOPageController extends SSOPagesController {

	@Autowired
	private XEmployeeLoginService loginService;

	@Autowired
	private XCompService compService;


	@Override
	protected void handle(String compToken, String token) {
		SysUserLoginEntity userLogin = loginService.findUserLoginByToken(token);
		SysEmployeeEntity employee = loginService.findEmployeeByUserLoginIdAndCompToken(userLogin.getId(), compToken);
		SysCompEntity comp = compService.findCompByToken(compToken);
		LKSession.setLogin(session, userLogin);
		LKSession.setUser(session, employee);
		LKSession.setComp(session, comp);
		LKSession.setDept(session, loginService.findDeptByEmployeeIdAndCompId(employee.getId(), comp.getId()));
	}

}
