package com.lichkin.springframework.entities.suppers;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;

import com.lichkin.framework.defines.annotations.IgnoreLog;
import com.lichkin.framework.defines.enums.impl.LKMapAPIEnum;

import lombok.Getter;
import lombok.Setter;

/**
 * 位置表实体类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseLocationEntity extends BaseCompEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 图片（BASE64） */
	@Lob
	@IgnoreLog
	@Column
	private String photo;

	/** 位置编码（字典） */
	@Column(length = 64, nullable = false)
	private String locationCode;

	/** 地图API类型（枚举） */
	@Enumerated(EnumType.STRING)
	@Column(length = 4, nullable = false)
	private LKMapAPIEnum mapType;

	/** 纬度 */
	@Column(nullable = false)
	private Double latitude;

	/** 经度 */
	@Column(nullable = false)
	private Double longitude;

	/** 高度 */
	@Column(nullable = false)
	private Double altitude;

}
