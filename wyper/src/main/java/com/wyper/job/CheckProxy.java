package com.wyper.job;

import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URL;
import java.util.Properties;

public class CheckProxy {

	public static void main(String[] args){
		 try {
	         Properties systemSettings = 
	         System.getProperties();
	         systemSettings.put("proxySet", "true");
	         systemSettings.put("http.proxyHost", args[0]);
	         systemSettings.put("http.proxyPort", args[1]);
	         URL u = new URL("http://www.baidu.com");
	         HttpURLConnection con = (HttpURLConnection)
	         u.openConnection();
	         System.out.println(con.getResponseCode() + " : " + con.getResponseMessage());
	         System.out.println(con.getResponseCode() == 
	         HttpURLConnection.HTTP_OK);
	      }
	      catch (Exception e) {
	         e.printStackTrace();
	         System.out.println(false);
	      }
		 
		 try{
		      System.setProperty("java.net.useSystemProxies", "true");
    	      Proxy proxy = (Proxy) ProxySelector.getDefault().
    	      select(new URI("http://www.yahoo.com/")).iterator().
    	      next();;
    	      System.out.println("proxy hostname : " + proxy.type());
    	      InetSocketAddress addr = (InetSocketAddress)
    	      proxy.address();
    	      if (addr == null) {
    	         System.out.println("No Proxy");
    	      }
    	      else {
    	         System.out.println("proxy hostname : " 
    	         + addr.getHostName());
    	         System.out.println("proxy port : "
    	         + addr.getPort());
    	      }
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	}
	
}
