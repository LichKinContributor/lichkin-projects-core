package com.lichkin.application.apis.GetUserInfo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lichkin.framework.beans.impl.LKRequestBean;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.framework.web.annotations.LKApiType;
import com.lichkin.framework.web.enums.ApiType;
import com.lichkin.springframework.controllers.LKApiY0Controller;
import com.lichkin.springframework.entities.impl.SysUserLoginEntity;

@RestController(Statics.CONTROLLER_NAME + "WEB")
@RequestMapping(value = LKFrameworkStatics.WEB_MAPPING_API_WEB_USER + Statics.SUB_URL)
@LKApiType(apiType = ApiType.PERSONAL_BUSINESS)
public class CWeb extends LKApiY0Controller<LKRequestBean, O> {

	@Deprecated
	@Override
	protected O doInvoke(LKRequestBean cin) throws LKException {
		SysUserLoginEntity login = (SysUserLoginEntity) cin.getDatas().getLogin();
		O out = new O();
		out.setLoginName(login.getLoginName());
		out.setCellphone(login.getCellphone());
		out.setEmail(login.getEmail());
		out.setLevel(login.getLevel());
		out.setUserName(login.getUserName());
		out.setUserCard(login.getUserCard());
		out.setGender(login.getGender());
		return out;
	}

}
