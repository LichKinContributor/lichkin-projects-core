package com.lichkin.application.services.impl;

import java.util.Map;
import java.util.Properties;

import org.springframework.stereotype.Service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.lichkin.application.services.LKSmsSender;
import com.lichkin.framework.defines.exceptions.LKFrameworkException;
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

	/** 签名 */
	private static String signName;

	/** 发送短信客户端对象 */
	private static IAcsClient acsClient;

	static {
		try {
			// 从文件中读取配置信息
			Properties configs = LKPropertiesReader.read("/opt/security/aliyun/smsSender.properties");

			String regionId = configs.getProperty("regionId", "cn-hangzhou");

			String accessKeyId = configs.getProperty("accessKeyId");
			String secret = configs.getProperty("secret");
			signName = configs.getProperty("signName");

			// 可自助调整超时时间
			System.setProperty("sun.net.client.defaultConnectTimeout", configs.getProperty("defaultConnectTimeout", "10000"));
			System.setProperty("sun.net.client.defaultReadTimeout", configs.getProperty("defaultReadTimeout", "10000"));

			// 初始化acsClient,暂不支持region化
			IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, secret);
			DefaultProfile.addEndpoint(configs.getProperty("endpointName", "cn-hangzhou"), regionId, configs.getProperty("product", "Dysmsapi"), configs.getProperty("domain", "dysmsapi.aliyuncs.com"));
			acsClient = new DefaultAcsClient(profile);
		} catch (Exception e) {
			throw new LKFrameworkException("aliyun smsSender config error.", e);
		}
	}


	@Override
	public void send(String locale, String busType, String subBusType, String cellphone, String smsContent, Map<String, Object> replaceParams) {
		// 组装请求对象-具体描述见控制台-文档部分内容
		SendSmsRequest request = new SendSmsRequest();
		// 必填:待发送手机号
		request.setPhoneNumbers(cellphone);
		// 必填:短信签名-可在短信控制台中找到
		request.setSignName(signName);
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
