package com.lichkin.application.apis.api10011.S.n01;

import com.lichkin.framework.beans.impl.LKRequestBean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestBean {

	private String deptCode;

	/** 方向。true:向上;false:向下; */
	private boolean up;

}
