package com.wyper;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wyper.service.HtmlParseService;
import com.wyper.util.NumberTools;

/**
 * 	参数 1： url
 * 	参数 2：频道jb
 * 	参数 3：网址htmlpath
 * 
 * */
public class One {
	
    public static void main(String[] args ){
    	
    	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:conf/applicationContent.xml");
    	HtmlParseService htmlParseService = (HtmlParseService)context.getBean("htmlParseService");
    	
    	if(args.length!=3){
    		System.out.println("参数错误！");
    		System.exit(0);
    	}
    	
    	//抓取内容页
    	if(args[0].equalsIgnoreCase("url")){
    		String pd = args[1];
    		String path = args[2];
    		htmlParseService.parseHtml(NumberTools.randomNumber(3), pd, path);//文件名是随机的3位整数
    	}
    	
    	System.exit(0);
    }
}
