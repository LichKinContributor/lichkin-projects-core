package com.lichkin.application.controllers.pages.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lichkin.application.services.extend.impl.XAdminLoginService;
import com.lichkin.application.services.extend.impl.XCompService;
import com.lichkin.springframework.entities.impl.SysAdminLoginEntity;
import com.lichkin.springframework.entities.impl.SysCompEntity;
import com.lichkin.springframework.web.LKSession;

@Controller
public class SSOPageController extends SSOPagesController {

	@Autowired
	private XAdminLoginService adminLoginService;

	@Autowired
	private XCompService compService;


	@Override
	protected String getPlatform() {
		return "admin";
	}


	@Override
	protected void handle(String compToken, String token) {
		SysAdminLoginEntity userLogin = adminLoginService.findUserLoginByToken(true, token);
		SysCompEntity comp = compService.findCompByToken(true, compToken);
		LKSession.setUser(session, userLogin);
		LKSession.setLogin(session, userLogin);
		LKSession.setComp(session, comp);
	}

}
