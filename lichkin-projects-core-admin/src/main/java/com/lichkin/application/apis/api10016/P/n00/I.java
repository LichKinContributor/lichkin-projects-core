package com.lichkin.application.apis.api10016.P.n00;

import com.lichkin.framework.beans.impl.LKRequestPageBean;
import com.lichkin.framework.defines.enums.impl.LKOperTypeEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestPageBean {

	private LKOperTypeEnum operType;

	private String busType;

	/** 员工姓名 */
	private String userName;

	/** 手机号码 */
	private String cellphone;

}
