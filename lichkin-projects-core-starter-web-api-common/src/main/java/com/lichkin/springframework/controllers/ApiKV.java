package com.lichkin.springframework.controllers;

import com.lichkin.framework.beans.impl.KeyValues;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.defines.Platform;
import com.lichkin.framework.defines.beans.LKPageable;
import com.lichkin.framework.defines.entities.I_Comp;
import com.lichkin.framework.defines.entities.I_CompId;
import com.lichkin.framework.defines.entities.I_ID;
import com.lichkin.framework.defines.entities.I_Locale;
import com.lichkin.framework.defines.entities.I_Login;
import com.lichkin.framework.defines.entities.I_UsingStatus;
import com.lichkin.framework.defines.enums.LKPlatform;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;

/**
 * API键值对封装对象
 * @param <CI> 控制器类入参类型
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
class ApiKV<CI> extends KeyValues {

	/** 键：国际化 */
	private static final String KEY_LOCALE = "KEY_LOCALE";

	/** 键：原参数对象 */
	private static final String KEY_ORIGINAL_OBJECT = "KEY_ORIGINAL_OBJECT";


	/**
	 * 构造方法
	 * @param locale 国际化
	 * @param originalObject 原参数对象
	 */
	ApiKV(String locale, CI originalObject) {
		putString(KEY_LOCALE, locale);
		putObject(KEY_ORIGINAL_OBJECT, originalObject);
	}


	/**
	 * 获取国际化
	 * <pre>
	 *   CI实现了I_Locale接口
	 *     如取到值则使用该值
	 *     如取不到值则使用解析值
	 *   CI未实现I_locale接口，使用解析值。
	 * </pre>
	 * @return 国际化
	 */
	public String getLocale() {
		CI cin = getOriginalObject();
		if (cin instanceof I_Locale) {
			String locale = ((I_Locale) cin).getLocale();
			if (locale != null) {
				return locale;
			}
		}
		return getString(KEY_LOCALE);
	}


	/**
	 * 获取原参数对象
	 * @return 原参数对象
	 */
	public CI getOriginalObject() {
		return getObject(KEY_ORIGINAL_OBJECT);
	}


	/**
	 * 获取ID
	 * @return ID
	 */
	public String getId() {
		CI cin = getOriginalObject();
		return (cin instanceof I_ID) ? ((I_ID) cin).getId() : null;
	}


	/**
	 * 获取在用状态
	 * @return 在用状态
	 */
	public LKUsingStatusEnum getUsingStatus() {
		CI cin = getOriginalObject();
		return (cin instanceof I_UsingStatus) ? ((I_UsingStatus) cin).getUsingStatus() : null;
	}


	/**
	 * 获取分页信息
	 * @return 分页信息
	 */
	public LKPageable getPageable() {
		CI cin = getOriginalObject();
		return (cin instanceof LKPageable) ? ((LKPageable) cin) : null;
	}


	/** 键：公司信息 */
	private static final String KEY_COMP = "KEY_COMP";


	/**
	 * 设置公司信息
	 * @param comp 公司信息
	 */
	void setComp(I_Comp comp) {
		putObject(KEY_COMP, comp);
	}


	/**
	 * 获取公司信息
	 * @return 公司信息
	 */
	public I_Comp getComp() {
		return map.containsKey(KEY_COMP) ? getObject(KEY_COMP) : null;
	}


	/**
	 * 获取公司ID
	 * @return 公司ID
	 */
	public String getCompId() {
		I_Comp comp = getComp();
		return comp != null ? comp.getId() : null;
	}


	/**
	 * 获取公司ID
	 * <pre>
	 *   非ROOT权限取值为null
	 *   ROOT权限且CI实现了I_CompId接口，并且传入了参数，将返回该值，否则返回null。
	 * </pre>
	 * @return 公司ID
	 */
	public String getBusCompId() {
		if (LKPlatform.ADMIN.equals(Platform.PLATFORM) && LKFrameworkStatics.LichKin.equals(getCompId())) {
			CI cin = getOriginalObject();
			if (cin instanceof I_CompId) {
				return ((I_CompId) cin).getCompId();
			}
		}
		return null;
	}


	/** 键：登录信息 */
	private static final String KEY_LOGIN = "KEY_LOGIN";


	/**
	 * 设置登录信息
	 * @param login 登录信息
	 */
	void setLogin(I_Login login) {
		putObject(KEY_LOGIN, login);
	}


	/**
	 * 获取登录信息
	 * @return 登录信息
	 */
	public I_Login getLogin() {
		return map.containsKey(KEY_LOGIN) ? getObject(KEY_LOGIN) : null;
	}


	/**
	 * 获取登录ID
	 * @return 登录ID
	 */
	public String getLoginId() {
		I_Login login = getLogin();
		return login != null ? login.getId() : null;
	}

}
