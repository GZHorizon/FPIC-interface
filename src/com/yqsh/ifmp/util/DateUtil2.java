package com.yqsh.ifmp.util;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Title: 日期时间 Description: 工具类
 * 
 * @author xuelin chen
 * @version 1.0
 */
@SuppressWarnings("all")
public class DateUtil2 {
	/** 类名 */
	private static String ClassName = "com.lovo.util.DateUtil";

	/** 本地化 */
	private static Locale locale = Locale.SIMPLIFIED_CHINESE;

	/** 缺省的DateFormat对象，可以将一个java.util.Date格式化成 yyyy-mm-dd 输出 */
	private static DateFormat dateDF = DateFormat.getDateInstance(
			DateFormat.MEDIUM, locale);

	/** 缺省的DateFormat对象，可以将一个java.util.Date格式化成 HH:SS:MM 输出 */
	private static DateFormat timeDF = DateFormat.getTimeInstance(
			DateFormat.MEDIUM, locale);

	/** 缺省的DateFormat对象，可以将一个java.util.Date格式化成 yyyy-mm-dd HH:SS:MM 输出 */
	private static DateFormat datetimeDF = DateFormat.getDateTimeInstance(
			DateFormat.MEDIUM, DateFormat.MEDIUM, locale);

	/**
	 * 私有构造函数，表示不可实例化
	 */
	private DateUtil2() {
	}

	/**
	 * 返回一个当前的时间，并按格式转换为字符串 例：17:27:03
	 * 
	 * @return String
	 */
	public static String getTime() {
		GregorianCalendar gcNow = new GregorianCalendar();
		java.util.Date dNow = gcNow.getTime();
		return timeDF.format(dNow);
	}

	/**
	 * 返回一个当前日期，并按格式转换为字符串 例：2009-12-12
	 * 
	 * @return String
	 */
	public static String getDate() {
		GregorianCalendar gcNow = new GregorianCalendar();
		java.util.Date dNow = gcNow.getTime();
		return dateDF.format(dNow);
	}

	/**
	 * 返回一个当前日期和时间，并按格式转换为字符串 例：2009-12-08 14:27:03
	 * 
	 * @return String
	 */
	public static String getDateTime() {
		GregorianCalendar gcNow = new GregorianCalendar();
		java.util.Date dNow = gcNow.getTime();
		return datetimeDF.format(dNow);
	}

	/**
	 * 返回当前年的年号
	 * 
	 * @return int
	 */
	public static int getYear() {
		GregorianCalendar gcNow = new GregorianCalendar();
		return gcNow.get(GregorianCalendar.YEAR);
	}

	/**
	 * 返回本月月号：从 0 开始
	 * 
	 * @return int
	 */
	public static int getMonth() {
		GregorianCalendar gcNow = new GregorianCalendar();
		return gcNow.get(GregorianCalendar.MONTH);
	}

	/**
	 * 返回今天是本月的第几天
	 * 
	 * @return int 从1开始
	 */
	public static int getToDayOfMonth() {
		GregorianCalendar gcNow = new GregorianCalendar();
		return gcNow.get(GregorianCalendar.DAY_OF_MONTH);
	}

	/**
	 * 返回一格式化的日期
	 * 
	 * @param date
	 *            java.util.Date
	 * @return String yyyy-mm-dd 格式
	 */
	public static String formatDate(java.util.Date date) {
		return dateDF.format(date);
	}

	/**
	 * 返回一格式化的日期
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(long date) {
		return formatDate(new java.util.Date(date));
	}

	/**
	 * 返回一格式化的时间
	 * 
	 * @param date
	 *            Date
	 * @return String hh:ss:mm 格式
	 */
	public static String formatTime(java.util.Date date) {
		return timeDF.format(date);
	}

	/**
	 * 返回一格式化的时间
	 * 
	 * @param date
	 * @return
	 */
	public static String formatTime(long date) {
		return formatTime(new java.util.Date(date));
	}

	/**
	 * 返回一格式化的日期时间
	 * 
	 * @param date
	 *            Date
	 * @return String yyyy-mm-dd hh:ss:mm 格式
	 */
	public static String formatDateTime(java.util.Date date) {
		return datetimeDF.format(date);
	}

