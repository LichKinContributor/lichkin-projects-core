package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;

import com.lichkin.framework.defines.entities.I_User;
import com.lichkin.framework.defines.enums.impl.LKGenderEnum;
import com.lichkin.springframework.entities.suppers.BaseCompEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 员工登录&amp;公司关联表实体类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@Entity
public class SysEmployeeLoginCompEntity extends BaseCompEntity implements I_User {

	/** serialVersionUID */
	private static final long serialVersionUID = 10014L;

	/** 员工登录ID（SysEmployeeLoginEntity.id） */
	@Column(length = 64, nullable = false)
	private String loginId;

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

	/** 员工工号 */
	@Column(length = 8, nullable = false)
	private String jobNumber;

	/** 员工职位（字典） */
	@Column(length = 64)
	private String jobTitle;

	/** 员工入职日期 */
	@Column(length = 10)
	private String entryDate;

}
