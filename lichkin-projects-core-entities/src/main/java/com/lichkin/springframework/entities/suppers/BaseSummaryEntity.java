package com.lichkin.springframework.entities.suppers;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

/**
 * 汇总表实体类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseSummaryEntity extends CompIDEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 业务ID */
	@Column(length = 64)
	private String sourceId;

	/** 汇总日期（yyyy-MM-dd） */
	@Column(length = 10, nullable = false)
	private String summaryDate;

	/** 汇总分钟 */
	@Column(nullable = false)
	private Short summaryMinutes;

	/** 汇总数量 */
	@Column(nullable = false)
	private Integer quantity;

}
