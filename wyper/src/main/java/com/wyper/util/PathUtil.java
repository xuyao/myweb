package com.wyper.util;

import java.io.File;
import java.util.Date;

public class PathUtil {

	/* 绝对路径url 带http*/
	public static String absUrl(Date d){
		String url = "http://"+PropertisUtil.get("www.url")+"/"+DateUtil.format(d, 
				DateUtil.YEAR_PATTERN)+"/"+DateUtil.format(d, DateUtil.Month_PATTERN)+"/"+DateUtil.format(d, 
						DateUtil.Day_PATTERN)+"/";
		return url;
	}
	
	
	/* 相对路径url */
	public static String rltUrl(Date d){
		String url = "/"+DateUtil.format(d, DateUtil.YEAR_PATTERN)+"/"+DateUtil.format(d, DateUtil.Month_PATTERN)
				+"/"+DateUtil.format(d, DateUtil.Day_PATTERN)+"/";
		return url;
	}
	
	
	/* 文件生成路径path */
	public static String path(Date d){
		String path = PropertisUtil.get("www.path") + File.separator + DateUtil.format(d, 
				DateUtil.YEAR_PATTERN) + File.separator + DateUtil.format(d, DateUtil.Month_PATTERN) + 
				File.separator + DateUtil.format(d, DateUtil.Day_PATTERN)+File.separator;
		return path;
		
	}
	
	
	/* 电影list文件生成路径path */
	public static String listMoviesPath(){
		String path = PropertisUtil.get("www.path.mlist");
		return path;
	}
	
	/* 电视剧list文件生成路径path */
	public static String listTVPath(){
		String path = PropertisUtil.get("www.path.tlist");
		return path;
		
	}
	
	/* 真正的列表，不是碎片 */
	public static String listMLPath(){
		String path = PropertisUtil.get("www.path.mllist");
		return path;
	}
	
	/* 真正的列表，不是碎片 */
	public static String listTLPath(){
		String path = PropertisUtil.get("www.path.tllist");
		return path;
	}
	
}
