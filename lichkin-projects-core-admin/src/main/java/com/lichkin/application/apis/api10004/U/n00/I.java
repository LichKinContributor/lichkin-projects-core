package com.lichkin.application.apis.api10004.U.n00;

import com.lichkin.framework.beans.impl.LKRequestIDBean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestIDBean {

	private String menuName;

	private Boolean rootOnly;

	private Boolean onLine;

	private Boolean assignable;

	private String icon;

	private String url;

}
