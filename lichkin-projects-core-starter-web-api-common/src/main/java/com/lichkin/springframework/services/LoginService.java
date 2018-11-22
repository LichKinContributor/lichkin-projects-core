package com.lichkin.springframework.services;

import com.lichkin.framework.defines.entities.I_Login;

/**
 * 登录业务服务类
 * @param <E> 登录表实体类类型
 * @param <U> 用户表实体类类型
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
public abstract class LoginService extends LKDBService {

	/**
	 * 按照令牌获取登录信息
	 * @param token 令牌
	 * @return 登录信息
	 */
	public abstract I_Login findUserLoginByToken(String token);

}