	/**
	 * 返回一格式化的日期时间
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDateTime(long date) {
		return formatDateTime(new java.util.Date(date));
	}

	/**
	 * 将字串转成日期和时间，字串格式: yyyy-MM-dd HH:mm:ss
	 * 
	 * @param string
	 *            String
	 * @return Date
	 */
	public static java.util.Date toDateTime(String string) {
		try {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return (java.util.Date) formatter.parse(string);
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 将字串转成日期，字串格式: yyyy-MM-dd
	 * 
	 * @param string
	 *            String
	 * @return Date
	 */
	public static java.util.Date toDate(String string) {
		try {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			return (java.util.Date) formatter.parse(string);
		} catch (Exception ex) {
			return null;
		}
	}
	/**
	 * 将字串转成日期，字串格式: yyyyMMdd
	 * 
	 * @param string
	 *            String
	 * @return Date
	 */
	public static java.util.Date toDate2(String string) {
		try {
			DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			return (java.util.Date) formatter.parse(string);
		} catch (Exception ex) {
			return null;
		}
	}
	
	/**
	 * 将java.util.Date转换为java.sql.Date
	 * 
	 * @param string
	 *            String
	 * @return Date
	 */
//	public static java.sql.Date utildateChangeSqlDate(java.util.Date date) {
//		try {
//			DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
//			return (java.util.Date) formatter.parse(string);
//		} catch (Exception ex) {
//			return null;
//		}
//	}

	/**
	 * 取值：某日期的年号
	 * 
	 * @param date
	 *            格式: yyyy-MM-dd
	 * @return
	 */
	public static int getYear(String date) {
		java.util.Date d = toDate(date);
		if (d == null)
			return 0;

		Calendar calendar = Calendar.getInstance(locale);
		calendar.setTime(d);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 取值：某日期的月号
	 * 
	 * @param date
	 *            格式: yyyy-MM-dd
	 * @return 从0开始
	 */
	public static int getMonth(String date) {
		java.util.Date d = toDate(date);
		if (d == null)
			return 0;

		Calendar calendar = Calendar.getInstance(locale);
		calendar.setTime(d);
		return calendar.get(Calendar.MONTH);
	}

	/**
	 * 取值：某日期的日号
	 * 
	 * @param date
	 *            格式: yyyy-MM-dd
	 * @return 从1开始
	 */
	public static int getDayOfMonth(String date) {
		java.util.Date d = toDate(date);
		if (d == null)
			return 0;

		Calendar calendar = Calendar.getInstance(locale);
		calendar.setTime(d);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 计算两个日期的年数差
	 * 
	 * @param one
	 *            格式: yyyy-MM-dd
	 * @param two
	 *            格式: yyyy-MM-dd
	 * @return
	 */
	public static int compareYear(String one, String two) {
		return getYear(one) - getYear(two);
	}

	/**
	 * 计算岁数
	 * 
	 * @param date
	 *            格式: yyyy-MM-dd
	 * @return
	 */
	public static int compareYear(String date) {
		return getYear() - getYear(date);
	}

	/**
	 * 使用format格式化Date对象为字符串
	 * 
	 * @param date
	 *            date
	 * @return String
	 */
	public static String getDateString(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}

	/**
	 * 使用format格式化Date对象为字符串,获取年份
	 * 
	 * @param date
	 *            date
	 * @return String
	 */
	static public String getYear(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		return format.format(date);
	}

	/**
	 * 使用format格式化Date对象为字符串,获取中文表示的年月日
	 * 
	 * @param date
	 *            date
	 * @return String 2009年12月08日
	 */
	static public String getDateStrC(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		return format.format(date);
	}

	/**
	 * 使用format格式化Date对象为字符串,获取年份
	 * 
	 * @param date
	 *            date
	 * @return String 20091208010101
	 */
	static public String getDateStrCompact(Date date) {
		if (date == null)
			return "";
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
		String str = format.format(date);
		return str;
	}

	/**
	 * 使用format格式化Date对象为字符串,获取年月日和时间
	 * 
	 * @param date
	 *            date
	 * @return String 2009年12月8日 14时03分10秒
	 */
	static public String getDateTimeStrC(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
		return format.format(date);
	}

	/** 上一个月 */
	public static Date getLastMonthFirstDate(Date date, int month) {
		if (date == null)
			return date;
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.MONTH, month);
		date = calendar.getTime();
		return date;
	}

	/**
	 * 获取指定日期后下一个月的第一天
	 * 
	 * @param java
	 *            .sql.Date date
	 * @return java.sql.Date
	 */
	static public java.sql.Date getNextMonthFirstDate(java.util.Date date)
			throws ParseException {
		Calendar scalendar = new GregorianCalendar();
		scalendar.setTime(date);
		scalendar.add(Calendar.MONTH, 1);
		scalendar.set(Calendar.DATE, 1);
		return new java.sql.Date(scalendar.getTime().getTime());
	}

	/**
	 * 获取指定日期的前面几天
	 * 
	 * @param java
	 *            .sql.Date date
	 * @param dayCount
	 *            表示前几天
	 * @return java.sql.Date
	 */
	static public java.sql.Date getFrontDateByDayCount(java.sql.Date date,
			int dayCount) throws ParseException {
		Calendar scalendar = new GregorianCalendar();
		scalendar.setTime(date);
		scalendar.add(Calendar.DATE, -dayCount);
		return new java.sql.Date(scalendar.getTime().getTime());
	}

	/**
	 * 获取指定日期的前面几天
	 * 
	 * @param Date
	 *            date
	 * @param dayCount
	 *            表示前几天
	 * @return Date
	 */
	static public Date getFrontDateByDayCountTwo(Date date, int dayCount)
			throws ParseException {
		Calendar scalendar = new GregorianCalendar();
		scalendar.setTime(date);
		scalendar.add(Calendar.DATE, -dayCount);
		return new Date(scalendar.getTime().getTime());
	}

	/**
	 * 取得指定年份和月份的第一天
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @return Date
	 */
	static public Date getFirstDay(String year, String month)
			throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.parse(year + "-" + month + "-1");
	}

	/**
	 * 取得指定年份和月份的第一天
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @return Date
	 */
	static public Date getFirstDay(int year, int month) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.parse(year + "-" + month + "-1");
	}

	/**
	 * 取得指定年份和月份的最后一天
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @return Date
	 */
	static public Date getLastDay(String year, String month)
			throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(year + "-" + month + "-1");

		Calendar scalendar = new GregorianCalendar();
		scalendar.setTime(date);
		scalendar.add(Calendar.MONTH, 1);
		scalendar.add(Calendar.DATE, -1);
		date = scalendar.getTime();
		return date;
	}

