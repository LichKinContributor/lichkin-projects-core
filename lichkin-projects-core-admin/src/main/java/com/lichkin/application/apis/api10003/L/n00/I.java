package com.lichkin.application.apis.api10003.L.n00;

import com.lichkin.framework.beans.impl.LKRequestBean;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestBean {

	private LKUsingStatusEnum usingStatus;

	private String compId;

	private String categoryCode;

}
