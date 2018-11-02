package com.lichkin.application.apis.api10014.I.n00;

import com.lichkin.framework.beans.impl.LKRequestBean;
import com.lichkin.framework.defines.enums.impl.LKGenderEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestBean {

	private String compId;

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

	private String jobNumber;

	private String jobTitle;

	private String entryDate;

	/** 部门ID */
	private String deptId;

}
