package com.wyper.po;

public class Mdown {

	private Integer id;
	
	private Integer movies_id;
	
	private String mname;//下载电影名字
	
	private String type;//不保存数据库，不持久  0--迅雷  1--西瓜  2--吉吉  3--先锋
	
	private String html_url;//本页面路径
	
	private String down_url;//视频ftp下载之类的协议路径
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMovies_id() {
		return movies_id;
	}

	public void setMovies_id(Integer movies_id) {
		this.movies_id = movies_id;
	}
	
	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDown_url() {
		return down_url;
	}

	public void setDown_url(String down_url) {
		this.down_url = down_url;
	}

	public String getHtml_url() {
		return html_url;
	}

	public void setHtml_url(String html_url) {
		this.html_url = html_url;
	}

}
