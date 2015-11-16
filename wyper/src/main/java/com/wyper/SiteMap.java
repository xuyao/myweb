package com.wyper;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wyper.po.Movies;
import com.wyper.service.DbService;
import com.wyper.service.FreeMarkerService;

public class SiteMap {
	
	private static ApplicationContext context;
	
	public static void main(String[] args){
		context = new ClassPathXmlApplicationContext("classpath:conf/applicationContent.xml");
		FreeMarkerService freeMarkerService = (FreeMarkerService)context.getBean("freeMarkerService");
		DbService dbService = (DbService)context.getBean("dbService");
		
		List<Movies> list = dbService.listMovies(999);
		freeMarkerService.genSiteMap(list);
		
	}
	
	
}
