package com.lichkin.application.cache;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	private String authType;// CategoryAuthTypeEnum

	private String authTypeDictCode;// for authType

	private Byte sortId;

	private String categoryName;

	private String categoryCode;

	private String locale;

	/** 是否为ROOT */
	private boolean root;

}
