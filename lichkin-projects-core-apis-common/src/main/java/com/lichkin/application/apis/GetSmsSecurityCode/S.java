package com.lichkin.application.apis.GetSmsSecurityCode;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lichkin.application.services.LKSmsSender;
import com.lichkin.framework.db.beans.SysScR;
import com.lichkin.framework.db.beans.UpdateSQL;
import com.lichkin.framework.defines.enums.impl.LKRangeTypeEnum;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.framework.utils.LKRandomUtils;
import com.lichkin.framework.utils.LKStringUtils;
import com.lichkin.framework.utils.i18n.LKI18NUtils;
import com.lichkin.springframework.entities.impl.SysScEntity;
import com.lichkin.springframework.entities.impl.SysSmsLogEntity;
import com.lichkin.springframework.services.LKApiServiceImpl;
import com.lichkin.springframework.services.LKApiVoidService;

@Service(Statics.SERVICE_NAME)
public class S extends LKApiServiceImpl<I, Void> implements LKApiVoidService<I> {

	@Autowired
	private LKSmsSender smsSender;


	@Transactional
	@Override
	public void handle(I sin, String locale, String compId, String loginId) throws LKException {
		sendSms(locale, sin.getCellphone(), sin.getBusType());
	}


	/**
	 * 发送验证码
	 * @param locale 国际化
	 * @param cellphone 手机号码
	 * @param busType 业务类型
	 */
	public void sendSms(String locale, String cellphone, String busType) {
		String securityCode = LKRandomUtils.create(6, LKRangeTypeEnum.NUMBER_FULL);
		String smsContent = LKI18NUtils.getString(LKI18NUtils.getLocale(locale), "api", "com.lichkin.api.sms.securityCode." + busType + ".content");

		// 清除已有验证码
		UpdateSQL sql = new UpdateSQL(SysScEntity.class);
		sql.set(SysScR.usingStatus, LKUsingStatusEnum.DEPRECATED);
		sql.eq(SysScR.usingStatus, LKUsingStatusEnum.USING);
		sql.eq(SysScR.cellphone, cellphone);
		dao.update(sql);

		// 保存新验证码
		SysScEntity newSc = new SysScEntity();
		newSc.setCellphone(cellphone);
		newSc.setSecurityCode(securityCode);
		dao.persistOne(newSc);

		// 设置替换参数
		Map<String, Object> replaceParams = new HashMap<>();
		replaceParams.put("code", securityCode);

		// 保存短信记录表
		SysSmsLogEntity sms = new SysSmsLogEntity();
		sms.setUsingStatus(LKUsingStatusEnum.STAND_BY);
		sms.setCellphone(cellphone);
		sms.setSms(LKStringUtils.replaceEL(smsContent, replaceParams));
		dao.persistOne(sms);

		// 发送短信
		new Thread(() -> smsSender.send(locale, "securityCode", busType, cellphone, smsContent, replaceParams)).start();
	}

}
