package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;

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
public class SysAdminLoginLogEntity extends CompIDEntity implements I_LoginId {

	/** serialVersionUID */
	private static final long serialVersionUID = 10009L;

	/** 管理员登录ID（SysAdminLoginEntity.id） */
	@Column(length = 64, nullable = false)
	private String loginId;

	/** 请求ID */
	@Column(length = 64, nullable = false)
	private String requestId;

	/** 请求时间（yyyyMMddHHmmssSSS） */
	@Column(length = 17, nullable = false)
	private String requestTime;

	/** 请求IP */
	@Column(length = 64, nullable = false)
	private String requestIp;

}
