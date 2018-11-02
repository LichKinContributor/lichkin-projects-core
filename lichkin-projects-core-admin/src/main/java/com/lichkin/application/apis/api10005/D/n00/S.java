package com.lichkin.application.apis.api10005.D.n00;

import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.DeleteSQL;
import com.lichkin.framework.db.beans.SysRoleMenuR;
import com.lichkin.framework.db.beans.SysRoleR;
import com.lichkin.framework.defines.enums.impl.LKErrorCodesEnum;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.defines.exceptions.LKRuntimeException;
import com.lichkin.springframework.entities.impl.SysRoleEntity;
import com.lichkin.springframework.entities.impl.SysRoleMenuEntity;
import com.lichkin.springframework.services.LKApiBusDeleteService;

@Service("SysRoleD00Service")
public class S extends LKApiBusDeleteService<I, SysRoleEntity> {

	@Override
	protected int getIdColumnResId() {
		return SysRoleR.id;
	}


	@Override
	protected boolean realDelete(I sin, String locale, String compId, String loginId) {
		return true;
	}


	@Override
	protected void beforeRealDelete(I sin, String locale, String compId, String loginId, SysRoleEntity entity, String id) {
		// TODO 删除数据为高风险操作，通常需要做一些业务校验才可以执行删除。
		LKUsingStatusEnum usingStatus = entity.getUsingStatus();
		switch (usingStatus) {
			case USING:// 在用
				DeleteSQL sql = new DeleteSQL(SysRoleMenuEntity.class);
				sql.eq(SysRoleMenuR.roleId, id);
				dao.delete(sql);
			break;
			default:
				throw new LKRuntimeException(LKErrorCodesEnum.PARAM_ERROR);
		}
	}

}
