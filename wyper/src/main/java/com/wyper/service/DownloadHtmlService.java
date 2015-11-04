package com.wyper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyper.po.Mdown;
import com.wyper.po.Movies;
import com.wyper.util.PropertisUtil;
import com.wyper.vo.JJ;
import com.wyper.vo.Xigua;
import com.wyper.vo.DownLoad;

@Service
public class DownloadHtmlService {

	@Autowired
	FreeMarkerService freeMarkerService;
	
	//迅雷下载html生成
	public void xunleiHtml(Movies m, List<Mdown> list, DownLoad dl){
		if(list!=null && list.size()!=0){
			StringBuilder html = new StringBuilder();
			html.append("<p><b>").append(m.getName())
			.append("迅雷下载（如果迅雷无法识别，请右键点击资源复制链接）：&nbsp</b></p><p style='font-size:14px;color:#F00'>");
			
	    	for(Mdown md : list){
	    		html.append("<a target='_self' onclick='return beginDown(this,1);' href='")
	    		.append(md.getDown_url()).append("'><u>").append(md.getMname()).append("</u></a><br>");
	    	}
			
	    	html.append("</p>");
	    	
	    	dl.setXunlei(html.toString());
	    	System.out.println(dl.getXunlei());
		}
	}
	
	
	
	//西瓜下载html生成
	public void xiguaHtml(Movies m, List<Mdown> list, DownLoad dl){
		if(list!=null && list.size()!=0){
			StringBuilder html = new StringBuilder();
			html.append("<p><b>").append(m.getName())
			.append("西瓜影音下载：&nbsp</b></p><p style='font-size:14px;color:#F00'>");
			
	    	for(Mdown md : list){
	    		html.append("<a href='"+md.getHtml_url()+"'><u>"+md.getMname()+"</u></a>&nbsp;&nbsp;");
				Xigua xigua = new Xigua();
				xigua.setUrl(md.getDown_url());
				//生成西瓜最终页
	    		freeMarkerService.genXiguaHtml(PropertisUtil.get("www.path")+md.getHtml_url(), md, xigua);
	    	}
			
	    	html.append("</p>");
	    	dl.setXigua(html.toString());
		}
	}
	
	//吉吉下载html生成
	public void jjHtml(Movies m, List<Mdown> list, DownLoad dl){
		if(list!=null && list.size()!=0){
			StringBuilder html = new StringBuilder();
			html.append("<p><b>").append(m.getName())
			.append("吉吉影音下载：&nbsp</b></p><p style='font-size:14px;color:#F00'>");
			
	    	for(Mdown md : list){
	    		html.append("<a href='"+md.getHtml_url()+"'><u>"+md.getMname()+"</u></a>&nbsp;&nbsp;");
				JJ jj = new JJ();
				jj.setJjvod_url(md.getDown_url());
	    		freeMarkerService.genJJHtml(PropertisUtil.get("www.path")+md.getHtml_url(), md, jj);
	    	}
			
	    	html.append("</p>");
	    	dl.setJj(html.toString());
		}
	}
	
	
	//先锋下载html生成
	public void xfHtml(Movies m, List<Mdown> list, DownLoad dl){
		if(list!=null && list.size()!=0){
			StringBuilder html = new StringBuilder();
			html.append("<p><b>").append(m.getName())
			.append("先锋影音下载：&nbsp</b></p><p style='font-size:14px;color:#F00'>");
			
	    	for(Mdown md : list){
	    		html.append("<a href='"+md.getHtml_url()+"'><u>"+md.getMname()+"</u></a>&nbsp;&nbsp;");
	    	}
			
	    	html.append("</p>");
	    	dl.setXf(html.toString());
		}
	}
	
	
	
}
