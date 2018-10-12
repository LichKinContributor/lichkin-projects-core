package com.lichkin.application.apis.api10005.O.n00;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.Condition;
import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysMenuR;
import com.lichkin.framework.db.beans.SysRoleMenuR;
import com.lichkin.framework.db.beans.eq;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.defines.enums.impl.LKYesNoEnum;
import com.lichkin.framework.utils.LKBeanUtils;
import com.lichkin.springframework.entities.impl.SysMenuEntity;
import com.lichkin.springframework.entities.impl.SysRoleEntity;
import com.lichkin.springframework.entities.impl.SysRoleMenuEntity;
import com.lichkin.springframework.services.LKApiBusGetOneService;

@Service("SysRoleO00Service")
public class S extends LKApiBusGetOneService<I, O, SysRoleEntity> {

	@Override
	protected void setOtherValues(SysRoleEntity entity, I in, O out) {
		LKBeanUtils.setStringValues(findListMenuByRoleId(entity.getId()), out,

				new String[] { "id", "menuName" },

				new String[] { "menuIds", "menuNames" },

				new String[] { LKFrameworkStatics.SPLITOR, "," }

		);
	}


	private List<SysMenuEntity> findListMenuByRoleId(String roleId) {
		QuerySQL sql = new QuerySQL(false, SysMenuEntity.class);

		sql.innerJoin(SysRoleMenuEntity.class,

				new Condition(SysRoleMenuR.menuId, SysMenuR.id),

				new Condition(new eq(SysRoleMenuR.roleId, roleId))

		);

		sql.eq(SysMenuR.onLine, LKYesNoEnum.YES);

		sql.addOrders(new Order(SysMenuR.menuCode));

		return dao.getList(sql, SysMenuEntity.class);
	}

}
