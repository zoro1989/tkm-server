package com.ccbjb.common.utils;

import java.io.IOException;
import java.text.ParseException;
import java.util.Properties;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.Minutes;
import org.joda.time.Months;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.Seconds;
import org.joda.time.Weeks;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.ccbjb.common.division.DateTimeDiffType;
import com.ccbjb.common.division.WeekDay;
import com.ccbjb.common.exception.ParamInvalidException;
import com.ccbjb.common.exception.UtilException;
import com.ccbjb.common.exception.ValidateException;

public class DateUtil {

	/**
	 * 获取系统当前日期时间<br>
	 * 可通过配置文件[DateUtil.properties]的DummyNowTime指定获取系统时间的模式<br>
	 * 
	 * @return 日期时间字符串
	 * @throws ParseException
	 * @since 1.0
	 */
	public static String getStringNow() {
		Properties prop = new Properties();
		String nowTime = "ServerMode";
		try {
			prop.load(new DateUtil().getClass().getClassLoader().getResourceAsStream("DateUtil.properties"));
			nowTime = prop.getProperty("DummyNowTime").trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if ("ServerMode".equals(nowTime)) {
			return DateTime.now().toString(UtilsConst.DateTimeFormat.Fmt_yyyyMMddHHmmss);
		} else {
			return getCompleteStringDate(nowTime);
		}
	}
	
	/**
	 * 获取系统当前日期时间<br>
	 * 可通过配置文件[DateUtil.properties]的DummyNowTime指定获取系统时间的模式<br>
	 * @param 格式化字符串
	 * @return 日期时间字符串
	 * @since 1.0
	 */
	public static String getStringNow(String format) {
		Properties prop = new Properties();
		String nowTime = "ServerMode";
		try {
			prop.load(new DateUtil().getClass().getClassLoader().getResourceAsStream("DateUtil.properties"));
			nowTime = prop.getProperty("DummyNowTime").trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if ("ServerMode".equals(nowTime)) {
			return DateTime.now().toString(format);
		} else {
			return getDateTime(nowTime).toString(format);
		}
	}
	
	/**
	 * 获取系统当前日期时间<br>
	 * 可通过配置文件[DateUtil.properties]的DummyNowTime指定获取系统时间的模式<br>
	 * @param 格式化字符串
	 * @return 日期时间字符串
	 * @since 1.0
	 */
	public static DateTime getDatetimeNow() {
		Properties prop = new Properties();
		String nowTime = "ServerMode";
		try {
			prop.load(new DateUtil().getClass().getClassLoader().getResourceAsStream("DateUtil.properties"));
			nowTime = prop.getProperty("DummyNowTime").trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if ("ServerMode".equals(nowTime)) {
			return DateTime.now();
		} else {
			return getDateTime(nowTime);
		}
	}
	
	/**
	 * 获取系统当前日期时间<br>
	 * 可通过配置文件[DateUtil.properties]的DummyNowTime指定获取系统时间的模式<br>
	 * @param 格式化字符串
	 * @return 日期时间字符串
	 * @since 1.0
	 */
	public static LocalDateTime getLocalDatetimeNow() {
		Properties prop = new Properties();
		String nowTime = "ServerMode";
		try {
			prop.load(new DateUtil().getClass().getClassLoader().getResourceAsStream("DateUtil.properties"));
			nowTime = prop.getProperty("DummyNowTime").trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if ("ServerMode".equals(nowTime)) {
			return LocalDateTime.now();
		} else {
			return getLocalDateTime(nowTime);
		}
	}
	
	/**
	 * 获取系统当前日期<br>
	 * 可通过配置文件[DateUtil.properties]的DummyNowTime指定获取系统时间的模式<br>
	 * @param 格式化字符串
	 * @return 日期时间字符串
	 * @since 1.0
	 */
	public static LocalDate getLocalDateNow() {
		Properties prop = new Properties();
		String nowTime = "ServerMode";
		try {
			prop.load(new DateUtil().getClass().getClassLoader().getResourceAsStream("DateUtil.properties"));
			nowTime = prop.getProperty("DummyNowTime").trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if ("ServerMode".equals(nowTime)) {
			return LocalDate.now();
		} else {
			return getLocalDate(nowTime);
		}
	}
	
	/**
	 * 获取系统当前时间<br>
	 * 可通过配置文件[DateUtil.properties]的DummyNowTime指定获取系统时间的模式<br>
	 * @param 格式化字符串
	 * @return 日期时间字符串
	 * @since 1.0
	 */
	public static LocalTime getLocalTime() {
		Properties prop = new Properties();
		String nowTime = "ServerMode";
		try {
			prop.load(new DateUtil().getClass().getClassLoader().getResourceAsStream("DateUtil.properties"));
			nowTime = prop.getProperty("DummyNowTime").trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if ("ServerMode".equals(nowTime)) {
			return LocalTime.now();
		} else {
			return getLocalTime(nowTime);
		}
	}
	
	
	/**
	 * 指示指定的日期字符串是否在要求的日期范围之内。<br>
	 * 支持如下的日期字符串格式：<br>
	 * ---------------------------------------<br>
	 * yyyy → yyyy0101000000<br>
	 * yyyyMM → yyyyMM01000000<br>
	 * yyyyMMdd → yyyyMMdd000000<br>
	 * yyyyMMddHH → yyyyMMddHH0000<br>
	 * yyyyMMddHHmm → yyyyMMddHHmm00<br>
	 * yyyyMMddHHmmss → yyyyMMddHHmmss<br>
	 * ---------------------------------------<br>
	 * 
	 * @param date
	 *            要测试的日期字符串
	 * @param startDate
	 *            日期范围范围最小值
	 * @param endDate
	 *            日期范围最大值
	 * @return 如果 date在参数startDate、endDate 之间，则返回true，反之返回false。
	 * @throws ParseException
	 *             参数不是有效的日期字符串
	 * @throws ValidateException
	 * @since 1.0
	 */
	public static boolean isBetween(String date, String startDate, String endDate) {

		if (!CheckUtil.isDateTimeFormat(DateUtil.getCompleteStringDate(date))) {
			throw new ParamInvalidException("参数无效：date不是有效的日期格式{" + date + "}");
		}
		if (!CheckUtil.isDateTimeFormat(DateUtil.getCompleteStringDate(startDate))) {
			throw new ParamInvalidException("参数无效：startDate不是有效的日{" + startDate + "}");
		}
		if (!CheckUtil.isDateTimeFormat(DateUtil.getCompleteStringDate(endDate))) {
			throw new ParamInvalidException("参数无效：endDate不是有效的日期格式{" + endDate + "}");
		}
		String completeDate = DateUtil.getCompleteStringDate(date);
		String completeStartDate = DateUtil.getCompleteStringDate(startDate);
		String completeEndDate = DateUtil.getCompleteStringDate(endDate, true);
		return CheckUtil.isBetween(completeDate, completeStartDate, completeEndDate);
	}

	/**
	 * 指示指定的日期(Joda Datetime类型)是否在要求的日期范围之内。<br>
	 * 
	 * @param date
	 *            要测试的日期(Joda Datetime)
	 * @param startDate
	 *            日期范围最小值(Joda Datetime)
	 * @param endDate
	 *            日期范围最大值(Joda Datetime)
	 * @return 如果 date在参数startDate、endDate 之间，则返回true，反之返回false。
	 * @throws ParamInvalidException
	 *             参数为null
	 * @since 1.0
	 */
	public static boolean isBetween(DateTime date, DateTime startDate, DateTime endDate) {
		if (date == null) {
			throw new ParamInvalidException("参数无效：date参数不可以为null");
		}
		if (startDate == null) {
			throw new ParamInvalidException("参数无效：startDate参数不可以为null");
		}
		if (endDate == null) {
			throw new ParamInvalidException("参数无效：startDate参数不可以为null");
		}
		return date.compareTo(startDate) >= 0 && date.compareTo(endDate) >= 0;
	}

	/**
	 * 指示指定的日期(Joda Datetime类型)是否在要求的日期范围之内。<br>
	 * 
	 * @param date
	 *            要测试的日期(Joda Datetime)
	 * @param startDate
	 *            日期范围最小值(Joda Datetime)
	 * @param endDate
	 *            日期范围最大值(Joda Datetime)
	 * @return 如果 date在参数startDate、endDate 之间，则返回true，反之返回false。
	 * @throws ParamInvalidException
	 *             参数为null
	 * @since 1.0
	 */
	public static boolean isBetween(LocalDate date, LocalDate startDate, LocalDate endDate) {
		if (date == null) {
			throw new ParamInvalidException("参数无效：date参数不可以为null");
		}
		if (startDate == null) {
			throw new ParamInvalidException("参数无效：startDate参数不可以为null");
		}
		if (endDate == null) {
			throw new ParamInvalidException("参数无效：startDate参数不可以为null");
		}
		return date.compareTo(startDate) >= 0 && date.compareTo(endDate) >= 0;
	}

	/**
	 * 指示指定的日期(Joda LocalTime类型)是否在要求的日期范围之内。<br>
	 * 
	 * @param date
	 *            要测试的日期(Joda LocalTime)
	 * @param startDate
	 *            日期范围最小值(Joda LocalTime)
	 * @param endDate
	 *            日期范围最大值(Joda LocalTime)
	 * @return 如果 date在参数startDate、endDate 之间，则返回true，反之返回false。
	 * @throws ParamInvalidException
	 *             参数为null
	 * @since 1.0
	 */
	public static boolean isBetween(LocalTime date, LocalTime startDate, LocalTime endDate) {
		if (date == null) {
			throw new ParamInvalidException("参数无效：date参数不可以为null");
		}
		if (startDate == null) {
			throw new ParamInvalidException("参数无效：startDate参数不可以为null");
		}
		if (endDate == null) {
			throw new ParamInvalidException("参数无效：startDate参数不可以为null");
		}
		return date.compareTo(startDate) >= 0 && date.compareTo(endDate) >= 0;
	}

	/**
	 * 指示指定的日期(Joda LocalDateTime类型)是否在要求的日期范围之内。<br>
	 * 
	 * @param date
	 *            要测试的日期(Joda LocalDateTime)
	 * @param startDate
	 *            日期范围最小值(Joda LocalDateTime)
	 * @param endDate
	 *            日期范围最大值(Joda LocalDateTime)
	 * @return 如果 date在参数startDate、endDate 之间，则返回true，反之返回false。
	 * @throws ParamInvalidException
	 *             参数为null
	 * @since 1.0
	 */
	public static boolean isBetween(LocalDateTime date, LocalDateTime startDate, LocalDateTime endDate) {
		if (date == null) {
			throw new ParamInvalidException("参数无效：date参数不可以为null");
		}
		if (startDate == null) {
			throw new ParamInvalidException("参数无效：startDate参数不可以为null");
		}
		if (endDate == null) {
			throw new ParamInvalidException("参数无效：startDate参数不可以为null");
		}
		return date.compareTo(startDate) >= 0 && date.compareTo(endDate) >= 0;
	}

	/**
	 * 获取当月最后一天的日期值。<br>
	 * ---------------------------------------<br>
	 * yyyy → yyyy0101000000<br>
	 * yyyyMM → yyyyMM01000000<br>
	 * yyyyMMdd → yyyyMMdd000000<br>
	 * ---------------------------------------<br>
	 * 
	 * @param date
	 *            要测试的日期字符串
	 * @return 该月最后一天的日期(DD)。
	 * @throws ParseException
	 * @since 1.0
	 */
	public static String getEndDayOfMonth(String date) {
		if (!CheckUtil.isDateTimeFormat(DateUtil.getCompleteStringDate(date))) {
			throw new ParamInvalidException("参数无效：date不是有效的日期格式{" + date + "}");
		}
		DateTimeFormatter format = DateTimeFormat.forPattern(UtilsConst.DateTimeFormat.Fmt_yyyyMMddHHmmss);
		LocalDateTime localDateTime = LocalDateTime.parse(DateUtil.getCompleteStringDate(date), format);
		LocalDateTime endDayOfMonth = localDateTime.plusMonths(1).withDayOfMonth(1).minusDays(1);
		return endDayOfMonth.toString("dd");
	}

	/**
	 * 获取当月最后一天的日期值。<br>
	 * 
	 * @param date
	 *            要测试的日期(Joda Datetime)
	 * @return 该月最后一天的日期。
	 * @throws ParseException
	 * @since 1.0
	 */
	public static int getEndDayOfMonth(DateTime date) {
		if (date == null) {
			throw new ParamInvalidException("参数无效：date不可以为null");
		}
		return date.plusMonths(1).withDayOfMonth(1).minusDays(1).getDayOfMonth();
	}

	/**
	 * 获取当月最后一天的日期值。<br>
	 * 
	 * @param date
	 *            要测试的日期(Joda LocalDateTime)
	 * @return 该月最后一天的日期(日部分)。
	 * @throws ParseException
	 * @since 1.0
	 */
	public static int getEndDayOfMonth(LocalDateTime date) {
		if (date == null) {
			throw new ParamInvalidException("参数无效：date不可以为null");
		}
		return date.plusMonths(1).withDayOfMonth(1).minusDays(1).getDayOfMonth();
	}

	/**
	 * 获取当月最后一天的日期值。<br>
	 * 
	 * @param date
	 *            要测试的日期(Joda LocalDate)
	 * @return 该月最后一天的日期。
	 * @throws ParseException
	 * @since 1.0
	 */
	public static int getEndDayOfMonth(LocalDate date) {
		if (date == null) {
			throw new ParamInvalidException("参数无效：date不可以为null");
		}
		return date.plusMonths(1).withDayOfMonth(1).minusDays(1).getDayOfMonth();
	}

	/**
	 * 返回一个新的日期时间字符串，他将指定的年数加到对象日期时间上。<br>
	 * 返回的日期时间字符串的长度同传入的日期时间的字符串长度保持一致
	 * 
	 * @param dateTime
	 *            日期字符串
	 * @param years
	 *            增加的年数（负数表示减去相应年数）
	 * @return 字符串日期时间加上指定的年数后的日期时间(字符串形式)。
	 * @throws ParseException
	 * @since 1.0
	 */
	public static String addYears(String dateTime, int years) {
		if (!CheckUtil.isDateTimeFormat(DateUtil.getCompleteStringDate(dateTime))) {
			throw new ParamInvalidException("dateTime不是有效的日期时间格式{" + dateTime + "}");
		}
		DateTimeFormatter format = DateTimeFormat.forPattern(UtilsConst.DateTimeFormat.Fmt_yyyyMMddHHmmss);
		LocalDateTime localDateTime = LocalDateTime.parse(DateUtil.getCompleteStringDate(dateTime), format);
		LocalDateTime returnVal;
		if (years < 0) {
			returnVal = localDateTime.minusYears(years);
		} else {
			returnVal = localDateTime.plusYears(years);
		}
		return returnVal.toString(UtilsConst.DateTimeFormat.Fmt_yyyyMMddHHmmss).substring(0, dateTime.length());
	}

	/**
	 * 返回一个新的日期，将指定的年数加到对象日期时间上。
	 * 
	 * @param dateTime
	 *            日期(Joda DateTime)
	 * @param years
	 *            增加的年数（负数表示减去相应年数）
	 * @return 加上指定的年数后的日期(Joda DateTime)。
	 * @throws ParamInvalidException
	 * @since 1.0
	 */
	public static DateTime addYears(DateTime dateTime, int years) {
		if (dateTime == null) {
			throw new ParamInvalidException("参数无效：dateTime不可以为null");
		}
		DateTime returnVal;
		if (years < 0) {
			returnVal = dateTime.minusYears(years);
		} else {
			returnVal = dateTime.plusYears(years);
		}
		return returnVal;
	}

	/**
	 * 返回一个新的日期，将指定的年数加到对象日期时间上。
	 * 
	 * @param date
	 *            日期(Joda LocalDateTime)
	 * @param years
	 *            增加的年数（负数表示减去相应年数）
	 * @return 加上指定的年数后的日期(Joda LocalDateTime)。
	 * @throws ParamInvalidException
	 * @since 1.0
	 */
	public static LocalDateTime addYears(LocalDateTime date, int years) {
		if (date == null) {
			throw new ParamInvalidException("参数无效：dateTime不可以为null");
		}
		LocalDateTime returnVal;
		if (years < 0) {
			returnVal = date.minusYears(years);
		} else {
			returnVal = date.plusYears(years);
		}
		return returnVal;
	}

	/**
	 * 返回一个新的日期，将指定的年数加到对象日期时间上。
	 * 
	 * @param date
	 *            日期(Joda LocalDate)
	 * @param years
	 *            增加的年数（负数表示减去相应年数）
	 * @return 加上指定的年数后的日期(Joda LocalDate)。
	 * @throws ParamInvalidException
	 * @since 1.0
	 */
	public static LocalDate addYears(LocalDate date, int years) {
		if (date == null) {
			throw new ParamInvalidException("参数无效：dateTime不可以为null");
		}
		LocalDate returnVal;
		if (years < 0) {
			returnVal = date.minusYears(years);
		} else {
			returnVal = date.plusYears(years);
		}
		return returnVal;
	}

	/**
	 * 返回一个新的日期时间字符串，他将指定的月数加到对象日期时间上。<br>
	 * 返回的日期时间字符串的长度同传入的日期时间的字符串长度保持一致
	 * 
	 * @param dateTime
	 *            日期字符串
	 * @param months
	 *            增加的月数（负数表示减去相应月数）
	 * @return 字符串日期时间加上指定的月数后的日期时间(字符串形式)。
	 * @throws ParseException
	 * @since 1.0
	 */
	public static String addMonths(String dateTime, int months) {
		if (!CheckUtil.isDateTimeFormat(DateUtil.getCompleteStringDate(dateTime))) {
			throw new ParamInvalidException("参数无效：dateTime不是有效的日期时间格式{" + dateTime + "}");
		}
		DateTimeFormatter format = DateTimeFormat.forPattern(UtilsConst.DateTimeFormat.Fmt_yyyyMMddHHmmss);
		LocalDateTime localDateTime = LocalDateTime.parse(DateUtil.getCompleteStringDate(dateTime), format);
		LocalDateTime returnVal;
		if (months < 0) {
			returnVal = localDateTime.minusMonths(months);
		} else {
			returnVal = localDateTime.plusMonths(months);
		}
		return returnVal.toString(UtilsConst.DateTimeFormat.Fmt_yyyyMMddHHmmss).substring(0, dateTime.length());
	}

	/**
	 * 返回一个新的日期，将指定的月数加到对象日期时间上。
	 * 
	 * @param dateTime
	 *            日期(Joda DateTime)
	 * @param months
	 *            增加的月数（负数表示减去相应月数）
	 * @return 加上指定的月数后的日期(Joda DateTime)。
	 * @throws ParamInvalidException
	 * @since 1.0
	 */
	public static DateTime addMonths(DateTime dateTime, int months) {
		if (dateTime == null) {
			throw new ParamInvalidException("参数无效：dateTime不可以为null");
		}
		DateTime returnVal;
		if (months < 0) {
			returnVal = dateTime.minusMonths(months);
		} else {
			returnVal = dateTime.plusMonths(months);
		}
		return returnVal;
	}

	/**
	 * 返回一个新的日期时间，将指定的月数加到对象日期时间上。
	 * 
	 * @param date
	 *            日期(Joda LocalDateTime)
	 * @param months
	 *            增加的月数（负数表示减去相应月数）
	 * @return 加上指定的月数后的日期(Joda LocalDateTime)。
	 * @throws ParamInvalidException
	 * @since 1.0
	 */
	public static LocalDateTime addMonths(LocalDateTime date, int months) {
		if (date == null) {
			throw new ParamInvalidException("参数无效：dateTime不可以为null");
		}
		LocalDateTime returnVal;
		if (months < 0) {
			returnVal = date.minusMonths(months);
		} else {
			returnVal = date.plusMonths(months);
		}
		return returnVal;
	}

	/**
	 * 返回一个新的日期时间，将指定的月数加到对象日期时间上。
	 * 
	 * @param date
	 *            日期(Joda LocalDate)
	 * @param months
	 *            增加的月数（负数表示减去相应月数）
	 * @return 加上指定的月数后的日期(Joda LocalDate)。
	 * @throws ParamInvalidException
	 * @since 1.0
	 */
	public static LocalDate addMonths(LocalDate date, int months) {
		if (date == null) {
			throw new ParamInvalidException("参数无效：dateTime不可以为null");
		}
		LocalDate returnVal;
		if (months < 0) {
			returnVal = date.minusMonths(months);
		} else {
			returnVal = date.plusMonths(months);
		}
		return returnVal;
	}

	/**
	 * 返回一个新的日期时间字符串，他将指定的日数加到对象日期时间上。<br>
	 * 返回的日期时间字符串的长度同传入的日期时间的字符串长度保持一致
	 * 
	 * @param dateTime
	 *            日期字符串
	 * @param days
	 *            增加的日数（负数表示减去相应日数）
	 * @return 字符串日期时间加上指定的日数后的日期时间(字符串形式)。
	 * @throws ParseException
	 * @since 1.0
	 */
	public static String addDays(String dateTime, int days) {
		if (!CheckUtil.isDateTimeFormat(DateUtil.getCompleteStringDate(dateTime))) {
			throw new ParamInvalidException("参数无效：dateTime不是有效的日期时间格式{" + dateTime + "}");
		}
		DateTimeFormatter format = DateTimeFormat.forPattern(UtilsConst.DateTimeFormat.Fmt_yyyyMMddHHmmss);
		LocalDateTime localDateTime = LocalDateTime.parse(DateUtil.getCompleteStringDate(dateTime), format);
		LocalDateTime returnVal;
		if (days < 0) {
			returnVal = localDateTime.minusDays(days);
		} else {
			returnVal = localDateTime.plusDays(days);
		}
		return returnVal.toString(UtilsConst.DateTimeFormat.Fmt_yyyyMMddHHmmss).substring(0, dateTime.length());
	}

	/**
	 * 返回一个新的日期，将指定的日数加到对象日期时间上。
	 * 
	 * @param dateTime
	 *            日期(Joda DateTime)
	 * @param days
	 *            增加的日数（负数表示减去相应日数）
	 * @return 加上指定的日数后的日期(Joda DateTime)。
	 * @throws ParamInvalidException
	 * @since 1.0
	 */
	public static DateTime addDays(DateTime dateTime, int days) {
		if (dateTime == null) {
			throw new ParamInvalidException("参数无效：dateTime不可以为null");
		}
		DateTime returnVal;
		if (days < 0) {
			returnVal = dateTime.minusDays(days);
		} else {
			returnVal = dateTime.plusDays(days);
		}
		return returnVal;
	}

	/**
	 * 返回一个新的日期时间，将指定的日数加到对象日期时间上。
	 * 
	 * @param date
	 *            日期(Joda LocalDateTime)
	 * @param days
	 *            增加的日数（负数表示减去相应日数）
	 * @return 加上指定的日数后的日期(Joda LocalDateTime)。
	 * @throws ParamInvalidException
	 * @since 1.0
	 */
	public static LocalDateTime addDays(LocalDateTime date, int days) {
		if (date == null) {
			throw new ParamInvalidException("参数无效：dateTime不可以为null");
		}
		LocalDateTime returnVal;
		if (days < 0) {
			returnVal = date.minusDays(days);
		} else {
			returnVal = date.plusDays(days);
		}
		return returnVal;
	}

	/**
	 * 返回一个新的日期时间，将指定的日数加到对象日期时间上。
	 * 
	 * @param date
	 *            日期(Joda LocalDate)
	 * @param days
	 *            增加的日数（负数表示减去相应日数）
	 * @return 加上指定的日数后的日期(Joda LocalDate)。
	 * @throws ParamInvalidException
	 * @since 1.0
	 */
	public static LocalDate addDays(LocalDate date, int days) {
		if (date == null) {
			throw new ParamInvalidException("参数无效：dateTime不可以为null");
		}
		LocalDate returnVal;
		if (days < 0) {
			returnVal = date.minusDays(days);
		} else {
			returnVal = date.plusDays(days);
		}
		return returnVal;
	}

	/**
	 * 返回一个新的日期时间字符串，他将指定的小时数加到对象日期时间上。<br>
	 * 返回的日期时间字符串的长度同传入的日期时间的字符串长度保持一致
	 * 
	 * @param dateTime
	 *            日期时间字符串
	 * @param hours
	 *            增加的小时数（负数表示减去相应小时数）
	 * @return 加上指定的小时数后的日期时间(String)。
	 * @throws ParseException
	 * @since 1.0
	 */
	public static String addHours(String dateTime, int hours) {
		if (!CheckUtil.isDateTimeFormat(DateUtil.getCompleteStringDate(dateTime))) {
			throw new ParamInvalidException("参数无效：dateTime不是有效的日期时间格式{" + dateTime + "}");
		}
		DateTimeFormatter format = DateTimeFormat.forPattern(UtilsConst.DateTimeFormat.Fmt_yyyyMMddHHmmss);
		LocalDateTime localDateTime = LocalDateTime.parse(DateUtil.getCompleteStringDate(dateTime), format);
		LocalDateTime returnVal;
		if (hours < 0) {
			returnVal = localDateTime.minusHours(hours);
		} else {
			returnVal = localDateTime.plusHours(hours);
		}
		return returnVal.toString(UtilsConst.DateTimeFormat.Fmt_yyyyMMddHHmmss).substring(0, dateTime.length());
	}

	/**
	 * 返回一个新的日期时间，将指定的小时数加到对象日期时间上。
	 * 
	 * @param dateTime
	 *            日期时间(Joda DateTime)
	 * @param hours
	 *            增加的小时数（负数表示减去相应小时数）
	 * @return 加上指定的小时数后的日期时间(Joda DateTime)。
	 * @since 1.0
	 */
	public static DateTime addHours(DateTime dateTime, int hours) {
		if (dateTime == null) {
			throw new ParamInvalidException("参数无效：dateTime不可以为null");
		}
		DateTime returnVal;
		if (hours < 0) {
			returnVal = dateTime.minusHours(hours);
		} else {
			returnVal = dateTime.plusHours(hours);
		}
		return returnVal;
	}

	/**
	 * 返回一个新的日期时间，将指定的小时数加到对象日期时间上。
	 * 
	 * @param date
	 *            日期(Joda LocalDateTime)
	 * @param hours
	 *            增加的小时数（负数表示减去相应小时数）
	 * @return 加上指定的小时数后的日期时间(Joda LocalDateTime)。
	 * @throws ParamInvalidException
	 * @since 1.0
	 */
	public static LocalDateTime addHours(LocalDateTime date, int hours) {
		if (date == null) {
			throw new ParamInvalidException("参数无效：dateTime不可以为null");
		}
		LocalDateTime returnVal;
		if (hours < 0) {
			returnVal = date.minusHours(hours);
		} else {
			returnVal = date.plusHours(hours);
		}
		return returnVal;
	}

	/**
	 * 返回一个新的时间，将指定的小时数加到对象日期时间上。
	 * 
	 * @param date
	 *            时间(Joda LocalTime)
	 * @param hours
	 *            增加的小时数（负数表示减去相应小时数）
	 * @return 加上指定的小时数后的时间(Joda LocalTime)。
	 * @throws ParamInvalidException
	 * @since 1.0
	 */
	public static LocalTime addHours(LocalTime date, int hours) {
		if (date == null) {
			throw new ParamInvalidException("参数无效：dateTime不可以为null");
		}
		LocalTime returnVal;
		if (hours < 0) {
			returnVal = date.minusHours(hours);
		} else {
			returnVal = date.plusHours(hours);
		}
		return returnVal;
	}

	/**
	 * 返回一个新的日期时间字符串，他将指定的分钟数加到对象日期时间上。<br>
	 * 返回的日期时间字符串的长度同传入的日期时间的字符串长度保持一致
	 * 
	 * @param dateTime
	 *            日期时间字符串
	 * @param minutes
	 *            增加的分钟数（负数表示减去相应分钟数）
	 * @return 加上指定的分钟数后的日期时间(String)。
	 * @throws ParseException
	 * @since 1.0
	 */
	public static String addMinutes(String dateTime, int minutes) {
		if (!CheckUtil.isDateTimeFormat(DateUtil.getCompleteStringDate(dateTime))) {
			throw new ParamInvalidException("参数无效：dateTime不是有效的日期时间格式{" + dateTime + "}");
		}
		DateTimeFormatter format = DateTimeFormat.forPattern(UtilsConst.DateTimeFormat.Fmt_yyyyMMddHHmmss);
		LocalDateTime localDateTime = LocalDateTime.parse(DateUtil.getCompleteStringDate(dateTime), format);
		LocalDateTime returnVal;
		if (minutes < 0) {
			returnVal = localDateTime.minusMinutes(minutes);
		} else {
			returnVal = localDateTime.plusMinutes(minutes);
		}
		return returnVal.toString(UtilsConst.DateTimeFormat.Fmt_yyyyMMddHHmmss).substring(0, dateTime.length());
	}

	/**
	 * 返回一个新的日期时间，将指定的分钟数加到对象日期时间上。
	 * 
	 * @param dateTime
	 *            日期时间(Joda DateTime)
	 * @param minutes
	 *            增加的分钟数（负数表示减去相应分钟数）
	 * @return 加上指定的分钟数后的日期时间(Joda DateTime)。
	 * @throws ParamInvalidException
	 * @since 1.0
	 */
	public static DateTime addMinutes(DateTime dateTime, int minutes) {
		if (dateTime == null) {
			throw new ParamInvalidException("参数无效：dateTime不可以为null");
		}
		DateTime returnVal;
		if (minutes < 0) {
			returnVal = dateTime.minusMinutes(minutes);
		} else {
			returnVal = dateTime.plusMinutes(minutes);
		}
		return returnVal;
	}

	/**
	 * 返回一个新的日期时间，将指定的分钟数加到对象日期时间上。
	 * 
	 * @param date
	 *            日期(Joda LocalDateTime)
	 * @param minutes
	 *            增加的分钟数（负数表示减去相应分钟数）
	 * @return 加上指定的分钟数后的日期时间(Joda LocalDateTime)。
	 * @throws ParamInvalidException
	 * @since 1.0
	 */
	public static LocalDateTime addMinutes(LocalDateTime date, int minutes) {
		if (date == null) {
			throw new ParamInvalidException("参数无效：dateTime不可以为null");
		}
		LocalDateTime returnVal;
		if (minutes < 0) {
			returnVal = date.minusMinutes(minutes);
		} else {
			returnVal = date.plusMinutes(minutes);
		}
		return returnVal;
	}

	/**
	 * 返回一个新的时间，将指定的分钟数加到对象日期时间上。
	 * 
	 * @param date
	 *            时间(Joda LocalTime)
	 * @param minutes
	 *            增加的分钟数（负数表示减去相应分钟数）
	 * @return 加上指定的分钟数后的时间(Joda LocalDate)。
	 * @throws ParamInvalidException
	 * @since 1.0
	 */
	public static LocalTime addMinutes(LocalTime date, int minutes) {
		if (date == null) {
			throw new ParamInvalidException("参数无效：dateTime不可以为null");
		}
		LocalTime returnVal;
		if (minutes < 0) {
			returnVal = date.minusMinutes(minutes);
		} else {
			returnVal = date.plusMinutes(minutes);
		}
		return returnVal;
	}

	/**
	 * 返回一个新的日期时间字符串，他将指定的秒数加到对象日期时间上。<br>
	 * 返回的日期时间字符串的长度同传入的日期时间的字符串长度保持一致
	 * 
	 * @param dateTime
	 *            日期时间字符串
	 * @param seconds
	 *            增加的秒数（负数表示减去相应秒数）
	 * @return 加上指定的秒数后的日期时间(String)。
	 * @throws ParseException
	 * @since 1.0
	 */
	public static String addSeconds(String dateTime, int seconds) {
		if (!CheckUtil.isDateTimeFormat(DateUtil.getCompleteStringDate(dateTime))) {
			throw new ParamInvalidException("参数无效：dateTime不是有效的日期时间格式{" + dateTime + "}");
		}
		DateTimeFormatter format = DateTimeFormat.forPattern(UtilsConst.DateTimeFormat.Fmt_yyyyMMddHHmmss);
		LocalDateTime localDateTime = LocalDateTime.parse(DateUtil.getCompleteStringDate(dateTime), format);
		LocalDateTime returnVal;
		if (seconds < 0) {
			returnVal = localDateTime.minusSeconds(seconds);
		} else {
			returnVal = localDateTime.plusSeconds(seconds);
		}
		return returnVal.toString(UtilsConst.DateTimeFormat.Fmt_yyyyMMddHHmmss).substring(0, dateTime.length());
	}

	/**
	 * 返回一个新的日期时间，将指定的秒数加到对象日期时间上。
	 * 
	 * @param dateTime
	 *            日期时间(Joda DateTime)
	 * @param seconds
	 *            增加的秒数（负数表示减去相应秒数）
	 * @return 加上指定的秒数后的日期时间(Joda DateTime)。
	 * @throws ParamInvalidException
	 * @since 1.0
	 */
	public static DateTime addSeconds(DateTime dateTime, int seconds) {
		if (dateTime == null) {
			throw new ParamInvalidException("参数无效：dateTime不可以为null");
		}
		DateTime returnVal;
		if (seconds < 0) {
			returnVal = dateTime.minusSeconds(seconds);
		} else {
			returnVal = dateTime.plusSeconds(seconds);
		}
		return returnVal;
	}

	/**
	 * 返回一个新的日期时间，将指定的秒数加到对象日期时间上。
	 * 
	 * @param date
	 *            日期(Joda LocalDateTime)
	 * @param seconds
	 *            增加的秒数（负数表示减去相应秒数）
	 * @return 加上指定的秒数后的日期时间(Joda LocalDateTime)。
	 * @throws ParamInvalidException
	 * @since 1.0
	 */
	public static LocalDateTime addSeconds(LocalDateTime date, int seconds) {
		if (date == null) {
			throw new ParamInvalidException("参数无效：date不可以为null");
		}
		LocalDateTime returnVal;
		if (seconds < 0) {
			returnVal = date.minusSeconds(seconds);
		} else {
			returnVal = date.plusSeconds(seconds);
		}
		return returnVal;
	}

	/**
	 * 返回一个新的时间，将指定的秒数加到对象日期时间上。
	 * 
	 * @param date
	 *            时间(Joda LocalTime)
	 * @param seconds
	 *            增加的秒数（负数表示减去相应秒数）
	 * @return 加上指定的秒数后的时间(Joda LocalDate)。
	 * @throws ParamInvalidException
	 * @since 1.0
	 */
	public static LocalTime addSeconds(LocalTime date, int seconds) {
		if (date == null) {
			throw new ParamInvalidException("参数无效：date不可以为null");
		}
		LocalTime returnVal;
		if (seconds < 0) {
			returnVal = date.minusSeconds(seconds);
		} else {
			returnVal = date.plusSeconds(seconds);
		}
		return returnVal;
	}

	/**
	 * 获取指定日期时间(Joda DateTime类型)的指定格式的字符串形式<br>
	 * 
	 * @param dateTime
	 *            要计算的日期时间类型
	 * @param format
	 *            格式化字符串（使用DateTimeFormatConst字符串）
	 * @return 指定格式的日期时间字符串
	 * @since 1.0
	 */
	public static String getDateTimeString(DateTime dateTime, String format) {
		if (dateTime == null) {
			throw new ParamInvalidException("参数无效：dateTime不可以为null");
		}
		return dateTime.toString(format);
	}

	/**
	 * 获取指定日期时间(Joda LocalDateTime类型)的指定格式的字符串形式<br>
	 * 
	 * @param dateTime
	 *            要计算的日期时间类型
	 * @param format
	 *            格式化字符串（使用DateTimeFormatConst字符串）
	 * @return 指定格式的日期时间字符串
	 * @since 1.0
	 */
	public static String getDateTimeString(LocalDateTime dateTime, String format) {
		if (dateTime == null) {
			throw new ParamInvalidException("参数无效：dateTime不可以为null");
		}
		return dateTime.toString(format);
	}

	/**
	 * 获取指定日期(Joda LocalDate类型)的指定格式的字符串形式<br>
	 * 
	 * @param dateTime
	 *            要计算的日期类型
	 * @param format
	 *            格式化字符串（使用DateTimeFormatConst字符串）
	 * @return 指定格式的日期字符串
	 * @since 1.0
	 */
	public static String getDateTimeString(LocalDate dateTime, String format) {
		if (dateTime == null) {
			throw new ParamInvalidException("参数无效：dateTime不可以为null");
		}
		if (!(UtilsConst.DateTimeFormat.Fmt_yyyyMMdd.equals("format") || UtilsConst.DateTimeFormat.Fmt_yyyyMMdd_Virgule.equals("format") || UtilsConst.DateTimeFormat.Fmt_yyyyMMdd_Char.equals("format")
				|| UtilsConst.DateTimeFormat.Fmt_yyMMdd.equals("format") || UtilsConst.DateTimeFormat.Fmt_yyMMdd_Virgule.equals("format") || UtilsConst.DateTimeFormat.Fmt_yyMMdd_Char.equals("format")
				|| UtilsConst.DateTimeFormat.Fmt_yyyyMM.equals("format") || UtilsConst.DateTimeFormat.Fmt_yyMM_Virgule.equals("format") || UtilsConst.DateTimeFormat.Fmt_yyyyMM_Char.equals("format")
				|| UtilsConst.DateTimeFormat.Fmt_yyMM.equals("format") || UtilsConst.DateTimeFormat.Fmt_yyMM_Virgule.equals("format") || UtilsConst.DateTimeFormat.Fmt_yyMM_Char.equals("format") || UtilsConst.DateTimeFormat.Fmt_yyyyMMdd_MidLine
					.equals("format"))) {
			throw new ParamInvalidException("参数无效：format不可以为包含时间的格式化字符串");
		}
		return dateTime.toString(format);
	}

	/**
	 * 获取指定时间(Joda LocalDate类型)的指定格式的字符串形式<br>
	 * 
	 * @param localTime
	 *            要计算的时间类型
	 * @param format
	 *            格式化字符串（使用DateTimeFormatConst字符串）
	 * @return 指定格式的时间字符串
	 * @since 1.0
	 */
	public static String getDateTimeString(LocalTime localTime, String format) {
		if (localTime == null) {
			throw new ParamInvalidException("参数无效：dateTime不可以为null");
		}
		if (!(UtilsConst.DateTimeFormat.Fmt_HHmmss.equals("format") || UtilsConst.DateTimeFormat.Fmt_HHmmss_Delimit.equals("format") || UtilsConst.DateTimeFormat.Fmt_HHmm.equals("format")
				|| UtilsConst.DateTimeFormat.Fmt_HHmm_Delimit.equals("format") || UtilsConst.DateTimeFormat.Fmt_HH.equals("format") || UtilsConst.DateTimeFormat.Fmt_mm.equals("format") || UtilsConst.DateTimeFormat.Fmt_ss
					.equals("format"))) {
			throw new ParamInvalidException("参数无效：format不可以为包含日期的格式化字符串");
		}
		return localTime.toString(format);
	}

	/**
	 * 获取指定日期时间(String类型)的指定格式的字符串形式<br>
	 * 
	 * @param dateTime
	 *            要计算的日期时间类型
	 * @param format
	 *            格式化字符串（使用DateTimeFormatConst字符串）
	 * @return 指定格式的时间字符串
	 * @since 1.0
	 */
	public static String getDateTimeString(String dateTime, String format) {
		return getDateTime(dateTime).toString(format);
	}

	/**
	 * 获取格式为"YYYYMMDDhhmmss"日期时间字符串的DateTime类型<br>
	 * @param dateTime
	 *            要计算的字符串
	 * @return 值为输入参数dateTime的Datetime对象。
	 * @since 1.0
	 */
	public static DateTime getDateTime(String dateTime) {
		dateTime = DateUtil.getCompleteStringDate(dateTime);
		
		DateTimeFormatter format = DateTimeFormat.forPattern(UtilsConst.DateTimeFormat.Fmt_yyyyMMddHHmmss);

		DateTime returnVal = null;
		try {
			returnVal = DateTime.parse(DateUtil.getCompleteStringDate(dateTime), format);
		} catch (Exception e) {
			UtilException ex = new UtilException("日期转换失败,dateTime不是有效的日期时间值{"+ dateTime +"}");
			ex.initCause(e);
			throw ex;
		}
		return returnVal;
	}
	
	/**
	 * 获取格式为"YYYYMMDDhhmmss"日期时间字符串的LocalDateTime类型<br>
	 * @param dateTime
	 *            要计算的字符串
	 * @return 值为输入参数dateTime的LocalDateTime对象。
	 * @since 1.0
	 */
	public static LocalDateTime getLocalDateTime(String dateTime) {
		if (!CheckUtil.isDateTimeFormat(DateUtil.getCompleteStringDate(dateTime))) {
			throw new ParamInvalidException("参数无效：dateTime不是有效的日期时间格式{" + dateTime + "}");
		}
		DateTimeFormatter format = DateTimeFormat.forPattern(UtilsConst.DateTimeFormat.Fmt_yyyyMMddHHmmss);

		LocalDateTime returnVal = null;
		try {
			returnVal = LocalDateTime.parse(DateUtil.getCompleteStringDate(dateTime), format);
		} catch (Exception e) {
			UtilException ex = new UtilException("日期转换失败,dateTime不是有效的日期时间值{"+ dateTime +"}");
			ex.initCause(e);
			throw ex;
		}
		return returnVal;
	}
	
	/**
	 * 获取格式为"YYYYMMDDhhmmss"日期时间字符串的LocalDate类型<br>
	 * @param dateTime
	 *            要计算的字符串
	 * @return 值为输入参数dateTime的LocalDate对象。
	 * @since 1.0
	 */
	public static LocalDate getLocalDate(String dateTime) {
		if (!CheckUtil.isDateTimeFormat(DateUtil.getCompleteStringDate(dateTime))) {
			throw new ParamInvalidException("参数无效：dateTime不是有效的日期时间格式{" + dateTime + "}");
		}
		DateTimeFormatter format = DateTimeFormat.forPattern(UtilsConst.DateTimeFormat.Fmt_yyyyMMddHHmmss);

		LocalDate returnVal = null;
		try {
			returnVal = LocalDate.parse(DateUtil.getCompleteStringDate(dateTime), format);
		} catch (Exception e) {
			UtilException ex = new UtilException("日期转换失败,dateTime不是有效的日期时间值{"+ dateTime +"}");
			ex.initCause(e);
			throw ex;
		}
		return returnVal;
	}
	
	/**
	 * 获取格式为"YYYYMMDDhhmmss"日期时间字符串的LocalTime类型<br>
	 * @param dateTime
	 *            要计算的字符串
	 * @return 值为输入参数dateTime的LocalTime对象。
	 * @since 1.0
	 */
	public static LocalTime getLocalTime(String dateTime) {
		if (!CheckUtil.isDateTimeFormat(DateUtil.getCompleteStringDate(dateTime))) {
			throw new ParamInvalidException("参数无效：dateTime不是有效的日期时间格式{" + dateTime + "}");
		}
		DateTimeFormatter format = DateTimeFormat.forPattern(UtilsConst.DateTimeFormat.Fmt_yyyyMMddHHmmss);

		LocalTime returnVal = null;
		try {
			returnVal = LocalTime.parse(DateUtil.getCompleteStringDate(dateTime), format);
		} catch (Exception e) {
			UtilException ex = new UtilException("日期转换失败,dateTime不是有效的日期时间值{"+ dateTime +"}");
			ex.initCause(e);
			throw ex;
		}
		return returnVal;
	}
	
	/**
	 * 获取指定日期的星期<br>
	 * @param dateTime
	 *            要获取星期的日期
	 * @return 星期信息(WeekDay)。
	 * @since 1.0
	 */
	public static WeekDay getDayOfWeekValue(DateTime dateTime) {
		return WeekDay.weekDayOf(dateTime.getDayOfWeek());
	}
	
	/**
	 * 获取指定日期的星期<br>
	 * @param dateTime
	 *            要获取星期的日期
	 * @return 星期信息(WeekDay)。
	 * @since 1.0
	 */
	public static WeekDay getDayOfWeekValue(LocalDateTime dateTime) {
		return WeekDay.weekDayOf(dateTime.getDayOfWeek());
	}
	/**
	 * 获取指定日期的星期<br>
	 * @param dateTime
	 *            要获取星期的日期
	 * @return 星期信息(WeekDay)。
	 * @since 1.0
	 */
	public static WeekDay getDayOfWeekValue(LocalDate dateTime) {
		return WeekDay.weekDayOf(dateTime.getDayOfWeek());
	}
	/**
	 * 获取指定日期的星期<br>
	 * @param dateTime
	 *            要获取星期的日期
	 * @return 星期信息(WeekDay)。
	 * @since 1.0
	 */
	public static WeekDay getDayOfWeekValue(String dateTime) {
		return getDayOfWeekValue(getDateTime(dateTime));
	}
	/**
	 * 计算两个时间之间的时间差，可根据DateTimeDiffType指定时间值显示单位。
	 * @param dtmFrom  开始时间
	 * @param dtmTo    结束时间
	 * @param dateTimeDiffType 时间差的单位（DateTimeDiffType枚举）
	 * @return  开始时间dtmFrom和结束时间dtmTo之间的时间间隔
	 */
	public static long getDateTimeDiff(DateTime dtmFrom,DateTime dtmTo,DateTimeDiffType dateTimeDiffType){
		long retrulVal = 0L;

		switch (dateTimeDiffType) {
		case Years:
			retrulVal = Years.yearsBetween(dtmFrom, dtmTo).getYears();
			break;
		case Months:
			retrulVal = Months.monthsBetween(dtmFrom, dtmTo).getMonths();
			break;
		case Days:
			retrulVal = Days.daysBetween(dtmFrom, dtmTo).getDays();
			break;
		case Hours:
			retrulVal = Hours.hoursBetween(dtmFrom, dtmTo).getHours();
			break;
		case Minutes:
			retrulVal = Minutes.minutesBetween(dtmFrom, dtmTo).getMinutes();
			break;
		case Seconds:
			retrulVal = Seconds.secondsBetween(dtmFrom, dtmTo).getSeconds();
			break;
		case Millis:
			retrulVal = Seconds.secondsBetween(dtmFrom, dtmTo).toStandardDuration().getMillis();
			break;
		case Weeks:
			retrulVal = Weeks.weeksBetween(dtmFrom, dtmTo).getWeeks();
			break;
		default:
			break;
		}	
		return retrulVal;
	}
	
	/**
	 * 计算两个时间之间的时间差，可根据DateTimeDiffType指定时间值显示单位。
	 * @param dtmFrom  开始时间
	 * @param dtmTo    结束时间
	 * @param dateTimeDiffType 时间差的单位（DateTimeDiffType枚举）
	 * @return  开始时间dtmFrom和结束时间dtmTo之间的时间间隔
	 */
	public static long getDateTimeDiff(LocalDateTime dtmFrom,LocalDateTime dtmTo,DateTimeDiffType dateTimeDiffType){
		long retrulVal = 0L;

		switch (dateTimeDiffType) {
		case Years:
			retrulVal = Years.yearsBetween(dtmFrom, dtmTo).getYears();
			break;
		case Months:
			retrulVal = Months.monthsBetween(dtmFrom, dtmTo).getMonths();
			break;
		case Days:
			retrulVal = Days.daysBetween(dtmFrom, dtmTo).getDays();
			break;
		case Hours:
			retrulVal = Hours.hoursBetween(dtmFrom, dtmTo).getHours();
			break;
		case Minutes:
			retrulVal = Minutes.minutesBetween(dtmFrom, dtmTo).getMinutes();
			break;
		case Seconds:
			retrulVal = Seconds.secondsBetween(dtmFrom, dtmTo).getSeconds();
			break;
		case Millis:
			retrulVal = Seconds.secondsBetween(dtmFrom, dtmTo).toStandardDuration().getMillis();
			break;
		case Weeks:
			retrulVal = Weeks.weeksBetween(dtmFrom, dtmTo).getWeeks();
			break;
		default:
			break;
		}	
		return retrulVal;
	}
	
	/**
	 * 计算两个时间之间的时间差，可根据DateTimeDiffType指定时间值显示单位。
	 * @param dtmFrom  开始时间
	 * @param dtmTo    结束时间
	 * @param dateTimeDiffType 时间差的单位（DateTimeDiffType枚举）
	 * @return  开始时间dtmFrom和结束时间dtmTo之间的时间间隔
	 */
	public static long getDateTimeDiff(LocalDate dtmFrom,LocalDate dtmTo,DateTimeDiffType dateTimeDiffType){
		long retrulVal = 0L;

		switch (dateTimeDiffType) {
		case Years:
			retrulVal = Years.yearsBetween(dtmFrom, dtmTo).getYears();
			break;
		case Months:
			retrulVal = Months.monthsBetween(dtmFrom, dtmTo).getMonths();
			break;
		case Days:
			retrulVal = Days.daysBetween(dtmFrom, dtmTo).getDays();
			break;
		case Hours:
			retrulVal = Hours.hoursBetween(dtmFrom, dtmTo).getHours();
			break;
		case Minutes:
			retrulVal = Minutes.minutesBetween(dtmFrom, dtmTo).getMinutes();
			break;
		case Seconds:
			retrulVal = Seconds.secondsBetween(dtmFrom, dtmTo).getSeconds();
			break;
		case Millis:
			retrulVal = Seconds.secondsBetween(dtmFrom, dtmTo).toStandardDuration().getMillis();
			break;
		case Weeks:
			retrulVal = Weeks.weeksBetween(dtmFrom, dtmTo).getWeeks();
			break;
		default:
			break;
		}	
		return retrulVal;
	}
	
	/**
	 * 计算两个时间之间的时间差，可根据DateTimeDiffType指定时间值显示单位。
	 * @param dtmFrom  开始时间
	 * @param dtmTo    结束时间
	 * @param dateTimeDiffType 时间差的单位（DateTimeDiffType枚举）
	 * @return  开始时间dtmFrom和结束时间dtmTo之间的时间间隔
	 */
	public static long getDateTimeDiff(LocalTime dtmFrom,LocalTime dtmTo,DateTimeDiffType dateTimeDiffType){
		long retrulVal = 0L;

		switch (dateTimeDiffType) {
		case Years:
			retrulVal = Years.yearsBetween(dtmFrom, dtmTo).getYears();
			break;
		case Months:
			retrulVal = Months.monthsBetween(dtmFrom, dtmTo).getMonths();
			break;
		case Days:
			retrulVal = Days.daysBetween(dtmFrom, dtmTo).getDays();
			break;
		case Hours:
			retrulVal = Hours.hoursBetween(dtmFrom, dtmTo).getHours();
			break;
		case Minutes:
			retrulVal = Minutes.minutesBetween(dtmFrom, dtmTo).getMinutes();
			break;
		case Seconds:
			retrulVal = Seconds.secondsBetween(dtmFrom, dtmTo).getSeconds();
			break;
		case Millis:
			retrulVal = Seconds.secondsBetween(dtmFrom, dtmTo).toStandardDuration().getMillis();
			break;
		case Weeks:
			retrulVal = Weeks.weeksBetween(dtmFrom, dtmTo).getWeeks();
			break;
		default:
			break;
		}	
		return retrulVal;
	}
	/**
	 * 计算两个时间之间的时间差，可根据DateTimeDiffType指定时间值显示单位。
	 * @param dtmFrom  开始时间
	 * @param dtmTo    结束时间
	 * @param dateTimeDiffType 时间差的单位（DateTimeDiffType枚举）
	 * @return  开始时间dtmFrom和结束时间dtmTo之间的时间间隔
	 */
	public static long getDateTimeDiff(String dtmFrom,String dtmTo,DateTimeDiffType dateTimeDiffType){
		return getDateTimeDiff(getDateTime(dtmFrom), getDateTime(dtmTo),dateTimeDiffType);
	}
	
	/**
	 * 计算两个时间之间的时间差，可根据DateTimeDiffType指定时间值。
	 * @param dtmFrom  开始时间
	 * @param dtmTo    结束时间
	 * @return  开始时间dtmFrom和结束时间dtmTo之间的时间间隔（几年几个月几天几个小时几分钟几秒几毫秒）
	 */
	public static Period getDateTimeDiff_Period(DateTime dtmFrom,DateTime dtmTo){
		Period period = new Period(dtmFrom, dtmTo,PeriodType.yearMonthDayTime() );
		return period;
	}
	
	/**
	 * 计算两个时间之间的时间差，可根据DateTimeDiffType指定时间值。
	 * @param dtmFrom  开始时间
	 * @param dtmTo    结束时间
	 * @return  开始时间dtmFrom和结束时间dtmTo之间的时间间隔（几年几个月几天几个小时几分钟几秒几毫秒）
	 */
	public static Period getDateTimeDiff_Period(LocalDateTime dtmFrom,LocalDateTime dtmTo){
		Period period = new Period(dtmFrom, dtmTo,PeriodType.yearMonthDayTime() );
		return period;
	}
	
	/**
	 * 计算两个时间之间的时间差，可根据DateTimeDiffType指定时间值。
	 * @param dtmFrom  开始时间
	 * @param dtmTo    结束时间
	 * @return  开始时间dtmFrom和结束时间dtmTo之间的时间间隔（几年几个月几天几个小时几分钟几秒几毫秒）
	 */
	public static Period getDateTimeDiff_Period(LocalDate dtmFrom,LocalDate dtmTo){
		Period period = new Period(dtmFrom, dtmTo,PeriodType.yearMonthDayTime() );
		return period;
	}
	
	/**
	 * 计算两个时间之间的时间差，可根据DateTimeDiffType指定时间值。
	 * @param dtmFrom  开始时间
	 * @param dtmTo    结束时间
	 * @return  开始时间dtmFrom和结束时间dtmTo之间的时间间隔（几年几个月几天几个小时几分钟几秒几毫秒）
	 */
	public static Period getDateTimeDiff_Period(LocalTime dtmFrom,LocalTime dtmTo){
		Period period = new Period(dtmFrom, dtmTo,PeriodType.yearMonthDayTime() );
		return period;
	}
	
	/**
	 * 计算两个时间之间的时间差，可根据DateTimeDiffType指定时间值。
	 * @param dtmFrom  开始时间
	 * @param dtmTo    结束时间
	 * @return  开始时间dtmFrom和结束时间dtmTo之间的时间间隔（几年几个月几天几个小时几分钟几秒几毫秒）
	 */
	public static Period getDateTimeDiff_Period(String dtmFrom,String dtmTo){
		return getDateTimeDiff_Period(getDateTime(dtmFrom), getDateTime(dtmTo));
	}
	
	/**
	 * 获取格式为"YYYYMMDDhhmmss"日期时间字符串<br>
	 * ---------------------------------------------<br>
	 * 输入形式"YYYY" ：输出YYYY0101000000<br>
	 * 输入形式"YYYYMM" ：输出YYYYMM01000000<br>
	 * 输入形式"YYYYMMDD" ：输出YYYYMMDD000000<br>
	 * 输入形式"YYYYMMDDhh" ：输出YYYYMMDDhh0000<br>
	 * 输入形式"YYYYMMDDhhmm" ：输出YYYYMMDDhhmm00<br>
	 * 输入形式"YYYYMMDDhhmmss"：输出YYYYMMDDhhmmss<br>
	 * ---------------------------------------------<br>
	 * 
	 * @param date
	 *            要测试的字符串
	 * @param isWide
	 *            是否向后取值
	 * @return 如参数text是是数值，则返回true，反之返回false。
	 * @throws ParseException
	 *             当参数date不是有效的日期字符串的时候
	 * @since 1.0
	 */
	protected static String getCompleteStringDate(String date, boolean isWide) {
		String month = "01";
		String day = "01";
		String hour = "00";
		String minute = "00";
		String second = "00";
		if (isWide) {
			month = "12";
			day = "31";
			hour = "23";
			minute = "59";
			second = "59";
		}
		if (CheckUtil.isNullOrEmpty(date)) {
			throw new ParamInvalidException("日期字符串长度错误。指定的日期为null或者");
		}

		if (date.length() % 2 != 0 || date.length() > 14) {
			throw new ParamInvalidException("日期字符串长度错误");
		}
		if (date.length() >= 4) {
			String year = date.substring(0, 4);
			if (date.length() >= 6) {
				month = date.substring(4, 6);
			}
			if (date.length() >= 8) {
				day = date.substring(6, 8);
			}
			if (date.length() >= 10) {
				hour = date.substring(8, 10);
			}
			if (date.length() >= 12) {
				minute = date.substring(10, 12);
			}
			if (date.length() >= 14) {
				second = date.substring(12, 14);
			}
			return year + month + day + hour + minute + second;
		}
		throw new ParamInvalidException("日期字符串长度错误。年必须为4位。");
	}

	protected static String getCompleteStringDate(String date) {
		return getCompleteStringDate(date, false);
	}
}
