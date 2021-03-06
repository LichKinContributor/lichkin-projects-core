package com.lichkin.application.apis.UploadPhoto;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lichkin.framework.defines.entities.I_Login;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.springframework.controllers.ApiKeyValues;
import com.lichkin.springframework.services.LKApiServiceImpl;
import com.lichkin.springframework.services.LKApiVoidService;

@Service(Statics.SERVICE_NAME)
public class S extends LKApiServiceImpl<I, Void> implements LKApiVoidService<I> {

	@Transactional
	@Override
	public void handle(I sin, ApiKeyValues<I> params) throws LKException {
		I_Login login = params.getLogin();
		login.setPhoto(sin.getPhoto());
		dao.mergeOne(login);
	}

}
