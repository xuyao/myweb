package com.wyper.service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.wyper.po.Mdown;
import com.wyper.po.Movies;
import com.wyper.util.DateUtil;
import com.wyper.util.DownUtil;
import com.wyper.util.PathUtil;
import com.wyper.util.PropertisUtil;
import com.wyper.util.UserAgent;

/**
 * 	一个西瓜www.yxigua.com
 * */
@Service
public class YxiguaService {
	
	@Autowired
	DbService dbService ;
	
	@Autowired
	FreeMarkerService freeMarkerService;
	
	/**
	 * 	param 1：频道
	 *  param 2：网页路径
	 * */
	public Movies parseHtml(String number, String url, String ctime, Movies movie, 
			List<Mdown> mdownList, Date d){
		Document doc = null;
		
		try {
			doc = Jsoup.connect(url).header("User-Agent", UserAgent.getUserAgent()).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements p = doc.select("tr td p");
		if(p.isEmpty())
			p = doc.select("p");
		
		setId(movie);//set id
		setName(movie, doc);//set title
		if(StringUtils.isEmpty(ctime))//set ctime
			setCtime(movie);
		else
			movie.setCtime(ctime);
			
		p.select("span").remove();
		String html = "";
		Integer index_num=0;
		for (Iterator<Element> it = p.iterator(); it.hasNext();){
			Element e = (Element) it.next();
			
			if(e.text().contains("下载") || e.text().contains("关键字"))//排除一些情况
				continue;
			
			if(e.select("input").attr("value").contains("thunder:"))//排除thunder
				continue;
			
			if(e.select("a").attr("href").contains("thunder:"))//排除thunder
				continue;
			
			e.select("a").attr("href", "http://"+PropertisUtil.get("www.url"));
			
			String picurl = null;
			try {
				picurl = e.select("img").attr("src");
				if(!StringUtils.isEmpty(picurl)){
					System.out.println("download...   "+picurl);
					DownUtil.download(e.select("img").attr("src"),number+"0"+index_num+".jpg", PathUtil.path(d));
					e.select("img").attr("src", PathUtil.absUrl(d)+number+"0"+index_num+".jpg");
					e.select("img").attr("width", "300");
					e.select("img").attr("height", "410");
					e.select("img").attr("onerror", "javascript:this.src='/images/nophoto.jpg'");
					index_num++;
					
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			if(!StringUtils.isEmpty(picurl))
				html+="<p><div style=\"float:left\">"+e.html()+
				"</div></p><!--#include virtual=\"/ad/content_ad_01.shtml\" -->\n<div style=\"clear:both;padding-top:18px;\">";
			else
				html+="<p>"+e.html()+"</p>\n";
		}
		//ad
		html+="";
		
		//内容简介
		html+="<p>"+doc.select(".vod_content").text().replaceAll("一个西瓜电影网", "51电影网")+"</p>\n";
		
		//ad
		html+="<!--#include virtual=\"/ad/content_ad_02.shtml\" -->";
		
		/** 下载链接  */
		//迅雷下载
		html+="<div style=\"clear:both;padding-top:18px;\">";
		Elements thunders = doc.select(".dwon_xl");
		if(thunders!=null && thunders.hasText())
			html+="<p><b>"+movie.getName()+"迅雷下载：&nbsp</b></p><p style='font-size:14px;color:#F00'>";
		
		for (Iterator<Element> it = thunders.iterator(); it.hasNext();){
			System.out.println("迅雷下载!");
			Element thunder = (Element) it.next().select("a").get(0);
			html+="<a target='_self' onclick='return beginDown(this,1);' href='"+thunder.attr("href")+"'><u>"+thunder.text()+"</u></a><br>";
		}
		html+="</p>";
		
		
		//西瓜、吉吉下载等
		Elements box2 = doc.select(".box2");
		Map<String,String> map = new HashMap<String,String>();
		Element box2_first = box2.get(0);
		Elements playlist = box2_first.select(".playlist");
		Integer i = 0;
		for (Iterator<Element> itp = playlist.iterator(); itp.hasNext();){
			Elements playlist_a = itp.next().select("a");
			String isWhat = box2_first.select("h3 div").get(i).text();
			
			//判断是否是西瓜
			if(playlist_a.hasText() && isWhat.contains("西瓜")){//西瓜下载
				html+="<p><b>"+movie.getName()+"西瓜影音下载：&nbsp</b></p><p style='font-size:14px;color:#F00'>";
				Integer xg_index=1;
				for (Iterator<Element> it = playlist_a.iterator(); it.hasNext();){
					Element a = it.next();
					Mdown mdown = new Mdown();
					mdown.setType("1");
					String xiguaUrl = "http://www.yxigua.com"+a.attr("href");
					mdown.setSrc_url(xiguaUrl);
					mdown.setHtml_url(PathUtil.rltUrl(d)+number+"_xg"+xg_index+".html");
					downUrlXigua(xiguaUrl, mdown);
					mdown.setMname(a.text());
					mdownList.add(mdown);
					html+="<a href='"+mdown.getHtml_url()+"'><u>"+mdown.getMname()+"</u></a>&nbsp;&nbsp;";
					xg_index++;
				}
				html+="</p>";
			}else if(playlist_a.hasText() && isWhat.contains("吉吉")){//判断是否是吉吉下载
				html+="<p><b>"+movie.getName()+"吉吉影音下载：&nbsp</b></p><p style='font-size:14px;color:#F00'>";
				Integer jj_index=1;
				for (Iterator<Element> it = playlist_a.iterator(); it.hasNext();){
					Element a = it.next();
					Mdown mdown = new Mdown();
					mdown.setType("2");
					String jjUrl = "http://www.yxigua.com"+a.attr("href");
					mdown.setSrc_url(jjUrl);
					mdown.setHtml_url(PathUtil.rltUrl(d)+number+"_jj"+jj_index+".html");
					downUrlJJ(jjUrl, mdown);
					mdown.setMname(a.text());
					mdownList.add(mdown);
					html+="<a href='"+mdown.getHtml_url()+"'><u>"+mdown.getMname()+"</u></a>&nbsp;&nbsp;";
					jj_index++;
				}
				html+="</p>";
			}
			i++;
		}
		html+="</div>";

		
		//BT下载
//		html+="<p><b>"+movie.getName()+"BT下载：&nbsp</b></p>";
//		html+="";
		
		movie.setPic_url(PathUtil.rltUrl(d)+number+"00.jpg");
		movie.setContent(html);
		movie.setSrc_url(url);//源url
		movie.setHtml_url(PathUtil.rltUrl(d) + number+".html");
		
		System.out.println(movie.getHtml_url());
		return movie;
	}
	
	
	
	private void setCtime(Movies movie){
		movie.setCtime(DateUtil.getNowformatLongPattern());
	}
	
	private void setId(Movies movie){
		movie.setId(null);
	}
	
	private void setName(Movies movie, Document doc){
		Elements p = doc.select("h1");
		System.out.println(p.text());
		movie.setName(p.text());
	}
	
	
	/**
	 * 	解析吉吉影音页面
	 * */
	private void downUrlJJ(String url, Mdown mdown){
		System.out.println("====吉吉下载："+url);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		Document doc = null;
		try {
			doc = Jsoup.connect(url).header("User-Agent", UserAgent.getUserAgent()).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String script = doc.select(".left").select("script").get(0).html();
		String sjson = script.substring(script.indexOf("{"),script.indexOf("}'")+1);
		JSONArray data = JSONObject.fromObject(sjson).getJSONArray("Data");
		for(int i=0;i<data.size();i++){
			if("jjvod".equals(data.getJSONObject(i).get("playname"))){
				JSONArray playurls = data.getJSONObject(i).getJSONArray("playurls");
				for(int j=0; j<playurls.size(); j++){
					JSONArray ja = playurls.getJSONArray(j);
					if(url.contains((String)ja.get(2))){
						mdown.setDown_url((String)ja.get(1));
						System.out.println(mdown.getDown_url());
						break;
					}
				}
			}
		}
	}
	
	
	
	/**
	 * 	解析西瓜影音页面
	 * */
	private void downUrlXigua(String url, Mdown mdown){
		System.out.println("====西瓜下载："+url);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		Document doc = null;
		try {
			doc = Jsoup.connect(url).header("User-Agent", UserAgent.getUserAgent()).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String script = doc.select(".left").select("script").get(0).html();
		String sjson = script.substring(script.indexOf("{"),script.indexOf("}'")+1);
		JSONArray data = JSONObject.fromObject(sjson).getJSONArray("Data");
		for(int i=0;i<data.size();i++){
			if("xigua".equals(data.getJSONObject(i).get("playname"))){
				JSONArray playurls = data.getJSONObject(i).getJSONArray("playurls");
				for(int j=0; j<playurls.size(); j++){
					JSONArray ja = playurls.getJSONArray(j);
					if(url.contains((String)ja.get(2))){
						mdown.setDown_url((String)ja.get(1));
						System.out.println(mdown.getDown_url());
						break;
					}
				}
			}
		}
	}
	
	//判断是西瓜链接，还是吉吉，不太好判断
	private String isWhat(){
		
		
		return "xigua";
	}
	
	
	public static void main(String[] args){
		
//		new YxiguaService().downUrlJJ("http://www.yxigua.com/xigua/72970_0_1.html", new Mdown());
		new YxiguaService().downUrlXigua("http://www.yxigua.com/xigua/75580_1_1.html", new Mdown());
	}
	
}
