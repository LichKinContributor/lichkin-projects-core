package com.lichkin.application.apis.api10007.U.n00;

import com.lichkin.framework.beans.impl.LKRequestIDBean;
import com.lichkin.framework.defines.annotations.IgnoreLog;
import com.lichkin.framework.defines.enums.impl.LKGenderEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestIDBean {

	private String userName;

	private LKGenderEnum gender;

//	private String pwd;

//	private String securityCode;

//	private Byte errorTimes;

//	private Byte level;

//	private String token;

//	private String lockTime;

	@IgnoreLog
	private String photo;

	/** 角色IDs */
	private String roleIds;

}
