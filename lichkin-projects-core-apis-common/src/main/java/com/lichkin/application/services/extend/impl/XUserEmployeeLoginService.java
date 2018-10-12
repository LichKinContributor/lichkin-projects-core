package com.lichkin.application.services.extend.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysEmployeeLoginR;
import com.lichkin.framework.defines.enums.impl.LKErrorCodesEnum;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.defines.exceptions.LKRuntimeException;
import com.lichkin.springframework.entities.impl.SysEmployeeLoginEntity;
import com.lichkin.springframework.entities.impl.SysUserLoginEntity;

@Service
public class XUserEmployeeLoginService extends XEmployeeLoginService {

	@Autowired
	private XUserLoginService userLoginService;


	@Override
	public SysEmployeeLoginEntity findUserLoginByToken(boolean throwException, String token) {
		if (StringUtils.isBlank(token)) {
			if (throwException) {
				throw new LKRuntimeException(LKErrorCodesEnum.INVALIDED_TOKEN);
			}
			return null;
		}

		SysUserLoginEntity userLogin = userLoginService.findUserLoginByToken(throwException, token);
		if (userLogin == null) {
			if (throwException) {
				throw new LKRuntimeException(LKErrorCodesEnum.ACCOUNT_INEXIST);
			}
			return null;
		}

		QuerySQL sql = new QuerySQL(false, SysEmployeeLoginEntity.class);

		sql.neq(SysEmployeeLoginR.usingStatus, LKUsingStatusEnum.DEPRECATED);
		sql.eq(SysEmployeeLoginR.cellphone, userLogin.getCellphone());

		SysEmployeeLoginEntity employeeLogin = dao.getOne(sql, SysEmployeeLoginEntity.class);
		if (employeeLogin == null) {
			if (throwException) {
				throw new LKRuntimeException(LKErrorCodesEnum.ACCOUNT_INEXIST);
			}
			return null;
		}
		return employeeLogin;
	}

}
