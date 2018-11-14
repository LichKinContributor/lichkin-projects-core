package com.lichkin.application.apis.SupplementRegisterInfo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lichkin.framework.db.beans.Condition;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysUserLoginR;
import com.lichkin.framework.db.beans.eq;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.framework.defines.exceptions.LKRuntimeException;
import com.lichkin.framework.utils.security.md5.LKMD5Encrypter;
import com.lichkin.springframework.entities.impl.SysUserLoginEntity;
import com.lichkin.springframework.services.LKApiService;
import com.lichkin.springframework.services.LKApiServiceImpl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service(Statics.SERVICE_NAME)
public class S extends LKApiServiceImpl<I, O> implements LKApiService<I, O> {

	@Getter
	@RequiredArgsConstructor
	enum ErrorCodes implements LKCodeEnum {

		app_account_exist(20006),

		;

		private final Integer code;

	}


	@Transactional
	@Override
	public O handle(I sin, String locale, String compId, String loginId) throws LKException {
		SysUserLoginEntity login = (SysUserLoginEntity) sin.getDatas().getLogin();
		SysUserLoginEntity exist = findUserLoginByLoginName(sin.getLoginName());
		if ((exist != null) && !exist.getId().equals(login.getId())) {
			throw new LKRuntimeException(ErrorCodes.app_account_exist);
		}
		login.setLoginName(sin.getLoginName());
		login.setPwd(LKMD5Encrypter.encrypt(sin.getPwd()));
		dao.mergeOne(login);

		return new O(login.getLoginName());
	}


	private SysUserLoginEntity findUserLoginByLoginName(String loginName) {
		if (StringUtils.isBlank(loginName)) {
			return null;
		}
		QuerySQL sql = new QuerySQL(false, SysUserLoginEntity.class);
		sql.neq(SysUserLoginR.usingStatus, LKUsingStatusEnum.DEPRECATED);
		sql.where(

				new Condition(true,

						new Condition(false, new eq(SysUserLoginR.loginName, loginName)),

						new Condition(false, new eq(SysUserLoginR.userCard, loginName)),

						new Condition(false, new eq(SysUserLoginR.cellphone, loginName)),

						new Condition(false, new eq(SysUserLoginR.email, loginName))

				)

		);

		return dao.getOne(sql, SysUserLoginEntity.class);
	}

}
