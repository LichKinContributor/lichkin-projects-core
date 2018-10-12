package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.lichkin.framework.defines.entities.I_Role;
import com.lichkin.springframework.entities.suppers.BaseCompEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 角色表实体类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@Entity
public class SysRoleEntity extends BaseCompEntity implements I_Role {

	/** serialVersionUID */
	private static final long serialVersionUID = 10005L;

	/** 角色名称 */
	@Column(length = 64, nullable = false)
	private String roleName;

	/** 描述 */
	@Column(length = 128, nullable = false)
	private String description;

}
