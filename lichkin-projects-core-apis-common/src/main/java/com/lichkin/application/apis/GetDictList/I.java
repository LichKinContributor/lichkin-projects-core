package com.lichkin.application.apis.GetDictList;

import com.lichkin.framework.beans.impl.LKRequestBean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestBean {

	/** 类目编码 */
	private String categoryCode;

	/** 包含的值 */
	private String includes;

	/** 排除的值 */
	private String excludes;

	/** 允许空值 */
	private boolean allowEmptyResult;

}
