package com.lichkin.application.services.extend.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.Condition;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysCompR;
import com.lichkin.framework.db.beans.SysDeptR;
import com.lichkin.framework.db.beans.SysEmployeeDeptR;
import com.lichkin.framework.db.beans.SysEmployeeR;
import com.lichkin.framework.db.beans.SysUserLoginR;
import com.lichkin.framework.db.beans.eq;
import com.lichkin.framework.db.beans.neq;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.defines.exceptions.LKRuntimeException;
import com.lichkin.springframework.entities.impl.SysCompEntity;
import com.lichkin.springframework.entities.impl.SysDeptEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeDeptEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeEntity;
import com.lichkin.springframework.entities.impl.SysUserLoginEntity;
import com.lichkin.springframework.services.LoginService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
public class XEmployeeLoginService extends LoginService {

	@Getter
	@RequiredArgsConstructor
	enum ErrorCodes implements LKCodeEnum {

		/** 登录信息已失效 */
		INVALIDED_TOKEN(29000),

		/** 账号不存在 */
		ACCOUNT_INEXIST(29001),

		/** 公司信息有误 */
		INVALIDED_COMP_TOKEN(29002),

		/** 不是员工 */
		YOU_ARE_NOT_A_EMPLOYEE(29003),

		/** 员工信息有误 */
		INVALIDED_EMPLOYEE_ID(29004),

		/** 公司信息有误 */
		INVALIDED_COMP_ID(29005),

		/** 没有所属部门 */
		DEPT_INEXIST(29006),

		;

		private final Integer code;

	}


	@Override
	public SysUserLoginEntity findUserLoginByToken(String token) {
		if (StringUtils.isBlank(token)) {
			throw new LKRuntimeException(ErrorCodes.INVALIDED_TOKEN);
		}

		QuerySQL sql = new QuerySQL(false, SysUserLoginEntity.class);

		sql.neq(SysUserLoginR.usingStatus, LKUsingStatusEnum.DEPRECATED);
		sql.eq(SysUserLoginR.token, token);

		SysUserLoginEntity userLogin = dao.getOne(sql, SysUserLoginEntity.class);
		if (userLogin == null) {
			throw new LKRuntimeException(ErrorCodes.ACCOUNT_INEXIST);
		}
		return userLogin;
	}


	public SysEmployeeEntity findEmployeeByUserLoginIdAndCompToken(String userLoginId, String compToken) {
		if (StringUtils.isBlank(userLoginId)) {
			throw new LKRuntimeException(ErrorCodes.ACCOUNT_INEXIST);
		}

		if (StringUtils.isBlank(compToken)) {
			throw new LKRuntimeException(ErrorCodes.INVALIDED_COMP_TOKEN);
		}

		QuerySQL sql = new QuerySQL(false, SysEmployeeEntity.class);

		sql.innerJoin(SysCompEntity.class,

				new Condition(SysCompR.id, SysEmployeeR.compId),

				new Condition(true, new eq(SysCompR.token, compToken)),

				new Condition(true, new neq(SysCompR.usingStatus, LKUsingStatusEnum.DEPRECATED))

		);

		sql.neq(SysEmployeeR.usingStatus, LKUsingStatusEnum.DEPRECATED);
		sql.eq(SysEmployeeR.loginId, userLoginId);

		SysEmployeeEntity employee = dao.getOne(sql, SysEmployeeEntity.class);
		if (employee == null) {
			throw new LKRuntimeException(ErrorCodes.YOU_ARE_NOT_A_EMPLOYEE);
		}
		return employee;
	}


	public SysDeptEntity findDeptByEmployeeIdAndCompId(String employeeId, String compId) {
		if (StringUtils.isBlank(employeeId)) {
			throw new LKRuntimeException(ErrorCodes.INVALIDED_EMPLOYEE_ID);
		}
		if (StringUtils.isBlank(compId)) {
			throw new LKRuntimeException(ErrorCodes.INVALIDED_COMP_ID);
		}

		QuerySQL sql = new QuerySQL(false, SysDeptEntity.class);

		sql.innerJoin(SysEmployeeDeptEntity.class,

				new Condition(SysEmployeeDeptR.deptId, SysDeptR.id),

				new Condition(new eq(SysEmployeeDeptR.employeeId, employeeId))

		);

		sql.eq(SysDeptR.compId, compId);

		List<SysDeptEntity> list = dao.getList(sql, SysDeptEntity.class);
		if (CollectionUtils.isEmpty(list)) {
			throw new LKRuntimeException(ErrorCodes.DEPT_INEXIST);
		}

		return list.get(0);
	}

}
