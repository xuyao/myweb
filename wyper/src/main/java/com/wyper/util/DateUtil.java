package com.wyper.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 	@author xuyao
 * */
public class DateUtil {
	
	public enum DateUnit{
		minute,hour,day,month,year
	}
	
	public static final String LONG_PATTERN = "yyyy-MM-dd HH:mm:ss.sss";
	public static final String STR_DATE_PATTERN = "yyyyMMdd";
	public static final String LONGID = "yyyyMMddHHmmss";
	public static final String YEAR_PATTERN = "yyyy";
	public static final String Month_PATTERN = "MM";
	public static final String Day_PATTERN = "dd";

	
	public static String getNowformatLongPattern() {
		SimpleDateFormat dateformat=new SimpleDateFormat(LONG_PATTERN);
		return dateformat.format(new Date());
	}
	
	public static String formatLongPattern(Date date) {
		return format(date, LONG_PATTERN);
	}

	public static String format(Date date, String pattern) {
		if (date == null)
			return null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(date);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Date getTodayDay() {
		return new Date();
	}

	
	//返回日期
	public static Date getDate(String ctime, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date d = null;
		try {
			d = sdf.parse(ctime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}
	
	
	public static void main(String[] args) {
		System.out.println(new Date().getTime());
		System.out.println(DateUtil.format(new Date(1417868141515l), DateUtil.LONG_PATTERN));
	}
	
}
