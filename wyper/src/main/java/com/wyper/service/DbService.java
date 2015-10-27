package com.wyper.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.wyper.po.Mdown;
import com.wyper.po.Movies;

@Service
public class DbService {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	//保存电影返回id
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
	
	
	//保存电影下载链接
	public void saveMdown(Mdown mdown){
		jdbcTemplate.update("insert into tb_movies_down (id, movies_id, mname,type, src_url, html_url,down_url) values(?,?,?,?,?,?,?)", 
				mdown.getId(), mdown.getMovies_id(), mdown.getMname(),mdown.getType(), mdown.getSrc_url(), 
				mdown.getHtml_url(), mdown.getDown_url());
	}
	
	
	//查询电影tb_movies表
	public List<Movies> listMovies(Integer limit){
		List<Movies> list = jdbcTemplate.query("select id, src_url, name, content, html_url, pic_url, "
				+ "ctime from tb_movies order by ctime desc limit ?", new Object[]{limit},new RowMapper<Movies>(){
			public Movies mapRow(ResultSet rs, int index) throws SQLException {  
				Movies movies = new Movies();
				movies.setId(rs.getInt("id"));
				movies.setSrc_url(rs.getString("src_url"));
				movies.setName(rs.getString("name"));
				movies.setContent(rs.getString("content"));
				movies.setHtml_url(rs.getString("html_url"));
				movies.setPic_url(rs.getString("pic_url"));
				movies.setCtime(rs.getString("ctime"));
                return movies;
            }
		});
		return list;
	}
	
	
	/** 电视剧  */
	//保存电视剧返回id
	public Integer saveTV(final Movies movie){
		KeyHolder keyHolder = new GeneratedKeyHolder();
		 jdbcTemplate.update(new PreparedStatementCreator() {  
		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {  
		            String sql = "insert into tb_tv (src_url, name, content, html_url, pic_url, ctime) values(?,?,?,?,?,datetime('"+movie.getCtime()+"'))";   
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
	
	
	//保存电视剧下载链接
	public void saveTV(Mdown mdown){
		jdbcTemplate.update("insert into tb_tv_down (id, tv_id, tname,type, src_url, html_url,down_url) values(?,?,?,?,?,?,?)", 
				mdown.getId(), mdown.getMovies_id(), mdown.getMname(),mdown.getType(), mdown.getSrc_url(), 
				mdown.getHtml_url(), mdown.getDown_url());
	}
	
	
	//查询电视剧tb_tv表
	public List<Movies> listTV(Integer limit){
		List<Movies> list = jdbcTemplate.query("select id, src_url, name, content, html_url, pic_url, "
				+ "ctime from tb_tv order by ctime desc limit ?", new Object[]{limit},new RowMapper<Movies>(){
			public Movies mapRow(ResultSet rs, int index) throws SQLException {  
				Movies movies = new Movies();
				movies.setId(rs.getInt("id"));
				movies.setSrc_url(rs.getString("src_url"));
				movies.setName(rs.getString("name"));
				movies.setContent(rs.getString("content"));
				movies.setHtml_url(rs.getString("html_url"));
				movies.setPic_url(rs.getString("pic_url"));
				movies.setCtime(rs.getString("ctime"));
                return movies;
            }
		});
		return list;
	}
	
	
	//根据name查询电影tb_movies表
	public Movies queryMovies(String name){
		Movies movies = jdbcTemplate.queryForObject("select id, src_url, name, content, html_url, pic_url, "
				+ "ctime from tb_movies where name=? order by ctime desc", new Object[]{name} ,new RowMapper<Movies>(){
			public Movies mapRow(ResultSet rs, int index) throws SQLException {  
				Movies movies = new Movies();
				movies.setId(rs.getInt("id"));
				movies.setSrc_url(rs.getString("src_url"));
				movies.setName(rs.getString("name"));
				movies.setContent(rs.getString("content"));
				movies.setHtml_url(rs.getString("html_url"));
				movies.setPic_url(rs.getString("pic_url"));
				movies.setCtime(rs.getString("ctime"));
                return movies;
            }
		});
		return movies;
	}
	
	
	//根据name查询电视剧tb_tv表
	public Movies queryTV(String name){
		Movies movies = jdbcTemplate.queryForObject("select id, src_url, name, content, html_url, pic_url, "
				+ "ctime from tb_tv where name=? order by ctime desc", new Object[]{name},new RowMapper<Movies>(){
			public Movies mapRow(ResultSet rs, int index) throws SQLException {  
				Movies movies = new Movies();
				movies.setId(rs.getInt("id"));
				movies.setSrc_url(rs.getString("src_url"));
				movies.setName(rs.getString("name"));
				movies.setContent(rs.getString("content"));
				movies.setHtml_url(rs.getString("html_url"));
				movies.setPic_url(rs.getString("pic_url"));
				movies.setCtime(rs.getString("ctime"));
                return movies;
            }
		});
		return movies;
	}
	
	
}
