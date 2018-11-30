package com.lichkin.application.apis.api10005.I.n00;

import com.lichkin.framework.beans.impl.LKRequestBean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestBean {

	private String roleName;

	private String description;

	/** 菜单IDs */
	private String menuIds;

}
