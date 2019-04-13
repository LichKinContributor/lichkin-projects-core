package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.lichkin.springframework.entities.suppers.IDEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 积分流水表实体类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@Entity
public class SysAccumulateFlowEntity extends IDEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = 10027L;

	/** 客户端唯一标识（字典） */
	@Column(length = 64, nullable = false)
	private String appKey;

	/** 登录ID。 */
	@Column(length = 64, nullable = false)
	private String loginId;

	/** 积分 */
	@Column(nullable = false)
	private Integer accumulate;

	/** true:增加;false:减少; */
	@Column(nullable = false)
	private Boolean increase;

	/** 备注 */
	@Column(length = 1024, nullable = false)
	private String remarks;

	/** 新增操作时间（yyyyMMddHHmmssSSS） */
	@Column(length = 17, nullable = false, updatable = false)
	private String insertTime;

}
