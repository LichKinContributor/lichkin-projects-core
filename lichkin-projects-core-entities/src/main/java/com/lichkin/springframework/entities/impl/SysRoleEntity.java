package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.lichkin.framework.defines.annotations.ClassGenerator;
import com.lichkin.framework.defines.annotations.FieldGenerator;
import com.lichkin.framework.defines.annotations.InsertCheckType;
import com.lichkin.framework.defines.annotations.UpdateCheckType;
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
@ClassGenerator(

		afterSaveMain = false, IUSubTables = { "SysRoleMenuEntity" }

		, insertCheckType = InsertCheckType.FORCE_CHECK

		, insertFields = { "String menuIds 菜单IDs" }

		, updateCheckType = UpdateCheckType.CHECK

		, updateFields = { "String menuIds 菜单IDs" }

		, oneResultColumns = { "String menuIds 菜单IDs", "String menuNames 菜单名称s" }

)
public class SysRoleEntity extends BaseCompEntity implements I_Role {

	/** serialVersionUID */
	private static final long serialVersionUID = 10005L;

	/** 角色名称 */
	@FieldGenerator(check = true, queryCondition = true, resultColumn = true)
	@Column(length = 64, nullable = false)
	private String roleName;

	/** 描述 */
	@FieldGenerator(resultColumn = true)
	@Column(length = 128, nullable = false)
	private String description;

}
