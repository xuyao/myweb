package com.wyper.job;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.impl.client.DefaultHttpClient;

public class ClickMe {

	
	public static void main(String args[])
	{
	 StringBuffer sb = new StringBuffer();
	 HttpClient client = null;
	 if(args.length != 0)
		 client = getHttpClient(args[0], args[1]);
	 else
		 client = getHttpClient();
	 HttpGet httpGet = new HttpGet("http://www.whatismyip.com.tw/");
	 try {
	  HttpResponse response = client.execute(httpGet);
	  
	  HttpEntity entry = response.getEntity();
	  
	  if(entry != null)
	  {
	   InputStreamReader is = new InputStreamReader(entry.getContent());
	   BufferedReader br = new BufferedReader(is);
	   String str = null;
	   while((str = br.readLine()) != null)
	   {
	    sb.append(str.trim());
	   }
	   br.close();
	  }
	 } catch (ClientProtocolException e) {
	  e.printStackTrace();
	 } catch (IOException e) {
	  e.printStackTrace();
	 }
	 System.out.println(sb.toString());
	}

	
	private static HttpClient getHttpClient(String ip, String port) {
		 DefaultHttpClient httpClient = new DefaultHttpClient();
		 HttpHost proxy = new HttpHost(ip, Integer.parseInt(port), null);  
		 httpClient.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, proxy); 
		 return httpClient;
	}
	
	
	private static HttpClient getHttpClient() {
	 DefaultHttpClient httpClient = new DefaultHttpClient();
	 String proxyHost = "202.106.16.36";
	 int proxyPort = 3128;
	 HttpHost proxy = new HttpHost(proxyHost,proxyPort, null);  
	 httpClient.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, proxy); 
//	 String userName = "china\\******";
//	 String password = "*******";
//	 httpClient.getCredentialsProvider().setCredentials(
//	   new AuthScope(proxyHost, proxyPort),
//	   new UsernamePasswordCredentials(userName, password));
//	 HttpHost proxy = new HttpHost(proxyHost,proxyPort);
//	 httpClient.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, proxy);
	 return httpClient;
	}


}