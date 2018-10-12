package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.lichkin.springframework.entities.suppers.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 短信记录表实体类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@Entity
public class SysSmsLogEntity extends BaseEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = 10018L;

	/** 手机号码 */
	@Column(length = 11, nullable = false)
	private String cellphone;

	/** 短信内容 */
	@Column(length = 128, nullable = false)
	private String sms;

	/** 发送时间（yyyyMMddHHmmssSSS） */
	@Column(length = 17)
	private String sendTime;

}
