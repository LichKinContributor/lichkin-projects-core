package com.lichkin.application.apis.UpdateUserInfo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.springframework.entities.impl.SysUserLoginEntity;
import com.lichkin.springframework.services.LKApiServiceImpl;
import com.lichkin.springframework.services.LKApiVoidService;

@Service(Statics.SERVICE_NAME)
public class S extends LKApiServiceImpl<I, Void> implements LKApiVoidService<I> {

	@Transactional
	@Override
	public void handle(I sin, String locale, String compId, String loginId) throws LKException {
		SysUserLoginEntity login = (SysUserLoginEntity) sin.getDatas().getLogin();

		boolean update = false;

		if (StringUtils.isNotBlank(sin.getUserName()) && !sin.getUserName().equals(login.getUserName())) {
			login.setUserName(sin.getUserName());
			update = true;
		}

		if ((sin.getGender() != null) && !sin.getGender().equals(login.getGender())) {
			login.setGender(sin.getGender());
			update = true;
		}

		if (StringUtils.isNotBlank(sin.getUserCard()) && !sin.getUserCard().equals(login.getUserCard())) {
			login.setUserCard(sin.getUserCard());
			update = true;
		}

		if (update) {
			dao.mergeOne(login);
		}
	}

}
