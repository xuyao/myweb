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
import com.wyper.util.UserAgent;
import com.wyper.vo.Content;

@Service
public class YxiguaService {
	
	@Autowired
	DbService dbService ;
	
	@Autowired
	FreeMarkerService freeMarkerService;
	
	/**
	 * 	arg 1: 频道
	 *  arg 2：网页路径
	 * */
	public Content parseHtml(String htmlname, String path){
		Content content = new Content();
		Document doc = null;
		try {
			doc = Jsoup.connect(path).header("User-Agent", UserAgent.userAgent).get();
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
			
			if(e.text().contains("下载") || e.text().contains("关键字"))//排除一些情况
				continue;
			
			if(e.select("input").attr("value").contains("thunder:"))//排除thunder
				continue;
			
			try {
				String picurl = e.select("img").attr("src");
				if(!StringUtils.isEmpty(picurl)){
					System.out.println("download...   "+picurl);
					DownUtil.download(e.select("img").attr("src"),htmlname+"0"+index_num+".jpg", PathUtil.path());
					e.select("img").attr("src", "http://"+PathUtil.url()+htmlname+"0"+index_num+".jpg");
					index_num++;
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			html+="<p>"+e.html()+"</p>\n";
			
		}
		
		//内容简介
		html+="<p>"+doc.select(".vod_content").text().replaceAll("一个西瓜电影网", "51电影网")+"</p>\n";
		
		//迅雷下载
		html+="<p><b>"+content.getTitle()+"迅雷下载：&nbsp</b>";
		Elements thunders = doc.select(".dwon_xl");
		for (Iterator<Element> it = thunders.iterator(); it.hasNext();){
			Element thunder = (Element) it.next().select("a").get(0);
			html+="<a target='_self' onclick='return beginDown(this,1);' href='"+thunder.attr("value")+"'><u>"+thunder.text()+"</u></a>&nbsp;&nbsp;\n";
		}
		html+="</p>";
		
		//西瓜下载
		html+="<p><b>"+content.getTitle()+"西瓜影音下载：&nbsp</b>";
		html+="</p>";
		
		//吉吉影音
		html+="<p><b>"+content.getTitle()+"吉吉影音下载：&nbsp</b>";
		html+="</p>";
		
		//BT下载
		html+="<p><b>"+content.getTitle()+"BT下载：&nbsp</b>";
		html+="</p>";
		
		content.setS_content(html);
		content.setUrl(PathUtil.url() + htmlname+".html");
		//dbService.save("t_"+pd, content);
		freeMarkerService.genHtml(htmlname+".html", content);
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
		System.out.println(p.text());
		content.setTitle(p.text());
	}
	
}
