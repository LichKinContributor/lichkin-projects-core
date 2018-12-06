package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.lichkin.springframework.entities.suppers.IDEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 日历信息表实体类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@Entity
public class SysCalendarEntity extends IDEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = 10023L;

	/** 日期（yyyy-MM-dd） */
	@Column(length = 10, nullable = false)
	private String calendarDate;

	/** 年份 */
	@Column(nullable = false)
	private Short year;

	/** 季度 */
	@Column(nullable = false)
	private Byte quarter;

	/** 月份 */
	@Column(nullable = false)
	private Byte month;

	/** 一年中第几周 */
	@Column(nullable = false)
	private Byte week;

	/** 一月中第几天 */
	@Column(nullable = false)
	private Byte dayOfMonth;

	/** 一周中第几天 */
	@Column(nullable = false)
	private Byte dayOfWeek;

	/** 是否为法定节假日 */
	@Column(nullable = false)
	private Boolean holiday;

}
