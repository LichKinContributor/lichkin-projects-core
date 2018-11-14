package com.lichkin.application.apis.GetUserInfo;

import com.lichkin.framework.beans.impl.LKRequestBean;
import com.lichkin.springframework.entities.impl.SysUserLoginEntity;

class Utils {

	static O getUserInfo(LKRequestBean cin) {
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
