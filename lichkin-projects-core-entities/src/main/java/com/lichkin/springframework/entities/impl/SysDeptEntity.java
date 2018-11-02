package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.lichkin.framework.defines.annotations.ClassGenerator;
import com.lichkin.framework.defines.annotations.FieldGenerator;
import com.lichkin.framework.defines.annotations.InsertCheckType;
import com.lichkin.framework.defines.annotations.InsertType;
import com.lichkin.framework.defines.annotations.UpdateCheckType;
import com.lichkin.springframework.entities.suppers.BaseCompEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 部门表实体类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@Entity
@ClassGenerator(

		afterSaveMain = false, IUSubTables = {}

		, insertCheckType = InsertCheckType.CHECK_RESTORE

		, updateCheckType = UpdateCheckType.CHECK

)
public class SysDeptEntity extends BaseCompEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = 10011L;

	/** 上级部门编码（工具类规则） */
	@FieldGenerator(check = true, insertType = InsertType.COPY_ERROR, updateable = false)
	@Column(length = 64, nullable = false)
	private String parentCode;

	/** 部门编码（工具类规则） */
	@FieldGenerator(insertType = InsertType.HANDLE_RETAIN, updateable = false)
	@Column(length = 64, nullable = false)
	private String deptCode;

	/** 部门名称 */
	@FieldGenerator(check = true)
	@Column(length = 64, nullable = false)
	private String deptName;

	/** 部门全称 */
	@FieldGenerator(insertType = InsertType.HANDLE_HANDLE, updateable = false)
	@Column(length = 256, nullable = false)
	private String fullName;

	/** 部门简介 */
	@FieldGenerator
	@Column(length = 128)
	private String description;

}
