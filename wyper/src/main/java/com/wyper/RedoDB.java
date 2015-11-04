package com.wyper;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wyper.po.Mdown;
import com.wyper.po.Movies;
import com.wyper.service.DbService;
import com.wyper.service.FreeMarkerService;

public class RedoDB {

	/**
	 * 参数1：type 电影类型 m-电影  t-电视剧
	 * 参数2：电影名称 数据库字段name
	 * 从数据库生成数据，不访问和解析网页
	 * 
	 * 例子：com.wyper.RedoDB m 消失的世界 
	 * 
	 * */
	public static void main(String[] args){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:conf/applicationContent.xml");
    	FreeMarkerService freeMarkerService = (FreeMarkerService)context.getBean("freeMarkerService");
    	DbService dbService = (DbService)context.getBean("dbService");
    	
    	if(args.length<3){
    		System.out.println("参数错误！");
    		System.exit(0);
    	}
    	
    	String type = args[0];//m-电影  t-电视剧tv
    	String name = args[1];
    	
    	Movies m = dbService.queryMovies(type, name);
    	
    	List<Mdown> list = dbService.queryMdown("tb_xl", m.getId());
    	for(Mdown md : list){
    		m.setContent(m.getContent()+md.getDown_url());
    	}
    	
    	list = dbService.queryMdown("tb_xg", m.getId());
    	for(Mdown md : list){
    		m.setContent(m.getContent()+md.getDown_url());
    	}
    	
    	list = dbService.queryMdown("tb_jj", m.getId());
    	for(Mdown md : list){
    		m.setContent(m.getContent()+md.getDown_url());
    	}
    	
    	list = dbService.queryMdown("tb_xf", m.getId());
    	for(Mdown md : list){
    		m.setContent(m.getContent()+md.getDown_url());
    	}

    	freeMarkerService.genHtml(m);
    	
    	System.exit(0);	
	}
	
	
}
