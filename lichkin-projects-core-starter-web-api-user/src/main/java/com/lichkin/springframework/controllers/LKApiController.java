package com.lichkin.springframework.controllers;

import com.lichkin.framework.beans.impl.Datas;
import com.lichkin.framework.beans.impl.LKRequestBean;
import com.lichkin.framework.web.annotations.LKController4Api;
import com.lichkin.framework.web.enums.ApiType;

/**
 * API数据请求控制器类定义
 * @param <CI> 控制器类入参类型
 * @param <CO> 控制器类出参类型
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@LKController4Api
public abstract class LKApiController<CI extends LKRequestBean, CO> extends ApiController<CI, CO> {

	@Override
	void initOthers(ApiType apiType, Datas datas, boolean fromSession) {
	}

}
