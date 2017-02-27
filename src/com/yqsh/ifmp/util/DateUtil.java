package com.yqsh.ifmp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;


/**
 * Date Utility Class
 */
public class DateUtil {
	public final static int SECOND = 1000;
	public final static int MINUTE = SECOND * 60;
	public final static int HOUR = MINUTE * 60;
	public final static int DAY = HOUR * 24;
	public final static int DAYOFMIDMONTH = 15;

	private DateUtil() {
		// nothing
	}

	/**
	 * 将date转换成String
	 * 
	 * @param timestamp
	 * @return
	 */
	public static String dateToStr(Date date) {
		return DateUtil.dateToStr(date, null);
	}
	
	public static String formatDateToStr(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	/**
	 * 将date转换成String
	 * 
	 * @param timestamp
	 * @return
	 */
	public static String dateToStr(Date date, String aMask) {
		String ret = null;
		String mask = aMask;
		if (mask == null || "".equals(mask))
			mask = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(mask);
		ret = sdf.format(date);
		return ret;
	}

	/**
	 * 将String转换成Date
	 * 
	 * @param timestamp
	 * @return
	 */
	public static java.util.Date strToDate(String date) {
		return DateUtil.strToDate(date, null);
	}

	public static java.util.Date stringToDate(String date, String aMask) throws Exception{
		java.util.Date ret = null;
		String mask = aMask;
		if (mask == null || "".equals(mask))
			mask = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(mask);
		ret = sdf.parse(date);
		return ret;
	}
	
	/**
	 * 将String转换成Date
	 * 
	 * @param timestamp
	 * @return
	 */
	public static java.util.Date strToDate(String date, String aMask) {
		java.util.Date ret = null;
		String mask = aMask;
		if (mask == null || "".equals(mask))
			mask = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(mask);
		try {
			ret = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * 判断两个日期是否同一年同一个月
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameMonth(String date1,String date2){
		boolean ism = true;
		int year1 = Integer.parseInt(date1.substring(0, date1.indexOf("-")));
		int year2 = Integer.parseInt(date2.substring(0, date2.indexOf("-")));
		if(year1 != year2){
			ism = false;
		}else{
			int month1 = Integer.parseInt(date1.substring(date1.indexOf("-") + 1, date1.lastIndexOf("-")));
			int month2 = Integer.parseInt(date2.substring(date2.indexOf("-") + 1, date2.lastIndexOf("-")));
			if(month1 != month2){
				ism = false;
			}
		}
		return ism;
	}
	
	/**
	 * 获取当前时间点的日期对象
	 * 
	 * @return 日期对象
	 */
	public static Date getNow() {
		Calendar c = Calendar.getInstance();
		return c.getTime();
	}

	/**
	 * 得到year年month月day日的的0点0分0秒的日期对象
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return 日期对象
	 */
	public static Date getDate(int year, int month, int day) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1);
		c.set(Calendar.DAY_OF_MONTH, day);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * 得到当天的0点0分0秒的日期
	 * 
	 * @return Date 日期对象
	 */
	public static Date getTodayZeroClock() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * 比较两个start日期是否在end日期之前
	 * 
	 * @param start
	 *            日期
	 * @param end
	 *            日期
	 * @return boolean
	 */
	public static boolean isBefore(Date start, Date end) {
		return start.getTime() - end.getTime() < 0 ? true : false;
	}

	/**
	 * 今天之前的日期
	 * 
	 * @return boolean
	 */
	public static boolean isBeforeToday(Date date) {
		Date todayZero = getTodayZeroClock();
		return isBefore(date, todayZero);
	}

	/**
	 * 该日期是否在上半月之内，即15日之前
	 * 
	 * @param date
	 *            待判断的日期
	 * @return boolean
	 */
	public static boolean isBeforeMidMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int day = c.get(Calendar.DAY_OF_MONTH);
		return day <= DAYOFMIDMONTH ? true : false;
	}

	/**
	 * 取某个时间点前几个月的某个时间点
	 * 
	 * @param d
	 *            原日期
	 * @param count
	 *            几个月前
	 * @return 目标日期
	 */
	public static Date beforeMonths(Date d, int count) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) - count);
		return c.getTime();
	}

	/**
	 * 取某个时间点前几天的某个时间点
	 * 
	 * @param d
	 *            原日期
	 * @param count
	 *            天数
	 * @return 目标日期
	 */
	public static Date beforeDays(Date d, int count) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.set(Calendar.MONTH, c.get(Calendar.DAY_OF_MONTH) - count);
		return c.getTime();
	}

	/**
	 * 取某个时间点后几天的某个时间点
	 * 
	 * @param d
	 *            原日期
	 * @param count
	 *            天数
	 * @return 目标日期
	 */
	public static Date afterDays(Date d, int count) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + count);
		return c.getTime();
	}

	/**
	 * 取当前日期的前几天的某个时间点
	 * 
	 * @param count
	 *            天数
	 * @return 目标日期
	 */
	public static Date beforeDays(int count) {
		return beforeDays(getNow(), count);
	}

	/**
	 * 取当前日期的后几天的某个时间点
	 * 
	 * @param count
	 *            天数
	 * @return 目标日期
	 */
	public static Date afterDays(int count) {
		return afterDays(getNow(), count);
	}

	/**
	 * 取当前时间点前几个小时的日期对象
	 * 
	 * @param count
	 *            小时数
	 * @return 目标日期
	 */
	public static Date beforeHours(int count) {
		return beforeHours(getNow(), count);
	}

	/**
	 * 取某时间点前几个小时的日期对象
	 * 
	 * @param d
	 *            原日期
	 * @param count
	 *            小时数
	 * @return 目标日期
	 */
	public static Date beforeHours(Date d, int count) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.set(Calendar.HOUR, c.get(Calendar.HOUR) - count);
		return c.getTime();
	}

	/**
	 * 取某个时间点前几分钟的某个时间点
	 * 
	 * @param d
	 * @param count
	 * @return
	 */
	public static Date beforeMinutes(Date d, int count) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) - count);
		return c.getTime();
	}

	/**
	 * 取某个时间点后几分钟的某个时间点
	 * 
	 * @param d
	 *            原日期
	 * @param count
	 *            分钟数
	 * @return 目标日期
	 */
	public static Date afterMinutes(Date d, int count) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) + count);
		return c.getTime();
	}

	/**
	 * 返回两个日期之间的毫秒数的差距，负数时取绝对值
	 * 
	 * @param d1
	 *            日期1
	 * @param d2
	 *            日期2
	 * @return 毫秒数
	 */
	public static long countMilliSecondsBetween(Date d1, Date d2) {
		if (d1 == null || d2 == null) {
			throw new IllegalArgumentException("参数d1或d2不能是null对象!");
		}
		return Math.abs(d1.getTime() - d2.getTime());
	}

	/**
	 * 获得两个日期之间相差的秒数
	 * 
	 * @param d1
	 *            日期1
	 * @param d2
	 *            日期2
	 * @return 秒数
	 */
	public static long countSecondsBetween(Date d1, Date d2) {
		return countMilliSecondsBetween(d1, d2) / SECOND;
	}

	/**
	 * 获得两个日期之间相差的分钟数
	 * 
	 * @param d1
	 * @param d2
	 * @return 分钟数
	 */
	public static long countMinutesBetween(Date d1, Date d2) {
		return countMilliSecondsBetween(d1, d2) / MINUTE;
	}

	/**
	 * 获得两个日期之间相差的小时数
	 * 
	 * @param d1
	 * @param d2
	 * @return 小时数
	 */
	public static long countHoursBetween(Date d1, Date d2) {
		return countMilliSecondsBetween(d1, d2) / HOUR;
	}

	/**
	 * 获得两个日期之间相差的天数
	 * 
	 * @param d1
	 * @param d2
	 * @return 天数
	 */
	public static long countDaysBetween(Date d1, Date d2) {
		return countMilliSecondsBetween(d1, d2) / DAY;
	}

	public static Date getThisYearBeginning() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 0);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * 今年之前的日期
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isBeforeThisYear(Date date) {
		Date yearBeginning = getThisYearBeginning();
		return isBefore(date, yearBeginning);
	}

	/**
	 * @param date
	 *            日期
	 * @param str
	 *            Calendar 常量（年月日小时分钟）
	 * @return
	 */
	public static int getCalendar(Date date, String str) {
		Calendar ctest = Calendar.getInstance();
		ctest.setTime(date);
		int result = 0;
		if ("YEAR".equals(str)) {
			result = ctest.get(Calendar.YEAR);
		}
		if ("MONTH".equals(str)) {
			result = ctest.get(Calendar.MONTH);
		}
		if ("DAY".equals(str)) {
			result = ctest.get(Calendar.DAY_OF_MONTH);
		}
		if ("MINUTE".equals(str)) {
			result = ctest.get(Calendar.MINUTE);
		}
		if ("HOUR_OF_DAY".equals(str)) {
			result = ctest.get(Calendar.HOUR_OF_DAY);
		}
		if ("SECOND".equals(str)) {
			result = ctest.get(Calendar.SECOND);
		}

		return result;

	}

	/**
	 * 判断一个日期是星期几
	 * 
	 * @param dt
	 * @return
	 */
	public static String getWeekOfDate(Date dt) {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);

		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}

	public static String getWeekOfDate2(Date dt) {
		String[] weekDays = { "0", "1", "2", "3", "4", "5", "6" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);

		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}

	/**
	 * 获取2个时间段的的日期
	 * 
	 * @param srateDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @return
	 */
	public static List<Date> getDateSegment(Date srateDate, Date endDate) {
		List<Date> list = new ArrayList<Date>();
		int startYear = getCalendar(srateDate, "YEAR");
		int endYear = getCalendar(endDate, "YEAR");
		int temp = endYear - startYear;
		if (srateDate == null || endDate == null) {
			throw new IllegalArgumentException("参数srateDate或endDate不能是null对象!");
		}
		if (temp <= 0) {
			throw new IllegalArgumentException("结束日期不能等于或大于开始日期");
		}
		for (int i = 0; i <= temp; i++) {
			Object year = startYear + i;
			for (int j = 1; j <= 12; j++) {
				Object month = null;
				if (j < 10) {
					month = "0" + j;
				} else {
					month = j;
				}
				String str = year + "-" + month.toString();
				Date date = DateUtil.strToDate(str, "yyyy-MM");
				list.add(date);
			}
		}
		return list;
	}

	/**
	 * 判断某个日期是否在一个时间段内
	 * 
	 * @param date1
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static boolean isAfterBefore(Date date1, Date startDate, Date endDate) {
		boolean result = false;
		Calendar ctest1 = Calendar.getInstance();
		ctest1.setTime(date1);
		Calendar ctest2 = Calendar.getInstance();
		ctest2.setTime(startDate);
		Calendar ctest3 = Calendar.getInstance();
		ctest3.setTime(endDate);
		if (ctest1.after(ctest2) && ctest1.before(ctest3)) {
			result = true;
		}
		if (ctest1.equals(ctest2)) {
			result = true;
		}
		return result;
	}

	/**
	 * 判断一个日期是否在一个日期前
	 * 
	 * @param date1
	 *            比较的日期
	 * @param date2
	 *            被比较的日期
	 * @return
	 */
	public static boolean isBeforeDate(Date date1, Date date2) {
		boolean result = false;
		Calendar ctest1 = Calendar.getInstance();
		ctest1.setTime(date1);
		Calendar ctest2 = Calendar.getInstance();
		ctest2.setTime(date2);
		if (ctest1.before(ctest2)) {
			result = true;
		}
		return result;
	}

	/**
	 * 判断某个日期的后个月
	 * 
	 * @param args
	 */
	public static Date afterModth(Date d, int count) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) + count);
		return c.getTime();
	}

	/**
	 * 判断某个日期前的一个月
	 * 
	 * @param args
	 */
	public static Date beforeMonth(Date d, int count) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) - count);
		return c.getTime();
	}

	/**
	 * 获取当前月的第一天和最后一天
	 * 
	 * @param args
	 */
	@SuppressWarnings("static-access")
	public static Map<String, String> getStartAndEndDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// 当前月＋1，即下个月
		cal.add(cal.MONTH, 1);
		// 将下个月1号作为日期初始
		cal.set(cal.DATE, 1);
		// 下个月1号减去一天，即得到当前月最后一天
		cal.add(cal.DATE, -1);
		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String day_end = df.format(cal.getTime());
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(c.DATE, 1);
		Map<String, String> map = new HashMap<String, String>();
		map.put("start", df.format(c.getTime()));
		map.put("end", day_end);
		return map;
	}

	/**
	 * 得到第一个月的第一天和最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static HashMap<String, String> getStartTimeAndEndTime(Date date) {
		String startTime = DateUtil.dateToStr(date, "yyyy-MM");
		String endTime = DateUtil.dateToStr(date, "yyyy-MM");
		startTime = startTime + "-01";
		endTime = endTime + "-" + Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return map;
	}

	/**
	 * 传月份过来得到这个月的第一天到最后一天
	 * 
	 * @param date
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static HashMap<String, String> getStartTimeAndEndTime(String date) {
		String startTime = date;
		String endTime = date;
		startTime = startTime + "-01";
		//
		// int year = Integer.parseInt(date.substring(0, 4));
		// int month = Integer.parseInt(date.substring(5, 7));
		startTime = startTime.replaceAll("-", "/");
		// 该月的最大的天数
		// Calendar calendar = Calendar.getInstance();
		//
		// calendar.set(Calendar.YEAR, year);
		//
		// calendar.set(Calendar.MONTH, month);
		//
		// int end = calendar.getActualMaximum(calendar.DAY_OF_MONTH);
		//
		// int begin = calendar.getActualMinimum(calendar.DAY_OF_MONTH);

		GregorianCalendar grc = new GregorianCalendar();
		grc.setTime(new Date(startTime));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		grc.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		grc.roll(Calendar.DATE, -1);// 日期回滚一天，也就是本月最后一天
		endTime = sdf.format(grc.getTime());

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return map;
	}

	/**
	 * 本月开始时间
	 * 
	 * @param beginDate
	 * @return
	 */
	public static String getMonthBegin(String beginDate) {
		if (StringUtils.isBlank(beginDate)) {
			Calendar calendar = Calendar.getInstance();

			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH) + 1;
			if ((month + "").length() == 1) {
				beginDate = year + "-0" + month + "-" + "01 00:00:00";
			} else {
				beginDate = year + "-" + month + "-" + "01 00:00:00";
			}
		} else {
			beginDate = beginDate + " 00:00:00";
		}
		return beginDate;
	}

	/**
	 * 本月当天
	 * 
	 * @param endDate
	 * @return
	 */
	public static String getMonthEnd(String endDate) {
		if (StringUtils.isBlank(endDate)) {
			Calendar calendar = Calendar.getInstance();

			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH) + 1;
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			if ((month + "").length() == 1) {
				if ((day + "").length() == 1) {
					endDate = year + "-0" + month + "-0" + day + " 23:59:59";
				} else {
					endDate = year + "-0" + month + "-" + day + " 23:59:59";
				}
			} else {
				if ((day + "").length() == 1) {
					endDate = year + "-" + month + "-0" + day + " 23:59:59";
				} else {
					endDate = year + "-" + month + "-" + day + " 23:59:59";
				}
			}
		} else {
			endDate = endDate + " 23:59:59";
		}
		return endDate;
	}

	public static void main(String[] args) {
		// 判断2个时间之间的分钟数
		Date date1 = DateUtil.strToDate("2010-10-07 08:00:00", "yyyy-MM-dd HH:mm:ss");
		Date date2 = DateUtil.strToDate("2010-10-09 27:00:00", "yyyy-MM-dd HH:mm:ss");
		System.out.println(DateUtil.countDaysBetween(date1, date2));
		System.out.println(DateUtil.isBefore(date2, date1));
	}
}