	/**
	 * 取得指定年份和月份的最后一天
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @return Date
	 */
	static public Date getLastDay(int year, int month) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(year + "-" + month + "-1");

		Calendar scalendar = new GregorianCalendar();
		scalendar.setTime(date);
		scalendar.add(Calendar.MONTH, 1);
		scalendar.add(Calendar.DATE, -1);
		date = scalendar.getTime();
		return date;
	}

	/**
	 * 取得指定年份之间的相隔月数
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @return Date
	 */
	static public long getDistinceMonth(String beforedate, String afterdate)
			throws ParseException {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
		long monthCount = 0;
		try {
			java.util.Date before = d.parse(beforedate);
			java.util.Date after = d.parse(afterdate);

			monthCount = (Integer.parseInt(DateUtil2.getYear(after)) - Integer
					.parseInt(DateUtil2.getYear(before)))
					* 12
					+ DateUtil2.getMonth(afterdate)
					- DateUtil2.getMonth(beforedate);

		} catch (ParseException e) {
			System.out.println("Date parse error!");
		}
		return monthCount;
	}

	/**
	 * 取得指定年份之间的相隔天数
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @return Date
	 */
	static public long getDistinceDay(String beforedate, String afterdate)
			throws ParseException {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
		long dayCount = 0;
		try {
			java.util.Date d1 = d.parse(beforedate);
			java.util.Date d2 = d.parse(afterdate);

			dayCount = (d2.getTime() - d1.getTime()) / (24 * 60 * 60 * 1000);

		} catch (ParseException e) {
			System.out.println("Date parse error!");
			// throw e;
		}
		return dayCount;
	}

	/**
	 * 指定日期加一天
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	static public Date getDaytoDay(Date date, int day) {
		if (date == null)
			return date;

		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, day);
		date = calendar.getTime();
		return date;
	}

	static public Date getMonthtoMonth(Date date, int day) {
		if (date == null)
			return date;

		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.MONTH, day);
		date = calendar.getTime();
		return date;
	}

}
