package com.wyper;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wyper.service.SpiderService;

public class List {

	public static void main(String[] args ){
    	
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:conf/applicationContent.xml");
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
