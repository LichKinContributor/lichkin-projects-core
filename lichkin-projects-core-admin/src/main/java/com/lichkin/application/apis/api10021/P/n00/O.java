package com.lichkin.application.apis.api10021.P.n00;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class O {

	private String id;

	private String busType;

	private String operType;// LKOperTypeEnum

	private String operTypeDictCode;// for operType

//	private String requestDatas;

//	private String requestUrl;

	private String requestIp;

	private String requestTime;

	private String requestId;

//	private String loginId;

	/** 登录名 */
	private String loginName;

	/** 手机号码 */
	private String cellphone;

}
