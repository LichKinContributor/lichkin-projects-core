package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.lichkin.framework.defines.annotations.ClassGenerator;
import com.lichkin.framework.defines.annotations.FieldGenerator;
import com.lichkin.framework.defines.annotations.InsertCheckType;
import com.lichkin.framework.defines.annotations.InsertType;
import com.lichkin.framework.defines.annotations.UpdateCheckType;
import com.lichkin.framework.defines.entities.I_CompId;
import com.lichkin.springframework.entities.suppers.OperLogEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 员工操作日志表实体类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@Entity
@ClassGenerator(

		afterSaveMain = false, IUSubTables = {}

		, insertCheckType = InsertCheckType.UNCHECK

		, insertFields = {}

		, updateCheckType = UpdateCheckType.UNCHECK

		, updateFields = {}

		, pageQueryConditions = {

				"String userName 员工姓名 SysEmployeeR", //
				"String cellphone 手机号码 SysEmployeeR", //
				"String startDate 开始日期 #entityR", //
				"String endDate 结束日期 #entityR", //

		}

		, pageResultColumns = {

				"String userName 员工姓名 SysEmployeeR", //
				"String cellphone 手机号码 SysEmployeeR", //

		}

)
public class SysEmployeeOperLogEntity extends OperLogEntity implements I_CompId {

	/** serialVersionUID */
	private static final long serialVersionUID = 10016L;

	/** 公司ID（SysCompEntity.id） */
	@FieldGenerator(check = true, insertType = InsertType.CHANGE_RETAIN, updateable = false, queryCondition = true)
	@Column(length = 64, nullable = false)
	private String compId;

}
