package com.wyper.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.wyper.po.Mdown;
import com.wyper.po.Movies;
import com.wyper.util.DateUtil;
import com.wyper.util.PathUtil;
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
		movie.setType(type);//类型m或者t
		
		List<Mdown> mdownList = new ArrayList<Mdown>();
		Date d = null;
		if(!StringUtils.isEmpty(mtime))
			d = DateUtil.getDate(mtime, DateUtil.STR_DATE_PATTERN);
		else 
			d = new Date();
		
		if("www.yxigua.com".equals(wwwName)){/** 一个西瓜 */
			yxiguaService.parseHtml(number, url, ctime, movie, mdownList, d);
		}

		
		Integer id = 0;
		//生成文件并入数据库
		freeMarkerService.genHtml(number+".html", movie, d);
		if(isSave){
			id = dbService.saveMovies(movie);
		}
			
		
		Integer i_xg=1;
		Integer i_jj=1;
		for(Mdown mdown : mdownList){
			mdown.setMovies_id(id);
			
			if(mdown.getType().equals("0")){//迅雷下载
				Integer count = dbService.countMdown("tb_xl", mdown.getDown_url());
				if(count==0)//如果库里没有这个迅雷链接
					dbService.saveMdown("tb_xl", mdown);//保存迅雷
			}
			if(mdown.getType().equals("1")){//生成西瓜下载页面
				Xigua xigua = new Xigua();
				xigua.setUrl(mdown.getDown_url());
				freeMarkerService.genXiguaHtml(number+"_xg"+i_xg+".html", mdown, xigua, d);
				Integer count = dbService.countMdown("tb_xg", mdown.getDown_url());
				if(count==0)//如果库里没有这个西瓜链接
					dbService.saveMdown("tb_xg", mdown);//保存西瓜
				i_xg++;
				
			}
			if(mdown.getType().equals("2")){//生成吉吉下载页面
				JJ jj = new JJ();
				jj.setJjvod_url(mdown.getDown_url());
				freeMarkerService.genJJHtml(number+"_jj"+i_jj+".html", mdown, jj, d);
				Integer count = dbService.countMdown("tb_jj", mdown.getDown_url());
				if(count==0)//如果库里没有这个吉吉链接
					dbService.saveMdown("tb_jj", mdown);//保存吉吉
				i_jj++;
			}
			
		}
		
	}
	
	
}
