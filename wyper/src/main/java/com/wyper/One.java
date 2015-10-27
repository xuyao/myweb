package com.wyper;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wyper.service.SpiderService;
import com.wyper.util.NumberTools;

/**
 * 	参数 1：支持网址
 * 	参数 2：网址url
 *  支持网址:www.yxigua.com,
 * 
 * */
public class One {
	
    public static void main(String[] args ){
    	
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:conf/applicationContent.xml");
    	SpiderService spiderService = (SpiderService)context.getBean("spiderService");
    	
    	if(args.length<2){
    		System.out.println("参数错误！");
    		System.exit(0);
    	}
    	
		String wwwName = args[0];//决定用哪种解析
		String url = args[1];
		String number = null;
		if(args.length==3)
			number = args[2];
		else
			number = NumberTools.randomNumber(3);//随机生成3位数字做文件名
			
		spiderService.parseHtml(wwwName, url, number);
		
    	System.exit(0);
    }
}
