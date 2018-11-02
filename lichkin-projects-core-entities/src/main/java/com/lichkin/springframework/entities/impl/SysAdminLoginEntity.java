package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;

import com.lichkin.framework.defines.annotations.ClassGenerator;
import com.lichkin.framework.defines.annotations.DefaultByteValue;
import com.lichkin.framework.defines.annotations.DefaultStringValue;
import com.lichkin.framework.defines.annotations.FieldGenerator;
import com.lichkin.framework.defines.annotations.InsertCheckType;
import com.lichkin.framework.defines.annotations.InsertType;
import com.lichkin.framework.defines.annotations.UpdateCheckType;
import com.lichkin.framework.defines.entities.I_Login;
import com.lichkin.framework.defines.entities.I_User;
import com.lichkin.framework.defines.enums.impl.LKGenderEnum;
import com.lichkin.springframework.entities.suppers.BaseCompEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 管理员登录表实体类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@Entity
@ClassGenerator(

		afterSaveMain = false, IUSubTables = { "SysAdminLoginRoleEntity" }

		, insertCheckType = InsertCheckType.CHECK_RESTORE

		, insertFields = { "String roleIds 角色IDs" }

		, updateCheckType = UpdateCheckType.CHECK

		, updateFields = { "String roleIds 角色IDs" }

		, pageQueryConditions = {}

		, oneResultColumns = { "String roleIds 角色IDs", "String roleNames 角色名称s" }

)
public class SysAdminLoginEntity extends BaseCompEntity implements I_User, I_Login {

	/** serialVersionUID */
	private static final long serialVersionUID = 10007L;

	/** 姓名 */
	@FieldGenerator(queryCondition = true, resultColumn = true)
	@Column(length = 16, nullable = false)
	private String userName;

	/** 性别（枚举） */
	@Enumerated(EnumType.STRING)
	@FieldGenerator(resultColumn = true)
	@Column(length = 7, nullable = false)
	private LKGenderEnum gender;

	/** 邮箱 */
	@FieldGenerator(check = true, insertType = InsertType.CHANGE_RETAIN, updateable = false, queryCondition = true, resultColumn = true)
	@Column(length = 128, nullable = false)
	private String email;

	/** 密码（两次MD5） */
	@FieldGenerator(insertType = InsertType.HANDLE_HANDLE)
	@Column(length = 32, nullable = false)
	private String pwd;

	/** 验证码 */
	@DefaultStringValue
	@FieldGenerator(insertType = InsertType.DEFAULT_DEFAULT)
	@Column(length = 6, nullable = false)
	private String securityCode;

	/** 密码错误次数 */
	@DefaultByteValue
	@FieldGenerator(insertType = InsertType.DEFAULT_DEFAULT)
	@Column(nullable = false)
	private Byte errorTimes;

	/** 账号等级 */
	@DefaultByteValue(1)
	@FieldGenerator(insertType = InsertType.DEFAULT_RETAIN, resultColumn = true)
	@Column(nullable = false)
	private Byte level;

	/** 登录令牌 */
	@DefaultStringValue(DefaultStringValue.TOKEN)
	@FieldGenerator(insertType = InsertType.DEFAULT_DEFAULT)
	@Column(length = 64, nullable = false)
	private String token;

	/** 锁定时间（yyyyMMddHHmmssSSS） */
	@DefaultStringValue(DefaultStringValue.GMT_START)
	@FieldGenerator(insertType = InsertType.DEFAULT_DEFAULT)
	@Column(length = 17, nullable = false)
	private String lockTime;

	/** true:超级管理员;false:普通管理员; */
	@FieldGenerator(insertType = InsertType.HANDLE_RETAIN, updateable = false)
	@Column(nullable = false)
	private Boolean superAdmin;

	/** 照片（Base64） */
	@Lob
	@FieldGenerator
	@Column
	private String photo;

}
