package com.lichkin.application.cache;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.lichkin.framework.db.beans.Order;
import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysCategoryR;
import com.lichkin.springframework.entities.impl.SysCategoryEntity;
import com.lichkin.springframework.services.LKDBService;

@Configuration
public class LKCatagoryCacheLoader extends LKDBService implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		QuerySQL sql = new QuerySQL(SysCategoryEntity.class);
		sql.addOrders(new Order(SysCategoryR.orderId), new Order(SysCategoryR.id));
		List<SysCategoryEntity> list = dao.getList(sql, SysCategoryEntity.class);
		if (CollectionUtils.isNotEmpty(list)) {
			LKSysCatagoryCache.list = list;
			for (SysCategoryEntity entity : list) {
				LKSysCatagoryCache.map.put(entity.getCategoryCode(), entity);
			}
		}
	}

}
