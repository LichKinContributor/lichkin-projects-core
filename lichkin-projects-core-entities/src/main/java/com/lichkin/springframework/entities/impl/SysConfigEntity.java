package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.lichkin.springframework.entities.suppers.CompIDEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 系统配置表实体类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@Entity
public class SysConfigEntity extends CompIDEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = 10000L;

	/** 配置键 */
	@Column(length = 128, nullable = false)
	private String configKey;

	/** 配置值 */
	@Column(length = 128, nullable = false)
	private String configValue;

	/** 配置说明 */
	@Column(length = 128, nullable = false)
	private String remarks;

}
