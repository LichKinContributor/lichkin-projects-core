package com.lichkin.application.apis.GetUserInfo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lichkin.framework.beans.impl.LKRequestBean;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.defines.Platform;
import com.lichkin.framework.defines.entities.I_Login;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.framework.web.annotations.LKApiType;
import com.lichkin.framework.web.enums.ApiType;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.controllers.LKApiY0Controller;
import com.lichkin.springframework.entities.impl.SysAdminLoginEntity;
import com.lichkin.springframework.entities.impl.SysUserLoginEntity;

@RestController(Statics.CONTROLLER_NAME)
@RequestMapping(value = LKFrameworkStatics.WEB_MAPPING_API + Statics.SUB_URL)
@LKApiType(apiType = ApiType.PERSONAL_BUSINESS)
public class C extends LKApiY0Controller<LKRequestBean, Map<String, Object>> {

	@Deprecated
	@Override
	protected Map<String, Object> doInvoke(LKRequestBean cin, ApiKeyValues<LKRequestBean> params) throws LKException {
		Map<String, Object> result = new HashMap<>();
		I_Login login = params.getLogin();
		switch (Platform.PLATFORM) {
			case EMPLOYEE:
			case USER:
				SysUserLoginEntity userLogin = (SysUserLoginEntity) login;
				result.put("loginName", userLogin.getLoginName());
				result.put("cellphone", userLogin.getCellphone());
				result.put("email", userLogin.getEmail());
				result.put("level", userLogin.getLevel());
				result.put("userName", userLogin.getUserName());
				result.put("userCard", userLogin.getUserCard());
				result.put("gender", userLogin.getGender());
			break;
			case ADMIN:
				SysAdminLoginEntity adminLogin = (SysAdminLoginEntity) login;
				result.put("userName", adminLogin.getUserName());
				result.put("gender", adminLogin.getGender());
				result.put("email", adminLogin.getEmail());
				result.put("level", adminLogin.getLevel());
				result.put("photo", adminLogin.getPhoto());
			break;
			default:
				return null;
		}
		return result;
	}


	@Override
	protected boolean saveLog(LKRequestBean cin, ApiKeyValues<LKRequestBean> params) {
		return false;
	}

}
