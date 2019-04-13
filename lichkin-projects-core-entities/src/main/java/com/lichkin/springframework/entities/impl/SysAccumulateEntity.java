package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.lichkin.springframework.entities.suppers.IDEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 积分表实体类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@Entity
public class SysAccumulateEntity extends IDEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = 10026L;

	/** 客户端唯一标识（字典） */
	@Column(length = 64, nullable = false)
	private String appKey;

	/** 登录ID。 */
	@Column(length = 64, nullable = false)
	private String loginId;

	/** 积分 */
	@Column(nullable = false)
	private Long accumulate;

}
