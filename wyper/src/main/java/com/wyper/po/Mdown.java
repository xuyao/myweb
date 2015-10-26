package com.wyper.po;

public class Mdown {

	private Integer id;
	
	private Integer movies_id;
	
	private String type;//下载类型0-迅雷 、1-西瓜、2-吉吉、3-快播、4-BT下载
	
	private String url;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
