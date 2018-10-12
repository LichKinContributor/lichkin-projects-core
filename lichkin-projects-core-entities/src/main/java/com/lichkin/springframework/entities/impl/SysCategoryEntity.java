package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.lichkin.framework.defines.annotations.DefaultByteValue;
import com.lichkin.framework.defines.enums.impl.LKYesNoEnum;
import com.lichkin.springframework.entities.suppers.IDEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 类目表实体类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@Entity
public class SysCategoryEntity extends IDEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = 10002L;

	/** 国际化编码 */
	@Column(length = 5, nullable = false)
	private String locale;

	/** 类目编码 */
	@Column(length = 64, nullable = false)
	private String categoryCode;

	/** 类目名称 */
	@Column(length = 64, nullable = false)
	private String categoryName;

	/** 排序号 */
	@DefaultByteValue
	@Column(nullable = false)
	private Byte orderId;

	/** YES:只有ROOT权限可见;NO:非ROOT权限也可见. */
	@Enumerated(EnumType.STRING)
	@Column(length = 3)
	private LKYesNoEnum rootOnly;

	/** 是否需要权限（枚举） */
	@Enumerated(EnumType.STRING)
	@Column(length = 3, nullable = false)
	private LKYesNoEnum auth;

}
