package com.wyper.util;

import java.io.File;
import java.util.Date;

public class PathUtil {

	/* 绝对路径url 带http*/
	public static String absUrl(){
		Date d = new Date();
		String url = "http://"+PropertisUtil.get("www.url")+"/"+DateUtil.format(d, 
				DateUtil.YEAR_PATTERN)+"/"+DateUtil.format(d, DateUtil.Month_PATTERN)+"/"+DateUtil.format(d, 
						DateUtil.Day_PATTERN)+"/";
		return url;
	}
	
	
	/* 相对路径url */
	public static String rltUrl(){
		Date d = new Date();
		String url = "/"+DateUtil.format(d, DateUtil.YEAR_PATTERN)+"/"+DateUtil.format(d, DateUtil.Month_PATTERN)
				+"/"+DateUtil.format(d, DateUtil.Day_PATTERN)+"/";
		return url;
	}
	
	
	/* 文件生成路径path */
	public static String path(){
		Date d = new Date();
		String path = PropertisUtil.get("www.path") + File.separator + DateUtil.format(d, 
				DateUtil.YEAR_PATTERN) + File.separator + DateUtil.format(d, DateUtil.Month_PATTERN) + 
				File.separator + DateUtil.format(d, DateUtil.Day_PATTERN)+File.separator;
		return path;
		
	}
	
}
