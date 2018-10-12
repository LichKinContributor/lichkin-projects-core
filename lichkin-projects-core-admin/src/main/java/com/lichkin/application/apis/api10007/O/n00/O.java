package com.lichkin.application.apis.api10007.O.n00;

import com.lichkin.framework.defines.enums.impl.LKGenderEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class O {

	private String id;

//	private String insertTime;

//	private LKUsingStatusEnum usingStatus;

//	private LKYesNoEnum superAdmin;

//	private String lockTime;

//	private String token;

//	private Byte level;

//	private Byte errorTimes;

//	private String securityCode;

//	private String pwd;

	private String email;

	private String photo;

	private LKGenderEnum gender;

	private String userName;

	/** 角色IDs */
	private String roleIds;

	/** 角色名称s */
	private String roleNames;

	/** 公司ID */
	private String busCompId;

}
