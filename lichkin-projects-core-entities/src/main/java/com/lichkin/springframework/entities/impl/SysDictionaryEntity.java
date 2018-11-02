package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.lichkin.framework.defines.annotations.ClassGenerator;
import com.lichkin.framework.defines.annotations.DefaultByteValue;
import com.lichkin.framework.defines.annotations.FieldGenerator;
import com.lichkin.framework.defines.annotations.InsertCheckType;
import com.lichkin.framework.defines.annotations.InsertType;
import com.lichkin.framework.defines.annotations.UpdateCheckType;
import com.lichkin.framework.defines.entities.I_Locale;
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
@ClassGenerator(

		afterSaveMain = false, IUSubTables = {}

		, insertCheckType = InsertCheckType.CHECK_RESTORE

		, updateCheckType = UpdateCheckType.CHECK

)
public class SysDictionaryEntity extends BaseCompEntity implements I_Locale {

	/** serialVersionUID */
	private static final long serialVersionUID = 10003L;

	/** 国际化编码 */
	@FieldGenerator(check = true, insertType = InsertType.CHANGE_RETAIN, updateable = false)
	@Column(length = 5, nullable = false)
	private String locale;

	/** 类目编码（SysCategoryEntity.categoryCode） */
	@FieldGenerator(check = true, insertType = InsertType.COPY_RETAIN, updateable = false, queryCondition = true, queryConditionLike = false)
	@Column(length = 64, nullable = false)
	private String categoryCode;

	/** 字典编码 */
	@FieldGenerator(check = true, insertType = InsertType.COPY_ERROR, updateable = false, resultColumn = true)
	@Column(length = 64, nullable = false)
	private String dictCode;

	/** 字典名称 */
	@FieldGenerator(check = true, resultColumn = true)
	@Column(length = 64, nullable = false)
	private String dictName;

	/** 排序号 */
	@DefaultByteValue
	@FieldGenerator(resultColumn = true)
	@Column(nullable = false)
	private Byte sortId;

}
