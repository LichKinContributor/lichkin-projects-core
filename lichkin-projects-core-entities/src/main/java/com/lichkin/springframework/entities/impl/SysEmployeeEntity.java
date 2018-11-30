package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;

import com.lichkin.framework.defines.annotations.ClassGenerator;
import com.lichkin.framework.defines.annotations.FieldGenerator;
import com.lichkin.framework.defines.annotations.InsertCheckType;
import com.lichkin.framework.defines.annotations.InsertType;
import com.lichkin.framework.defines.annotations.UpdateCheckType;
import com.lichkin.framework.defines.entities.I_User;
import com.lichkin.framework.defines.enums.impl.LKGenderEnum;
import com.lichkin.springframework.entities.suppers.BaseCompEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 员工表实体类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@Entity
@ClassGenerator(

		afterSaveMain = false, IUSubTables = { "SysEmployeeDeptEntity" }

		, insertCheckType = InsertCheckType.FORCE_CHECK

		, insertFields = { "String deptId 部门ID" }

		, updateCheckType = UpdateCheckType.CHECK

		, updateFields = { "String deptId 部门ID" }

		, pageQueryConditions = {

				"String deptId 部门ID SysDeptR"//
				, "String includes 包含的IDs #entityR"//
				, "String excludes 排除的IDs #entityR"//

		}

		, pageResultColumns = {

				"String deptId 部门ID SysDeptR" //
				, "String fullName 部门名称 SysDeptR"

		}

		, oneResultColumns = { "String deptId 部门ID", "String loginName 登录名" }

)
public class SysEmployeeEntity extends BaseCompEntity implements I_User {

	/** serialVersionUID */
	private static final long serialVersionUID = 10014L;

	/** 用户登录ID（SysUserLoginEntity.id） */
	@FieldGenerator(insertType = InsertType.DEFAULT_DEFAULT, updateable = false)
	@Column(length = 64)
	private String loginId;

	/** 照片（Base64） */
	@Lob
	@FieldGenerator
	@Column
	private String photo;

	/** 姓名 */
	@FieldGenerator(queryCondition = true, resultColumn = true)
	@Column(length = 64, nullable = false)
	private String userName;

	/** 手机号码 */
	@FieldGenerator(check = true, queryCondition = true, resultColumn = true)
	@Column(length = 11, nullable = false)
	private String cellphone;

	/** 邮箱 */
	@FieldGenerator(check = true, queryCondition = true, resultColumn = true)
	@Column(length = 128)
	private String email;

	/** 身份证号（自定义加密） */
	@FieldGenerator(check = true, insertType = InsertType.CHANGE_RETAIN, queryCondition = true, resultColumn = true)
	@Column(length = 64)
	private String userCard;

	/** 生日（yyyy-MM-dd） */
	@FieldGenerator(insertType = InsertType.HANDLE_HANDLE, updateable = false)
	@Column(length = 10)
	private String birthday;

	/** 性别（枚举） */
	@Enumerated(EnumType.STRING)
	@FieldGenerator(queryCondition = true, resultColumn = true)
	@Column(length = 7, nullable = false)
	private LKGenderEnum gender;

	/** 出生地（字典） */
	@FieldGenerator(dictionary = true)
	@Column(length = 64)
	private String birthplace;

	/** 学历（字典） */
	@FieldGenerator(resultColumn = true, dictionary = true)
	@Column(length = 64)
	private String degree;

	/** 学位（字典） */
	@FieldGenerator(resultColumn = true, dictionary = true)
	@Column(length = 64)
	private String education;

	/** 婚姻状态（字典） */
	@FieldGenerator(resultColumn = true, dictionary = true)
	@Column(length = 64)
	private String maritalStatus;

	/** 民族（字典） */
	@FieldGenerator(resultColumn = true, dictionary = true)
	@Column(length = 64)
	private String nation;

	/** 员工工号 */
	@FieldGenerator(check = true, insertType = InsertType.COPY_ERROR, updateable = false, resultColumn = true)
	@Column(length = 8, nullable = false)
	private String jobNumber;

	/** 员工职位（字典） */
	@FieldGenerator(resultColumn = true, dictionary = true)
	@Column(length = 64)
	private String jobTitle;

	/** 员工入职日期 */
	@FieldGenerator(insertType = InsertType.COPY_ERROR, updateable = false, resultColumn = true)
	@Column(length = 10, nullable = false)
	private String entryDate;

}
