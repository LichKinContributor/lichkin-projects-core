package com.lichkin.application.apis.api10001.I.n00;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.lichkin.application.services.bus.impl.SysCompBusService;
import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysCompR;
import com.lichkin.framework.defines.beans.impl.LKPageBean;
import com.lichkin.framework.defines.enums.LKCodeEnum;
import com.lichkin.framework.utils.LKCodeUtils;
import com.lichkin.springframework.entities.impl.SysCompEntity;
import com.lichkin.springframework.services.LKApiBusInsertService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service("SysCompI00Service")
public class S extends LKApiBusInsertService<I, SysCompEntity> {

	@Autowired
	private SysCompBusService busService;


	@Getter
	@RequiredArgsConstructor
	enum ErrorCodes implements LKCodeEnum {

		SysComp_EXIST(100000),

		;

		private final Integer code;

	}


	@Override
	public boolean needCheckExist(I sin) {
		return true;
	}


	@Override
	public List<SysCompEntity> findExist(I sin) {
		return busService.findExist(null, sin.getParentCode(), sin.getCompName(), sin.getCompKey());
	}


	@Override
	public LKCodeEnum existErrorCode(I sin) {
		return ErrorCodes.SysComp_EXIST;
	}


	@Override
	protected void beforeSaveMainTable(I sin, SysCompEntity entity, SysCompEntity exist) {
		if (exist == null) {
			entity.setCompCode(analysisCode(sin.getCompId(), sin.getParentCode()));
		} else {
			entity.setCompCode(exist.getCompCode());
		}
	}


	private String analysisCode(String compId, String parentCode) {
		QuerySQL sql = new QuerySQL(false, SysCompEntity.class);
		sql.eq(SysCompR.id, compId);
		sql.eq(SysCompR.parentCode, parentCode);
		sql.setPage(new LKPageBean(1));
		sql.addOrders(new Order(SysCompR.compCode, false));
		Page<SysCompEntity> page = dao.getPage(sql, SysCompEntity.class);
		if (CollectionUtils.isNotEmpty(page.getContent())) {
			return LKCodeUtils.nextCode(page.getContent().get(0).getCompCode());
		}
		return LKCodeUtils.createCode(parentCode);
	}

}
