package com.lichkin.application.apis.api10011.S.n00;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysDeptR;
import com.lichkin.framework.db.enums.LikeType;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.utils.LKCodeUtils;
import com.lichkin.springframework.entities.impl.SysDeptEntity;
import com.lichkin.springframework.services.LKApiBusGetListService;

@Service("SysDeptS00Service")
public class S extends LKApiBusGetListService<I, SysDeptEntity, SysDeptEntity> {

	@Override
	protected void initSQL(I sin, QuerySQL sql) {
		sql.eq(SysDeptR.usingStatus, LKUsingStatusEnum.USING);

		if (StringUtils.isNotBlank(sin.getBusCompId())) {
			sql.eq(SysDeptR.compId, sin.getBusCompId());
		} else {
			sql.eq(SysDeptR.compId, sin.getCompId());
		}

		if (StringUtils.isNotBlank(sin.getDeptName())) {
			sql.like(SysDeptR.deptName, LikeType.ALL, sin.getDeptName());
		}

		sql.addOrders(new Order(SysDeptR.deptCode));
	}


	@Override
	protected List<SysDeptEntity> afterQuery(I sin, List<SysDeptEntity> list) {
		if (StringUtils.isNotBlank(sin.getDeptName())) {
			List<String> codeList = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				codeList.add(list.get(i).getDeptCode());
			}

			codeList.addAll(LKCodeUtils.parentsCode(codeList, false));

			QuerySQL sql = new QuerySQL(false, SysDeptEntity.class, true);

			sql.in(SysDeptR.deptCode, codeList);
			sql.addOrders(new Order(SysDeptR.deptCode));

			return dao.getList(sql, SysDeptEntity.class);
		}
		return list;
	}

}
