package com.lichkin.application.apis.TokenLogin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class O {

	/** 头像 */
	private String photo;

	/** 等级 */
	private Byte level;

	/** 安全中心地址 */
	private String securityCenterUrl;

	/** api页面地址 */
	private String apiServerRootUrl;

	/** 登录名 */
	private String loginName;

}
