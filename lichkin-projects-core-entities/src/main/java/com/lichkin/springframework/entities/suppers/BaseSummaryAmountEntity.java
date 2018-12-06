package com.lichkin.springframework.entities.suppers;

import java.math.BigDecimal;

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
public abstract class BaseSummaryAmountEntity extends BaseSummaryEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 汇总金额 */
	@Column(precision = 10, scale = 2, nullable = false)
	private BigDecimal amount;

}
