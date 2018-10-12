package com.lichkin.application.apis.api10001.LD.n00;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysCompR;
import com.lichkin.framework.defines.beans.impl.LKDroplistBean;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.springframework.entities.impl.SysCompEntity;
import com.lichkin.springframework.services.LKApiBusGetDroplistService;

@Service("SysCompLD00Service")
public class S extends LKApiBusGetDroplistService<I> {

	@Override
	public List<LKDroplistBean> handle(I sin) throws LKException {
		QuerySQL sql = new QuerySQL(SysCompEntity.class);

		sql.select(SysCompR.id, "value");
		sql.select(SysCompR.compName, "text");

		sql.eq(SysCompR.usingStatus, LKUsingStatusEnum.USING);

		return dao.getList(sql, LKDroplistBean.class);
	}

}
