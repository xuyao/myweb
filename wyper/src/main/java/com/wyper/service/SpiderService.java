package com.wyper.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.wyper.po.Mdown;
import com.wyper.po.Movies;
import com.wyper.util.DateUtil;
import com.wyper.util.PathUtil;
import com.wyper.vo.DownLoad;

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
	
	@Autowired
	DownloadHtmlService downloadHtmlService;
	
	//生成list列表
	public void listHtml(String type){
		System.out.println("生成"+type+" list列表...");
		List<Movies> list = null;
		list = dbService.listMovies(type, 28);
		String path = "";
		if("m".equals(type)){
			path = PathUtil.listMoviesPath();
		}
		else if("t".equals(type)){
			path = PathUtil.listTVPath();
		}
		else
			System.out.println("参数错误，请输入m或者t！");
		
		freeMarkerService.genMoviesListHtml(list, path);
		
	}
	
	
	/**
	 * 	isSave 是否保存数据到数据库
	 * */
	public void parseHtml(String wwwName, String type, String url, String number, String ctime, 
			String mtime, boolean isSave){
		Movies movie = new Movies();
		DownLoad dl = new DownLoad();
		
		movie.setType(type);//类型m或者t
		
		Date d = null;
		if(!StringUtils.isEmpty(mtime))
			d = DateUtil.getDate(mtime, DateUtil.STR_DATE_PATTERN);
		else 
			d = new Date();
		
		
		if("www.yxigua.com".equals(wwwName)){/** 一个西瓜 */
			yxiguaService.parseHtml(number, url, ctime, movie, d, dl);
		}
		
		Integer id = 0;
		if(isSave){
			id = dbService.saveMovies(movie);
		}
		//生成文件并入数据库
		freeMarkerService.genHtml(number+".html", movie, d, dl);
		
		for(Mdown mdown : dl.getXunleiList()){
			mdown.setMovies_id(id);//设置movie id
			Integer count = dbService.countMdown("tb_xl", mdown.getDown_url());
			if(count==0)
				dbService.saveMdown("tb_xl", mdown);
		}
		
		for(Mdown mdown : dl.getXiguaList()){
			mdown.setMovies_id(id);//设置movie id
			Integer count = dbService.countMdown("tb_xg", mdown.getDown_url());
			if(count==0)
				dbService.saveMdown("tb_xg", mdown);
		}
		
		for(Mdown mdown : dl.getJjList()){
			mdown.setMovies_id(id);//设置movie id
			Integer count = dbService.countMdown("tb_jj", mdown.getDown_url());
			if(count==0)
				dbService.saveMdown("tb_jj", mdown);
		}
		
		for(Mdown mdown : dl.getXfList()){
			mdown.setMovies_id(id);//设置movie id
			Integer count = dbService.countMdown("tb_xf", mdown.getDown_url());
			if(count==0)
				dbService.saveMdown("tb_xf", mdown);
		}
		
	}
	
	
	//生成下载页面
	public void genDetail(Movies m){
    	DownLoad dl = new DownLoad();
    	List<Mdown> list = dbService.queryMdown("tb_xl", m.getId());
    	downloadHtmlService.xunleiHtml(m, list, dl);

    	list = dbService.queryMdown("tb_xg", m.getId());
    	downloadHtmlService.xiguaHtml(m, list, dl);

    	list = dbService.queryMdown("tb_jj", m.getId());
    	downloadHtmlService.jjHtml(m, list, dl);

    	list = dbService.queryMdown("tb_xf", m.getId());
    	downloadHtmlService.xfHtml(m, list, dl);

    	freeMarkerService.genHtml(m, dl);
    	
	}
	
	
}
