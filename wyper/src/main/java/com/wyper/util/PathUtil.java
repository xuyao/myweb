package com.wyper.util;

import java.io.File;
import java.util.Date;

public class PathUtil {

	/* 返回url */
	public static String url(){
		Date d = new Date();
		String url = PropertisUtil.get("www.url")+"/"+DateUtil.format(d, 
				DateUtil.YEAR_PATTERN)+"/"+DateUtil.format(d, DateUtil.Month_PATTERN)+"/"+DateUtil.format(d, 
						DateUtil.Day_PATTERN)+"/";
		return url;
	}
	
	/* 返回path */
	public static String path(){
		Date d = new Date();
		String path = PropertisUtil.get("www.path") + File.separator + DateUtil.format(d, 
				DateUtil.YEAR_PATTERN) + File.separator + DateUtil.format(d, DateUtil.Month_PATTERN) + 
				File.separator + DateUtil.format(d, DateUtil.Day_PATTERN)+File.separator;
		return path;
		
	}
	
}
