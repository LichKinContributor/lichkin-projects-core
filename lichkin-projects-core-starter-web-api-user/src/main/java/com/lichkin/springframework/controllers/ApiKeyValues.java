package com.lichkin.springframework.controllers;

/**
 * API键值对封装对象
 * @param <CI> 控制器类入参类型
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
public class ApiKeyValues<CI> extends ApiKV<CI> {

	ApiKeyValues(String locale, CI originalObject) {
		super(locale, originalObject);
	}

}
