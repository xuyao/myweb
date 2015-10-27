package com.wyper.vo;

public class Xigua {
	
	private String Url="";//本集播放地址，需更改
	
	private String NextcacheUrl="" ;//缓冲下一集，需更改
	
	private String LastWebPage="";
	
	private String NextWebPage="";//下一集播放页面地址，需更改
	
	private String Buffer="";// 播缓冲AD，需更改
	
	private String Pase="";// 暂停AD，需更改
	
	private String Width ="840";// 播放器显示宽度
	
	private String Height ="680";// 播放器显示高度
	
	private String Second ="8";// 缓冲时间
	
	private String Installpage ="http://static.xigua.com/installpage.html";

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	public String getNextcacheUrl() {
		return NextcacheUrl;
	}

	public void setNextcacheUrl(String nextcacheUrl) {
		NextcacheUrl = nextcacheUrl;
	}

	public String getLastWebPage() {
		return LastWebPage;
	}

	public void setLastWebPage(String lastWebPage) {
		LastWebPage = lastWebPage;
	}

	public String getNextWebPage() {
		return NextWebPage;
	}

	public void setNextWebPage(String nextWebPage) {
		NextWebPage = nextWebPage;
	}

	public String getBuffer() {
		return Buffer;
	}

	public void setBuffer(String buffer) {
		Buffer = buffer;
	}

	public String getPase() {
		return Pase;
	}

	public void setPase(String pase) {
		Pase = pase;
	}

	public String getWidth() {
		return Width;
	}

	public String getHeight() {
		return Height;
	}

	public String getSecond() {
		return Second;
	}

	public void setSecond(String second) {
		Second = second;
	}

	public String getInstallpage() {
		return Installpage;
	}

	public String toString(){
		String r = "var XgPlayer = {";
		r+= "'Url': \""+Url+"\",";//本集播放地址，需更改
		r+= "'NextcacheUrl': \""+NextcacheUrl+"\","; //缓冲下一集，需更改
		r+= "'LastWebPage': '"+LastWebPage+"',";
		r+= "'NextWebPage': \""+NextWebPage+"\",";//下一集播放页面地址，需更改
		r+= "'Buffer': '"+Buffer+"',";//播缓冲AD，需更改
		r+= "'Pase': '"+Pase+"',";//暂停AD，需更改
		r+= "'Width': "+Width+",";//播放器显示宽度
		r+= "'Height': "+Height+",";//播放器显示高度
		r+= "'Second': "+Second+",";//缓冲时间
		r+= "\"Installpage\":'"+Installpage+"'";
		r+= "};";
		return r;
		 
		 
		 
		 
		 
		

	}
	
}
