package com.wyper;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wyper.service.YxiguaService;
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
    	YxiguaService yxiguaService = (YxiguaService)context.getBean("yxiguaService");
    	
    	if(args.length!=2){
    		System.out.println("参数错误！");
    		System.exit(0);
    	}
    	
		String www = args[0];//决定用哪种解析
		String path = args[1];
		if(www.equals("www.yxigua.com"))
			yxiguaService.parseHtml(NumberTools.randomNumber(3), path);//文件名是随机的3位整数
    	
    	System.exit(0);
    }
}
