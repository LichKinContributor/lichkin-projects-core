package com.lichkin.application.controllers.pages.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lichkin.framework.defines.enums.impl.LKErrorCodesEnum;
import com.lichkin.framework.defines.exceptions.LKRuntimeException;
import com.lichkin.framework.web.annotations.WithoutLogin;
import com.lichkin.springframework.controllers.LKPagesController;
import com.lichkin.springframework.web.LKSession;
import com.lichkin.springframework.web.beans.LKPage;

@Controller
@RequestMapping("/employee")
public class EmployeeCorePagesController extends LKPagesController {

	@WithoutLogin
	@GetMapping(value = "/index" + MAPPING)
	public LKPage toIndex() {
		LKPage mv = new LKPage();
		mv.putAttribute("systemTag", $systemTag);
		return mv;
	}


	/** 日志服务器URL根路径 */
	@Value("${com.lichkin.logs.server.rootUrl:http://logs.lichkin.com}")
	private String logsServerRootUrl;


	@GetMapping(value = "/home" + MAPPING)
	public LKPage toHome() {
		LKPage mv = new LKPage();
		mv.putAttribute("logsServerRootUrl", logsServerRootUrl);
		return mv;
	}


	@GetMapping("/core/{module}/index" + MAPPING)
	public LKPage linkTo(@PathVariable String module) {
		if (!LKSession.checkMenuAuth(request, "/index")) {
			throw new LKRuntimeException(LKErrorCodesEnum.NOT_FOUND);
		}
		return null;
	}


	@PostMapping("/core/{module}/index" + MAPPING)
	public LKPage jumpTo(@PathVariable String module) {
		return linkTo(module);
	}

}
