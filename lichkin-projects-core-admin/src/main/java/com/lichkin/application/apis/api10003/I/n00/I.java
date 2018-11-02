package com.lichkin.application.apis.api10003.I.n00;

import com.lichkin.framework.beans.impl.LKRequestBean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestBean {

	private String compId;

	private String locale;

	private String categoryCode;

	private String dictCode;

	private String dictName;

	private Byte sortId;

}
