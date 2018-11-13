package com.lichkin.application.apis.GetSmsSecurityCode;

import javax.validation.constraints.NotNull;

import com.lichkin.framework.beans.impl.LKRequestBean;
import com.lichkin.framework.constraints.Cellphone;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestBean {

	/** 手机号码 */
	@Cellphone
	@NotNull
	private String cellphone;

	/** 业务类型 */
	private String busType = "FastLogin";

}
