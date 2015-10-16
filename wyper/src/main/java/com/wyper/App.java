package com.wyper;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.wyper.service.HtmlParseService;

/**
 * 
 * */
public class App {
	
    public static void main(String[] args ){
    	
    	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:conf/applicationContent.xml");
    	HtmlParseService htmlParseService = (HtmlParseService)context.getBean("htmlParseService");
    }
}
