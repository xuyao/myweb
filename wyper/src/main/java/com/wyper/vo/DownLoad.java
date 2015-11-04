package com.wyper.vo;

import java.util.ArrayList;
import java.util.List;

import com.wyper.po.Mdown;

public class DownLoad {

	private String xunlei;
	
	private String xigua;
	
	private String jj;
	
	private String xf;
	
	private List<Mdown> xunleiList = new ArrayList<Mdown>();
	
	private List<Mdown> xiguaList = new ArrayList<Mdown>();
	
	private List<Mdown> jjList = new ArrayList<Mdown>();
	
	private List<Mdown> xfList = new ArrayList<Mdown>();
	
	public String getXunlei() {
		return xunlei;
	}

	public void setXunlei(String xunlei) {
		this.xunlei = xunlei;
	}

	public String getXigua() {
		return xigua;
	}

	public void setXigua(String xigua) {
		this.xigua = xigua;
	}

	public String getJj() {
		return jj;
	}

	public void setJj(String jj) {
		this.jj = jj;
	}

	public String getXf() {
		return xf;
	}

	public void setXf(String xf) {
		this.xf = xf;
	}

	public List<Mdown> getXunleiList() {
		return xunleiList;
	}

	public List<Mdown> getXiguaList() {
		return xiguaList;
	}

	public List<Mdown> getJjList() {
		return jjList;
	}

	public List<Mdown> getXfList() {
		return xfList;
	}

}
