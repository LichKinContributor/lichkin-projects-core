package com.lichkin.application.apis.api005;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.springframework.entities.impl.SysAdminLoginEntity;
import com.lichkin.springframework.services.LKApiServiceImpl;
import com.lichkin.springframework.services.LKApiVoidService;

@Service("ModifyPhotoService")
public class S extends LKApiServiceImpl<I, Void> implements LKApiVoidService<I> {

	@Transactional
	@Override
	public void handle(I sin, String locale, String compId, String loginId) throws LKException {
		SysAdminLoginEntity exist = (SysAdminLoginEntity) sin.getDatas().getLogin();
		exist.setPhoto(sin.getPhoto());
		dao.mergeOne(exist);
	}

}
