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
 * �ַ����������߰�
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
	 * ���ַ���תΪ��������
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
	 * ���ַ���תΪ����ʱ������
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
	 * ���Ѻõķ�ʽ��ʾʱ��
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

		// �ж��Ƿ���ͬһ��
		String curDate = dateFormater.get().format(cal.getTime());
		String paramDate = dateFormater.get().format(time);
		if (curDate.equals(paramDate)) {
			int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
			if (hour == 0)
				ftime = Math.max(
						(cal.getTimeInMillis() - time.getTime()) / 60000, 1)
						+ "����ǰ";
			else
				ftime = hour + "Сʱǰ";
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
						+ "����ǰ";
			else
				ftime = hour + "Сʱǰ";
		} else if (days == 1) {
			ftime = "����";
		} else if (days == 2) {
			ftime = "ǰ��";
		} else if (days > 2 && days <= 10) {
			ftime = days + "��ǰ";
		} else if (days > 10) {
			ftime = dateFormater.get().format(time);
		}
		return ftime;
	}

	
	/**
	 * ���Ѻõķ�ʽ��ʾ����
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

		// �ж��Ƿ���ͬһ��
		String curDate = dateFormater.get().format(cal.getTime());
		String paramDate = dateFormater.get().format(sdate);
		if (curDate.equals(paramDate)) {
			
			ftime = "����";
			return ftime;
		}

		long lt = sdate.getTime() / 86400000;
		long ct = cal.getTimeInMillis() / 86400000;
		int days = (int) (ct - lt);
		if (days == 0) {
			ftime = "����";
		} else if (days == 1) {
			ftime = "����";
		} else if (days == 2) {
			ftime = "ǰ��";
		} else if (days > 2 && days <= 10) {
			ftime = days + "��ǰ";
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
			altStr = String.valueOf(mCount) + "��";
		}
		altStr += String.valueOf(sCount) + "��";
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
	 * �жϸ����ַ���ʱ���Ƿ�Ϊ����
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
	 * ����long���͵Ľ��������
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
	 * �жϸ����ַ����Ƿ�հ״��� �հ״���ָ�ɿո��Ʊ�����س��������з���ɵ��ַ��� �������ַ���Ϊnull����ַ���������true
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
	 * �ж��ǲ���һ���Ϸ��ĵ����ʼ���ַ
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
	 * �ַ���ת����
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
	 * ����ת����
	 * 
	 * @param obj
	 * @return ת���쳣���� 0
	 */
	public static int toInt(Object obj) {
		if (obj == null)
			return 0;
		return toInt(obj.toString(), 0);
	}

	/**
	 * �ַ���תDouble
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
	 * ����תdouble
	 * 
	 * @param obj
	 * @return ת���쳣���� 0
	 */
	public static double toDouble(Object obj) {
		if (obj == null)
			return 0;
		return  toDouble(obj.toString(), 0);
	}		
	
	/**
	 * ����ת����
	 * 
	 * @param obj
	 * @return ת���쳣���� 0
	 */
	public static long toLong(String obj) {
		try {
			return Long.parseLong(obj);
		} catch (Exception e) {
		}
		return 0;
	}

	/**
	 * �ַ���ת����ֵ
	 * 
	 * @param b
	 * @return ת���쳣���� false
	 */
	public static boolean toBool(String b) {
		try {
			return Boolean.parseBoolean(b);
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * ��һ��InputStream��ת�����ַ���
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
