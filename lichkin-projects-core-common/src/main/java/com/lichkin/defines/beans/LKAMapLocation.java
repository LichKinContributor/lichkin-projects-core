package com.lichkin.defines.beans;

import lombok.Getter;
import lombok.Setter;

/**
 * 高德地图定位信息
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Getter
@Setter
public class LKAMapLocation {

	/** 当前定位结果来源，如网络定位结果，详见定位类型表 */
	private Integer locationType;

	/** 精度信息 */
	private Float accuracy;

	/** 纬度 */
	private Double latitude;

	/** 经度 */
	private Double longitude;

	/** 高度 */
	private Double altitude;

	/** 定位时间 */
	private Long time;

	/** 国家信息 */
	private String country;

	/** 省信息 */
	private String province;

	/** 城市信息 */
	private String city;

	/** 城区信息 */
	private String district;

	/** 街道信息 */
	private String street;

	/** 街道门牌号信息 */
	private String streetNum;

	/** 城市编码 */
	private String cityCode;

	/** 地区编码 */
	private String adCode;

	/** 方向角 */
	private Float bearing;

	/** 当前定位点的AOI信息 */
	private String aoiName;

	/** 当前室内定位的建筑物Id */
	private String buildingId;

	/** 当前室内定位的楼层 */
	private String floor;

	/** GPS的当前状态 */
	private Integer gpsAccuracyStatus;

}
