package com.lichkin.application.apis.api10007.O.n01;

import com.lichkin.framework.defines.enums.impl.LKGenderEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class O {

	private String id;

	private String compId;

	private String userName;

	private LKGenderEnum gender;

	private String email;

	private String photo;

	/** 角色IDs */
	private String roleIds;

	/** 角色名称s */
	private String roleNames;

}
