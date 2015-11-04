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
		            String sql = "insert into tb_movies (src_url, name, type,content, html_url, pic_url, ctime) "
		            		+ "values(?,?,?,?,?,?,datetime('"+movie.getCtime()+"'))";   
		               PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);  
		               ps.setString(1, movie.getSrc_url());
		               ps.setString(2, movie.getName());
		               ps.setString(3, movie.getType());
		               ps.setString(4, movie.getContent());
		               ps.setString(5, movie.getHtml_url());
		               ps.setString(6, movie.getPic_url());
		               return ps;  
		        }  
		    }, keyHolder);  
		Integer id = keyHolder.getKey().intValue();
		return id;
	}
	
	
	//查询电影tb_movies表
	public List<Movies> listMovies(String type, Integer limit){
		List<Movies> list = jdbcTemplate.query("select id, src_url, name, type,content, html_url, pic_url, "
				+ "ctime from tb_movies where type=? order by ctime desc limit ?", 
				new Object[]{type, limit}, new RowMapper<Movies>(){
			public Movies mapRow(ResultSet rs, int index) throws SQLException {  
				Movies movies = new Movies();
				movies.setId(rs.getInt("id"));
				movies.setSrc_url(rs.getString("src_url"));
				movies.setName(rs.getString("name"));
				movies.setType(rs.getString("type"));
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
	public Movies queryMovies(String type, String name){
		Movies movies = jdbcTemplate.queryForObject("select id, src_url, name, type, content, html_url, pic_url, "
				+ "ctime from tb_movies where type=? and name=? order by ctime desc", 
				new Object[]{type, name} ,new RowMapper<Movies>(){
			public Movies mapRow(ResultSet rs, int index) throws SQLException {  
				Movies movies = new Movies();
				movies.setId(rs.getInt("id"));
				movies.setSrc_url(rs.getString("src_url"));
				movies.setName(rs.getString("name"));
				movies.setType(rs.getString("type"));
				movies.setContent(rs.getString("content"));
				movies.setHtml_url(rs.getString("html_url"));
				movies.setPic_url(rs.getString("pic_url"));
				movies.setCtime(rs.getString("ctime"));
                return movies;
            }
		});
		return movies;
	}
	
	
	//保存吉吉影音
	public void saveMdown(String tableName, Mdown mdown){
		jdbcTemplate.update("insert into "+tableName+" (id, movies_id, mname, html_url, down_url) values(?,?,?,?,?)", 
				mdown.getId(), mdown.getMovies_id(), mdown.getMname(),mdown.getHtml_url(), mdown.getDown_url());
	}
	
	
	//根据资源地址查询数据
	public Integer countMdown(String tableName, String downurl){
		return (Integer)jdbcTemplate.queryForObject("select count(0) from "+tableName+" where down_url=?", 
				new Object[]{downurl }, Integer.class);
	}
	
	
	//查询mdown
	public List<Mdown> queryMdown(String tableName, Integer moviesId){
		List<Mdown> list = jdbcTemplate.query("select id, movies_id, mname, html_url, down_url "
				+ "from "+tableName+" where movies_id=? order by id", 
				new Object[]{moviesId}, new RowMapper<Mdown>(){
			public Mdown mapRow(ResultSet rs, int index) throws SQLException {  
				Mdown md = new Mdown();
				md.setId(rs.getInt("id"));
				md.setMovies_id(rs.getInt("movies_id"));
				md.setMname(rs.getString("mname"));
				md.setHtml_url(rs.getString("html_url"));
				md.setDown_url(rs.getString("down_url"));
                return md;
            }
		});
		return list;
	}
	
}
