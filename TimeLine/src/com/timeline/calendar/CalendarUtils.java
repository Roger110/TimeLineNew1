package com.timeline.calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.timeline.bean.CalendarBean;
import com.timeline.bean.CalendarTagBean;


public class CalendarUtils {

	private int row = 6;
	private int column = 7;
	private static CalendarUtils instance;
	public static final int CURRENT_PAGENO = 500;

	public static CalendarUtils getInstance() {
		if (null == instance)
			instance = new CalendarUtils();
		return instance;
	}

	private CalendarUtils() {
	}

	/**
	 * @param mPageNumber
	 * @return
	 */
	public Calendar getSelectedMonth(int mPageNumber) {
		Calendar calendar = Calendar.getInstance();
		if (mPageNumber > 500) {
			for (int i = 0; i < mPageNumber - 500; i++) {
				calendar.add(Calendar.MONTH, 1);
			}
		} else if (mPageNumber < 500) {
			for (int i = 0; i < 500 - mPageNumber; i++) {
				calendar.add(Calendar.MONTH, -1);
			}
		} else {

		}
		return calendar;
	}

	public Calendar getSelectedMonth(int mPageNumber, Calendar calendar) {
		if (mPageNumber > 500) {
			for (int i = 0; i < mPageNumber - 500; i++) {
				calendar.add(Calendar.MONTH, 1);
			}
		} else if (mPageNumber < 500) {
			for (int i = 0; i < 500 - mPageNumber; i++) {
				calendar.add(Calendar.MONTH, -1);
			}
		} else {

		}
		return calendar;
	}

	public Calendar getSelectedWeekOfYear(int mPageNumber) {
		Calendar calendar = Calendar.getInstance();
		if (mPageNumber > 500) {
			for (int i = 0; i < mPageNumber - 500; i++) {
				calendar.add(Calendar.WEEK_OF_YEAR, 1);
			}
		} else if (mPageNumber < 500) {
			for (int i = 0; i < 500 - mPageNumber; i++) {
				calendar.add(Calendar.WEEK_OF_YEAR, -1);
			}
		} else {

		}
		return calendar;
	}

	public Calendar getSelectedWeekOfYear(int mPageNumber, Calendar calendar) {
		if (mPageNumber > 500) {
			for (int i = 0; i < mPageNumber - 500; i++) {
				calendar.add(Calendar.WEEK_OF_YEAR, 1);
			}
		} else if (mPageNumber < 500) {
			for (int i = 0; i < 500 - mPageNumber; i++) {
				calendar.add(Calendar.WEEK_OF_YEAR, -1);
			}
		} else {

		}
		return calendar;
	}

	public Calendar getSelectedDayOfMonth(int mPageNumber) {
		Calendar calendar = Calendar.getInstance();
		if (mPageNumber > 500) {
			for (int i = 0; i < mPageNumber - 500; i++) {
				calendar.add(Calendar.DAY_OF_MONTH, 1);
			}
		} else if (mPageNumber < 500) {
			for (int i = 0; i < 500 - mPageNumber; i++) {
				calendar.add(Calendar.DAY_OF_MONTH, -1);
			}
		} else {

		}
		return calendar;
	}

	public Calendar getSelectedDayOfMonth(int mPageNumber, Calendar calendar) {
		if (mPageNumber > 500) {
			for (int i = 0; i < mPageNumber - 500; i++) {
				calendar.add(Calendar.DAY_OF_MONTH, 1);
			}
		} else if (mPageNumber < 500) {
			for (int i = 0; i < 500 - mPageNumber; i++) {
				calendar.add(Calendar.DAY_OF_MONTH, -1);
			}
		} else {

		}
		return calendar;
	}

