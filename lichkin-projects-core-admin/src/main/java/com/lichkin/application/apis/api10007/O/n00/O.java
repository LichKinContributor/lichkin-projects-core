package com.lichkin.application.apis.api10007.O.n00;

import com.lichkin.framework.defines.enums.impl.LKGenderEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class O {

	private String id;

//	private LKUsingStatusEnum usingStatus;

//	private String insertTime;

	private String compId;

	private String userName;

	private LKGenderEnum gender;

	private String email;

//	private String pwd;

//	private String securityCode;

//	private Byte errorTimes;

//	private Byte level;

//	private String token;

//	private String lockTime;

//	private Boolean superAdmin;

	private String photo;

	/** 角色IDs */
	private String roleIds;

	/** 角色名称s */
	private String roleNames;

}
