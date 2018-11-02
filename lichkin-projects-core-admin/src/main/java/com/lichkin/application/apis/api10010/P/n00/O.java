package com.lichkin.application.apis.api10010.P.n00;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class O {

	private String id;

	private String operType;

	private String operTypeDictCode;// for operType

	private String busType;

	private String requestId;

	private String requestTime;

	private String requestIp;

	/** 操作人姓名 */
	private String userName;

	/** 操作人邮箱 */
	private String email;

}
