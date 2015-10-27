package com.wyper.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.wyper.po.Mdown;
import com.wyper.po.Movies;

@Service
public class DbService {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	//保存返回id
	public Integer saveMovies(final Movies movie){
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		 jdbcTemplate.update(new PreparedStatementCreator() {  
		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {  
		            String sql = "insert into tb_movies (src_url, name, content, html_url, pic_url, ctime) values(?,?,?,?,?,datetime('"+movie.getCtime()+"'))";   
		               PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);  
		               ps.setString(1, movie.getSrc_url());  
		               ps.setString(2, movie.getName());  
		               ps.setString(3, movie.getContent());  
		               ps.setString(4, movie.getHtml_url());  
		               ps.setString(5, movie.getPic_url());  
		               return ps;  
		        }  
		    }, keyHolder);  
		Integer id = keyHolder.getKey().intValue();
		return id;
	}
	
	
	//保存下载链接
	public void saveMdown(Mdown mdown){
		jdbcTemplate.update("insert into tb_movies_down (id, movies_id, mname,type, src_url, html_url,down_url) values(?,?,?,?,?,?,?)", 
				mdown.getId(), mdown.getMovies_id(), mdown.getMname(),mdown.getType(), mdown.getSrc_url(), 
				mdown.getHtml_url(), mdown.getDown_url());
	}
	
	
}
