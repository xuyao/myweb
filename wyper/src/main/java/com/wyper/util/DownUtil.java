package com.wyper.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownUtil {

	
public static void download(String urlString, String filename,String savePath) throws Exception {  
        URL url = new URL(urlString);
        URLConnection con = url.openConnection();
        con.setRequestProperty("User-agent", UserAgent.getUserAgent());
        con.setConnectTimeout(50*1000);
        con.setReadTimeout(50*1000);
        InputStream is = con.getInputStream();
        
        byte[] bs = new byte[1024];
        int len;
        
       File sf=new File(savePath);  
       if(!sf.exists()){  
           sf.mkdirs();  
       }  
       OutputStream os = new FileOutputStream(sf.getPath()+File.separator+filename);  
        while ((len = is.read(bs)) != -1) {  
          os.write(bs, 0, len);  
        }  
        os.close();  
        is.close();  
    }   
}
