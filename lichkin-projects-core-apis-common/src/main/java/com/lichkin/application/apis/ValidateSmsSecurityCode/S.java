package com.lichkin.application.apis.ValidateSmsSecurityCode;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysScR;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.framework.defines.exceptions.LKRuntimeException;
import com.lichkin.framework.utils.LKDateTimeUtils;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysScEntity;
import com.lichkin.springframework.services.LKApiServiceImpl;
import com.lichkin.springframework.services.LKApiVoidService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service(Statics.SERVICE_NAME)
public class S extends LKApiServiceImpl<I, Void> implements LKApiVoidService<I> {

	@Value("${com.lichkin.api.smsSender.ScTimeoutMinutes:5}")
	private int scTimeoutMinutes;


	@Getter
	@RequiredArgsConstructor
	enum ErrorCodes implements LKCodeEnum {

		app_sc_inexistent(20000),

		app_sc_timeout(20001),

		app_sc_incorrect(20002),

		;

		private final Integer code;

	}


	@Transactional
	@Override
	public void handle(I sin, ApiKeyValues<I> params) throws LKException {
		validateSms(sin.getCellphone(), sin.getSecurityCode());
	}


	/**
	 * 校验验证码
	 * @param cellphone 手机号码
	 * @param securityCode 验证码
	 */
	public void validateSms(String cellphone, String securityCode) {
		QuerySQL sql = new QuerySQL(false, SysScEntity.class);
		sql.eq(SysScR.usingStatus, LKUsingStatusEnum.USING);
		sql.eq(SysScR.cellphone, cellphone);
		SysScEntity sc = dao.getOne(sql, SysScEntity.class);
		if (sc == null) {
			throw new LKRuntimeException(ErrorCodes.app_sc_inexistent);
		}
		if (LKDateTimeUtils.toDateTime(sc.getInsertTime()).plusMinutes(scTimeoutMinutes).isBeforeNow()) {
			throw new LKRuntimeException(ErrorCodes.app_sc_timeout);
		}
		if (!sc.getSecurityCode().equals(securityCode)) {
			throw new LKRuntimeException(ErrorCodes.app_sc_incorrect);
		}
		sc.setUsingStatus(LKUsingStatusEnum.DEPRECATED);
		dao.mergeOne(sc);
	}

}
