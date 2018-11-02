package com.lichkin.application.controllers.pages.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lichkin.framework.web.annotations.WithoutLogin;
import com.lichkin.springframework.controllers.LKPagesController;
import com.lichkin.springframework.web.beans.LKPage;

@Controller
@RequestMapping("/user")
public class UserCorePagesController extends LKPagesController {

	@WithoutLogin
	@GetMapping(value = "/index" + MAPPING)
	public LKPage toIndex() {
		return null;
	}


	@GetMapping(value = "/home" + MAPPING)
	public LKPage toHome() {
		return null;
	}


	@GetMapping("/core/{module}/index" + MAPPING)
	public LKPage linkTo(@PathVariable String module) {
		return null;
	}


	@PostMapping("/core/{module}/index" + MAPPING)
	public LKPage jumpTo(@PathVariable String module) {
		return linkTo(module);
	}

}
