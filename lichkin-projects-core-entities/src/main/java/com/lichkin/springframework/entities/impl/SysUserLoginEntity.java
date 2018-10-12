package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

import com.lichkin.framework.defines.annotations.DefaultByteValue;
import com.lichkin.framework.defines.annotations.DefaultStringValue;
import com.lichkin.framework.defines.entities.I_Login;
import com.lichkin.springframework.entities.suppers.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户登录表实体类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@Entity
public class SysUserLoginEntity extends BaseEntity implements I_Login {

	/** serialVersionUID */
	private static final long serialVersionUID = 10020L;

	/** 照片（Base64） */
	@Lob
	@Column
	private String photo;

	/** 用户ID（SysUserEntity.id） */
	@Column(length = 64, nullable = false)
	private String userId;

	/** 登录名 */
	@Column(length = 64, nullable = false)
	private String loginName;

	/** 手机号码 */
	@Column(length = 11, nullable = false)
	private String cellphone;

	/** 邮箱 */
	@DefaultStringValue
	@Column(length = 128, nullable = false)
	private String email;

	/** 身份证号（自定义加密） */
	@DefaultStringValue
	@Column(length = 64, nullable = false)
	private String userCard;

	/** 密码（两次MD5） */
	@DefaultStringValue
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

}
