package com.wyper.service;

import java.io.File;
import java.util.HashMap;
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

	public void genHtml(String filename, Movies movie){
		FreeMarkerUtil.initFreeMarker(PropertisUtil.get("tl.content.path"));
		Map<String, Object> args = new HashMap<String, Object>(); 
		args.put("name", movie.getName());
		args.put("content", movie.getContent());
		args.put("html_url", movie.getHtml_url());
		FreeMarkerUtil.crateFile(args, "c01.tl", PathUtil.path()+File.separator+filename, true);
	}
	
	
	public void genXiguaHtml(String filename, Mdown mdown,  Xigua xigua){
		FreeMarkerUtil.initFreeMarker(PropertisUtil.get("tl.content.path"));
		Map<String, Object> args = new HashMap<String, Object>(); 
		args.put("name", mdown.getMname());
		args.put("content", xigua.toString());
		args.put("html_url", mdown.getHtml_url());
		FreeMarkerUtil.crateFile(args, "c01_xg.tl", PathUtil.path()+File.separator+filename, true);
	}
	
	
	public void genJJHtml(String filename, Mdown mdown, JJ jj){
		FreeMarkerUtil.initFreeMarker(PropertisUtil.get("tl.content.path"));
		Map<String, Object> args = new HashMap<String, Object>(); 
		args.put("name", mdown.getMname());
		args.put("content", jj.toString());
		args.put("html_url", mdown.getHtml_url());
		FreeMarkerUtil.crateFile(args, "c01_jj.tl", PathUtil.path()+File.separator+filename, true);
	}
	
}
