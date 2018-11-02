package com.lichkin.application.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.lichkin.application.services.bus.impl.SysCategoryBusService;

@Configuration
public class LKCatagoryCacheLoader implements CommandLineRunner {

	@Autowired
	private SysCategoryBusService busService;


	@Override
	public void run(String... args) throws Exception {
		busService.cacheCategory();
	}

}
