package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.lichkin.framework.defines.entities.I_Locale;
import com.lichkin.framework.defines.enums.impl.CategoryAuthTypeEnum;
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
public class SysCategoryEntity extends IDEntity implements I_Locale {

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
	@Column(nullable = false)
	private Byte sortId;

	/** 权限类型（枚举） */
	@Enumerated(EnumType.STRING)
	@Column(length = 6, nullable = false)
	private CategoryAuthTypeEnum authType;

}
