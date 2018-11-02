package com.lichkin.application.apis.api10004.S.n03;

import com.lichkin.framework.beans.impl.LKRequestBean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestBean {

//	private String id;

//	private String url;

//	private String icon;

//	private Boolean assignable;

//	private Boolean onLine;

//	private Boolean rootOnly;

//	private String menuName;

	private String menuCode;

//	private String parentCode;

	/** 方向。true:向上;false:向下; */
	private boolean up;

}
