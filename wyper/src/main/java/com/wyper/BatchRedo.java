package com.wyper;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wyper.po.Movies;
import com.wyper.service.DbService;
import com.wyper.service.SpiderService;

public class BatchRedo {

	/**
	 * 这个类运行会从数据库里取数据然后生成html，用于模板调整，不会新增数据，新增数据用ReOne
	 * 例子：com.wyper.Redo www.yxigua.com m 《消失的世界》 20151010
	 * 
	 * */
	public static void main(String[] args){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:conf/applicationContent.xml");
    	SpiderService spiderService = (SpiderService)context.getBean("spiderService");
    	DbService dbService = (DbService)context.getBean("dbService");
    	
    	if(args.length<1){
    		System.out.println("参数错误！");
    		System.exit(0);
    	}
    	
    	String wwwName = args[0];
    	String type = args[1];
    	
    	List<Movies> list = dbService.listMovies(type, 999);
    	
    	for(Movies m : list){
    		String src_url = m.getSrc_url();
    		String html_url = m.getHtml_url();
    		html_url = html_url.split("\\.")[0];
    		String[] arr = html_url.split("\\/");
    		String number = arr[arr.length-1];
    		String mtime = arr[0]+arr[1]+arr[2]+arr[3];

    		spiderService.parseHtml(wwwName, type, src_url, number, null, mtime, false);
    	}
    	
    	System.exit(0);	
	}
	
	
}
