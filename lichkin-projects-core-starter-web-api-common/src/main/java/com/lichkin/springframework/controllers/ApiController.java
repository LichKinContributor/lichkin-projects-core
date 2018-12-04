package com.lichkin.springframework.controllers;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.lichkin.application.services.OperLogService;
import com.lichkin.application.services.extend.impl.XCompService;
import com.lichkin.framework.beans.impl.Datas;
import com.lichkin.framework.beans.impl.LKRequestBean;
import com.lichkin.framework.beans.impl.LKResponseBean;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.defines.Platform;
import com.lichkin.framework.defines.beans.impl.LichKin;
import com.lichkin.framework.defines.entities.I_Comp;
import com.lichkin.framework.defines.entities.I_Login;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.framework.defines.enums.impl.LKClientTypeEnum;
import com.lichkin.framework.defines.enums.impl.LKOperTypeEnum;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.framework.defines.exceptions.LKFrameworkException;
import com.lichkin.framework.defines.exceptions.LKRuntimeException;
import com.lichkin.framework.json.LKJsonUtils;
import com.lichkin.framework.utils.LKClassUtils;
import com.lichkin.framework.utils.LKDateTimeUtils;
import com.lichkin.framework.utils.LKStringUtils;
import com.lichkin.framework.web.annotations.LKApiType;
import com.lichkin.framework.web.annotations.LKController4Api;
import com.lichkin.framework.web.enums.ApiType;
import com.lichkin.springframework.services.LoginService;
import com.lichkin.springframework.web.LKSession;
import com.lichkin.springframework.web.beans.LKRequestInfo;
import com.lichkin.springframework.web.utils.LKRequestUtils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * API数据请求控制器类定义
 * @param <CI> 控制器类入参类型
 * @param <CO> 控制器类出参类型
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@LKController4Api
public abstract class ApiController<CI extends LKRequestBean, CO> extends LKController {

	/**
	 * 请求处理方法
	 * @deprecated API必有方法，不可重写。
	 * @param cin 控制器类入参
	 * @return 控制器类出参
	 * @throws LKException 业务处理失败但不希望已处理数据回滚时抛出异常
	 */
	@Deprecated
	@PostMapping
	public LKResponseBean<CO> invoke(@Valid @RequestBody CI cin) throws LKException {
		String requestDatasJson = LKJsonUtils.toJsonWithExcludes(cin, "datas");
		request.setAttribute("requestDatasJson", requestDatasJson);
		if (logger.isDebugEnabled()) {
			logger.debug("{requestId:\"" + ((LKRequestInfo) request.getAttribute("requestInfo")).getRequestId() + "\",requestDatas:" + requestDatasJson + "}");
		}

		// 取接口类型
		ApiType apiType = ((LKApiType) LKClassUtils.deepGetAnnotation(getClass(), LKApiType.class.getName())).apiType();
		if (apiType == null) {
			throw new LKFrameworkException("LKApiType must config and param apiType can not be null.");
		}

		// 创建解析对象，设置国际化值。
		ApiKeyValues<CI> params = new ApiKeyValues<>(LKRequestUtils.getLocale(request).toString(), cin);

		// 取入参
		Datas datas = cin.getDatas();

		// 根据客户端类型判断是否从session中取值
		boolean fromSession = LKClientTypeEnum.JAVASCRIPT.equals(datas.getClientType());

		// 根据不同接口类型初始化数据
		switch (apiType) {
			case OPEN: {
			}
			break;
			case ROOT_QUERY: {
				params.setComp(LichKin.getInstance());
			}
			break;
			case BEFORE_LOGIN: {
				initLogin(datas, cin, params, fromSession, false);
			}
			break;
			case PERSONAL_BUSINESS: {
				initLogin(datas, cin, params, fromSession, true);
			}
			break;
			case COMPANY_QUERY: {
				initComp(datas, cin, params, fromSession);
			}
			break;
		}

		initOthers(apiType, datas, cin, params, fromSession);

		LKResponseBean<CO> responseBean = new LKResponseBean<>(handleInvoke(cin, params));
		if (saveLog(cin, params)) {
			saveLog(cin, params, LKRequestUtils.getRequestURI(request));
		}
		return responseBean;
	}


