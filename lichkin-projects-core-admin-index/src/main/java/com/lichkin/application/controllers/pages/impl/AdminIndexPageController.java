package com.lichkin.application.controllers.pages.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lichkin.framework.web.annotations.WithoutLogin;
import com.lichkin.springframework.controllers.LKPagesController;
import com.lichkin.springframework.web.beans.LKPage;

@Controller
@RequestMapping("/admin")
public class AdminIndexPageController extends LKPagesController {

	@WithoutLogin
	@GetMapping(value = "/index" + MAPPING)
	public LKPage toIndex() {
		LKPage mv = new LKPage();
		mv.putAttribute("systemTag", $systemTag);
		return mv;
	}

}
