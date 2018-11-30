package com.lichkin.springframework.controllers;

import com.lichkin.framework.defines.entities.I_Dept;
import com.lichkin.framework.defines.entities.I_User;

/**
 * API键值对封装对象
 * @param <CI> 控制器类入参类型
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
public class ApiKeyValues<CI> extends ApiKV<CI> {

	ApiKeyValues(String locale, CI originalObject) {
		super(locale, originalObject);
	}


	/** 键：用户信息 */
	private static final String KEY_USER = "KEY_USER";


	/**
	 * 设置用户信息
	 * @param user 用户信息
	 */
	void setUser(I_User user) {
		putObject(KEY_USER, user);
	}


	/**
	 * 获取用户信息
	 * @return 用户信息
	 */
	public I_User getUser() {
		return map.containsKey(KEY_USER) ? getObject(KEY_USER) : null;
	}


	/** 键：部门信息 */
	private static final String KEY_DEPT = "KEY_DEPT";


	/**
	 * 设置部门信息
	 * @param dept 部门信息
	 */
	void setDept(I_Dept dept) {
		putObject(KEY_DEPT, dept);
	}


	/**
	 * 获取部门信息
	 * @return 部门信息
	 */
	public I_Dept getDept() {
		return map.containsKey(KEY_DEPT) ? getObject(KEY_DEPT) : null;
	}

}
