package com.lichkin.application.controllers.pages.impl;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lichkin.application.services.extend.impl.XAdminLoginService;
import com.lichkin.application.services.extend.impl.XCompService;
import com.lichkin.application.services.extend.impl.XUserLoginService;
import com.lichkin.framework.defines.LKConfigStatics;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.defines.enums.impl.LKErrorCodesEnum;
import com.lichkin.framework.defines.exceptions.LKRuntimeException;
import com.lichkin.springframework.entities.impl.SysAdminLoginEntity;
import com.lichkin.springframework.entities.impl.SysCompEntity;
import com.lichkin.springframework.entities.impl.SysDeptEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeEntity;
import com.lichkin.springframework.entities.impl.SysUserLoginEntity;
import com.lichkin.springframework.web.LKSession;

@Controller
public class SSOPageController {

	@Autowired
	protected HttpSession session;

	@Autowired
	private XUserLoginService userLoginService;

	@Autowired
	private XAdminLoginService adminLoginService;

	@Autowired
	private XCompService compService;


	@GetMapping(value = "/SSO")
	public ModelAndView sso(String compToken, String token, String redirectUrl) {
		try {
			if (StringUtils.isBlank(compToken) || StringUtils.isBlank(token) || StringUtils.isBlank(redirectUrl)) {
				throw new LKRuntimeException(LKErrorCodesEnum.PARAM_ERROR);
			}
			switch (LKConfigStatics.PLATFORM) {
				case ADMIN: {
					SysAdminLoginEntity userLogin = adminLoginService.findUserLoginByToken(true, token);
					SysCompEntity comp = compService.findCompByToken(true, compToken);
					LKSession.setUser(session, userLogin);
					LKSession.setLogin(session, userLogin);
					LKSession.setComp(session, comp);
				}
				break;
				case EMPLOYEE: {
					SysUserLoginEntity userLogin = userLoginService.findUserLoginByToken(true, token);
					SysEmployeeEntity employee = (SysEmployeeEntity) userLoginService.findEmployeeByUserLoginAndCompToken(true, userLogin, compToken);
					SysCompEntity comp = compService.findCompByToken(true, compToken);
					SysDeptEntity dept = userLoginService.findDeptByLoginIdAndCompId(true, employee.getId(), comp.getId());
					LKSession.setUser(session, employee);
					LKSession.setLogin(session, userLogin);
					LKSession.setComp(session, comp);
					LKSession.setDept(session, dept);
				}
				break;
				case USER: {
					SysUserLoginEntity userLogin = userLoginService.findUserLoginByToken(true, token);
					LKSession.setUser(session, userLogin);
					LKSession.setLogin(session, userLogin);
				}
				break;
			}
			return new ModelAndView("redirect:" + "/" + LKConfigStatics.PLATFORM.toString().toLowerCase() + redirectUrl + "/index" + LKFrameworkStatics.WEB_MAPPING_PAGES);
		} catch (Exception e) {
			return new ModelAndView("redirect:" + "/" + LKConfigStatics.PLATFORM.toString().toLowerCase() + "/index");
		}
	}

}
