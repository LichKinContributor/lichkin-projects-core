package com.lichkin.application.apis.api10007.U.n00;

import com.lichkin.framework.beans.impl.LKRequestIDBean;
import com.lichkin.framework.defines.enums.impl.LKGenderEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestIDBean {

//	private LKYesNoEnum superAdmin;

//	private String lockTime;

//	private String token;

//	private Byte level;

//	private Byte errorTimes;

//	private String securityCode;

//	private String pwd;

//	private String email;

	private String photo;

	private LKGenderEnum gender;

	private String userName;

	/** 角色IDs */
	private String roleIds;

}
