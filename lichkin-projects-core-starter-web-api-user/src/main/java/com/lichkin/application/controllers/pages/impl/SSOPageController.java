package com.lichkin.application.controllers.pages.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lichkin.application.services.extend.impl.XUserLoginService;
import com.lichkin.springframework.entities.impl.SysUserLoginEntity;
import com.lichkin.springframework.web.LKSession;

@Controller
public class SSOPageController extends SSOPagesController {

	@Autowired
	private XUserLoginService userLoginService;


	@Override
	protected String getPlatform() {
		return "user";
	}


	@Override
	protected void handle(String compToken, String token) {
		SysUserLoginEntity userLogin = userLoginService.findUserLoginByToken(true, token);
		LKSession.setUser(session, userLogin);
		LKSession.setLogin(session, userLogin);
	}

}
