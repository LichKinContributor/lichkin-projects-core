package com.lichkin.application.apis.api10002.O.n00;

import com.lichkin.framework.defines.enums.impl.CategoryAuthTypeEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class O {

	private String id;

	private String locale;

	private String categoryCode;

	private String categoryName;

	private Byte sortId;

	private CategoryAuthTypeEnum authType;

}
