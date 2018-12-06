package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.lichkin.framework.defines.enums.impl.HolidayTypeEnum;
import com.lichkin.springframework.entities.suppers.IDEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 法定节假日表实体类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@Entity
public class SysHolidayEntity extends IDEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = 10024L;

	/** 日期（yyyy-MM-dd） */
	@Column(length = 10, nullable = false)
	private String calendarDate;

	/** 法定节假日类型 */
	@Enumerated(EnumType.STRING)
	@Column(length = 1, nullable = false)
	private HolidayTypeEnum holidayType;

}
