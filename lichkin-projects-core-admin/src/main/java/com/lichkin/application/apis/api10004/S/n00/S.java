package com.lichkin.application.apis.api10004.S.n00;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.Condition;
import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysMenuR;
import com.lichkin.framework.db.beans.eq;
import com.lichkin.framework.db.beans.isNull;
import com.lichkin.framework.db.enums.LikeType;
import com.lichkin.framework.defines.LKFrameworkStatics;
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
			if (rootOnly.contains(LKFrameworkStatics.SPLITOR)) {
				String[] rootOnlyArr = rootOnly.split(LKFrameworkStatics.SPLITOR);
				List<Condition> listCondition = new ArrayList<>(rootOnlyArr.length);
				for (String rootOnlyI : rootOnlyArr) {
					if ("null".equals(rootOnlyI)) {
						listCondition.add(new Condition(false, new isNull(SysMenuR.rootOnly)));
						continue;
					}
					listCondition.add(new Condition(false, new eq(SysMenuR.rootOnly, Boolean.parseBoolean(rootOnlyI))));
				}
				int size = listCondition.size();
				switch (size) {
					case 2:
						sql.where(new Condition(true, listCondition.get(0), listCondition.get(1)));
					break;
					default:
						Condition condition0 = listCondition.remove(0);
						Condition condition1 = listCondition.remove(1);
						Condition[] conditions = new Condition[listCondition.size()];
						sql.where(new Condition(true, condition0, condition1, listCondition.toArray(conditions)));
					break;
				}
			} else {
				if (!"null".equals(rootOnly)) {
					sql.eq(SysMenuR.rootOnly, Boolean.parseBoolean(rootOnly));
				} else {
					sql.isNull(SysMenuR.rootOnly);
				}
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
