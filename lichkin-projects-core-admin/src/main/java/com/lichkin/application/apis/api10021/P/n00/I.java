package com.lichkin.application.apis.api10021.P.n00;

import com.lichkin.framework.beans.impl.LKRequestPageBean;
import com.lichkin.framework.defines.enums.impl.LKOperTypeEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestPageBean {

	private String compId;

	private LKOperTypeEnum operType;

	private String busType;

	/** 登录名 */
	private String loginName;

	/** 手机号码 */
	private String cellphone;

	/** 开始日期 */
	private String startDate;

	/** 结束日期 */
	private String endDate;

}
