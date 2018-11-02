package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

import com.lichkin.framework.defines.annotations.ClassGenerator;
import com.lichkin.framework.defines.annotations.FieldGenerator;
import com.lichkin.framework.defines.annotations.InsertCheckType;
import com.lichkin.framework.defines.annotations.UpdateCheckType;
import com.lichkin.framework.defines.entities.I_LoginId;
import com.lichkin.springframework.entities.suppers.CompIDEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 管理员登录日志表实体类
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

				"String userName 登录人姓名 SysAdminLoginR", //
				"String email 登录人邮箱 SysAdminLoginR", //
				"String startDate 开始日期 #entityR", //
				"String endDate 结束日期 #entityR", //

		}

		, pageResultColumns = {

				"String userName 登录人姓名 SysAdminLoginR", //
				"String email 登录人邮箱 SysAdminLoginR", //

		}

)
public class SysAdminLoginLogEntity extends CompIDEntity implements I_LoginId {

	/** serialVersionUID */
	private static final long serialVersionUID = 10009L;

	/** 管理员登录ID（SysAdminLoginEntity.id） */
	@Column(length = 64, nullable = false)
	private String loginId;

	/** 请求ID */
	@FieldGenerator(resultColumn = true)
	@Column(length = 64, nullable = false)
	private String requestId;

	/** 请求时间（yyyyMMddHHmmssSSS） */
	@FieldGenerator(resultColumn = true)
	@Column(length = 17, nullable = false)
	private String requestTime;

	/** 请求IP */
	@FieldGenerator(resultColumn = true)
	@Column(length = 64, nullable = false)
	private String requestIp;

	/** 请求数据 */
	@Lob
	@Column(nullable = false)
	private String requestDatas;
}
