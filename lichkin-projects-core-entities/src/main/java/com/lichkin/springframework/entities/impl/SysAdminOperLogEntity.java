package com.lichkin.springframework.entities.impl;

import javax.persistence.Entity;

import com.lichkin.framework.defines.annotations.ClassGenerator;
import com.lichkin.framework.defines.annotations.InsertCheckType;
import com.lichkin.framework.defines.annotations.UpdateCheckType;
import com.lichkin.springframework.entities.suppers.OperLogEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 管理员操作日志表实体类
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

				"String userName 操作人姓名 SysAdminLoginR", //
				"String email 操作人邮箱 SysAdminLoginR", //
				"String startDate 开始日期 #entityR", //
				"String endDate 结束日期 #entityR", //

		}

		, pageResultColumns = {

				"String userName 操作人姓名 SysAdminLoginR", //
				"String email 操作人邮箱 SysAdminLoginR", //

		}

)
public class SysAdminOperLogEntity extends OperLogEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = 10010L;

}
