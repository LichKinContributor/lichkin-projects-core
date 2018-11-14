package com.lichkin.application.apis.SupplementRegisterInfo;

import javax.validation.constraints.NotBlank;

import com.lichkin.framework.beans.impl.LKRequestBean;
import com.lichkin.framework.constraints.LoginName;
import com.lichkin.framework.constraints.MD5;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestBean {

	/** 登录名 */
	@LoginName
	@NotBlank
	private String loginName;

	/** 密码 （MD5） */
	@MD5
	@NotBlank
	private String pwd;

}
