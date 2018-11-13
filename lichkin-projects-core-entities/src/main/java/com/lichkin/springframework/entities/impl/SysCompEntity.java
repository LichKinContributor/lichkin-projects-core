package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

import com.lichkin.framework.defines.annotations.ClassGenerator;
import com.lichkin.framework.defines.annotations.FieldGenerator;
import com.lichkin.framework.defines.annotations.InsertCheckType;
import com.lichkin.framework.defines.annotations.InsertType;
import com.lichkin.framework.defines.annotations.UpdateCheckType;
import com.lichkin.framework.defines.entities.I_Comp;
import com.lichkin.springframework.entities.suppers.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 公司表实体类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@Entity
@ClassGenerator(

		afterSaveMain = false, IUSubTables = {}

		, insertCheckType = InsertCheckType.CHECK_RESTORE

		, updateCheckType = UpdateCheckType.UNCHECK

)
public class SysCompEntity extends BaseEntity implements I_Comp {

	/** serialVersionUID */
	private static final long serialVersionUID = 10001L;

	/** 上级公司编码（工具类规则） */
	@FieldGenerator(check = true, insertType = InsertType.COPY_ERROR, updateable = false)
	@Column(length = 64, nullable = false)
	private String parentCode;

	/** 公司编码（工具类规则） */
	@FieldGenerator(insertType = InsertType.HANDLE_RETAIN, updateable = false)
	@Column(length = 64, nullable = false)
	private String compCode;

	/** 公司名称 */
	@FieldGenerator(check = true, queryCondition = true, resultColumn = true)
	@Column(length = 64, nullable = false)
	private String compName;

	/** 公司标识 */
	@FieldGenerator(check = true, insertType = InsertType.COPY_ERROR, updateable = false, resultColumn = true)
	@Column(length = 16, nullable = false)
	private String compKey;

	/** 令牌 */
	@Column(length = 64)
	private String token;

	/** 联系电话 */
	@FieldGenerator(resultColumn = true)
	@Column(length = 16)
	private String telephone;

	/** 联系邮箱 */
	@FieldGenerator(queryCondition = true, resultColumn = true)
	@Column(length = 128)
	private String email;

	/** 详细地址 */
	@FieldGenerator(resultColumn = true)
	@Column(length = 128)
	private String address;

	/** 公司网站 */
	@FieldGenerator(resultColumn = true)
	@Column(length = 128)
	private String website;

	/** 公司简介 */
	@FieldGenerator
	@Column(length = 1024)
	private String description;

	/** 联系人姓名 */
	@FieldGenerator(queryCondition = true, resultColumn = true)
	@Column(length = 128)
	private String linkmanName;

	/** 联系人手机号码 */
	@FieldGenerator(queryCondition = true, resultColumn = true)
	@Column(length = 11)
	private String linkmanCellphone;

	/** 照片（Base64） */
	@Lob
	@FieldGenerator
	@Column(nullable = false)
	private String photo;

}
