package com.inxedu.os.edu.service.statistics;


import com.inxedu.os.edu.entity.statistics.StatisticsDay;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * StatisticsDay管理接口
 * @author www.inxedu.com
 */
public interface StatisticsDayService {

	 /**
     * 定时添加StatisticsDay
     */
	 void addStatisticsDayAuto();

    /**
	 * 网站统计 （按年、月）
	 */
	Map<String, Object> getStatisticsMsg(String month, String year);
	/**
	 * 查询最近30条的统计数据
	 */
	List<StatisticsDay> getStatisticThirty(int days);
	/**
	 * 查询指定时间段的统计数据
	 */
	List<StatisticsDay> getStatisticsByDate(String startTime, String endTime);
	/**
	 * 删除指定时间段的统计数据
	 */
	void delStatisticsByDate(String startTime, String endTime);
	/**
	 * 生成指定时间段的统计数据
	 */
	void createStatisticsByDate(Date startTime, Date endTime);

	/**
	 * 获取日期的登录人数
	 */
	int getTodayLoginNum(Date date);
	/**
	 * 获取日期的注册人数
	 */
	int getTodayRegisteredNum(Date date);
	/**
	 * 获取日期的订单数
	 */
	Map<String, Object> getTodayOrderNum(Date date);

	/**
	 * 网校课程数
	 */
	int getEudCouresCount();

	/**
	 * 网校总用户数
	 */
	int getEudUserCount();

	/**
	 * 按时间段查询统计
	 */
	List<StatisticsDay> getStatisticsDayList(Date startDate, Date endDate);

	/**
	 * 收入
	 */
	Double getEveryDayIncome(Date startDate, Date endDate);
}