	public List<CalendarBean> getDaysOfMonth(Calendar cal) {

		Calendar currentCal = (Calendar) cal.clone();
		Calendar nextCal = (Calendar) cal.clone();
		Calendar upCal = (Calendar) cal.clone();

		List<CalendarBean> days = new LinkedList<CalendarBean>();
		days.addAll(getCurrent(currentCal));
		// System.out.println("current: " + days);

		List<CalendarBean> nextDays = new ArrayList<CalendarBean>();

		nextCal.add(Calendar.MONTH, 1);
		nextDays.addAll(getNext(nextCal));

		List<CalendarBean> upDays = new ArrayList<CalendarBean>();

		upCal.add(Calendar.MONTH, -1);
		upDays.addAll(getUp(upCal));
		// 7-0 1-1 2-2 3-3 4-4 5-5 6-6
		// 从上个月结束取出

		int day_of_week2firstday = days.get(0).day_of_week;
		if (day_of_week2firstday != 7) {
			List<CalendarBean> header = upDays.subList(upDays.size()
					- day_of_week2firstday, upDays.size());
			days.addAll(0, header);
			// System.out.println("header: " + header);
		}

		if (days.size() < row * column) {
			int count = row * column - days.size();
			List<CalendarBean> footer = nextDays.subList(0, count);
			days.addAll(days.size(), footer);
			// System.out.println("footer: " + footer);
		}
		return days;
	}

	private List<CalendarBean> getCurrent(Calendar cal) {
		List<CalendarBean> days = new ArrayList<CalendarBean>();
		Calendar today = (Calendar) cal.clone();
		// new
		today.set(Calendar.DAY_OF_MONTH, Calendar.getInstance(Locale.CHINA)
				.get(Calendar.DAY_OF_MONTH));

		cal.set(Calendar.DATE, 1);
		int month = cal.get(Calendar.MONTH);
		int y = 1;
		while (cal.get(Calendar.MONTH) == month) {
			CalendarBean mb = new CalendarBean();
			int day = cal.get(Calendar.DATE);
			int x = cal.get(Calendar.DAY_OF_WEEK);
			if (x == 1) {
				x = 7;
			} else {
				x = x - 1;
			}
			if (cal.equals(today)) {
				mb.isToday = true;
			}

			mb.day_of_month = day;
			mb.day_of_week = x;
			mb.isCurrentMonth = true;
			mb.month_of_year = (month + 1);
			mb.week_of_month = cal.get(Calendar.WEEK_OF_MONTH);
			mb.week_of_year = cal.get(Calendar.WEEK_OF_YEAR);
			mb.year = cal.get(Calendar.YEAR);
			days.add(mb);
			if (x == 7) {
				y = y + 1;
			}
			cal.add(Calendar.DATE, 1);
		}
		cal.add(Calendar.DATE, -1);
		return days;
	}

	private List<CalendarBean> getUp(Calendar cal) {
		List<CalendarBean> days = new ArrayList<CalendarBean>();
		Calendar today = (Calendar) cal.clone();
		cal.set(Calendar.DATE, 1);
		int month = cal.get(Calendar.MONTH);
		int y = 1;
		while (cal.get(Calendar.MONTH) == month) {
			CalendarBean mb = new CalendarBean();
			int day = cal.get(Calendar.DATE);
			int x = cal.get(Calendar.DAY_OF_WEEK);
			if (x == 1) {
				x = 7;
			} else {
				x = x - 1;
			}
			if (cal.equals(today)) {
				// mb.isToday = true;
			}

			mb.day_of_month = day;
			mb.day_of_week = x;
			mb.isUpMonth = true;

			mb.month_of_year = (month + 1);
			mb.week_of_month = cal.get(Calendar.WEEK_OF_MONTH);
			mb.week_of_year = cal.get(Calendar.WEEK_OF_YEAR);
			mb.year = cal.get(Calendar.YEAR);

			days.add(mb);
			if (x == 7) {
				y = y + 1;
			}
			cal.add(Calendar.DATE, 1);
		}
		cal.add(Calendar.DATE, -1);
		return days;
	}

