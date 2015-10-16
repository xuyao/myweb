package com.wyper.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 	配置文件读取类
 *  @author xuyao
 * */
public class PropertisUtil {

	private static Properties p = new Properties();

	static{
		try {
			InputStream in = PropertisUtil.class.getResourceAsStream("/conf/config.propertis");
			p.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String get(String key) {
		return (String)p.get(key);
	}
	
}
