package com.lichkin.springframework.web;

import static com.lichkin.framework.defines.LKSessionStatics.KEY_DEPT;

import javax.servlet.http.HttpSession;

import com.lichkin.framework.defines.entities.I_Dept;

/**
 * 会话信息
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
public class LKSession extends Session {

	/**
	 * 设置部门信息
	 * @param session HttpSession
	 * @param dept 部门信息
	 */
	public static void setDept(HttpSession session, I_Dept dept) {
		session.setAttribute(KEY_DEPT, dept);
	}


	/**
	 * 获取部门信息
	 * @param session HttpSession
	 * @return 部门信息
	 */
	public static I_Dept getDept(HttpSession session) {
		Object dept = session.getAttribute(KEY_DEPT);
		return dept == null ? null : (I_Dept) dept;
	}

}
