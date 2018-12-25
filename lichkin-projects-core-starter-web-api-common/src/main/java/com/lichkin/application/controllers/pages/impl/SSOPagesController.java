package com.lichkin.application.controllers.pages.impl;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lichkin.framework.defines.LKConfigStatics;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.defines.Platform;
import com.lichkin.framework.defines.enums.impl.LKClientTypeEnum;
import com.lichkin.framework.defines.enums.impl.LKErrorCodesEnum;
import com.lichkin.framework.defines.exceptions.LKRuntimeException;

public abstract class SSOPagesController {

	@Autowired
	protected HttpSession session;


	@GetMapping(value = "/SSO")
	public ModelAndView sso(String compToken, String token, String redirectUrl, LKClientTypeEnum clientType) {
		String platform = Platform.PLATFORM.toString().toLowerCase();
		try {
			if (StringUtils.isBlank(compToken) || StringUtils.isBlank(token) || StringUtils.isBlank(redirectUrl) || (clientType == null)) {
				throw new LKRuntimeException(LKErrorCodesEnum.PARAM_ERROR);
			}

			switch (clientType) {
				case ANDROID:
				case IOS:
				case APP:
					session.setAttribute("jsBridge", LKConfigStatics.WEB_DEBUG ? "false" : "true");
				break;
				default:
					session.setAttribute("jsBridge", "false");
				break;
			}

			handle(compToken, token);

			return new ModelAndView("redirect:" + "/" + platform + redirectUrl + (((redirectUrl.split("/").length > 2) || redirectUrl.equals("/home")) ? "" : "/index") + LKFrameworkStatics.WEB_MAPPING_PAGES);
		} catch (Exception e) {
			return new ModelAndView("redirect:" + "/" + platform + "/index" + LKFrameworkStatics.WEB_MAPPING_PAGES);
		}
	}


	protected abstract void handle(String compToken, String token);

}
