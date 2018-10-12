package com.lichkin.application.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lichkin.framework.defines.enums.impl.LKOperTypeEnum;
import com.lichkin.springframework.entities.impl.SysAdminOperLogEntity;
import com.lichkin.springframework.services.OperLogService;

@Service
public class SysAdminOperLogService extends OperLogService {

	@Override
	@Transactional
	public void log(String compId, String loginId, String requestId, String requestTime, String requestIp, String requestUrl, String requestDatas, LKOperTypeEnum operType, String busType) {
		SysAdminOperLogEntity entity = new SysAdminOperLogEntity();
		entity.setCompId(compId);
		entity.setLoginId(loginId);
		entity.setRequestId(requestId);
		entity.setRequestTime(requestTime);
		entity.setRequestIp(requestIp);
		entity.setRequestUrl(requestUrl);
		entity.setRequestDatas(requestDatas);
		entity.setOperType(operType);
		entity.setBusType(busType);
		dao.persistOne(entity);
	}

}
