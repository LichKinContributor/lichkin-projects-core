package com.lichkin.framework.db.beans;

/**
 * 数据库资源初始化类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
class SysCoreRInitializer implements LKRInitializer {

	/**
	 * 初始化数据库资源
	 */
	public static void init() {
		LKDBResource.addTable("com.lichkin.springframework.entities.impl.SysConfigEntity", "T_SYS_CONFIG", "SysConfigEntity");
		LKDBResource.addColumn("10000000", "SysConfigEntity", "configKey");
		LKDBResource.addColumn("10000001", "SysConfigEntity", "configValue");
		LKDBResource.addColumn("10000002", "SysConfigEntity", "remarks");
		LKDBResource.addColumn("10000003", "SysConfigEntity", "compId");
		LKDBResource.addColumn("10000004", "SysConfigEntity", "id");
		LKDBResource.addTable("com.lichkin.springframework.entities.impl.SysCompEntity", "T_SYS_COMP", "SysCompEntity");
		LKDBResource.addColumn("10001000", "SysCompEntity", "photo");
		LKDBResource.addColumn("10001001", "SysCompEntity", "parentCode");
		LKDBResource.addColumn("10001002", "SysCompEntity", "compCode");
		LKDBResource.addColumn("10001003", "SysCompEntity", "compName");
		LKDBResource.addColumn("10001004", "SysCompEntity", "compKey");
		LKDBResource.addColumn("10001005", "SysCompEntity", "telephone");
		LKDBResource.addColumn("10001006", "SysCompEntity", "email");
		LKDBResource.addColumn("10001007", "SysCompEntity", "address");
		LKDBResource.addColumn("10001008", "SysCompEntity", "website");
		LKDBResource.addColumn("10001009", "SysCompEntity", "description");
		LKDBResource.addColumn("10001010", "SysCompEntity", "linkmanName");
		LKDBResource.addColumn("10001011", "SysCompEntity", "linkmanCellphone");
		LKDBResource.addColumn("10001012", "SysCompEntity", "usingStatus");
		LKDBResource.addColumn("10001013", "SysCompEntity", "insertTime");
		LKDBResource.addColumn("10001014", "SysCompEntity", "id");
		LKDBResource.addTable("com.lichkin.springframework.entities.impl.SysCategoryEntity", "T_SYS_CATEGORY", "SysCategoryEntity");
		LKDBResource.addColumn("10002000", "SysCategoryEntity", "locale");
		LKDBResource.addColumn("10002001", "SysCategoryEntity", "categoryCode");
		LKDBResource.addColumn("10002002", "SysCategoryEntity", "categoryName");
		LKDBResource.addColumn("10002003", "SysCategoryEntity", "orderId");
		LKDBResource.addColumn("10002004", "SysCategoryEntity", "rootOnly");
		LKDBResource.addColumn("10002005", "SysCategoryEntity", "auth");
		LKDBResource.addColumn("10002006", "SysCategoryEntity", "id");
		LKDBResource.addTable("com.lichkin.springframework.entities.impl.SysDictionaryEntity", "T_SYS_DICTIONARY", "SysDictionaryEntity");
		LKDBResource.addColumn("10003000", "SysDictionaryEntity", "locale");
		LKDBResource.addColumn("10003001", "SysDictionaryEntity", "categoryCode");
		LKDBResource.addColumn("10003002", "SysDictionaryEntity", "dictCode");
		LKDBResource.addColumn("10003003", "SysDictionaryEntity", "dictName");
		LKDBResource.addColumn("10003004", "SysDictionaryEntity", "orderId");
		LKDBResource.addColumn("10003005", "SysDictionaryEntity", "compId");
		LKDBResource.addColumn("10003006", "SysDictionaryEntity", "usingStatus");
		LKDBResource.addColumn("10003007", "SysDictionaryEntity", "insertTime");
		LKDBResource.addColumn("10003008", "SysDictionaryEntity", "id");
		LKDBResource.addTable("com.lichkin.springframework.entities.impl.SysMenuEntity", "T_SYS_MENU", "SysMenuEntity");
		LKDBResource.addColumn("10004000", "SysMenuEntity", "parentCode");
		LKDBResource.addColumn("10004001", "SysMenuEntity", "menuCode");
		LKDBResource.addColumn("10004002", "SysMenuEntity", "menuName");
		LKDBResource.addColumn("10004003", "SysMenuEntity", "url");
		LKDBResource.addColumn("10004004", "SysMenuEntity", "icon");
		LKDBResource.addColumn("10004005", "SysMenuEntity", "assignable");
		LKDBResource.addColumn("10004006", "SysMenuEntity", "auth");
		LKDBResource.addColumn("10004007", "SysMenuEntity", "onLine");
		LKDBResource.addColumn("10004008", "SysMenuEntity", "id");
		LKDBResource.addTable("com.lichkin.springframework.entities.impl.SysRoleEntity", "T_SYS_ROLE", "SysRoleEntity");
		LKDBResource.addColumn("10005000", "SysRoleEntity", "roleName");
		LKDBResource.addColumn("10005001", "SysRoleEntity", "description");
		LKDBResource.addColumn("10005002", "SysRoleEntity", "compId");
		LKDBResource.addColumn("10005003", "SysRoleEntity", "usingStatus");
		LKDBResource.addColumn("10005004", "SysRoleEntity", "insertTime");
		LKDBResource.addColumn("10005005", "SysRoleEntity", "id");
		LKDBResource.addTable("com.lichkin.springframework.entities.impl.SysRoleMenuEntity", "T_SYS_ROLE_MENU", "SysRoleMenuEntity");
		LKDBResource.addColumn("10006000", "SysRoleMenuEntity", "roleId");
		LKDBResource.addColumn("10006001", "SysRoleMenuEntity", "menuId");
		LKDBResource.addColumn("10006002", "SysRoleMenuEntity", "id");
		LKDBResource.addTable("com.lichkin.springframework.entities.impl.SysAdminLoginEntity", "T_SYS_ADMIN_LOGIN", "SysAdminLoginEntity");
		LKDBResource.addColumn("10007000", "SysAdminLoginEntity", "userName");
		LKDBResource.addColumn("10007001", "SysAdminLoginEntity", "gender");
		LKDBResource.addColumn("10007002", "SysAdminLoginEntity", "photo");
		LKDBResource.addColumn("10007003", "SysAdminLoginEntity", "email");
		LKDBResource.addColumn("10007004", "SysAdminLoginEntity", "pwd");
		LKDBResource.addColumn("10007005", "SysAdminLoginEntity", "securityCode");
		LKDBResource.addColumn("10007006", "SysAdminLoginEntity", "errorTimes");
		LKDBResource.addColumn("10007007", "SysAdminLoginEntity", "level");
		LKDBResource.addColumn("10007008", "SysAdminLoginEntity", "token");
		LKDBResource.addColumn("10007009", "SysAdminLoginEntity", "lockTime");
		LKDBResource.addColumn("10007010", "SysAdminLoginEntity", "superAdmin");
		LKDBResource.addColumn("10007011", "SysAdminLoginEntity", "compId");
		LKDBResource.addColumn("10007012", "SysAdminLoginEntity", "usingStatus");
		LKDBResource.addColumn("10007013", "SysAdminLoginEntity", "insertTime");
		LKDBResource.addColumn("10007014", "SysAdminLoginEntity", "id");
		LKDBResource.addTable("com.lichkin.springframework.entities.impl.SysAdminLoginRoleEntity", "T_SYS_ADMIN_LOGIN_ROLE", "SysAdminLoginRoleEntity");
		LKDBResource.addColumn("10008000", "SysAdminLoginRoleEntity", "loginId");
		LKDBResource.addColumn("10008001", "SysAdminLoginRoleEntity", "roleId");
		LKDBResource.addColumn("10008002", "SysAdminLoginRoleEntity", "id");
		LKDBResource.addTable("com.lichkin.springframework.entities.impl.SysAdminLoginLogEntity", "T_SYS_ADMIN_LOGIN_LOG", "SysAdminLoginLogEntity");
		LKDBResource.addColumn("10009000", "SysAdminLoginLogEntity", "loginId");
		LKDBResource.addColumn("10009001", "SysAdminLoginLogEntity", "requestId");
		LKDBResource.addColumn("10009002", "SysAdminLoginLogEntity", "requestTime");
		LKDBResource.addColumn("10009003", "SysAdminLoginLogEntity", "requestIp");
		LKDBResource.addColumn("10009004", "SysAdminLoginLogEntity", "requestDatas");
		LKDBResource.addColumn("10009005", "SysAdminLoginLogEntity", "compId");
		LKDBResource.addColumn("10009006", "SysAdminLoginLogEntity", "id");
		LKDBResource.addTable("com.lichkin.springframework.entities.impl.SysAdminOperLogEntity", "T_SYS_ADMIN_OPER_LOG", "SysAdminOperLogEntity");
		LKDBResource.addColumn("10010000", "SysAdminOperLogEntity", "loginId");
		LKDBResource.addColumn("10010001", "SysAdminOperLogEntity", "requestId");
		LKDBResource.addColumn("10010002", "SysAdminOperLogEntity", "requestTime");
		LKDBResource.addColumn("10010003", "SysAdminOperLogEntity", "requestIp");
		LKDBResource.addColumn("10010004", "SysAdminOperLogEntity", "requestUrl");
		LKDBResource.addColumn("10010005", "SysAdminOperLogEntity", "requestDatas");
		LKDBResource.addColumn("10010006", "SysAdminOperLogEntity", "operType");
		LKDBResource.addColumn("10010007", "SysAdminOperLogEntity", "busType");
		LKDBResource.addColumn("10010008", "SysAdminOperLogEntity", "compId");
		LKDBResource.addColumn("10010009", "SysAdminOperLogEntity", "id");
		LKDBResource.addTable("com.lichkin.springframework.entities.impl.SysDeptEntity", "T_SYS_DEPT", "SysDeptEntity");
		LKDBResource.addColumn("10011000", "SysDeptEntity", "parentCode");
		LKDBResource.addColumn("10011001", "SysDeptEntity", "deptCode");
		LKDBResource.addColumn("10011002", "SysDeptEntity", "deptName");
		LKDBResource.addColumn("10011003", "SysDeptEntity", "description");
		LKDBResource.addColumn("10011004", "SysDeptEntity", "compId");
		LKDBResource.addColumn("10011005", "SysDeptEntity", "usingStatus");
		LKDBResource.addColumn("10011006", "SysDeptEntity", "insertTime");
		LKDBResource.addColumn("10011007", "SysDeptEntity", "id");
		LKDBResource.addTable("com.lichkin.springframework.entities.impl.SysEmployeeEntity", "T_SYS_EMPLOYEE", "SysEmployeeEntity");
		LKDBResource.addColumn("10012000", "SysEmployeeEntity", "photo");
		LKDBResource.addColumn("10012001", "SysEmployeeEntity", "userName");
		LKDBResource.addColumn("10012002", "SysEmployeeEntity", "cellphone");
		LKDBResource.addColumn("10012003", "SysEmployeeEntity", "email");
		LKDBResource.addColumn("10012004", "SysEmployeeEntity", "userCard");
		LKDBResource.addColumn("10012005", "SysEmployeeEntity", "birthday");
		LKDBResource.addColumn("10012006", "SysEmployeeEntity", "gender");
		LKDBResource.addColumn("10012007", "SysEmployeeEntity", "birthplace");
		LKDBResource.addColumn("10012008", "SysEmployeeEntity", "degree");
		LKDBResource.addColumn("10012009", "SysEmployeeEntity", "education");
		LKDBResource.addColumn("10012010", "SysEmployeeEntity", "maritalStatus");
		LKDBResource.addColumn("10012011", "SysEmployeeEntity", "nation");
		LKDBResource.addColumn("10012012", "SysEmployeeEntity", "usingStatus");
		LKDBResource.addColumn("10012013", "SysEmployeeEntity", "insertTime");
		LKDBResource.addColumn("10012014", "SysEmployeeEntity", "id");
		LKDBResource.addTable("com.lichkin.springframework.entities.impl.SysEmployeeLoginEntity", "T_SYS_EMPLOYEE_LOGIN", "SysEmployeeLoginEntity");
		LKDBResource.addColumn("10013000", "SysEmployeeLoginEntity", "photo");
		LKDBResource.addColumn("10013001", "SysEmployeeLoginEntity", "userId");
		LKDBResource.addColumn("10013002", "SysEmployeeLoginEntity", "loginName");
		LKDBResource.addColumn("10013003", "SysEmployeeLoginEntity", "cellphone");
		LKDBResource.addColumn("10013004", "SysEmployeeLoginEntity", "email");
		LKDBResource.addColumn("10013005", "SysEmployeeLoginEntity", "userCard");
		LKDBResource.addColumn("10013006", "SysEmployeeLoginEntity", "pwd");
		LKDBResource.addColumn("10013007", "SysEmployeeLoginEntity", "securityCode");
		LKDBResource.addColumn("10013008", "SysEmployeeLoginEntity", "errorTimes");
		LKDBResource.addColumn("10013009", "SysEmployeeLoginEntity", "level");
		LKDBResource.addColumn("10013010", "SysEmployeeLoginEntity", "token");
		LKDBResource.addColumn("10013011", "SysEmployeeLoginEntity", "lockTime");
		LKDBResource.addColumn("10013012", "SysEmployeeLoginEntity", "usingStatus");
		LKDBResource.addColumn("10013013", "SysEmployeeLoginEntity", "insertTime");
		LKDBResource.addColumn("10013014", "SysEmployeeLoginEntity", "id");
		LKDBResource.addTable("com.lichkin.springframework.entities.impl.SysEmployeeLoginCompEntity", "T_SYS_EMPLOYEE_LOGIN_COMP", "SysEmployeeLoginCompEntity");
		LKDBResource.addColumn("10014000", "SysEmployeeLoginCompEntity", "loginId");
		LKDBResource.addColumn("10014001", "SysEmployeeLoginCompEntity", "photo");
		LKDBResource.addColumn("10014002", "SysEmployeeLoginCompEntity", "userName");
		LKDBResource.addColumn("10014003", "SysEmployeeLoginCompEntity", "cellphone");
		LKDBResource.addColumn("10014004", "SysEmployeeLoginCompEntity", "email");
		LKDBResource.addColumn("10014005", "SysEmployeeLoginCompEntity", "userCard");
		LKDBResource.addColumn("10014006", "SysEmployeeLoginCompEntity", "birthday");
		LKDBResource.addColumn("10014007", "SysEmployeeLoginCompEntity", "gender");
		LKDBResource.addColumn("10014008", "SysEmployeeLoginCompEntity", "birthplace");
		LKDBResource.addColumn("10014009", "SysEmployeeLoginCompEntity", "degree");
		LKDBResource.addColumn("10014010", "SysEmployeeLoginCompEntity", "education");
		LKDBResource.addColumn("10014011", "SysEmployeeLoginCompEntity", "maritalStatus");
		LKDBResource.addColumn("10014012", "SysEmployeeLoginCompEntity", "nation");
		LKDBResource.addColumn("10014013", "SysEmployeeLoginCompEntity", "jobNumber");
		LKDBResource.addColumn("10014014", "SysEmployeeLoginCompEntity", "jobTitle");
		LKDBResource.addColumn("10014015", "SysEmployeeLoginCompEntity", "entryDate");
		LKDBResource.addColumn("10014016", "SysEmployeeLoginCompEntity", "compId");
		LKDBResource.addColumn("10014017", "SysEmployeeLoginCompEntity", "usingStatus");
		LKDBResource.addColumn("10014018", "SysEmployeeLoginCompEntity", "insertTime");
		LKDBResource.addColumn("10014019", "SysEmployeeLoginCompEntity", "id");
		LKDBResource.addTable("com.lichkin.springframework.entities.impl.SysEmployeeLoginDeptEntity", "T_SYS_EMPLOYEE_LOGIN_DEPT", "SysEmployeeLoginDeptEntity");
		LKDBResource.addColumn("10015000", "SysEmployeeLoginDeptEntity", "loginId");
		LKDBResource.addColumn("10015001", "SysEmployeeLoginDeptEntity", "deptId");
		LKDBResource.addColumn("10015002", "SysEmployeeLoginDeptEntity", "id");
		LKDBResource.addTable("com.lichkin.springframework.entities.impl.SysEmployeeOperLogEntity", "T_SYS_EMPLOYEE_OPER_LOG", "SysEmployeeOperLogEntity");
		LKDBResource.addColumn("10016000", "SysEmployeeOperLogEntity", "loginId");
		LKDBResource.addColumn("10016001", "SysEmployeeOperLogEntity", "requestId");
		LKDBResource.addColumn("10016002", "SysEmployeeOperLogEntity", "requestTime");
		LKDBResource.addColumn("10016003", "SysEmployeeOperLogEntity", "requestIp");
		LKDBResource.addColumn("10016004", "SysEmployeeOperLogEntity", "requestUrl");
		LKDBResource.addColumn("10016005", "SysEmployeeOperLogEntity", "requestDatas");
		LKDBResource.addColumn("10016006", "SysEmployeeOperLogEntity", "operType");
		LKDBResource.addColumn("10016007", "SysEmployeeOperLogEntity", "busType");
		LKDBResource.addColumn("10016008", "SysEmployeeOperLogEntity", "compId");
		LKDBResource.addColumn("10016009", "SysEmployeeOperLogEntity", "id");
		LKDBResource.addTable("com.lichkin.springframework.entities.impl.SysScEntity", "T_SYS_SC", "SysScEntity");
		LKDBResource.addColumn("10017000", "SysScEntity", "securityCode");
		LKDBResource.addColumn("10017001", "SysScEntity", "cellphone");
		LKDBResource.addColumn("10017002", "SysScEntity", "email");
		LKDBResource.addColumn("10017003", "SysScEntity", "usingStatus");
		LKDBResource.addColumn("10017004", "SysScEntity", "insertTime");
		LKDBResource.addColumn("10017005", "SysScEntity", "id");
		LKDBResource.addTable("com.lichkin.springframework.entities.impl.SysSmsLogEntity", "T_SYS_SMS_LOG", "SysSmsLogEntity");
		LKDBResource.addColumn("10018000", "SysSmsLogEntity", "cellphone");
		LKDBResource.addColumn("10018001", "SysSmsLogEntity", "sms");
		LKDBResource.addColumn("10018002", "SysSmsLogEntity", "sendTime");
		LKDBResource.addColumn("10018003", "SysSmsLogEntity", "usingStatus");
		LKDBResource.addColumn("10018004", "SysSmsLogEntity", "insertTime");
		LKDBResource.addColumn("10018005", "SysSmsLogEntity", "id");
		LKDBResource.addTable("com.lichkin.springframework.entities.impl.SysUserEntity", "T_SYS_USER", "SysUserEntity");
		LKDBResource.addColumn("10019000", "SysUserEntity", "userName");
		LKDBResource.addColumn("10019001", "SysUserEntity", "userCard");
		LKDBResource.addColumn("10019002", "SysUserEntity", "birthday");
		LKDBResource.addColumn("10019003", "SysUserEntity", "gender");
		LKDBResource.addColumn("10019004", "SysUserEntity", "usingStatus");
		LKDBResource.addColumn("10019005", "SysUserEntity", "insertTime");
		LKDBResource.addColumn("10019006", "SysUserEntity", "id");
		LKDBResource.addTable("com.lichkin.springframework.entities.impl.SysUserLoginEntity", "T_SYS_USER_LOGIN", "SysUserLoginEntity");
		LKDBResource.addColumn("10020000", "SysUserLoginEntity", "photo");
		LKDBResource.addColumn("10020001", "SysUserLoginEntity", "userId");
		LKDBResource.addColumn("10020002", "SysUserLoginEntity", "loginName");
		LKDBResource.addColumn("10020003", "SysUserLoginEntity", "cellphone");
		LKDBResource.addColumn("10020004", "SysUserLoginEntity", "email");
		LKDBResource.addColumn("10020005", "SysUserLoginEntity", "userCard");
		LKDBResource.addColumn("10020006", "SysUserLoginEntity", "pwd");
		LKDBResource.addColumn("10020007", "SysUserLoginEntity", "securityCode");
		LKDBResource.addColumn("10020008", "SysUserLoginEntity", "errorTimes");
		LKDBResource.addColumn("10020009", "SysUserLoginEntity", "level");
		LKDBResource.addColumn("10020010", "SysUserLoginEntity", "token");
		LKDBResource.addColumn("10020011", "SysUserLoginEntity", "lockTime");
		LKDBResource.addColumn("10020012", "SysUserLoginEntity", "usingStatus");
		LKDBResource.addColumn("10020013", "SysUserLoginEntity", "insertTime");
		LKDBResource.addColumn("10020014", "SysUserLoginEntity", "id");
		LKDBResource.addTable("com.lichkin.springframework.entities.impl.SysUserOperLogEntity", "T_SYS_USER_OPER_LOG", "SysUserOperLogEntity");
		LKDBResource.addColumn("10021000", "SysUserOperLogEntity", "loginId");
		LKDBResource.addColumn("10021001", "SysUserOperLogEntity", "requestId");
		LKDBResource.addColumn("10021002", "SysUserOperLogEntity", "requestTime");
		LKDBResource.addColumn("10021003", "SysUserOperLogEntity", "requestIp");
		LKDBResource.addColumn("10021004", "SysUserOperLogEntity", "requestUrl");
		LKDBResource.addColumn("10021005", "SysUserOperLogEntity", "requestDatas");
		LKDBResource.addColumn("10021006", "SysUserOperLogEntity", "operType");
		LKDBResource.addColumn("10021007", "SysUserOperLogEntity", "busType");
		LKDBResource.addColumn("10021008", "SysUserOperLogEntity", "id");
		LKDBResource.addTable("com.lichkin.springframework.entities.impl.SysAMapLocationEntity", "T_SYS_AMAP_LOCATION", "SysAMapLocationEntity");
		LKDBResource.addColumn("10022000", "SysAMapLocationEntity", "busId");
		LKDBResource.addColumn("10022001", "SysAMapLocationEntity", "locationType");
		LKDBResource.addColumn("10022002", "SysAMapLocationEntity", "accuracy");
		LKDBResource.addColumn("10022003", "SysAMapLocationEntity", "latitude");
		LKDBResource.addColumn("10022004", "SysAMapLocationEntity", "longitude");
		LKDBResource.addColumn("10022005", "SysAMapLocationEntity", "altitude");
		LKDBResource.addColumn("10022006", "SysAMapLocationEntity", "locateTime");
		LKDBResource.addColumn("10022007", "SysAMapLocationEntity", "country");
		LKDBResource.addColumn("10022008", "SysAMapLocationEntity", "province");
		LKDBResource.addColumn("10022009", "SysAMapLocationEntity", "city");
		LKDBResource.addColumn("10022010", "SysAMapLocationEntity", "district");
		LKDBResource.addColumn("10022011", "SysAMapLocationEntity", "street");
		LKDBResource.addColumn("10022012", "SysAMapLocationEntity", "streetNum");
		LKDBResource.addColumn("10022013", "SysAMapLocationEntity", "cityCode");
		LKDBResource.addColumn("10022014", "SysAMapLocationEntity", "adCode");
		LKDBResource.addColumn("10022015", "SysAMapLocationEntity", "bearing");
		LKDBResource.addColumn("10022016", "SysAMapLocationEntity", "aoiName");
		LKDBResource.addColumn("10022017", "SysAMapLocationEntity", "buildingId");
		LKDBResource.addColumn("10022018", "SysAMapLocationEntity", "floor");
		LKDBResource.addColumn("10022019", "SysAMapLocationEntity", "gpsAccuracyStatus");
		LKDBResource.addColumn("10022020", "SysAMapLocationEntity", "id");
	}

}