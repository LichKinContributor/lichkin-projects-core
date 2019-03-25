package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

import com.lichkin.framework.defines.annotations.IgnoreLog;
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
public class SysCompEntity extends BaseEntity implements I_Comp {

	/** serialVersionUID */
	private static final long serialVersionUID = 10001L;

	/** 上级公司编码（工具类规则） */
	@Column(length = 64, nullable = false)
	private String parentCode;

	/** 公司编码（工具类规则） */
	@Column(length = 64, nullable = false)
	private String compCode;

	/** 公司名称 */
	@Column(length = 64, nullable = false)
	private String compName;

	/** 公司标识 */
	@Column(length = 16, nullable = false)
	private String compKey;

	/** 令牌 */
	@Column(length = 64)
	private String token;

	/** 简名（用于客户端显示） */
	@Column(length = 6)
	private String abbreviation;

	/** 联系电话 */
	@Column(length = 16)
	private String telephone;

	/** 联系邮箱 */
	@Column(length = 128)
	private String email;

	/** 详细地址 */
	@Column(length = 128)
	private String address;

	/** 公司网站 */
	@Column(length = 128)
	private String website;

	/** 公司简介 */
	@Column(length = 1024)
	private String description;

	/** 联系人姓名 */
	@Column(length = 128)
	private String linkmanName;

	/** 联系人手机号码 */
	@Column(length = 11)
	private String linkmanCellphone;

	/** 照片（Base64） */
	@Lob
	@IgnoreLog
	@Column(nullable = false)
	private String photo;

}
