package com.lichkin.application.services.impl.quartz;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import com.lichkin.application.services.impl.SysCalendarService;
import com.lichkin.framework.springboot.services.LKBaseDBJobService;

/**
 * 初始化日历信息表
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
public class InitSysCalendar extends LKBaseDBJobService {

	@Autowired
	private SysCalendarService service;


	@Override
	protected void doTask(DateTime lastExecuteTime, DateTime lastFinishedTime) {
		service.init();
	}

}
