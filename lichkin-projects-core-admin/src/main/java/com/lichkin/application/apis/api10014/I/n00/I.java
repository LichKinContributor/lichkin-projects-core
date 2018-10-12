package com.lichkin.application.apis.api10014.I.n00;

import com.lichkin.framework.beans.impl.LKRequestBean;
import com.lichkin.framework.defines.enums.impl.LKGenderEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestBean {

	private String entryDate;

	private String jobTitle;

	private String jobNumber;

	private String nation;

	private String maritalStatus;

	private String education;

	private String degree;

	private String birthplace;

	private LKGenderEnum gender;

//	private String birthday;

	private String userCard;

	private String email;

	private String cellphone;

	private String userName;

//	private String photo;

//	private String loginId;

	/** 部门IDs */
	private String deptId;

}
