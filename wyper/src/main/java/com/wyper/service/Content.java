package com.wyper.service;

public class Content {

	private Integer id;
	
	private String title;
	
	private String s_content;
	
	private byte[] content;
	
	private String ctime;
	
	private String url;
	
	private String urlm;
	
	public String getUrlm() {
		return urlm;
	}

	public void setUrlm(String urlm) {
		this.urlm = urlm;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public byte[] getContent() {
		this.s_content = new String(content);
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public String getS_content() {
		return s_content;
	}

	public void setS_content(String s_content) {
		this.s_content = s_content;
		this.content = s_content.getBytes();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
