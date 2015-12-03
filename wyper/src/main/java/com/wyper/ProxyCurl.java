package com.wyper;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProxyCurl {
	
	public static void main(String[] args){
		
//		HttpClient httpClient = new DefaultHttpClient();
//		HttpHost httpHost = new HttpHost("http://www.ip138.com/ips138.asp?ip=218.241.108.40&action=2");
//		HttpHost proxy = new HttpHost("202.106.16.36", 3128);

        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpHost target = new HttpHost("www.whatismyip.com.tw", 80, "http");
        HttpHost proxy = new HttpHost("60.211.60.89", 8888, "http");
        RequestConfig config = RequestConfig.custom().setProxy(proxy).build();

        HttpGet httpGet = new HttpGet("http://www.whatismyip.com.tw/");
        httpGet.setConfig(config);
//        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
//        formparams.add(new BasicNameValuePair("ip", "218.241.108.40"));
//        formparams.add(new BasicNameValuePair("action", "2"));
//        UrlEncodedFormEntity entity;
        
        try {
//            entity = new UrlEncodedFormEntity(formparams, "UTF-8");
//            httpGet.setEntity(entity);
            CloseableHttpResponse response = closeableHttpClient.execute(
                    target, httpGet);
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                System.out.println(EntityUtils.toString(httpEntity, "gbk"));
            }
            closeableHttpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

	}
	
}
