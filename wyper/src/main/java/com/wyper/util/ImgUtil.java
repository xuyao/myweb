package com.wyper.util;

import java.io.*;
import java.awt.*;
import java.awt.image.*;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.*;

/**
 * 图片压缩类	
 * */
public class ImgUtil {
	
		private static Image img = null;
		
		private static Integer width = 0;
		private static Integer height = 0;
		
		/**
		 * 强制压缩/放大图片到固定的大小
		 * @param w int 新宽度
		 * @param h int 新高度
		 */
		public static void resize(File f, String path, Integer w, Integer h) throws IOException {
//			img = ImageIO.read(is);// 构造Image对象
			img = ImageIO.read(f);// 构造Image对象
			
			width = img.getWidth(null);
			height = img.getHeight(null);

			if (width / height > w / h) {
				w = (Integer) (width * h / height);
			} else {
				h = (Integer) (height * w / width);
			}
			
			// SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
			BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB ); 
			image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图
			File destFile = new File(path);
			FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流
			// 可以正常实现bmp、png、gif转jpg
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(image); // JPEG编码
			out.close();
		}
		

		//args[0]源文件  args[1]压缩文件
		public static void main(String[] args){
			try {
				new ImgUtil().resize(new File(args[0]), args[1], 131, 203);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
}
