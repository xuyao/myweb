package com.wyper.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyper.po.Mdown;
import com.wyper.po.Movies;
import com.wyper.util.PropertisUtil;
import com.wyper.vo.JJ;
import com.wyper.vo.Xigua;

/**
 * 	爬虫service
 * */
@Service
public class SpiderService {
	
	@Autowired
	DbService dbService ;
	
	@Autowired
	YxiguaService yxiguaService;
	
	@Autowired
	FreeMarkerService freeMarkerService;
	
	
	public void parseHtml(String wwwName, String url, String number){
		Movies movie = new Movies();
		List<Mdown> mdownList = new ArrayList<Mdown>();
		
		if("www.yxigua.com".equals(wwwName)){/** 一个西瓜 */
			yxiguaService.parseHtml(number, url, movie, mdownList);
		}
		
		
		//生成文件并入数据库
		freeMarkerService.genHtml(number+".html", movie);
		Integer id = dbService.saveMovies(movie);
		
		Integer i_xg=1;
		Integer i_jj=1;
		for(Mdown mdown : mdownList){
			mdown.setMovies_id(id);
			if(mdown.getType().equals("1")){//生成西瓜下载页面
				Xigua xigua = new Xigua();
				xigua.setUrl(mdown.getDown_url());
				freeMarkerService.genXiguaHtml(number+"_xg"+i_xg+".html", mdown, xigua);
				i_xg++;
			}
			if(mdown.getType().equals("2")){//生成吉吉下载页面
				JJ jj = new JJ();
				jj.setJjvod_url(mdown.getDown_url());
				freeMarkerService.genJJHtml(number+"_jj"+i_jj+".html", mdown, jj);
				i_jj++;
			}
			dbService.saveMdown(mdown);
		}
		
		try {
			FileUtils.write(new File(PropertisUtil.get("output")), movie.getHtml_url()+"\n", true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
