package com.lichkin.application.apis.api004;

import com.lichkin.framework.beans.impl.LKRequestBean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestBean {

	/** 原密码 */
	private String pwdOld;

	/** 新密码 */
	private String pwdNew;

}
