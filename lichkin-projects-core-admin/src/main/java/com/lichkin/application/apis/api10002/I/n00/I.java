package com.lichkin.application.apis.api10002.I.n00;

import com.lichkin.framework.beans.impl.LKRequestBean;
import com.lichkin.framework.defines.enums.impl.CategoryAuthTypeEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestBean {

	private String locale;

	private String categoryCode;

	private String categoryName;

	private Byte sortId;

	private CategoryAuthTypeEnum authType;

}
