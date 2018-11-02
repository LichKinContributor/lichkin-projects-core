package com.lichkin.application.apis.api10009.P.n00;

import com.lichkin.framework.beans.impl.LKRequestPageBean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestPageBean {

	private String compId;

	/** 登录人姓名 */
	private String userName;

	/** 登录人邮箱 */
	private String email;

	/** 开始日期 */
	private String startDate;

	/** 结束日期 */
	private String endDate;

}
