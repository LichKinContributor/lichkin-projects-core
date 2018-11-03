package com.lichkin.application.services.extend.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.Condition;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysDeptR;
import com.lichkin.framework.db.beans.SysEmployeeDeptR;
import com.lichkin.framework.db.beans.SysEmployeeR;
import com.lichkin.framework.db.beans.SysUserLoginR;
import com.lichkin.framework.db.beans.eq;
import com.lichkin.framework.defines.enums.impl.LKErrorCodesEnum;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.defines.exceptions.LKRuntimeException;
import com.lichkin.springframework.entities.impl.SysDeptEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeDeptEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeEntity;
import com.lichkin.springframework.entities.impl.SysUserLoginEntity;
import com.lichkin.springframework.services.LoginService;

@Service
public class XUserLoginService extends LoginService<SysUserLoginEntity, SysUserLoginEntity> {

	@Override
	public SysUserLoginEntity findUserLoginByToken(boolean throwException, String token) {
		if (StringUtils.isBlank(token)) {
			if (throwException) {
				throw new LKRuntimeException(LKErrorCodesEnum.INVALIDED_TOKEN);
			}
			return null;
		}

		QuerySQL sql = new QuerySQL(false, SysUserLoginEntity.class);

		sql.neq(SysUserLoginR.usingStatus, LKUsingStatusEnum.DEPRECATED);
		sql.eq(SysUserLoginR.token, token);

		SysUserLoginEntity userLogin = dao.getOne(sql, SysUserLoginEntity.class);
		if (userLogin == null) {
			if (throwException) {
				throw new LKRuntimeException(LKErrorCodesEnum.ACCOUNT_INEXIST);
			}
			return null;
		}
		return userLogin;
	}


	public SysEmployeeEntity findEmployeeByUserLoginAndCompId(boolean throwException, SysUserLoginEntity userLogin, String compId) {
		if (StringUtils.isBlank(compId)) {
			if (throwException) {
				throw new LKRuntimeException(LKErrorCodesEnum.INVALIDED_COMP_ID);
			}
			return null;
		}

		if (userLogin == null) {
			if (throwException) {
				throw new LKRuntimeException(LKErrorCodesEnum.ACCOUNT_INEXIST);
			}
			return null;
		}

		QuerySQL sql = new QuerySQL(false, SysEmployeeEntity.class);

		sql.eq(SysEmployeeR.compId, compId);
		sql.eq(SysEmployeeR.loginId, userLogin.getId());

		SysEmployeeEntity employee = dao.getOne(sql, SysEmployeeEntity.class);
		if (employee == null) {
			if (throwException) {
				throw new LKRuntimeException(LKErrorCodesEnum.ACCOUNT_INEXIST);
			}
		}
		return employee;
	}


	public SysEmployeeEntity findEmployeeByTokenAndCompId(boolean throwException, String token, String compId) {
		return findEmployeeByUserLoginAndCompId(throwException, findUserLoginByToken(throwException, token), compId);
	}


	public SysDeptEntity findDeptByLoginIdAndCompId(boolean throwException, String employeeId, String compId) {
		if (StringUtils.isBlank(employeeId)) {
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

		sql.innerJoin(SysEmployeeDeptEntity.class,

				new Condition(SysEmployeeDeptR.deptId, SysDeptR.id),

				new Condition(new eq(SysEmployeeDeptR.employeeId, employeeId))

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
