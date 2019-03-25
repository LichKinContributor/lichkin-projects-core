package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;

import com.lichkin.framework.defines.annotations.DefaultByteValue;
import com.lichkin.framework.defines.annotations.DefaultStringValue;
import com.lichkin.framework.defines.annotations.IgnoreLog;
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
public class SysAdminLoginEntity extends BaseCompEntity implements I_User, I_Login {

	/** serialVersionUID */
	private static final long serialVersionUID = 10007L;

	/** 姓名 */
	@Column(length = 16, nullable = false)
	private String userName;

	/** 性别（枚举） */
	@Enumerated(EnumType.STRING)
	@Column(length = 7, nullable = false)
	private LKGenderEnum gender;

	/** 邮箱 */
	@Column(length = 128, nullable = false)
	private String email;

	/** 密码（两次MD5） */
	@Column(length = 32, nullable = false)
	private String pwd;

	/** 验证码 */
	@DefaultStringValue
	@Column(length = 6, nullable = false)
	private String securityCode;

	/** 密码错误次数 */
	@DefaultByteValue
	@Column(nullable = false)
	private Byte errorTimes;

	/** 账号等级 */
	@DefaultByteValue(1)
	@Column(nullable = false)
	private Byte level;

	/** 登录令牌 */
	@DefaultStringValue(DefaultStringValue.TOKEN)
	@Column(length = 64, nullable = false)
	private String token;

	/** 锁定时间（yyyyMMddHHmmssSSS） */
	@DefaultStringValue(DefaultStringValue.GMT_START)
	@Column(length = 17, nullable = false)
	private String lockTime;

	/** true:超级管理员;false:普通管理员; */
	@Column(nullable = false)
	private Boolean superAdmin;

	/** 照片（Base64） */
	@Lob
	@IgnoreLog
	@Column
	private String photo;

}
