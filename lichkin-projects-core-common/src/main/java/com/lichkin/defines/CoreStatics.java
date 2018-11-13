package com.lichkin.defines;

import com.lichkin.framework.defines.LKFrameworkStatics;

/**
 * 常量定义
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
public interface CoreStatics {

	/** 用户单点登录地址 */
	public static final String USER_SSO_URL = "/user/SSO" + LKFrameworkStatics.WEB_MAPPING_PAGES + "?redirectUrl=";

	/** 员工单点登录地址 */
	public static final String EMPLOYEE_SSO_URL = "/employee/SSO" + LKFrameworkStatics.WEB_MAPPING_PAGES + "?redirectUrl=";

	/** 安全中心地址 */
	public static final String SECURITY_CENTER_URL = USER_SSO_URL + "/securityCenter/index" + LKFrameworkStatics.WEB_MAPPING_PAGES;

}
