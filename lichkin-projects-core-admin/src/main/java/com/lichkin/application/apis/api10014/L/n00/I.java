package com.lichkin.application.apis.api10014.L.n00;

import com.lichkin.framework.beans.impl.LKRequestBean;
import com.lichkin.framework.defines.enums.impl.LKGenderEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestBean {

//	private LKUsingStatusEnum usingStatus;

//	private String entryDate;

//	private String jobTitle;

//	private String jobNumber;

//	private String nation;

//	private String maritalStatus;

//	private String education;

//	private String degree;

//	private String birthplace;

	private LKGenderEnum gender;

//	private String birthday;

//	private String userCard;

//	private String email;

//	private String cellphone;

	private String userName;

//	private String photo;

//	private String loginId;

	/** 部门IDs */
	private String deptIds;

	/** 包含的IDs */
	private String includes;

	/** 排除的IDs */
	private String excludes;

}
