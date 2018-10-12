package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.lichkin.framework.defines.entities.I_User;
import com.lichkin.framework.defines.enums.impl.LKGenderEnum;
import com.lichkin.springframework.entities.suppers.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户表实体类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@Entity
public class SysUserEntity extends BaseEntity implements I_User {

	/** serialVersionUID */
	private static final long serialVersionUID = 10019L;

	/** 姓名 */
	@Column(length = 64, nullable = false)
	private String userName;

	/** 身份证号（自定义加密） */
	@Column(length = 64, nullable = false)
	private String userCard;

	/** 生日（yyyy-MM-dd） */
	@Column(length = 10)
	private String birthday;

	/** 性别（枚举） */
	@Enumerated(EnumType.STRING)
	@Column(length = 7, nullable = false)
	private LKGenderEnum gender;

}
