package com.lichkin.application.apis.api10010.P.n00;

import com.lichkin.framework.beans.impl.LKRequestPageBean;
import com.lichkin.framework.defines.enums.impl.LKOperTypeEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestPageBean {

	private LKOperTypeEnum operType;

	private String busType;

	/** 操作人姓名 */
	private String userName;

	/** 操作人邮箱 */
	private String email;

}
