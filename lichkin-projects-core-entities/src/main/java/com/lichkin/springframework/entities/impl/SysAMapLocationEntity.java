package com.lichkin.springframework.entities.impl;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.lichkin.springframework.entities.suppers.IDEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 高德地图定位信息表实体类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
@Entity
public class SysAMapLocationEntity extends IDEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = 10022L;

	/** 业务表ID */
	@Column(length = 64, nullable = false)
	private String busId;

	/** 当前定位结果来源，如网络定位结果，详见定位类型表 */
	@Column
	private Integer locationType;

	/** 精度信息 */
	@Column
	private Float accuracy;

	/** 纬度 */
	@Column
	private Double latitude;

	/** 经度 */
	@Column
	private Double longitude;

	/** 高度 */
	@Column
	private Double altitude;

	/** 定位时间 */
	@Column
	private Long locateTime;

	/** 国家信息 */
	@Column(length = 32)
	private String country;

	/** 省信息 */
	@Column(length = 16)
	private String province;

	/** 城市信息 */
	@Column(length = 16)
	private String city;

	/** 城区信息 */
	@Column(length = 64)
	private String district;

	/** 街道信息 */
	@Column(length = 64)
	private String street;

	/** 街道门牌号信息 */
	@Column(length = 64)
	private String streetNum;

	/** 城市编码 */
	@Column(length = 16)
	private String cityCode;

	/** 地区编码 */
	@Column(length = 16)
	private String adCode;

	/** 方向角 */
	@Column
	private Float bearing;

	/** 当前定位点的AOI信息 */
	@Column(length = 64)
	private String aoiName;

	/** 当前室内定位的建筑物Id */
	@Column(length = 64)
	private String buildingId;

	/** 当前室内定位的楼层 */
	@Column(length = 64)
	private String floor;

	/** GPS的当前状态 */
	@Column
	private Integer gpsAccuracyStatus;

}
