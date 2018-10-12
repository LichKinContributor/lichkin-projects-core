package com.lichkin.application.services.extend.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.Condition;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysDeptR;
import com.lichkin.framework.db.beans.SysEmployeeLoginCompR;
import com.lichkin.framework.db.beans.SysEmployeeLoginDeptR;
import com.lichkin.framework.db.beans.SysEmployeeLoginR;
import com.lichkin.framework.db.beans.eq;
import com.lichkin.framework.defines.enums.impl.LKErrorCodesEnum;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.defines.exceptions.LKRuntimeException;
import com.lichkin.springframework.entities.impl.SysDeptEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeLoginCompEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeLoginDeptEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeLoginEntity;
import com.lichkin.springframework.services.LoginService;

@Service
public class XEmployeeLoginService extends LoginService<SysEmployeeLoginEntity, SysEmployeeEntity> {

	@Override
	public SysEmployeeLoginEntity findUserLoginByToken(boolean throwException, String token) {
		if (StringUtils.isBlank(token)) {
			if (throwException) {
				throw new LKRuntimeException(LKErrorCodesEnum.INVALIDED_TOKEN);
			}
			return null;
		}

		QuerySQL sql = new QuerySQL(false, SysEmployeeLoginEntity.class);

		sql.neq(SysEmployeeLoginR.usingStatus, LKUsingStatusEnum.DEPRECATED);
		sql.eq(SysEmployeeLoginR.token, token);

		SysEmployeeLoginEntity userLogin = dao.getOne(sql, SysEmployeeLoginEntity.class);
		if (userLogin == null) {
			if (throwException) {
				throw new LKRuntimeException(LKErrorCodesEnum.ACCOUNT_INEXIST);
			}
			return null;
		}
		return userLogin;
	}


	@Override
	public SysEmployeeEntity findUserByUserLogin(boolean throwException, SysEmployeeLoginEntity userLogin) {
		if (userLogin == null) {
			if (throwException) {
				throw new LKRuntimeException(LKErrorCodesEnum.INVALIDED_USER_LOGIN);
			}
			return null;
		}

		SysEmployeeEntity user = dao.findOneById(SysEmployeeEntity.class, userLogin.getUserId());
		if (user == null) {
			if (throwException) {
				throw new LKRuntimeException(LKErrorCodesEnum.ACCOUNT_INEXIST);
			}
		}
		return user;
	}


	public SysEmployeeLoginCompEntity findEmployeeLoginCompByUserLoginAndCompId(boolean throwException, SysEmployeeLoginEntity employeeLogin, String compId) {
		if (employeeLogin == null) {
			if (throwException) {
				throw new LKRuntimeException(LKErrorCodesEnum.INVALIDED_USER_LOGIN);
			}
			return null;
		}
		if (StringUtils.isBlank(compId)) {
			if (throwException) {
				throw new LKRuntimeException(LKErrorCodesEnum.INVALIDED_COMP_ID);
			}
			return null;
		}

		QuerySQL sql = new QuerySQL(false, SysEmployeeLoginCompEntity.class);

		sql.eq(SysEmployeeLoginCompR.compId, compId);
		sql.eq(SysEmployeeLoginCompR.loginId, employeeLogin.getId());

		SysEmployeeLoginCompEntity employeeLoginComp = dao.getOne(sql, SysEmployeeLoginCompEntity.class);
		if (employeeLoginComp == null) {
			if (throwException) {
				throw new LKRuntimeException(LKErrorCodesEnum.ACCOUNT_INEXIST);
			}
		}
		return employeeLoginComp;
	}


	public SysDeptEntity findDeptByLoginIdAndCompId(boolean throwException, String loginId, String compId) {
		if (StringUtils.isBlank(loginId)) {
			if (throwException) {
				throw new LKRuntimeException(LKErrorCodesEnum.INVALIDED_USER_LOGIN);
			}
			return null;
		}
		if (StringUtils.isBlank(compId)) {
			if (throwException) {
				throw new LKRuntimeException(LKErrorCodesEnum.INVALIDED_COMP_ID);
			}
			return null;
		}

		QuerySQL sql = new QuerySQL(false, SysDeptEntity.class);

		sql.innerJoin(SysEmployeeLoginDeptEntity.class,

				new Condition(SysEmployeeLoginDeptR.deptId, SysDeptR.id),

				new Condition(new eq(SysEmployeeLoginDeptR.loginId, loginId))

		);

		sql.eq(SysDeptR.compId, compId);

		List<SysDeptEntity> list = dao.getList(sql, SysDeptEntity.class);
		if (CollectionUtils.isEmpty(list)) {
			if (throwException) {
				throw new LKRuntimeException(LKErrorCodesEnum.DEPT_INEXIST);
			}
			return null;
		}

		return list.get(0);
	}

}
