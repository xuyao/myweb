package com.wyper.service;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.wyper.util.DateUtil;
import com.wyper.util.DownUtil;
import com.wyper.util.PathUtil;
import com.wyper.util.PropertisUtil;

@Service
public class HtmlParseService {
	
	@Autowired
	DbService dbService ;
	
	@Autowired
	FreeMarkerService freeMarkerService;
	
	/**
	 * 	arg 1: 频道
	 *  arg 2：网页路径
	 * */
	public Content parseHtml(String htmlname, String pd, String path){
		Content content = new Content();
		Document doc = null;
		try {
			doc = Jsoup.connect(path).timeout(10000).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setId(content);//set id
		setTitle(content, doc);//set title
		setCtime(content);//set ctime
		
		Elements p = doc.select("tr td p");
		if(p.isEmpty())
			p = doc.select("p");
		
		p.select("span").remove();
		String html = "";
		Integer index_num=0;
		for (Iterator<Element> it = p.iterator(); it.hasNext();){
			Element e = (Element) it.next();
			e.select("a").attr("href", "http://"+PropertisUtil.get("www.url"));
			try {
				String picurl = e.select("img").attr("src");
				if(!StringUtils.isEmpty(picurl)){
					System.out.println("download...   "+picurl);
					DownUtil.download(e.select("img").attr("src"),htmlname+"0"+index_num+".jpg", PathUtil.path(pd));
					e.select("img").attr("src", "http://"+PathUtil.url(pd)+htmlname+"0"+index_num+".jpg");
					index_num++;
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			html+="<p>"+e.html()+"</p>\n";
		}
		//System.out.println(html);
		
		content.setS_content(html);
		content.setUrl(PathUtil.url(pd) + htmlname+".html");
		//dbService.save("t_"+pd, content);
		freeMarkerService.genHtml(htmlname+".html", pd, content);
		System.out.println(content.getUrl());
		
		try {
			FileUtils.write(new File(PropertisUtil.get("output")), content.getUrl()+"\n", true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return content;
	}
	
	private void setCtime(Content content){
		content.setCtime(DateUtil.getNowformatLongPattern());
	}
	
	private void setId(Content content){
		content.setId(null);
	}
	
	private void setTitle(Content content, Document doc){
		Elements p = doc.select("h1");
		content.setTitle(p.text());
	}
	
}
