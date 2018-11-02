package com.lichkin.application.services;

import java.util.Map;

/**
 * 短信发送服务类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
public interface LKSmsSender {

	/**
	 * 发送短信
	 * @param locale 国际化编码
	 * @param busType 业务类型
	 * @param subBusType 子业务类型
	 * @param cellphone 手机号码
	 * @param smsContent 短信内容
	 * @param replaceParams 替换参数
	 * @param smsContentReplaced 替换后的短信内容
	 */
	public void send(String locale, String busType, String subBusType, String cellphone, String smsContent, Map<String, String> replaceParams, String smsContentReplaced);

}
