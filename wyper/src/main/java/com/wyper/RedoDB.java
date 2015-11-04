package com.wyper;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

import com.wyper.po.Movies;
import com.wyper.service.DbService;
import com.wyper.service.SpiderService;

public class RedoDB {

	private static ApplicationContext context;

	/**
	 * 从数据库生成数据，不访问和解析网页，只从数据库取数据生成 用于模板修改
	 * 
	 * 参数1：type 电影类型 m-电影  t-电视剧
	 * 参数2：电影名称 数据库字段name
	 * 例子：com.wyper.RedoDB m 消失的世界  或者 com.wyper.RedoDB m 
	 * 
	 * */
	public static void main(String[] args){
		context = new ClassPathXmlApplicationContext("classpath:conf/applicationContent.xml");
    	DbService dbService = (DbService)context.getBean("dbService");
    	SpiderService spiderService = (SpiderService)context.getBean("spiderService");

    	if(args.length<1){
    		System.out.println("参数错误！");
    		System.exit(0);
    	}

    	String type = args[0];//m-电影  t-电视剧tv
    	String name = null;
    	
    	if(args.length==2)
    		name = args[1];//电影名称
    	
    	if(StringUtils.isEmpty(name)){
        	List<Movies> lists = dbService.listMovies(type, 9999);
        	for(Movies m : lists){
        		spiderService.genDetail(m);
        		System.out.println("从数据库生成页面结束！"+m.getName());
        	}
    	}else{
    		Movies m = dbService.queryMovies(type, name);
    		spiderService.genDetail(m);
    		System.out.println("从数据库生成页面结束！"+m.getName());
    	}

    	System.exit(0);	
	}
	
	
}
