package com.lichkin.application.apis.api10020.P.n00;

import com.lichkin.framework.beans.impl.LKRequestPageBean;
import com.lichkin.framework.defines.enums.impl.LKGenderEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestPageBean {

	private String userName;

	private LKGenderEnum gender;

	private String loginName;

	private String cellphone;

	private String email;

}
