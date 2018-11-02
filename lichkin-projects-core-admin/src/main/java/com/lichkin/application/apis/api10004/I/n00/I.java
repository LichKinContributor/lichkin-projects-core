package com.lichkin.application.apis.api10004.I.n00;

import com.lichkin.framework.beans.impl.LKRequestBean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestBean {

	private String parentCode;

	private String menuName;

	private Boolean rootOnly;

	private Boolean onLine;

	private Boolean assignable;

	private String icon;

	private String url;

}
