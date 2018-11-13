package com.lichkin.application.apis.GetUserInfo;

import com.lichkin.framework.defines.enums.impl.LKGenderEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class O {

	/** 登录名 */
	private String loginName;

	/** 手机号码 */
	private String cellphone;

	/** 邮箱 */
	private String email;

	/** 等级 */
	private Byte level;

	/** 姓名 */
	private String userName;

	/** 身份证号 */
	private String userCard;

	/** 性别 */
	private LKGenderEnum gender;

}
