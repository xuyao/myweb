package com.wyper.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.wyper.util.FreeMarkerUtil;
import com.wyper.util.PathUtil;
import com.wyper.util.PropertisUtil;

@Service
public class FreeMarkerService {

	public void genHtml(String filename, String pd, Content c){
		FreeMarkerUtil.initFreeMarker(PropertisUtil.get("tl.content.path"));
		Map<String, Object> args = new HashMap<String, Object>(); 
		args.put("title", c.getTitle());
		args.put("content", c.getS_content());
		args.put("url", c.getUrl());
		
		FreeMarkerUtil.crateFile(args, "c01.tl", PathUtil.path(pd)+File.separator+filename, true);
		
	}
}
