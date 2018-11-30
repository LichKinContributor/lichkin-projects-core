package com.lichkin.springframework.controllers;

import com.lichkin.application.services.extend.impl.XEmployeeLoginService;
import com.lichkin.framework.beans.impl.Datas;
import com.lichkin.framework.beans.impl.LKRequestBean;
import com.lichkin.framework.defines.entities.I_Dept;
import com.lichkin.framework.defines.entities.I_User;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.framework.defines.exceptions.LKRuntimeException;
import com.lichkin.framework.web.enums.ApiType;
import com.lichkin.springframework.web.LKSession;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * API数据请求控制器类定义
 * @param <CI> 控制器类入参类型
 * @param <CO> 控制器类出参类型
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
public abstract class LKApiController<CI extends LKRequestBean, CO> extends ApiController<CI, CO> {

	@Getter
	@RequiredArgsConstructor
	enum XErrorCodes implements LKCodeEnum {

		/** 不是员工 */
		YOU_ARE_NOT_A_EMPLOYEE(29003),

		/** 没有所属部门 */
		DEPT_INEXIST(29006),

		;

		private final Integer code;

	}


	@SuppressWarnings("incomplete-switch")
	@Override
	void initOthers(ApiType apiType, Datas datas, CI cin, ApiKeyValues<CI> params, boolean fromSession) {
		switch (apiType) {
			case COMPANY_BUSINESS:
				initLogin(datas, cin, params, fromSession, true);
				initComp(datas, cin, params, fromSession);
				initEmployee(datas, cin, params, fromSession);
			break;
			case DEPT_BUSINESS: {
				initLogin(datas, cin, params, fromSession, true);
				initComp(datas, cin, params, fromSession);
				initEmployee(datas, cin, params, fromSession);
				initDept(datas, cin, params, fromSession);
			}
			break;
		}
	}


	/**
	 * 初始化员工信息
	 * @param datas 统一请求参数
	 * @param cin 控制器类入参
	 * @param params 解析值参数
	 * @param fromSession 是否从session中初始化
	 */
	private void initEmployee(Datas datas, CI cin, ApiKeyValues<CI> params, boolean fromSession) {
		I_User employee = fromSession ? LKSession.getUser(session) : ((XEmployeeLoginService) loginService).findEmployeeByUserLoginIdAndCompToken(params.getLoginId(), datas.getCompToken());

		if (employee == null) {
			throw new LKRuntimeException(XErrorCodes.YOU_ARE_NOT_A_EMPLOYEE);
		}

		params.setUser(employee);
	}


	/**
	 * 初始化部门信息
	 * @param datas 统一请求参数
	 * @param cin 控制器类入参
	 * @param params 解析值参数
	 * @param fromSession 是否从session中初始化
	 */
	private void initDept(Datas datas, CI cin, ApiKeyValues<CI> params, boolean fromSession) {
		I_Dept dept = fromSession ? LKSession.getDept(session) : ((XEmployeeLoginService) loginService).findDeptByEmployeeIdAndCompId(params.getUser().getId(), params.getCompId());

		if (dept == null) {
			throw new LKRuntimeException(XErrorCodes.DEPT_INEXIST);
		}

		params.setDept(dept);
	}

}
