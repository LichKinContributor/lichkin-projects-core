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
 * 管理员登录&amp;角色关联表实体类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class SysAdminLoginRoleEntity extends IDEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = 10008L;

	/** 管理员登录ID（SysAdminLoginEntity.id） */
	@NonNull
	@Column(length = 64, nullable = false)
	private String loginId;

	/** 角色ID（SysRoleEntity.id） */
	@NonNull
	@Column(length = 64, nullable = false)
	private String roleId;

}
