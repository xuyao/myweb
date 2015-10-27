package com.wyper.service;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.wyper.po.Mdown;
import com.wyper.po.Movies;
import com.wyper.util.FreeMarkerUtil;
import com.wyper.util.PathUtil;
import com.wyper.util.PropertisUtil;
import com.wyper.vo.JJ;
import com.wyper.vo.Xigua;

@Service
public class FreeMarkerService {

	
	public void genHtml(String filename, Movies movie, Date d){
		FreeMarkerUtil.initFreeMarker(PropertisUtil.get("tl.content.path"));
		Map<String, Object> args = new HashMap<String, Object>(); 
		args.put("name", movie.getName());
		args.put("content", movie.getContent());
		args.put("html_url", "http://" + PropertisUtil.get("www.url") + movie.getHtml_url());
		FreeMarkerUtil.crateFile(args, "c01.tl", PathUtil.path(d) + File.separator + filename, true);
	}
	
	
	public void genXiguaHtml(String filename, Mdown mdown,  Xigua xigua, Date d){
		FreeMarkerUtil.initFreeMarker(PropertisUtil.get("tl.content.path"));
		Map<String, Object> args = new HashMap<String, Object>(); 
		args.put("name", mdown.getMname());
		args.put("content", xigua.toString());
		args.put("html_url", mdown.getHtml_url());
		FreeMarkerUtil.crateFile(args, "c01_xg.tl", PathUtil.path(d)+File.separator+filename, true);
	}
	
	
	public void genJJHtml(String filename, Mdown mdown, JJ jj, Date d){
		FreeMarkerUtil.initFreeMarker(PropertisUtil.get("tl.content.path"));
		Map<String, Object> args = new HashMap<String, Object>(); 
		args.put("name", mdown.getMname());
		args.put("content", jj.toString());
		args.put("html_url", mdown.getHtml_url());
		FreeMarkerUtil.crateFile(args, "c01_jj.tl", PathUtil.path(d)+File.separator+filename, true);
	}
	
	
	public void genMoviesListHtml(List<Movies> list){
		FreeMarkerUtil.initFreeMarker(PropertisUtil.get("tl.content.path"));
		Map<String, Object> args = new HashMap<String, Object>(); 
		args.put("ll", list);
		FreeMarkerUtil.crateFile(args, "l.tl", PathUtil.listMoviesPath(), true);
	}
	
	public void genTVListHtml(List<Movies> list){
		FreeMarkerUtil.initFreeMarker(PropertisUtil.get("tl.content.path"));
		Map<String, Object> args = new HashMap<String, Object>(); 
		args.put("ll", list);
		FreeMarkerUtil.crateFile(args, "l.tl", PathUtil.listTVPath(), true);
	}
	
	
}
