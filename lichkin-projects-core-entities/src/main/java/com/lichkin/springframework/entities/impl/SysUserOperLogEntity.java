package com.lichkin.springframework.entities.impl;

import javax.persistence.Entity;

import com.lichkin.framework.defines.annotations.ClassGenerator;
import com.lichkin.framework.defines.annotations.InsertCheckType;
import com.lichkin.framework.defines.annotations.UpdateCheckType;
import com.lichkin.springframework.entities.suppers.OperLogEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户操作日志表实体类
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

				"String loginName 登录名 SysUserLoginR", //
				"String cellphone 手机号码 SysUserLoginR", //
				"String startDate 开始日期 #entityR", //
				"String endDate 结束日期 #entityR", //

		}

		, pageResultColumns = {

				"String loginName 登录名 SysUserLoginR", //
				"String cellphone 手机号码 SysUserLoginR", //

		}

)
public class SysUserOperLogEntity extends OperLogEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = 10021L;

}
