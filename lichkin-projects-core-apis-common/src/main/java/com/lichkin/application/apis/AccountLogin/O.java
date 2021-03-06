package com.lichkin.application.apis.AccountLogin;

import com.lichkin.framework.defines.annotations.IgnoreLog;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class O {

	/** 头像 */
	@IgnoreLog
	private String photo;

	/** 等级 */
	private int level;

	/** 安全中心地址 */
	private String securityCenterUrl;

	/** api页面地址 */
	private String apiServerRootUrl;

	/** 登录名 */
	private String loginName;

	/** 令牌 */
	private String token;

	/** 姓名 */
	private String userName;

}
