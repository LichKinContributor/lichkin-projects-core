package com.lichkin.application.apis.ModifyPassword;

import javax.validation.constraints.NotBlank;

import com.lichkin.framework.beans.impl.LKRequestBean;
import com.lichkin.framework.constraints.MD5;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestBean {

	/** 原密码 */
	@MD5
	@NotBlank
	private String pwdOld;

	/** 新密码 */
	@MD5
	@NotBlank
	private String pwdNew;

}
