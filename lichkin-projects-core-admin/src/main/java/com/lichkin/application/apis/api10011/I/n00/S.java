package com.lichkin.application.apis.api10011.I.n00;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.lichkin.application.services.bus.impl.SysDeptBusService;
import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysDeptR;
import com.lichkin.framework.defines.beans.impl.LKPageBean;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.framework.utils.LKCodeUtils;
import com.lichkin.springframework.entities.impl.SysDeptEntity;
import com.lichkin.springframework.services.LKApiBusInsertService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service("SysDeptI00Service")
public class S extends LKApiBusInsertService<I, SysDeptEntity> {

	@Autowired
	private SysDeptBusService busService;


	@Getter
	@RequiredArgsConstructor
	enum ErrorCodes implements LKCodeEnum {

		SysDept_EXIST(100000),

		;

		private final Integer code;

	}


	@Override
	public boolean needCheckExist(I sin) {
		return true;
	}


	@Override
	public List<SysDeptEntity> findExist(I sin) {
		return busService.findExist(null, sin.getParentCode(), sin.getDeptName());
	}


	@Override
	public LKCodeEnum existErrorCode(I sin) {
		return ErrorCodes.SysDept_EXIST;
	}


	@Override
	protected void beforeSaveMainTable(I sin, SysDeptEntity entity, SysDeptEntity exist) {
		if (exist == null) {
			entity.setDeptCode(analysisCode(sin.getCompId(), sin.getParentCode()));
		} else {
			entity.setDeptCode(exist.getDeptCode());
		}
	}


	private String analysisCode(String compId, String parentCode) {
		QuerySQL sql = new QuerySQL(false, SysDeptEntity.class);
		sql.eq(SysDeptR.compId, compId);
		sql.eq(SysDeptR.parentCode, parentCode);
		sql.setPage(new LKPageBean(1));
		sql.addOrders(new Order(SysDeptR.deptCode, false));
		Page<SysDeptEntity> page = dao.getPage(sql, SysDeptEntity.class);
		if (CollectionUtils.isNotEmpty(page.getContent())) {
			return LKCodeUtils.nextCode(page.getContent().get(0).getDeptCode());
		}
		return LKCodeUtils.createCode(parentCode);
	}

}
