package com.timeline.controls;

import java.util.Calendar;

import android.util.Log;

public class DateUtils {
	/**
     * 获取某年某月的天数
     * 
     * @param year
     * @param month
     * @return
     */
    public static int getMonthDays(int year, int month) {
		month++;
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12: 
		    return 31;
		case 4:
		case 6:
		case 9:
		case 11: 
		    return 30;
		case 2:
			if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)){
				return 29;
			}else{
				return 28;
			}
		default:
			return  -1;
		}
    }
    /**
     * 获取某年某月第一天是星期几
     * @param year
     * 		年
     * @param month
     * 		月
     * @return
     * 	
     */
    public static int getFirstDayWeek(int year, int month){
    	Calendar calendar = Calendar.getInstance();
    	calendar.set(year, month, 1);
    	Log.d("DateView", "DateView:First:" + calendar.getFirstDayOfWeek());
    	return calendar.get(Calendar.DAY_OF_WEEK);
    }
    
}
