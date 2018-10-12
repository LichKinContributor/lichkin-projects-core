package com.lichkin.application.apis.api10019.P.n00;

import com.lichkin.framework.beans.impl.LKRequestPageBean;
import com.lichkin.framework.defines.enums.impl.LKGenderEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestPageBean {

//	private LKUsingStatusEnum usingStatus;

	private LKGenderEnum gender;

//	private String birthday;

//	private String userCard;

	private String userName;

	/** 登录名 */
	private String loginName;

	/** 手机号码 */
	private String cellphone;

	/** 邮箱 */
	private String email;

	/** 开始日期 */
	private String startDate;

	/** 结束日期 */
	private String endDate;

}
