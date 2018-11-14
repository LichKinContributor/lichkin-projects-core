package com.lichkin.application.apis.ModifyCellphone;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysUserLoginR;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.framework.defines.exceptions.LKRuntimeException;
import com.lichkin.springframework.entities.impl.SysUserLoginEntity;
import com.lichkin.springframework.services.LKApiServiceImpl;
import com.lichkin.springframework.services.LKApiVoidService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service(Statics.SERVICE_NAME)
public class S extends LKApiServiceImpl<I, Void> implements LKApiVoidService<I> {

	@Getter
	@RequiredArgsConstructor
	enum ErrorCodes implements LKCodeEnum {

		app_cellphone_not_changed(20008),

		app_cellphone_exist(20009),

		;

		private final Integer code;

	}


	@Autowired
	private com.lichkin.application.apis.ValidateSmsSecurityCode.S validateSmsSecurityCodeService;


	@Transactional
	@Override
	public void handle(I sin, String locale, String compId, String loginId) throws LKException {
		String cellphone = sin.getCellphone();
		SysUserLoginEntity login = (SysUserLoginEntity) sin.getDatas().getLogin();

		// 手机号码未修改
		if (login.getCellphone().equals(cellphone)) {
			throw new LKRuntimeException(ErrorCodes.app_cellphone_not_changed);
		}

		// 查询手机号码是否已经存在
		if (findUserLoginByCellphone(cellphone) != null) {
			throw new LKRuntimeException(ErrorCodes.app_cellphone_exist);
		}

		// 校验验证码
		validateSmsSecurityCodeService.validateSms(cellphone, sin.getSecurityCode());

		// 修改手机号码
		login.setCellphone(cellphone);
		dao.mergeOne(login);
	}


	/**
	 * 按照手机号码获取登录信息
	 * @param cellphone 手机号码
	 * @return 登录信息
	 */
	private SysUserLoginEntity findUserLoginByCellphone(String cellphone) {
		if (StringUtils.isBlank(cellphone)) {
			return null;
		}
		QuerySQL sql = new QuerySQL(false, SysUserLoginEntity.class);
		sql.neq(SysUserLoginR.usingStatus, LKUsingStatusEnum.DEPRECATED);
		sql.eq(SysUserLoginR.cellphone, cellphone);

		return dao.getOne(sql, SysUserLoginEntity.class);
	}

}
