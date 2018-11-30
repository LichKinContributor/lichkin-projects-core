package com.lichkin.application.apis.api10002.L.n00;

import com.lichkin.framework.beans.impl.LKRequestBean;
import com.lichkin.framework.defines.enums.impl.CategoryAuthTypeEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestBean {

	private CategoryAuthTypeEnum authType;

	private String categoryName;

	private String categoryCode;

	/** 显示所有 */
	private boolean showFull;

}