	private List<CalendarBean> getNext(Calendar cal) {
		List<CalendarBean> days = new ArrayList<CalendarBean>();
		Calendar today = (Calendar) cal.clone();
		cal.set(Calendar.DATE, 1);
		int month = cal.get(Calendar.MONTH);
		int y = 1;
		while (cal.get(Calendar.MONTH) == month) {
			CalendarBean mb = new CalendarBean();
			int day = cal.get(Calendar.DATE);
			int x = cal.get(Calendar.DAY_OF_WEEK);
			if (x == 1) {
				x = 7;
			} else {
				x = x - 1;
			}
			if (cal.equals(today)) {
				// mb.isToday = true;
			}

			mb.day_of_month = day;
			mb.day_of_week = x;
			mb.isNextMonth = true;

			mb.month_of_year = (month + 1);
			mb.week_of_month = cal.get(Calendar.WEEK_OF_MONTH);
			mb.week_of_year = cal.get(Calendar.WEEK_OF_YEAR);
			mb.year = cal.get(Calendar.YEAR);
			days.add(mb);
			if (x == 7) {
				y = y + 1;
			}
			cal.add(Calendar.DATE, 1);
		}
		cal.add(Calendar.DATE, -1);
		return days;
	}

	public List<CalendarBean> getDaysOfWeek(Calendar calendar, int week) {
		List<CalendarBean> weekDays = new ArrayList<CalendarBean>();
		calendar.set(Calendar.WEEK_OF_YEAR, week);

		calendar.set(Calendar.DAY_OF_WEEK, 1);
		for (int i = Calendar.SUNDAY; i <= Calendar.SATURDAY; i++) {
			CalendarBean day = new CalendarBean();
			day.day_of_month = calendar.get(Calendar.DAY_OF_MONTH);
			day.day_of_week = calendar.get(Calendar.DAY_OF_WEEK);
			day.month_of_year = calendar.get(Calendar.MONTH);
			day.week_of_month = calendar.get(Calendar.WEEK_OF_MONTH);
			day.week_of_year = calendar.get(Calendar.WEEK_OF_YEAR);
			day.year = calendar.get(Calendar.YEAR);
			weekDays.add(day);

			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}

		return weekDays;
	}

	public int[] getDaysOfWeekArray(Calendar calendar, int week) {

		int[] weekDays = new int[7];
		calendar.set(Calendar.WEEK_OF_YEAR, week);

		calendar.set(Calendar.DAY_OF_WEEK, 1);
		for (int i = Calendar.SUNDAY; i <= Calendar.SATURDAY; i++) {
			weekDays[i - 1] = calendar.get(Calendar.DAY_OF_MONTH);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}

		return weekDays;
	}

	public String getWeekday(int day) {
		String weekday = "";
		switch (day) {
		case 1:
			weekday = "星期日";
			break;
		case 2:
			weekday = "星期一";
			break;
		case 3:
			weekday = "星期二";
			break;
		case 4:
			weekday = "星期三";
			break;
		case 5:
			weekday = "星期四";
			break;
		case 6:
			weekday = "星期五";
			break;
		case 7:
			weekday = "星期六";
			break;
		}
		return weekday;
	}

