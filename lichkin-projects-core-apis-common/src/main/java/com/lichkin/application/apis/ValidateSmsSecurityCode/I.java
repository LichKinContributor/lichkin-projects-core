package com.lichkin.application.apis.ValidateSmsSecurityCode;

import javax.validation.constraints.NotBlank;

import com.lichkin.framework.beans.impl.LKRequestBean;
import com.lichkin.framework.constraints.Cellphone;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestBean {

	/** 手机号码 */
	@NonNull
	@Cellphone
	@NotBlank
	private String cellphone;

	/** 验证码 */
	@NonNull
	@NotBlank
	private String securityCode;

}
