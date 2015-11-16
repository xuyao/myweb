package com.wyper.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
import com.wyper.util.ImgUtil;
import com.wyper.util.PathUtil;
import com.wyper.util.PropertisUtil;
import com.wyper.util.UserAgent;
import com.wyper.vo.DownLoad;

/**
 * 	一个西瓜www.yxigua.com
 * */
@Service
public class YxiguaService {
	
	@Autowired
	DbService dbService ;
	
	@Autowired
	FreeMarkerService freeMarkerService;
	
	@Autowired
	DownloadHtmlService downloadHtmlService;
	
	/**
	 * 	param 1：频道
	 *  param 2：网页路径
	 * */
	public Movies parseHtml(String number, String url, String ctime, Movies movie, Date d, DownLoad dl){
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
		StringBuilder html = new StringBuilder();
		Integer index_num=1;
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
					/* 第一个文章页图片，用01，00是给列表用的*/
					String imgUrl = e.select("img").attr("src");
					if(imgUrl.contains("jpg"))
						DownUtil.download(imgUrl, number+"0"+index_num+".jpg", PathUtil.path(d));//
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
				html.append("<p><div style=\"float:left\">").append(e.html())
				.append("</div></p><!--#include virtual=\"/ad/content_ad_01.shtml\" -->\n<div style=\"clear:both;padding-top:18px;\">");
			else
				html.append("<p>").append(e.html()).append("</p>\n");
		}
		
		//生成首页图片
		try {
			ImgUtil.resize(new File(PathUtil.path(d)+number+"01.jpg"), PathUtil.path(d)+number+"00.jpg", 131, 203);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//内容简介
		html.append("<p>").append(doc.select(".vod_content").text().replaceAll("一个西瓜电影网", "51电影网")+"</p>\n");
		movie.setContent(html.toString());
		
		/** 下载链接  */
		List<Mdown> mdownList = new ArrayList<Mdown>();
		//迅雷下载
		Elements thunders = doc.select(".dwon_xl");
		for (Iterator<Element> it = thunders.iterator(); it.hasNext();){
			Mdown md = new Mdown();
			Element thunder = (Element) it.next().select("a").get(0);
			md.setDown_url(thunder.attr("href"));
			md.setMname(thunder.text());
			md.setType("0");//迅雷
			mdownList.add(md);
		}
		dl.getXunleiList().addAll(mdownList);
		downloadHtmlService.xunleiHtml(movie, mdownList, dl);
		
		
		//西瓜、吉吉下载等
		Elements box2 = doc.select(".box2");
		Element box2_first = box2.get(0);
		Elements playlist = box2_first.select(".playlist");
		Integer i = 0;
		for (Iterator<Element> itp = playlist.iterator(); itp.hasNext();){
			Elements playlist_a = itp.next().select("a");
			String isWhat = box2_first.select("h3 div").get(i).text();
			
			//判断是否是西瓜
			if(playlist_a.hasText() && isWhat.contains("西瓜")){//西瓜下载
				mdownList = new ArrayList<Mdown>();
				Integer xg_index=1;
				for (Iterator<Element> it = playlist_a.iterator(); it.hasNext();){
					Element a = it.next();
					Mdown mdown = new Mdown();
					mdown.setType("1");
					String xiguaUrl = "http://www.yxigua.com"+a.attr("href");
					mdown.setHtml_url(PathUtil.rltUrl(d)+number+"_xg"+xg_index+".html");
					downUrlXigua(xiguaUrl, mdown);
					mdown.setMname(a.text());
					mdownList.add(mdown);
					xg_index++;
				}
				dl.getXiguaList().addAll(mdownList);
				downloadHtmlService.xiguaHtml(movie, mdownList, dl);
				
			}else if(playlist_a.hasText() && isWhat.contains("吉吉")){//判断是否是吉吉下载
				mdownList = new ArrayList<Mdown>();
				Integer jj_index=1;
				for (Iterator<Element> it = playlist_a.iterator(); it.hasNext();){
					Element a = it.next();
					Mdown mdown = new Mdown();
					mdown.setType("2");
					String jjUrl = "http://www.yxigua.com"+a.attr("href");
					mdown.setHtml_url(PathUtil.rltUrl(d)+number+"_jj"+jj_index+".html");
					downUrlJJ(jjUrl, mdown);//解析西瓜down_url
					mdown.setMname(a.text());
					mdownList.add(mdown);
					jj_index++;
				}
				dl.getJjList().addAll(mdownList);
				downloadHtmlService.jjHtml(movie, mdownList, dl);
			}
			i++;
		}

		
		movie.setPic_url(PathUtil.rltUrl(d)+number+"00.jpg");
		movie.setSrc_url(url);//源url
		movie.setHtml_url(PathUtil.rltUrl(d) + number+".html");
		downloadHtmlService.returnDescription(movie);
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
		String name = p.text().replaceAll("《", "").replaceAll("》", "");
		movie.setName(name);
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
	
	
	public static void main(String[] args){
		
//		new YxiguaService().downUrlJJ("http://www.yxigua.com/xigua/72970_0_1.html", new Mdown());
		new YxiguaService().downUrlXigua("http://www.yxigua.com/xigua/75580_1_1.html", new Mdown());
	}
	
}
