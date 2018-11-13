package com.lichkin.application.controllers.pages.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lichkin.application.services.extend.impl.XCompService;
import com.lichkin.application.services.extend.impl.XUserLoginService;
import com.lichkin.springframework.controllers.LKPagesController;
import com.lichkin.springframework.entities.impl.SysCompEntity;
import com.lichkin.springframework.entities.impl.SysDeptEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeEntity;
import com.lichkin.springframework.entities.impl.SysUserLoginEntity;
import com.lichkin.springframework.web.LKSession;

@Controller
@RequestMapping("/employee")
public class EmployeePageSSOController extends LKPagesController {

	@Autowired
	private XUserLoginService userLoginService;

	@Autowired
	private XCompService compService;


	@GetMapping(value = "/SSO")
	public ModelAndView sso(String compToken, String token, String redirectUrl) {
		ModelAndView mv = new ModelAndView("redirect:/employee/index");
		if (StringUtils.isNotBlank(compToken) && StringUtils.isNotBlank(token)) {
			try {
				SysUserLoginEntity userLogin = userLoginService.findUserLoginByToken(true, token);
				SysEmployeeEntity employee = (SysEmployeeEntity) userLoginService.findEmployeeByUserLoginAndCompToken(true, userLogin, compToken);
				SysCompEntity comp = compService.findCompByToken(true, compToken);
				SysDeptEntity dept = userLoginService.findDeptByLoginIdAndCompId(true, employee.getId(), comp.getId());
				LKSession.setComp(session, comp);
				LKSession.setUser(session, employee);
				LKSession.setLogin(session, userLogin);
				LKSession.setDept(session, dept);
				mv.setViewName("redirect:" + redirectUrl);
			} catch (Exception e) {
			}
		}
		return mv;
	}

}
