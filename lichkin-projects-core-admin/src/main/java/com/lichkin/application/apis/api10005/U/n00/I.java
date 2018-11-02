package com.lichkin.application.apis.api10005.U.n00;

import com.lichkin.framework.beans.impl.LKRequestIDBean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestIDBean {

	private String roleName;

	private String description;

	/** 菜单IDs */
	private String menuIds;

}
