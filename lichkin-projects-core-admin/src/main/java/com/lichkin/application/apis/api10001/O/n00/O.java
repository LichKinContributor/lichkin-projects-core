package com.lichkin.application.apis.api10001.O.n00;

import com.lichkin.framework.defines.annotations.IgnoreLog;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class O {

	private String id;

//	private LKUsingStatusEnum usingStatus;

//	private String insertTime;

//	private String parentCode;

//	private String compCode;

	private String compName;

	private String compKey;

	private String token;

	private String abbreviation;

	private String telephone;

	private String email;

	private String address;

	private String website;

	private String description;

	private String linkmanName;

	private String linkmanCellphone;

	@IgnoreLog
	private String photo;

}
