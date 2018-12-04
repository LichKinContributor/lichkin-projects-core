package com.lichkin.application.apis.api10014.U.n00;

import com.lichkin.framework.beans.impl.LKRequestIDBean;
import com.lichkin.framework.defines.annotations.IgnoreLog;
import com.lichkin.framework.defines.enums.impl.LKGenderEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestIDBean {

	@IgnoreLog
	private String photo;

	private String userName;

	private String cellphone;

	private String email;

	private String userCard;

	private LKGenderEnum gender;

	private String birthplace;

	private String degree;

	private String education;

	private String maritalStatus;

	private String nation;

	private String jobTitle;

	/** 部门ID */
	private String deptId;

}