	/**
	 * getTimestamp("2015-01-05 23:59:59")
	 * 
	 * @param template
	 *            default : yyyy-MM-dd HH:mm:ss
	 * */
	public long getTimestamp(String date, String template) {
		if (null == template || "".equals(template)) {
			template = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat format = new SimpleDateFormat(template, Locale.CHINA);
		try {
			return format.parse(date).getTime() / 1000;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * getTime((long)1420300799 * 1000)
	 * */
	public String getTime(long timestamp, String template) {
		Date date = new Date(timestamp);
		if (null == template || "".equals(template)) {
			template = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat format = new SimpleDateFormat(template, Locale.CHINA);
		return format.format(date);
	}

	/**
	 * getYear((long)1420300799 * 1000)
	 * */
	public int getYear(long timestamp) {
		Date date = new Date(timestamp);
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		return cl.get(Calendar.YEAR);
	}

	/**
	 * getMonth((long)1420300799 * 1000)
	 * */
	public int getMonth(long timestamp) {
		Date date = new Date(timestamp);
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		return cl.get(Calendar.MONTH);
	}

	/**
	 * getDayOfMonth((long)1420300799 * 1000)
	 * */
	public int getDayOfMonth(long timestamp) {
		Date date = new Date(timestamp);
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		return cl.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * getHours((long)1420300799 * 1000)
	 * */
	public int getHours(long timestamp) {
		Date date = new Date(timestamp);
		Calendar cl = Calendar.getInstance(Locale.CHINA);
		cl.setTime(date);
		return cl.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * getMinutes((long)1420300799 * 1000)
	 * */
	public int getMinutes(long timestamp) {
		Date date = new Date(timestamp);
		Calendar cl = Calendar.getInstance(Locale.CHINA);
		cl.setTime(date);
		return cl.get(Calendar.MINUTE);
	}

	public Map<String, List<CalendarTagBean>> tagGroupByDay(
			List<CalendarTagBean> tags) {
		Map<String, List<CalendarTagBean>> tagMap = new HashMap<String, List<CalendarTagBean>>();
		for (int i = 0; i < tags.size(); i++) {
			CalendarTagBean tag = tags.get(i);
			String key = getTime(tag.start * 1000, "yyyy-MM-dd");
			if (tagMap.containsKey(key)) {
				tagMap.get(key).add(tag);
			} else {
				tagMap.put(key, new ArrayList<CalendarTagBean>());
				tagMap.get(key).add(tag);
			}
		}
		return tagMap;
	}

	public String getViewTag(CalendarBean bean) {
		int year = bean.year;
		int month = bean.month_of_year;
		int day = bean.day_of_month;
		String tag = year + "-" + (month < 10 ? "0" + month : month) + "-"
				+ (day < 10 ? "0" + day : day);
		return tag;
	}

	public String getDayStart(Calendar cl) {
		Calendar calendar = (Calendar) cl.clone();
		String start = calendar.get(Calendar.YEAR)
				+ "-"
				+ ((calendar.get(Calendar.MONTH) + 1) < 10 ? "0"
						+ (calendar.get(Calendar.MONTH) + 1) : (calendar
						.get(Calendar.MONTH)) + 1)
				+ "-"
				+ (calendar.get(Calendar.DAY_OF_MONTH) < 10 ? "0"
						+ calendar.get(Calendar.DAY_OF_MONTH) : (calendar
						.get(Calendar.DAY_OF_MONTH)) + 1) + " 00:00:00";
		System.out.println("getDayStart: " + start);
		return start;
	}

	public String getDayEnd(Calendar cl) {
		Calendar calendar = (Calendar) cl.clone();
		String end = calendar.get(Calendar.YEAR)
				+ "-"
				+ ((calendar.get(Calendar.MONTH) + 1) < 10 ? "0"
						+ (calendar.get(Calendar.MONTH) + 1) : (calendar
						.get(Calendar.MONTH)) + 1)
				+ "-"
				+ (calendar.get(Calendar.DAY_OF_MONTH) < 10 ? "0"
						+ calendar.get(Calendar.DAY_OF_MONTH) : calendar
						.get(Calendar.DAY_OF_MONTH)) + " 23:59:59";
		System.out.println("getDayEnd: " + end);
		return end;
	}

	public int[] getMinutes(CalendarTagBean tagBean) {
		int[] minutes = new int[2];
		long start = tagBean.start * 1000;
		long end = tagBean.end * 1000;
		System.out.println("start==========================================:");
		System.out.println(getTime(start, null));
		System.out.println(getTime(end, null));
		System.out.println(getHours(start));
		System.out.println(getHours(end));
		System.out.println(getMinutes(start));
		System.out.println(getMinutes(end));
		System.out
				.println("end==============================================:");
		minutes[0] = getHours(start) * 60 + getMinutes(start);
		minutes[1] = getHours(end) * 60 + getMinutes(end);
		return minutes;
	}
}
