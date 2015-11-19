package com.wyper.service;

import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.wyper.util.UserAgent;

/**
 * 	万能下载
 * */
@Service
public class AnyDownService {
	
	
	public void parseHtml(String baseUrl, String url, String name){
		Document doc = null;
		
		try {
			doc = Jsoup.connect(url).header("User-Agent", UserAgent.getUserAgent()).get();
		
			Elements p = doc.select("a");
			for(Iterator it = p.iterator();it.hasNext();){
				Element a = (Element)it.next();
				String a_href = a.attr("href");
				if(!a_href.contains(".html"))
					continue;
				String a_text = a.text();
//				if(a_href.contains("thunder:")){
//					System.out.println(a_href);
//				}
				
//				if(a_text.contains("TS") || a_text.contains("TC") 
//						|| a_text.contains("BD") || a_text.contains("HD")){
//					System.out.println(a_href);
//				}
//				if((a_text.contains(name) || a_text.contains("BD") || a_text.contains("HD")) && !url.contains(a_href)){
//					System.out.println(a_href);
//					Document doc1 = Jsoup.connect(baseUrl+a_href)
//							.header("User-Agent", UserAgent.getUserAgent()).get();
//					Elements jjhd = doc1.getElementsContainingOwnText("jjhd");
//					for(Iterator it1 = jjhd.iterator();it1.hasNext();){
//						System.out.println(((Element)it1.next()).text());
//					}
//					Elements xg = doc1.getElementsContainingOwnText("ftp");
//					for(Iterator it2 = xg.iterator();it2.hasNext();){
//						System.out.println(((Element)it2.next()).text());
//					}
//				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args){
		
		new AnyDownService().parseHtml("http://www.yingshidaquan.cc/", "http://www.yingshidaquan.cc/html/DQ221010.html","校花驾到2蜜桃时代");
//		new AnyDownService().parseHtml("http://www.yxigua.com/", "http://www.yxigua.com/xiguayingyin/73831.html","谜城");
	}
	
}
