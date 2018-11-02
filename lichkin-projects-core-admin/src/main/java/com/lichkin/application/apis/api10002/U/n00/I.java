package com.lichkin.application.apis.api10002.U.n00;

import com.lichkin.framework.beans.impl.LKRequestIDBean;
import com.lichkin.framework.defines.enums.impl.CategoryAuthTypeEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestIDBean {

	private String categoryName;

	private Byte sortId;

	private CategoryAuthTypeEnum authType;

}
