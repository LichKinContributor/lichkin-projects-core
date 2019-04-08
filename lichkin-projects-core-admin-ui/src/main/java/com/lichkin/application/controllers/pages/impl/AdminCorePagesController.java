package com.lichkin.application.controllers.pages.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lichkin.framework.defines.enums.impl.LKErrorCodesEnum;
import com.lichkin.framework.defines.exceptions.LKRuntimeException;
import com.lichkin.springframework.controllers.LKPagesController;
import com.lichkin.springframework.web.LKSession;
import com.lichkin.springframework.web.beans.LKPage;

@Controller
@RequestMapping("/admin")
public class AdminCorePagesController extends LKPagesController {

	/** 日志服务器URL根路径 */
	@Value("${com.lichkin.logs.server.rootUrl:http://logs.lichkin.com}")
	private String logsServerRootUrl;

	/** 高德地图API参数 */
	@Value("${com.lichkin.AmapParams:v=1.4.14&key=xxx}")
	private String AmapParams;


	@GetMapping(value = "/home" + MAPPING)
	public LKPage toHome() {
		LKPage mv = new LKPage();
		mv.putAttribute("logsServerRootUrl", logsServerRootUrl);
		mv.putAttribute("AmapParams", AmapParams);
		return mv;
	}


	@GetMapping("/core/{module}/index" + MAPPING)
	public LKPage linkTo(@PathVariable String module) {
		if (!LKSession.checkMenuAuth(request, "/index")) {
			throw new LKRuntimeException(LKErrorCodesEnum.NOT_FOUND);
		}
		switch (module) {
			case "dictMgmt":
				return null;
			default:
				return new LKPage(LKPage.BLANK);
		}
	}


	@PostMapping("/core/{module}/index" + MAPPING)
	public LKPage jumpTo(@PathVariable String module) {
		return linkTo(module);
	}

}
