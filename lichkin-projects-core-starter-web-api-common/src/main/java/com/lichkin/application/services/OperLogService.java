package com.lichkin.application.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lichkin.framework.defines.Platform;
import com.lichkin.framework.defines.enums.impl.LKOperTypeEnum;
import com.lichkin.springframework.entities.impl.SysAdminOperLogEntity;
import com.lichkin.springframework.entities.impl.SysEmployeeOperLogEntity;
import com.lichkin.springframework.entities.impl.SysUserOperLogEntity;
import com.lichkin.springframework.entities.suppers.OperLogEntity;
import com.lichkin.springframework.services.LKDBService;

/**
 * 日志服务类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Service
public class OperLogService extends LKDBService {

	/**
	 * 记录日志
	 * @param userType 用户类型
	 * @param compId 公司ID
	 * @param loginId 登录ID
	 * @param requestId 请求ID
	 * @param requestTime 请求时间
	 * @param requestIp 请求IP地址
	 * @param requestUri 请求路径
	 * @param requestDatas 请求数据
	 * @param operType 操作类型
	 * @throws Exception 记录日志可能抛出的异常
	 */
	@Transactional
	public void save(

			String userType,

			String compId,

			String loginId,

			String requestId,

			String requestTime,

			String requestIp,

			String requestUri,

			String requestDatas,

			LKOperTypeEnum operType

	) throws Exception {
		OperLogEntity entity = null;
		switch (Platform.PLATFORM) {
			case ADMIN:
				entity = new SysAdminOperLogEntity();
				((SysAdminOperLogEntity) entity).setCompId(compId);
			break;
			case USER:
				entity = new SysUserOperLogEntity();
			break;
			case EMPLOYEE:
				entity = new SysEmployeeOperLogEntity();
				((SysEmployeeOperLogEntity) entity).setCompId(compId);
			break;
		}
		entity.setLoginId(loginId);
		entity.setRequestId(requestId);
		entity.setRequestTime(requestTime);
		entity.setRequestIp(requestIp);
		entity.setRequestUrl(requestUri);
		entity.setRequestDatas(requestDatas);
		entity.setOperType(operType);
		dao.persistOne(entity);
	}

}
