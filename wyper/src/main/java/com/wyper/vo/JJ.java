package com.wyper.vo;

public class JJ {
//	var jjvod_url = 'jjhd://20000|b50f7b06236a68f4d62b8f2ff64b39ebf88baece|侏罗纪世界2015.HD修正中字.720p.mkv';//播放视频地址
	private String jjvod_url;
	
	private String jjvod_w = "840";
	
	private String jjvod_h = "680";
	
	private String jjvod_ad = "";
	
	private String jjvod_c = "51per";
	
	private String jjvod_install = "http://player.jjvod.com/js/jjplayer_install.html?v=1&c=51per";
	
	private String jjvod_weburl = "unescape(window.location.href)";

	public String getJjvod_url() {
		return jjvod_url;
	}

	public void setJjvod_url(String jjvod_url) {
		this.jjvod_url = jjvod_url;
	}

	public String getJjvod_w() {
		return jjvod_w;
	}

	public String getJjvod_h() {
		return jjvod_h;
	}

	public String getJjvod_ad() {
		return jjvod_ad;
	}

	public String getJjvod_c() {
		return jjvod_c;
	}

	public String getJjvod_install() {
		return jjvod_install;
	}

	public String getJjvod_weburl() {
		return jjvod_weburl;
	}
	
	public String toString(){
		String r = "var jjvod_url ='"+jjvod_url + "';";
		r+="var jjvod_w =" + jjvod_w + ";";
		r+="var jjvod_h =" + jjvod_h + ";";
		r+="var jjvod_ad ='" + jjvod_ad + "';";
		r+="var jjvod_c ='" + jjvod_c + "';";
		r+="var jjvod_install ='" + jjvod_install + "';";
		r+="var jjvod_weburl =" + jjvod_weburl + ";";
		return r;
	}

}
