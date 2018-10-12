package com.lichkin.application.apis.api10007.I.n00;

import com.lichkin.framework.beans.impl.LKRequestBean;
import com.lichkin.framework.defines.enums.impl.LKGenderEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestBean {

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

	/** 公司ID */
	private String busCompId;

}
