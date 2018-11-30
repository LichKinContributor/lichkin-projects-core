package com.lichkin.application.apis.FastLogin;

import javax.validation.constraints.NotNull;

import com.lichkin.framework.beans.impl.LKRequestBean;
import com.lichkin.framework.constraints.Cellphone;
import com.lichkin.framework.constraints.SecurityCode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestBean {

	/** 手机号码 */
	@NotNull
	@Cellphone
	private String cellphone;

	/** 验证码 */
	@NotNull
	@SecurityCode
	private String securityCode;

}