	@Getter
	@RequiredArgsConstructor
	enum ErrorCodes implements LKCodeEnum {

		/** 没有所属公司 */
		COMP_INEXIST(29007),

		/** 登录信息已失效 */
		INVALIDED_TOKEN(29000),

		/** 账号不存在 */
		ACCOUNT_INEXIST(29001),

		;

		private final Integer code;

	}


	@Autowired
	LoginService loginService;


	/**
	 * 初始化登录信息
	 * @param datas 统一请求参数
	 * @param cin 控制器类入参
	 * @param params 解析值参数
	 * @param fromSession 是否从session中初始化
	 * @param force 是否强制校验
	 */
	void initLogin(Datas datas, CI cin, ApiKeyValues<CI> params, boolean fromSession, boolean force) {
		String token = fromSession ? LKSession.getToken(session) : datas.getToken();

		if (StringUtils.isBlank(token)) {
			if (force) {
				throw new LKRuntimeException(ErrorCodes.INVALIDED_TOKEN);
			} else {
				return;
			}
		}

		I_Login login = fromSession ? LKSession.getLogin(session) : loginService.findUserLoginByToken(token);

		if (login == null) {
			throw new LKRuntimeException(ErrorCodes.ACCOUNT_INEXIST);
		}

		params.setLogin(login);
	}


	@Autowired
	private XCompService compService;


	/**
	 * 初始化公司信息
	 * @param datas 统一请求参数
	 * @param cin 控制器类入参
	 * @param params 解析值参数
	 * @param fromSession 是否从session中初始化
	 */
	void initComp(Datas datas, CI cin, ApiKeyValues<CI> params, boolean fromSession) {
		I_Comp comp = fromSession ? LKSession.getComp(session) : compService.findCompByToken(datas.getCompToken());

		if (comp == null) {
			throw new LKRuntimeException(ErrorCodes.COMP_INEXIST);
		}

		params.setComp(comp);
	}


	/**
	 * 初始化其它信息
	 * @param apiType 接口类型
	 * @param datas 统一请求参数
	 * @param cin 控制器类入参
	 * @param params 解析值参数
	 * @param fromSession 是否从session中初始化
	 */
	abstract void initOthers(ApiType apiType, Datas datas, CI cin, ApiKeyValues<CI> params, boolean fromSession);


	/**
	 * 请求处理方法
	 * @param cin 控制器类入参
	 * @param params 解析值参数
	 * @return 控制器类出参
	 * @throws LKException 业务处理失败但不希望已处理数据回滚时抛出异常
	 */
	abstract CO handleInvoke(CI cin, ApiKeyValues<CI> params) throws LKException;


	@Autowired
	private OperLogService operLogService;


	/**
	 * 是否记录日志
	 * @param cin 控制器类入参
	 * @param params 解析值参数
	 * @return true:记录日志;false:不记录日志;
	 */
	protected boolean saveLog(CI cin, ApiKeyValues<CI> params) {
		return true;
	}


	/**
	 * 记录日志
	 * @param cin 控制器类入参
	 * @param params 解析值参数
	 * @param requestURI 请求地址
	 */
	private void saveLog(CI cin, ApiKeyValues<CI> params, String requestURI) {
		if (LKFrameworkStatics.LichKin.equals(params.getCompId())) {
			return;
		}
		try {
			LKRequestInfo requestInfo = (LKRequestInfo) request.getAttribute("requestInfo");
			operLogService.save(

					LKStringUtils.capitalize(Platform.PLATFORM.toString().toLowerCase()),

					StringUtils.trimToEmpty(params.getCompId()),

					params.getLoginId(),

					requestInfo.getRequestId(),

					LKDateTimeUtils.toString(requestInfo.getRequestTime()),

					requestInfo.getRequestIp(),

					requestInfo.getRequestUri(),

					request.getAttribute("requestDatasJson").toString(),

					getOperType(cin, params)

			);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 获取操作类型
	 * @param cin 控制器类入参
	 * @param params 解析值参数
	 * @return 操作类型
	 */
	protected LKOperTypeEnum getOperType(CI cin, ApiKeyValues<CI> params) {
		return LKOperTypeEnum.OTHER;
	}

}
