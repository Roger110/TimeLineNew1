package com.timeline.common;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 字符串操作工具包
 * 
 * @author 
 * @version 1.0
 * @created 2016-7-3
 */
public class StringUtils {
	private final static Pattern emailer = Pattern
			.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
	// private final static SimpleDateFormat dateFormater = new
	// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// private final static SimpleDateFormat dateFormater2 = new
	// SimpleDateFormat("yyyy-MM-dd");

	private final static ThreadLocal<SimpleDateFormat> datetimeFormater = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
	};

	private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	};

	/**
	 * 将字符串转为日期类型
	 * 
	 * @param sdate
	 * @return
	 */
	public static Date toDate(String sdate) {
		try {
			return dateFormater.get().parse(sdate);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * 将字符串转为日期时间类型
	 * 
	 * @param sdate
	 * @return
	 */
	public static Date toDateTime(String sdate) {
		try {
			return datetimeFormater.get().parse(sdate);
		} catch (ParseException e) {
			return null;
		}
	}
	

	/**
	 * 以友好的方式显示时间
	 * 
	 * @param sdate
	 * @return
	 */
	public static String friendly_time(String sdate) {
		Date time = null;
		if (TimeZoneUtil.isInEasternEightZones()) {
			time = toDate(sdate);
		} else {
			time = TimeZoneUtil.transformTime(toDate(sdate),
					TimeZone.getTimeZone("GMT+08"), TimeZone.getDefault());
		}
		if (time == null) {
			return "Unknown";
		}
		String ftime = "";
		Calendar cal = Calendar.getInstance();

		// 判断是否是同一天
		String curDate = dateFormater.get().format(cal.getTime());
		String paramDate = dateFormater.get().format(time);
		if (curDate.equals(paramDate)) {
			int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
			if (hour == 0)
				ftime = Math.max(
						(cal.getTimeInMillis() - time.getTime()) / 60000, 1)
						+ "分钟前";
			else
				ftime = hour + "小时前";
			return ftime;
		}

		long lt = time.getTime() / 86400000;
		long ct = cal.getTimeInMillis() / 86400000;
		int days = (int) (ct - lt);
		if (days == 0) {
			int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
			if (hour == 0)
				ftime = Math.max(
						(cal.getTimeInMillis() - time.getTime()) / 60000, 1)
						+ "分钟前";
			else
				ftime = hour + "小时前";
		} else if (days == 1) {
			ftime = "昨天";
		} else if (days == 2) {
			ftime = "前天";
		} else if (days > 2 && days <= 10) {
			ftime = days + "天前";
		} else if (days > 10) {
			ftime = dateFormater.get().format(time);
		}
		return ftime;
	}

	
	/**
	 * 以友好的方式显示日期
	 * 
	 * @param sdate
	 * @return
	 */
	public static String friendly_date(Date sdate) {
		/*
		Date time = null;
		if (TimeZoneUtil.isInEasternEightZones()) {
			time = toDate(sdate);
		} else {
			time = TimeZoneUtil.transformTime(toDate(sdate),
					TimeZone.getTimeZone("GMT+08"), TimeZone.getDefault());
		}
		if (time == null) {
			return "Unknown";
		}
		*/
		String ftime = "";
		Calendar cal = Calendar.getInstance();

		// 判断是否是同一天
		String curDate = dateFormater.get().format(cal.getTime());
		String paramDate = dateFormater.get().format(sdate);
		if (curDate.equals(paramDate)) {
			
			ftime = "今天";
			return ftime;
		}

		long lt = sdate.getTime() / 86400000;
		long ct = cal.getTimeInMillis() / 86400000;
		int days = (int) (ct - lt);
		if (days == 0) {
			ftime = "今天";
		} else if (days == 1) {
			ftime = "昨天";
		} else if (days == 2) {
			ftime = "前天";
		} else if (days > 2 && days <= 10) {
			ftime = days + "天前";
		} else if (days > 10) {
			ftime = dateFormater.get().format(sdate);
		}
		return ftime;
	}	
	
	
	public static String formatDate(Date sdate){
		if(sdate == null){
			return "";
		}
		else{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(sdate);
		}
	}
	
	public static String formatTime(Date sdate){
		if(sdate == null){
			return "";
		}
		else{		
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			return sdf.format(sdate);
		}
	}
	
	public static String formatTimeShort(Date sdate){
		if(sdate == null){
			return "";
		}
		else{		
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			return sdf.format(sdate);
		}
	}	
	
	public static String formatDateTime(Date sdate){
		if(sdate == null){
			return "";
		}
		else{
			return formatDate(sdate) + " " + formatTime(sdate);
		}
	}
	
	public static String formatDateTimeShort(Date sdate){
		if(sdate == null){
			return "";
		}
		else{
			return formatDate(sdate) + " " + formatTimeShort(sdate);
		}
	}
		
	
	public static String formatTimeCount(int timecount){
		int mCount = 0;
		int sCount = 0;
		String altStr = "";
		
		mCount = timecount / 60;
		sCount = timecount % 60;
		
		if(mCount > 0){
			altStr = String.valueOf(mCount) + "分";
		}
		altStr += String.valueOf(sCount) + "秒";
		return altStr;
	}	
	
	//
	public static String formatDouble(double dvalue){	
		DecimalFormat dformat = new DecimalFormat("0.00");
		return dformat.format(dvalue);	
	}
	
	//
	public static String formatDouble(String prefixStr, double dvalue){	
		DecimalFormat dformat = new DecimalFormat("0.00");
		return prefixStr + dformat.format(dvalue);	
	}	
	//
	public static String friendly_formatDouble(String prefixStr, double dvalue, String unitStr){	
		if(dvalue == 0.0){
			return "";
		}
		else{
			DecimalFormat dformat = new DecimalFormat("0.00");
			return prefixStr + dformat.format(dvalue) + unitStr;
		}
	}
	
	/**
	 * 判断给定字符串时间是否为今日
	 * 
	 * @param sdate
	 * @return boolean
	 */
	public static boolean isToday(String sdate) {
		boolean b = false;
		Date time = toDate(sdate);
		Date today = new Date();
		if (time != null) {
			String nowDate = dateFormater.get().format(today);
			String timeDate = dateFormater.get().format(time);
			if (nowDate.equals(timeDate)) {
				b = true;
			}
		}
		return b;
	}

	/**
	 * 返回long类型的今天的日期
	 * 
	 * @return
	 */
	public static long getToday() {
		Calendar cal = Calendar.getInstance();
		String curDate = dateFormater.get().format(cal.getTime());
		curDate = curDate.replace("-", "");
		return Long.parseLong(curDate);
	}

	/**
	 * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
	 * 
	 * @param input
	 * @return boolean
	 */
	public static boolean isEmpty(String input) {
		if (input == null || "".equals(input))
			return true;

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断是不是一个合法的电子邮件地址
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		if (email == null || email.trim().length() == 0)
			return false;
		return emailer.matcher(email).matches();
	}

	/**
	 * 字符串转整数
	 * 
	 * @param str
	 * @param defValue
	 * @return
	 */
	public static int toInt(String str, int defValue) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
		}
		return defValue;
	}

	/**
	 * 对象转整数
	 * 
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static int toInt(Object obj) {
		if (obj == null)
			return 0;
		return toInt(obj.toString(), 0);
	}

	/**
	 * 字符串转Double
	 * 
	 * @param str
	 * @param defValue
	 * @return
	 */
	public static double toDouble(String str, double defValue) {
		try {
			return Double.parseDouble(str);
		} catch (Exception e) {
		}
		return defValue;
	}	
	/**
	 * 对象转double
	 * 
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static double toDouble(Object obj) {
		if (obj == null)
			return 0;
		return  toDouble(obj.toString(), 0);
	}		
	
	/**
	 * 对象转整数
	 * 
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static long toLong(String obj) {
		try {
			return Long.parseLong(obj);
		} catch (Exception e) {
		}
		return 0;
	}

	/**
	 * 字符串转布尔值
	 * 
	 * @param b
	 * @return 转换异常返回 false
	 */
	public static boolean toBool(String b) {
		try {
			return Boolean.parseBoolean(b);
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * 将一个InputStream流转换成字符串
	 * 
	 * @param is
	 * @return
	 */
	public static String toConvertString(InputStream is) {
		StringBuffer res = new StringBuffer();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader read = new BufferedReader(isr);
		try {
			String line;
			line = read.readLine();
			while (line != null) {
				res.append(line);
				line = read.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != isr) {
					isr.close();
					isr.close();
				}
				if (null != read) {
					read.close();
					read = null;
				}
				if (null != is) {
					is.close();
					is = null;
				}
			} catch (IOException e) {
			}
		}
		return res.toString();
	}
}
