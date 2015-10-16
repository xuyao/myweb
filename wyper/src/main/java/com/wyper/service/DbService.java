package com.wyper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DbService {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	/* save to db */
	public void save(String table, Content content){
		jdbcTemplate.update("insert into ? (id,title,content,ctime) values(?,?,?,?)", table, 
				content.getId(), content.getTitle(), content.getContent(), content.getCtime());
	}
}
