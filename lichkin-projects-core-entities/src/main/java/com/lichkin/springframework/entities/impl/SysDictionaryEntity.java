package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.lichkin.framework.defines.annotations.DefaultByteValue;
import com.lichkin.springframework.entities.suppers.BaseCompEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 字典表实体类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@Entity
public class SysDictionaryEntity extends BaseCompEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = 10003L;

	/** 国际化编码 */
	@Column(length = 5, nullable = false)
	private String locale;

	/** 类目编码（SysCategoryEntity.categoryCode） */
	@Column(length = 64, nullable = false)
	private String categoryCode;

	/** 字典编码 */
	@Column(length = 64, nullable = false)
	private String dictCode;

	/** 字典名称 */
	@Column(length = 64, nullable = false)
	private String dictName;

	/** 排序号 */
	@DefaultByteValue
	@Column(nullable = false)
	private Byte orderId;

}
