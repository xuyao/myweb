package com.wyper;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

import com.wyper.service.SpiderService;
import com.wyper.util.NumberTools;

/**
 * 生成主页，解析网页并且入库
 * 	
 * 参数1：网址www.xxx.com
 * 参数2：网址类型：m or t 电影或电视剧
 * 参数3：url 源网址的url
 * 参数4：number 生成的3位随机文件名
 * 参数5：ctime 数据库记录创建时间
 * 
 * 支持网址:www.yxigua.com,
 * 
 * 例子：com.wyper.One www.yxigua.com m http://www.yxigua.com/xiguayingyin/72970.html 100 "2000-10-29 00:00:01.000"
 * 
 * */
public class One {
	
    private static ApplicationContext context;
    
	public static void main(String[] args ){
		context = new ClassPathXmlApplicationContext("classpath:conf/applicationContent.xml");
    	SpiderService spiderService = (SpiderService)context.getBean("spiderService");
    	
    	if(args.length<3){
    		System.out.println("参数错误！");
    		System.exit(0);
    	}
    	
		String wwwName = args[0];//决定用哪种解析
		String type = args[1];//m-电影  t-电视剧tv
		String url = args[2];
		String number = null;
		String ctime = null;
		if(args.length>=4 && !StringUtils.isEmpty(args[3])){
			number = args[3];
			if(args.length>=5)
				ctime = args[4];//时间设置，确定列表顺序
		}
		else
			number = NumberTools.randomNumber(3);//随机生成3位数字做文件名
		
		spiderService.parseHtml(wwwName, type, url, number, ctime, null, true, null);
		
    	System.exit(0);
    }
    
    
}
