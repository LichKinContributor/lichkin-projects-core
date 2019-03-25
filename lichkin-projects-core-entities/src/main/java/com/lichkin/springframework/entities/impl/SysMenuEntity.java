package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.lichkin.framework.defines.annotations.DefaultStringValue;
import com.lichkin.framework.defines.entities.I_Menu;
import com.lichkin.springframework.entities.suppers.IDEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 菜单表实体类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@Entity
public class SysMenuEntity extends IDEntity implements I_Menu {

	/** serialVersionUID */
	private static final long serialVersionUID = 10004L;

	/** 上级菜单编码（工具类规则） */
	@Column(length = 64, nullable = false)
	private String parentCode;

	/** 菜单编码（工具类规则） */
	@Column(length = 64, nullable = false)
	private String menuCode;

	/** 菜单名称 */
	@Column(length = 64, nullable = false)
	private String menuName;

	/** true:ROOT权限可用;false:非ROOT权限可用;NULL:通用; */
	@Column
	private Boolean rootOnly;

	/** true:已上线;false:未上线; */
	@Column(nullable = false)
	private Boolean onLine;

	/** true:可分配;false:不可分配; */
	@Column(nullable = false)
	private Boolean assignable;

	/** 图标 */
	@Column(length = 32)
	private String icon;

	/** 链接地址 */
	@DefaultStringValue
	@Column(length = 128, nullable = false)
	private String url;

}
