package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.lichkin.framework.defines.entities.I_Menu;
import com.lichkin.framework.defines.enums.impl.LKYesNoEnum;
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

	/** 链接地址 */
	@Column(length = 128, nullable = false)
	private String url;

	/** 图标 */
	@Column(length = 32)
	private String icon;

	/** YES:可分配;NO:不可分配; */
	@Enumerated(EnumType.STRING)
	@Column(length = 3, nullable = false)
	private LKYesNoEnum assignable;

	/** YES:ROOT账户可用;NO:公司账户可用;NULL:通用; */
	@Enumerated(EnumType.STRING)
	@Column(length = 3)
	private LKYesNoEnum auth;

	/** YES:已上线;NO:未上线; */
	@Enumerated(EnumType.STRING)
	@Column(length = 3, nullable = false)
	private LKYesNoEnum onLine;

}
