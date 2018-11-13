package com.lichkin.application.apis.AccountLogin;

import javax.validation.constraints.NotBlank;

import com.lichkin.framework.beans.impl.LKRequestBean;
import com.lichkin.framework.constraints.MD5;
import com.lichkin.framework.constraints.MixLoginName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestBean {

	/** 用户名 */
	@MixLoginName
	@NotBlank
	private String loginName;

	/** 密码 （MD5） */
	@MD5
	@NotBlank
	private String pwd;

}
