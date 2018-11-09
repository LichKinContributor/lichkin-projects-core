package com.lichkin.application.apis.api10004.S.n00;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysMenuR;
import com.lichkin.framework.db.enums.LikeType;
import com.lichkin.framework.utils.LKCodeUtils;
import com.lichkin.springframework.entities.impl.SysMenuEntity;
import com.lichkin.springframework.services.LKApiBusGetListService;

@Service("SysMenuS00Service")
public class S extends LKApiBusGetListService<I, SysMenuEntity, SysMenuEntity> {

	@Override
	protected void initSQL(I sin, String locale, String compId, String loginId, QuerySQL sql) {
		if (StringUtils.isNotBlank(sin.getMenuName())) {
			sql.like(SysMenuR.menuName, LikeType.ALL, sin.getMenuName());
		}

		String rootOnly = sin.getRootOnly();
		if (StringUtils.isNotBlank(rootOnly)) {
			if (!"null".equals(rootOnly)) {
				sql.eq(SysMenuR.rootOnly, Boolean.parseBoolean(rootOnly));
			} else {
				sql.isNull(SysMenuR.rootOnly);
			}
		}

		String onLine = sin.getOnLine();
		if (StringUtils.isNotBlank(onLine)) {
			sql.eq(SysMenuR.onLine, Boolean.parseBoolean(onLine));
		}

		String assignable = sin.getAssignable();
		if (StringUtils.isNotBlank(assignable)) {
			sql.eq(SysMenuR.assignable, Boolean.parseBoolean(assignable));
		}

		sql.addOrders(new Order(SysMenuR.menuCode));
	}


	@Override
	protected List<SysMenuEntity> afterQuery(I sin, String locale, String compId, String loginId, List<SysMenuEntity> list) {
		if (StringUtils.isNotBlank(sin.getMenuName()) || StringUtils.isNotBlank(sin.getRootOnly()) || StringUtils.isNotBlank(sin.getOnLine()) || StringUtils.isNotBlank(sin.getAssignable())) {
			List<String> codeList = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				codeList.add(list.get(i).getMenuCode());
			}

			codeList.addAll(LKCodeUtils.parentsCode(codeList, false));

			QuerySQL sql = new QuerySQL(false, SysMenuEntity.class, true);

			sql.in(SysMenuR.menuCode, codeList);
			sql.addOrders(new Order(SysMenuR.menuCode));

			return dao.getList(sql, SysMenuEntity.class);
		}
		return list;
	}

}
