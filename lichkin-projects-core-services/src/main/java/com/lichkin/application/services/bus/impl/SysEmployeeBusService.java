package com.lichkin.application.services.bus.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.Condition;
import com.lichkin.framework.db.beans.DeleteSQL;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysEmployeeDeptR;
import com.lichkin.framework.db.beans.SysEmployeeR;
import com.lichkin.framework.db.beans.eq;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysEmployeeDeptEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeEntity;
import com.lichkin.springframework.services.LKDBService;

@Service
public class SysEmployeeBusService extends LKDBService {

	public List<SysEmployeeEntity> findExist(ApiKeyValues<?> params, String cellphone, String email, String userCard, String jobNumber) {
		QuerySQL sql = new QuerySQL(false, SysEmployeeEntity.class);

		addConditionId(sql, SysEmployeeR.id, params.getId());
//		addConditionLocale(sql, SysEmployeeR.locale, params.getLocale());
		addConditionCompId(true, sql, SysEmployeeR.compId, params.getCompId(), params.getBusCompId());
//		addConditionUsingStatus(true, params.getCompId(), sql, SysEmployeeR.usingStatus, params.getUsingStatus(), LKUsingStatusEnum.USING);

		sql.where(

				new Condition(true,

						new Condition(false, new eq(SysEmployeeR.cellphone, cellphone)),

						new Condition(false, new eq(SysEmployeeR.email, email)),

						new Condition(false, new eq(SysEmployeeR.userCard, userCard)),

						new Condition(false, new eq(SysEmployeeR.jobNumber, jobNumber))

				)

		);

		return dao.getList(sql, SysEmployeeEntity.class);
	}


	public void clearEmployeeDept(String employeeId) {
		DeleteSQL sql = new DeleteSQL(SysEmployeeDeptEntity.class);

		sql.eq(SysEmployeeDeptR.employeeId, employeeId);

		dao.delete(sql);
	}


	public void addEmployeeDept(String employeeId, String deptId) {
		dao.persistOne(new SysEmployeeDeptEntity(employeeId, deptId));
	}


	public String analysisBirthday(String userCard) {
		return StringUtils.isBlank(userCard) ? null : (userCard.substring(6, 10) + "-" + userCard.substring(10, 12) + "-" + userCard.substring(12, 14));
	}


	public String analysisUserCard(String userCard) {
		return userCard;// TODO 加密
	}

}
