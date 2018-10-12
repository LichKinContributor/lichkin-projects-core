package com.lichkin.application.apis.api10003.LD.n00;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lichkin.application.cache.LKSysCatagoryCache;
import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysDictionaryR;
import com.lichkin.framework.defines.LKConfigStatics;
import com.lichkin.framework.defines.LKFrameworkStatics;
import com.lichkin.framework.defines.beans.impl.LKDroplistBean;
import com.lichkin.framework.defines.enums.impl.LKUsingStatusEnum;
import com.lichkin.framework.defines.enums.impl.LKYesNoEnum;
import com.lichkin.framework.defines.exceptions.LKException;
import com.lichkin.springframework.entities.impl.SysDictionaryEntity;
import com.lichkin.springframework.services.LKApiBusGetDroplistService;

@Service("SysDictionaryLD00Service")
public class S extends LKApiBusGetDroplistService<I> {

	@Override
	public List<LKDroplistBean> handle(I sin) throws LKException {
		String categoryCode = sin.getCategoryCode();
		if ("LOCALE".equals(categoryCode)) {
			Locale[] locales = LKConfigStatics.IMPLEMENTED_LOCALE_ARR;
			List<LKDroplistBean> list = new ArrayList<>(locales.length + 1);
			LKDroplistBean en = new LKDroplistBean();
			en.setText("en");
			en.setValue("en");
			list.add(en);
			for (Locale locale : locales) {
				list.add(new LKDroplistBean(locale.toString(), locale.toString()));
			}
			return list;
		}

		QuerySQL sql = new QuerySQL(SysDictionaryEntity.class);
		sql.select(SysDictionaryR.dictCode, "value");
		sql.select(SysDictionaryR.dictName, "text");

		sql.eq(SysDictionaryR.usingStatus, LKUsingStatusEnum.USING);
		sql.eq(SysDictionaryR.locale, sin.getLocale());
		sql.eq(SysDictionaryR.categoryCode, categoryCode);

		if (LKYesNoEnum.YES.equals(LKSysCatagoryCache.get(categoryCode).getRootOnly())) {
			sql.eq(SysDictionaryR.compId, LKFrameworkStatics.LichKin);
		} else {
			sql.eq(SysDictionaryR.compId, sin.getCompId());
		}

		String includes = sin.getIncludes();
		if (StringUtils.isNotBlank(includes)) {
			sql.in(SysDictionaryR.dictCode, includes);
		}

		String excludes = sin.getExcludes();
		if (StringUtils.isNotBlank(excludes)) {
			sql.notIn(SysDictionaryR.dictCode, excludes);
		}

		sql.addOrders(new Order(SysDictionaryR.orderId), new Order(SysDictionaryR.id));

		return dao.getList(sql, LKDroplistBean.class);
	}

}
