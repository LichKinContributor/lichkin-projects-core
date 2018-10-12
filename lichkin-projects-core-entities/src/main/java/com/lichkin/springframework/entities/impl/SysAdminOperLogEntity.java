package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;

import com.lichkin.framework.defines.enums.impl.LKOperTypeEnum;
import com.lichkin.springframework.entities.suppers.CompIDEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 管理员操作日志表实体类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@Entity
public class SysAdminOperLogEntity extends CompIDEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = 10010L;

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

	/** 请求URL */
	@Column(length = 128, nullable = false)
	private String requestUrl;

	/** 请求数据 */
	@Lob
	@Column(nullable = false)
	private String requestDatas;

	/** 操作类型（枚举） */
	@Enumerated(EnumType.STRING)
	@Column(length = 6, nullable = false)
	private LKOperTypeEnum operType;

	/** 业务操作类型（字典） */
	@Column(length = 64, nullable = false)
	private String busType;

}
