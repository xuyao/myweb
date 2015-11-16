package com.wyper.service;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.wyper.po.Mdown;
import com.wyper.po.Movies;
import com.wyper.util.DateUtil;
import com.wyper.util.FreeMarkerUtil;
import com.wyper.util.PathUtil;
import com.wyper.util.PropertisUtil;
import com.wyper.vo.JJ;
import com.wyper.vo.Xigua;
import com.wyper.vo.DownLoad;

@Service
public class FreeMarkerService {

	
	public void genHtml(String filename, Movies movie, Date d, DownLoad dl){
		FreeMarkerUtil.initFreeMarker(PropertisUtil.get("tl.content.path"));
		Map<String, Object> args = new HashMap<String, Object>(); 
		args.put("name", movie.getName());
		args.put("content", movie.getContent());
		args.put("xunlei", dl.getXunlei());
		args.put("xigua", dl.getXigua());
		args.put("jj", dl.getJj());
		args.put("xf", dl.getXf());
		args.put("description", movie.getDescription());
		args.put("html_url", "http://" + PropertisUtil.get("www.url") + movie.getHtml_url());
		FreeMarkerUtil.crateFile(args, "c01.tl", PathUtil.path(d) + File.separator + filename, true);
	}
	
	public void genHtml(Movies movie, DownLoad dl){
		FreeMarkerUtil.initFreeMarker(PropertisUtil.get("tl.content.path"));
		Map<String, Object> args = new HashMap<String, Object>(); 
		args.put("name", movie.getName());
		args.put("content", movie.getContent());
		args.put("xunlei", dl.getXunlei());
		args.put("xigua", dl.getXigua());
		args.put("jj", dl.getJj());
		args.put("xf", dl.getXf());
		args.put("description", movie.getDescription());
		args.put("html_url", "http://" + PropertisUtil.get("www.url") + movie.getHtml_url());
		FreeMarkerUtil.crateFile(args, "c01.tl", PropertisUtil.get("www.path") + movie.getHtml_url(), true);
	}
	
	
	//生成西瓜影音最终页
	public void genXiguaHtml(String path_filename, Mdown mdown,  Xigua xigua){
		FreeMarkerUtil.initFreeMarker(PropertisUtil.get("tl.content.path"));
		Map<String, Object> args = new HashMap<String, Object>(); 
		args.put("name", mdown.getMname());
		args.put("content", xigua.toString());
		args.put("down_url", mdown.getDown_url());
		args.put("mname", mdown.getMname());
		args.put("description", mdown.getDescription());
		args.put("html_url", "http://" + PropertisUtil.get("www.url") + mdown.getHtml_url());
		FreeMarkerUtil.crateFile(args, "c01_xg.tl", path_filename, true);
	}
	
	//生成吉吉影音最终页
	public void genJJHtml(String path_filename, Mdown mdown, JJ jj){
		FreeMarkerUtil.initFreeMarker(PropertisUtil.get("tl.content.path"));
		Map<String, Object> args = new HashMap<String, Object>(); 
		args.put("name", mdown.getMname());
		args.put("content", jj.toString());
		args.put("down_url", mdown.getDown_url());
		args.put("mname", mdown.getMname());
		args.put("description", mdown.getDescription());
		args.put("html_url", "http://" + PropertisUtil.get("www.url") + mdown.getHtml_url());
		FreeMarkerUtil.crateFile(args, "c01_jj.tl", path_filename, true);
	}
	
	
	public void genMoviesListHtml(List<Movies> list, String path){
		FreeMarkerUtil.initFreeMarker(PropertisUtil.get("tl.content.path"));
		Map<String, Object> args = new HashMap<String, Object>(); 
		args.put("ll", list);
		FreeMarkerUtil.crateFile(args, "l.tl", path, true);
	}
	
	
	public void genSiteMap(List<Movies> list){
		FreeMarkerUtil.initFreeMarker(PropertisUtil.get("tl.content.path"));
		Map<String, Object> args = new HashMap<String, Object>(); 
		args.put("ll", list);
		args.put("date", DateUtil.format(new Date(), "yyyy-MM-dd"));
		FreeMarkerUtil.crateFile(args, "sitemap.tl", PropertisUtil.get("sitemap"), true);
	}
	
}
