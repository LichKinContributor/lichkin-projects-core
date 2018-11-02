package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.lichkin.springframework.entities.suppers.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 验证码表实体类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@Entity
public class SysScEntity extends BaseEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = 10017L;

	/** 验证码 */
	@Column(length = 6, nullable = false)
	private String securityCode;

	/** 手机号码 */
	@Column(length = 11)
	private String cellphone;

	/** 邮箱 */
	@Column(length = 128)
	private String email;

}
