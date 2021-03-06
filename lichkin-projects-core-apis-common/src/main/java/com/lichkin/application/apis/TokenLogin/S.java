package com.lichkin.application.apis.TokenLogin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.lichkin.defines.CoreStatics;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.framework.utils.LKBeanUtils;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.entities.impl.SysUserLoginEntity;
import com.lichkin.springframework.services.LKApiService;
import com.lichkin.springframework.services.LKApiServiceImpl;

@Service(Statics.SERVICE_NAME)
public class S extends LKApiServiceImpl<I, O> implements LKApiService<I, O> {

	/** 接口服务器URL根路径 */
	@Value("${com.lichkin.apis.server.rootUrl:http://apis.lichkin.com}")
	private String apisServerRootUrl;


	@Override
	public O handle(I sin, ApiKeyValues<I> params) throws LKException {
		SysUserLoginEntity login = (SysUserLoginEntity) params.getLogin();
		O out = LKBeanUtils.newInstance(login, O.class);
		out.setSecurityCenterUrl(apisServerRootUrl + CoreStatics.SSO_URL + CoreStatics.SECURITY_CENTER_URL);
		out.setApiServerRootUrl(apisServerRootUrl);
		return out;
	}

}
