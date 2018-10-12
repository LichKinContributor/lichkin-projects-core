package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;

import com.lichkin.framework.defines.entities.I_User;
import com.lichkin.framework.defines.enums.impl.LKGenderEnum;
import com.lichkin.springframework.entities.suppers.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 员工表实体类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@Entity
public class SysEmployeeEntity extends BaseEntity implements I_User {

	/** serialVersionUID */
	private static final long serialVersionUID = 10012L;

	/** 照片（Base64） */
	@Lob
	@Column
	private String photo;

	/** 姓名 */
	@Column(length = 64, nullable = false)
	private String userName;

	/** 手机号码 */
	@Column(length = 11, nullable = false)
	private String cellphone;

	/** 邮箱 */
	@Column(length = 128, nullable = false)
	private String email;

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

	/** 出生地（字典） */
	@Column(length = 64)
	private String birthplace;

	/** 学历（字典） */
	@Column(length = 64)
	private String degree;

	/** 学位（字典） */
	@Column(length = 64)
	private String education;

	/** 婚姻状态（字典） */
	@Column(length = 64)
	private String maritalStatus;

	/** 民族（字典） */
	@Column(length = 64)
	private String nation;

}
