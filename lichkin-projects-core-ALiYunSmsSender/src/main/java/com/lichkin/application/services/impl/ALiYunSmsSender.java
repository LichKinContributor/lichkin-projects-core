package com.lichkin.application.services.impl;

import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.lichkin.application.services.LKSmsSender;
import com.lichkin.framework.json.LKJsonUtils;
import com.lichkin.framework.log.LKLog;
import com.lichkin.framework.log.LKLogFactory;
import com.lichkin.framework.utils.i18n.LKI18NUtils;
import com.lichkin.framework.utils.security.properties.LKPropertiesReader;

/**
 * 阿里云短信发送服务类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Service
public class ALiYunSmsSender implements LKSmsSender {

	protected final LKLog logger = LKLogFactory.getLog(getClass());

	/** 云通信短信API产品名称 */
	private static final String product = "Dysmsapi";

	/** 云通信产品域名 */
	private static final String domain = "dysmsapi.aliyuncs.com";

	@Value("${com.lichkin.api.smsSender.aliyun.filePath:/opt/security/aliyun/smsSender.properties}")
	private String fileName;


	@Override
	public void send(String locale, String busType, String subBusType, String cellphone, String smsContent, Map<String, Object> replaceParams) {
		// 从文件中读取配置信息
		Properties configs = LKPropertiesReader.read(fileName);

		// 可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");

		// 初始化acsClient,暂不支持region化
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", configs.getProperty("accessKeyId"), configs.getProperty("accessKeySecret"));
		try {
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		} catch (ClientException e) {
			logger.error("sms send failed.", e);
			return;
		}
		IAcsClient acsClient = new DefaultAcsClient(profile);

		// 组装请求对象-具体描述见控制台-文档部分内容
		SendSmsRequest request = new SendSmsRequest();
		// 必填:待发送手机号
		request.setPhoneNumbers(cellphone);
		// 必填:短信签名-可在短信控制台中找到
		request.setSignName(configs.getProperty("signName"));
		// 必填:短信模板-可在短信控制台中找到
		request.setTemplateCode(LKI18NUtils.getString(LKI18NUtils.getLocale(locale), "api", "com.lichkin.api.sms." + busType + "." + subBusType + ".aliyun.templateCode"));
		// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		request.setTemplateParam(LKJsonUtils.toJson(replaceParams));

		try {
			acsClient.getAcsResponse(request);
		} catch (Exception e) {
			logger.error("sms send failed.", e);
		}
	}

}
