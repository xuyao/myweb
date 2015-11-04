package com.wyper;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wyper.service.SpiderService;

/**
 * 生成主页列表碎片
 * 	
 * 参数1：网址类型：m or t 电影或电视剧
 * 例子：com.wyper.List m
 * 
 * */
public class List {

	private static ApplicationContext context;

	public static void main(String[] args ){
    	
		context = new ClassPathXmlApplicationContext("classpath:conf/applicationContent.xml");
    	SpiderService spiderService = (SpiderService)context.getBean("spiderService");
    	
    	if(args.length<1){
    		System.out.println("参数错误！");
    		System.exit(0);
    	}
    	String type = args[0];//m-电影  t-电视剧tv
		spiderService.listHtml(type);
		
    	System.exit(0);
    }
    
    
}
