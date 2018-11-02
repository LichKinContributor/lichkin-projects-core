package com.lichkin.application.apis.api10014.O.n00;

import com.lichkin.framework.defines.enums.impl.LKGenderEnum;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class O {

	private String id;

	private LKUsingStatusEnum usingStatus;

//	private String insertTime;

//	private String compId;

//	private String loginId;

	private String photo;

	private String userName;

	private String cellphone;

	private String email;

	private String userCard;

	private String birthday;

	private LKGenderEnum gender;

	private String birthplace;

	private String degree;

	private String education;

	private String maritalStatus;

	private String nation;

	private String jobNumber;

	private String jobTitle;

	private String entryDate;

	/** 部门ID */
	private String deptId;

	/** 登录名 */
	private String loginName;

}
