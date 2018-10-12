package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.lichkin.springframework.entities.suppers.IDEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * 员工登录&amp;部门关联表实体类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class SysEmployeeLoginDeptEntity extends IDEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = 10015L;

	/** 员工登录ID（SysEmployeeLoginEntity.id） */
	@NonNull
	@Column(length = 64, nullable = false)
	private String loginId;

	/** 部门ID（SysDeptEntity.id） */
	@NonNull
	@Column(length = 64, nullable = false)
	private String deptId;

}
