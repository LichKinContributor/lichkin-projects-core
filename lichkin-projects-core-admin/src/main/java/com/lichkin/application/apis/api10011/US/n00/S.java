package com.lichkin.application.apis.api10011.US.n00;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysDeptR;
import com.lichkin.framework.db.beans.SysEmployeeDeptR;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.framework.defines.exceptions.LKRuntimeException;
import com.lichkin.springframework.entities.impl.SysDeptEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeDeptEntity;
import com.lichkin.springframework.services.LKApiBusUpdateUsingStatusService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service("SysDeptUS00Service")
public class S extends LKApiBusUpdateUsingStatusService<I, SysDeptEntity> {

	@Override
	protected int getIdColumnResId() {
		return SysDeptR.id;
	}


	@Getter
	@RequiredArgsConstructor
	enum ErrorCodes implements LKCodeEnum {

		SysDept_can_not_delete_dept_when_has_employee(20000),

		;

		private final Integer code;

	}


	@Override
	protected void beforeSaveMain(I sin, String locale, String compId, String loginId, SysDeptEntity entity, String id) {
		QuerySQL sql = new QuerySQL(SysEmployeeDeptEntity.class);
		sql.eq(SysEmployeeDeptR.deptId, id);
		List<SysEmployeeDeptEntity> list = dao.getList(sql, SysEmployeeDeptEntity.class);
		// 部门中存在员工，不可删除
		if (CollectionUtils.isNotEmpty(list)) {
			throw new LKRuntimeException(ErrorCodes.SysDept_can_not_delete_dept_when_has_employee);
		}
	}
}
