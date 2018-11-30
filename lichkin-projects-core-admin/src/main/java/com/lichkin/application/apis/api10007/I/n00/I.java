package com.lichkin.application.apis.api10007.I.n00;

import com.lichkin.framework.beans.impl.LKRequestBean;
import com.lichkin.framework.defines.enums.impl.LKGenderEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestBean {

	private String userName;

	private LKGenderEnum gender;

	private String email;

	private String photo;

	/** 角色IDs */
	private String roleIds;

}
