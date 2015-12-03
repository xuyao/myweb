package com.wyper.job;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;

public class CheckSocketProxy {

	public static void main(String args[]){
		System.setProperty("socksProxyHost", "122.89.138.20");
		System.setProperty("socksProxyPort", "6675");
		try{
			URL url = new URL("http://ip.catr.cn/");
			URLConnection con = url.openConnection();
			InputStreamReader isr = new InputStreamReader(con.getInputStream());
			char[] cs = new char[1024];
			int i = 0;
			while ((i = isr.read(cs)) > 0) {
				System.out.println(new String(cs, 0, i));
			}
			isr.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
//        try {
//            URL url = new URL("http://www.baidu.com");
//            // 创建代理服务器
//            InetSocketAddress addr = new InetSocketAddress("192.168.0.254",
//                    8080);
//            // Proxy proxy = new Proxy(Proxy.Type.SOCKS, addr); // Socket 代理
//            Proxy proxy = new Proxy(Proxy.Type.HTTP, addr); // http 代理
//            // 如果我们知道代理server的名字, 可以直接使用
//            // 结束
//            URLConnection conn = url.openConnection(proxy);
//            InputStream in = conn.getInputStream();
//            // InputStream in = url.openStream();
//            String s = IOUtils.toString(in);
//            System.out.println(s);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//            http("127.0.0.1", 1080);
//            socket5("122.89.138.20", 6675);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

	}
	
	
	/**
     * @param ip
     * @param port
     * @throws Exception
     */
    static void http(String ip, int port) throws Exception {
        Authenticator.setDefault(new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("username", "password"
                        .toCharArray());
            }
        });
        // 此处可以是： HTTP、SOCKS5
        Proxy proxy = new Proxy(Proxy.Type.HTTP,
                new InetSocketAddress(ip, port));
        URL url = new URL("http://www.baidu.com/");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection(proxy);
        conn.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
        conn.setConnectTimeout(10000);
        InputStream is = conn.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        StringBuffer text = new StringBuffer();
        String line = null;
        while ((line = in.readLine()) != null) {
            text.append(line);
        }
        if (is != null) {
            is.close();
        }
        if (conn != null) {
            conn.disconnect();
        }
        System.out.println(text.toString());
    }
    
	
	 /**
     * @param ip
     * @param port
     * @throws Exception
     */
    static void socket5(String ip, int port) throws Exception {
        Authenticator.setDefault(new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("username", "password"
                        .toCharArray());
            }
        });
        Proxy proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(ip,
                port));
        Socket socket = new Socket(proxy);
        socket.connect(new InetSocketAddress("www.baidu.com", 80));
        OutputStream output = socket.getOutputStream();
        InputStreamReader isr = new InputStreamReader(socket.getInputStream(),
                "GBK");
        BufferedReader br = new BufferedReader(isr);
        StringBuilder request = new StringBuilder();
        request.append("GET /index.php HTTP/1.1\r\n");
        request.append("Accept-Language: zh-cn\r\n");
        request.append("Host: www.baidu.com\r\n");
        request.append("\r\n");
        output.write(request.toString().getBytes());
        output.flush();
 
        StringBuilder sb = new StringBuilder();
        String str = null;
        while ((str = br.readLine()) != null) {
            sb.append(str + "\n");
        }
        System.out.println(sb.toString());
        br.close();
        isr.close();
        output.close();
        socket.close();
    }
	
}
