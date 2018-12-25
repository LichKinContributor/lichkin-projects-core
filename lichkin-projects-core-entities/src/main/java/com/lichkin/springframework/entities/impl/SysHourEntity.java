package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.lichkin.springframework.entities.suppers.IDEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 小时表实体类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@Entity
public class SysHourEntity extends IDEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = 10025L;

	/** 开始分钟 */
	@Column(nullable = false)
	private Short startMinutes;

	/** 结束分钟 */
	@Column(nullable = false)
	private Short endMinutes;

	/** 小时显示值 */
	@Column(nullable = false)
	private String hourText1;

	/** 小时显示值 */
	@Column(nullable = false)
	private String hourText2;

}
