package com.lichkin.application.apis.api10019.P.n00;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class O {

	private String id;

	private String insertTime;

//	private String usingStatus;// LKUsingStatusEnum

//	private String usingStatusDictCode;// for usingStatus

	private String gender;// LKGenderEnum

//	private String genderDictCode;// for gender

	private String birthday;

//	private String userCard;

	private String userName;

	/** 登录名 */
	private String loginName;

	/** 手机号码 */
	private String cellphone;

	/** 邮箱 */
	private String email;

	/** 账号等级 */
	private Byte level;

}
