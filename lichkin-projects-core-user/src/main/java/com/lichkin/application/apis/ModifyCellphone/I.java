package com.lichkin.application.apis.ModifyCellphone;

import javax.validation.constraints.NotBlank;

import com.lichkin.framework.beans.impl.LKRequestBean;
import com.lichkin.framework.constraints.Cellphone;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestBean {

	/** 手机号码 */
	@Cellphone
	@NotBlank
	private String cellphone;

	/** 验证码 */
	@NotBlank
	private String securityCode;

}
