package com.lichkin.springframework.controllers;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
import com.lichkin.framework.utils.LKClassUtils;
import com.lichkin.framework.utils.LKDateTimeUtils;
import com.lichkin.framework.utils.LKStringUtils;
import com.lichkin.framework.web.annotations.LKApiType;
import com.lichkin.framework.web.annotations.LKController4Api;
import com.lichkin.framework.web.enums.ApiType;
import com.lichkin.springframework.services.LoginService;
import com.lichkin.springframework.services.OperLogService;
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
		// 取接口类型
		ApiType apiType = ((LKApiType) LKClassUtils.deepGetAnnotation(getClass(), LKApiType.class.getName())).apiType();
		if (apiType == null) {
			throw new LKFrameworkException("LKApiType must config and param apiType can not be null.");
		}

		// 取入参
		Datas datas = cin.getDatas();

		// 设置国际化值
		datas.setLocale(LKRequestUtils.getLocale(request).toString());

		// 根据客户端类型判断是否从session中取值
		boolean fromSession = LKClientTypeEnum.JAVASCRIPT.equals(datas.getClientType());

		// 根据不同接口类型初始化数据
		switch (apiType) {
			case OPEN: {
			}
			break;
			case ROOT_QUERY: {
				I_Comp comp = LichKin.getInstance();
				datas.setComp(comp);
				datas.setCompId(comp.getId());
			}
			break;
			case BEFORE_LOGIN: {
				initLogin(datas, fromSession, false);
			}
			break;
			case PERSONAL_BUSINESS: {
				initLogin(datas, fromSession, true);
			}
			break;
			case COMPANY_QUERY: {
				initComp(datas, fromSession);
			}
			break;
		}

		initOthers(apiType, datas, fromSession);

		LKResponseBean<CO> responseBean = new LKResponseBean<>(handleInvoke(cin, datas.getLocale(), datas.getCompId(), datas.getLoginId()));
		if (saveLog(cin)) {
			saveLog(cin, datas, LKRequestUtils.getRequestURI(request));
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
	 * @param fromSession 是否从session中初始化
	 * @param force 是否强制校验
	 */
	void initLogin(Datas datas, boolean fromSession, boolean force) {
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

		datas.setToken(token);
		datas.setLogin(login);
		datas.setLoginId(login.getId());
	}


	@Autowired
	private XCompService compService;


	/**
	 * 初始化公司信息
	 * @param datas 统一请求参数
	 * @param fromSession 是否从session中初始化
	 */
	void initComp(Datas datas, boolean fromSession) {
		I_Comp comp = fromSession ? LKSession.getComp(session) : compService.findCompByToken(datas.getCompToken());

		if (comp == null) {
			throw new LKRuntimeException(ErrorCodes.COMP_INEXIST);
		}

		datas.setComp(comp);
		datas.setCompId(comp.getId());
	}


	/**
	 * 初始化其它信息
	 * @param apiType 接口类型
	 * @param datas 统一请求参数
	 * @param fromSession 是否从session中初始化
	 */
	abstract void initOthers(ApiType apiType, Datas datas, boolean fromSession);


	/**
	 * 请求处理方法
	 * @param cin 控制器类入参
	 * @param locale 国际化
	 * @param compId 公司ID
	 * @param loginId 登录ID
	 * @return 控制器类出参
	 * @throws LKException 业务处理失败但不希望已处理数据回滚时抛出异常
	 */
	abstract CO handleInvoke(@Valid CI cin, String locale, String compId, String loginId) throws LKException;


	@Autowired
	private OperLogService operLogService;


	/**
	 * 是否记录日志
	 * @param cin 控制器类入参
	 * @return true:记录日志;false:不记录日志;
	 */
	protected boolean saveLog(CI cin) {
		return true;
	}


	/**
	 * 记录日志
	 * @param cin 控制器类入参
	 * @param datas 统一请求参数
	 * @param requestURI 请求地址
	 */
	private void saveLog(CI cin, Datas datas, String requestURI) {
		if (LKFrameworkStatics.LichKin.equals(datas.getCompId())) {
			return;
		}
		try {
			LKRequestInfo requestInfo = (LKRequestInfo) request.getAttribute("requestInfo");
			operLogService.save(

					LKStringUtils.capitalize(Platform.PLATFORM.toString().toLowerCase()),

					StringUtils.trimToEmpty(datas.getCompId()),

					datas.getLoginId(),

					requestInfo.getRequestId(),

					LKDateTimeUtils.toString(requestInfo.getRequestTime()),

					requestInfo.getRequestIp(),

					requestInfo.getRequestUri(),

					requestInfo.getRequestDatas(),

					getOperType(cin),

					getBusType(cin)

			);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 获取操作类型
	 * @param cin 控制器类入参
	 * @return 操作类型
	 */
	protected LKOperTypeEnum getOperType(@Valid CI cin) {
		return LKOperTypeEnum.OTHER;
	}


	/**
	 * 获取业务操作类型
	 * @param cin 控制器类入参
	 * @return 业务操作类型
	 */
	protected String getBusType(CI cin) {
		return null;
	}

}
