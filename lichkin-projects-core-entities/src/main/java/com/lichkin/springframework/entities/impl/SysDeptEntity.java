package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.lichkin.framework.defines.entities.I_Dept;
import com.lichkin.springframework.entities.suppers.BaseCompEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 部门表实体类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@Entity
public class SysDeptEntity extends BaseCompEntity implements I_Dept {

	/** serialVersionUID */
	private static final long serialVersionUID = 10011L;

	/** 上级部门编码（工具类规则） */
	@Column(length = 64, nullable = false)
	private String parentCode;

	/** 部门编码（工具类规则） */
	@Column(length = 64, nullable = false)
	private String deptCode;

	/** 部门名称 */
	@Column(length = 64, nullable = false)
	private String deptName;

	/** 部门全称 */
	@Column(length = 256, nullable = false)
	private String fullName;

	/** 部门简介 */
	@Column(length = 128)
	private String description;

}
