package com.lichkin.application.apis.api10003.LD.n00;

import com.lichkin.framework.beans.impl.LKRequestBean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestBean {

	/** 类目编码 */
	private String categoryCode;

	/** 包含的字典编码 */
	private String includes;

	/** 排除的字典编码 */
	private String excludes;

}
