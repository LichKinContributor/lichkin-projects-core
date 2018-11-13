package com.lichkin.application.apis.UpdateUserInfo;

import com.lichkin.framework.beans.impl.LKRequestBean;
import com.lichkin.framework.defines.enums.impl.LKGenderEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestBean {

	/** 姓名 */
	private String userName;

	/** 身份证号 */
	private String userCard;

	/** 性别（枚举） */
	private LKGenderEnum gender;

}
