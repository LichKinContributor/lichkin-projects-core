package com.lichkin.defines;

import com.lichkin.framework.defines.LKFrameworkStatics;

/**
 * 常量定义
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
public interface CoreStatics {

	public static final String USER_SSO_URL = "/User/SSO" + LKFrameworkStatics.WEB_MAPPING_PAGES + "?redirectTo=";

	public static final String USER_EMPLOYEE_SSO_URL = "/UserEmployee/SSO" + LKFrameworkStatics.WEB_MAPPING_PAGES + "?redirectTo=";

	public static final String EMPLOYEE_SSO_URL = "/Employee/SSO" + LKFrameworkStatics.WEB_MAPPING_PAGES + "?redirectTo=";

	public static final String SECURITY_CENTER_URL = "/appSecurityCenter/index" + LKFrameworkStatics.WEB_MAPPING_PAGES;

	public static final String ACTIVITI_CENTER_URL = "/activitiCenter/index" + LKFrameworkStatics.WEB_MAPPING_PAGES;

	public static final String APP_BANNER_URL = "/appBanner/detail" + LKFrameworkStatics.WEB_MAPPING_PAGES;

	public static final String APP_NEWS_URL = "/appNews/detail" + LKFrameworkStatics.WEB_MAPPING_PAGES;

}
