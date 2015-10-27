package com.wyper;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wyper.po.Movies;
import com.wyper.service.DbService;
import com.wyper.service.SpiderService;

public class Redo {

	/**
	 * 参数1：www.xxx.com
	 * 参数2：type 电影类型 m-电影  t-电视剧
	 * 参数3：电影名称 数据库字段name
	 * 参数4：mtime 时间用于分目录 20151010
	 * 这个类运行会从数据库里取数据然后生成html，用于模板调整，不会新增数据，新增数据用ReOne
	 * 
	 * */
	public static void main(String[] args){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:conf/applicationContent.xml");
    	SpiderService spiderService = (SpiderService)context.getBean("spiderService");
    	DbService dbService = (DbService)context.getBean("dbService");
    	
    	if(args.length<4){
    		System.out.println("参数错误！");
    		System.exit(0);
    	}
    	
    	String wwwName = args[0];
    	String type = args[1];//m-电影  t-电视剧tv
    	String name = args[2];
    	String mtime = args[3];
    	
    	Movies m = dbService.queryMovies(name);
    	
		String src_url = m.getSrc_url();
		String html_url = m.getHtml_url();
		html_url = html_url.split("\\.")[0];
		String[] arr = html_url.split("\\/");
		String number = arr[arr.length-1];
		
		spiderService.parseHtml(wwwName, type, src_url, number, null, mtime, false);
		try {
			Thread.sleep(1000*3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
    	System.exit(0);	
	}
	
	
}
