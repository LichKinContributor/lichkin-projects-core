package com.lichkin.application.controllers.pages.impl;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.defines.enums.impl.LKErrorCodesEnum;
import com.lichkin.framework.defines.exceptions.LKRuntimeException;

public abstract class SSOPagesController {

	@Autowired
	protected HttpSession session;


	@GetMapping(value = "/SSO")
	public ModelAndView sso(String compToken, String token, String redirectUrl) {
		String platform = getPlatform();
		try {
			if (StringUtils.isBlank(compToken) || StringUtils.isBlank(token) || StringUtils.isBlank(redirectUrl)) {
				throw new LKRuntimeException(LKErrorCodesEnum.PARAM_ERROR);
			}

			handle(compToken, token);

			return new ModelAndView("redirect:" + "/" + platform + redirectUrl + "/index" + LKFrameworkStatics.WEB_MAPPING_PAGES);
		} catch (Exception e) {
			return new ModelAndView("redirect:" + "/" + platform + "/index");
		}
	}


	protected abstract String getPlatform();


	protected abstract void handle(String compToken, String token);

}
