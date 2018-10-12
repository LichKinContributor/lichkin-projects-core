package com.lichkin.application.apis.api10007.P.n01;

import com.lichkin.framework.beans.impl.LKRequestPageBean;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestPageBean {

	private LKUsingStatusEnum usingStatus;

//	private LKYesNoEnum superAdmin;

//	private String lockTime;

//	private String token;

//	private Byte level;

//	private Byte errorTimes;

//	private String securityCode;

//	private String pwd;

	private String email;

//	private String photo;

//	private LKGenderEnum gender;

	private String userName;

	/** 公司名称 */
	private String compName;

}
