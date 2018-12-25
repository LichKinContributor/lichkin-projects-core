package com.lichkin.application.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lichkin.framework.db.beans.QuerySQL;
import com.lichkin.framework.db.beans.SysCalendarR;
import com.lichkin.framework.defines.enums.impl.LKDateTimeTypeEnum;
import com.lichkin.framework.utils.LKDateTimeUtils;
import com.lichkin.springframework.entities.impl.SysCalendarEntity;
import com.lichkin.springframework.services.LKDBService;

/**
 * 日历信息表服务类
 * @author SuZhou LichKin Information Technology Co., Ltd.
 */
@Service
public class SysCalendarService extends LKDBService {

	/** 每月日期数 */
	private static final Map<Boolean, int[]> DAYS = new HashMap<>();

	static {
		DAYS.put(false, new int[] { -1, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 });// 正常年份每月日期数
		DAYS.put(true, new int[] { -1, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 });// 闰年年份每月日期数
	}


	/**
	 * 初始化
	 */
	@Transactional
	public void init() {
		// 当前年份
		int year = DateTime.now().getYear();
		init(year);
		init(year + 1);
	}


	/**
	 * 初始化
	 * @param year 年份
	 */
	private void init(int year) {
		// 本年度第一天
		DateTime firstDayOfYear = new DateTime(year, 1, 1, 0, 0, 0);

		// 判断是否已经创建过
		QuerySQL sql = new QuerySQL(false, SysCalendarEntity.class);
		sql.eq(SysCalendarR.calendarDate, LKDateTimeUtils.toString(firstDayOfYear, LKDateTimeTypeEnum.DATE_ONLY));
		SysCalendarEntity exist = dao.getOne(sql, SysCalendarEntity.class);
		if (exist != null) {
			return;
		}

		// 本年度最后一天
		DateTime lastDayOfYear = firstDayOfYear.plusYears(1).minusDays(1);
		// 如果本年最后一天是一年中的第366天，表示是闰年年份，否则表示是正常年份。
		boolean leapYear = lastDayOfYear.getDayOfYear() == 366;

		// 循环创建
		List<SysCalendarEntity> list = new ArrayList<>();
		for (int month = 1; month <= 12; month++) {
			int days = DAYS.get(leapYear)[month];
			byte quarter = -1;
			switch (month) {
				case 1:
				case 2:
				case 3:
					quarter = 1;
				break;
				case 4:
				case 5:
				case 6:
					quarter = 2;
				break;
				case 7:
				case 8:
				case 9:
					quarter = 3;
				break;
				case 10:
				case 11:
				case 12:
					quarter = 4;
				break;
			}
			for (int day = 1; day <= days; day++) {
				DateTime dateTime = new DateTime(year, month, day, 0, 0, 0);
				SysCalendarEntity entity = new SysCalendarEntity();
				entity.setCalendarDate(LKDateTimeUtils.toString(dateTime, LKDateTimeTypeEnum.DATE_ONLY));
				entity.setYear((short) year);
				entity.setQuarter(quarter);
				entity.setMonth((byte) month);
				entity.setWeek((byte) dateTime.getWeekOfWeekyear());
				entity.setDayOfMonth((byte) dateTime.getDayOfMonth());
				entity.setDayOfWeek((byte) dateTime.getDayOfWeek());
				entity.setHoliday(false);
				list.add(entity);
			}
		}

		// 保存列表
		dao.persistList(list);
	}

}
