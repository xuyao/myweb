package com.wyper;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 抓取网页解析
 * 文章和相关图片按照时间的顺序保存
 *
 */
public class App {
    public static void main( String[] args ){
    	
    	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:conf/applicationContent.xml");
    	JdbcTemplate jdbcTemplate = (JdbcTemplate)context.getBean("jdbcTemplate");
    	
    	System.out.println(jdbcTemplate.queryForList("select * from t_jb;").size());
    	
    	//1、抓取单个网页参数
    	
    	
    	//1、定时抓取
    }
}
