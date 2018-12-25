package com.lichkin.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.lichkin.application.services.impl.quartz.InitSysCalendar;
import com.lichkin.framework.springboot.LKDBJobInfo;
import com.lichkin.framework.springboot.LKTaskRunner4InitSysConfigQuartz;

@Configuration
@Order(value = 1)
public class RunnerInitQuartz extends LKTaskRunner4InitSysConfigQuartz {

	@Override
	protected List<LKDBJobInfo> getJobs() {
		final List<LKDBJobInfo> list = new ArrayList<>();

		// 初始化日历信息表
		list.add(new LKDBJobInfo(InitSysCalendar.class, "0 0 1 * * ?"));

		return list;
	}

}