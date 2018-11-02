package com.lichkin.application.apis.api10014.P.n00;

import com.lichkin.framework.beans.impl.LKRequestPageBean;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.defines.enums.impl.LKGenderEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestPageBean {

	private LKUsingStatusEnum usingStatus;

	private String compId;

	private String userName;

	private String cellphone;

	private String email;

	private String userCard;

	private LKGenderEnum gender;

	/** 部门IDs */
	private String deptIds;

	/** 包含的IDs */
	private String includes;

	/** 排除的IDs */
	private String excludes;

}
