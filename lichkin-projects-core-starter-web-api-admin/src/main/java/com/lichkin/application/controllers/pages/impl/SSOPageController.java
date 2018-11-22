package com.lichkin.application.controllers.pages.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lichkin.application.services.extend.impl.XAdminLoginService;
import com.lichkin.application.services.extend.impl.XCompService;
import com.lichkin.springframework.entities.impl.SysAdminLoginEntity;
import com.lichkin.springframework.web.LKSession;

@Controller
public class SSOPageController extends SSOPagesController {

	@Autowired
	private XAdminLoginService loginService;

	@Autowired
	private XCompService compService;


	@Override
	protected void handle(String compToken, String token) {
		SysAdminLoginEntity userLogin = loginService.findUserLoginByToken(token);
		LKSession.setLogin(session, userLogin);
		LKSession.setUser(session, userLogin);
		LKSession.setComp(session, compService.findCompByToken(compToken));
	}

}
