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
 * 角色&amp;菜单关联表实体类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class SysRoleMenuEntity extends IDEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = 10006L;

	/** 角色ID（SysRoleEntity.id） */
	@NonNull
	@Column(length = 64)
	private String roleId;

	/** 菜单ID（SysMenuEntity.id） */
	@NonNull
	@Column(length = 64)
	private String menuId;

}
