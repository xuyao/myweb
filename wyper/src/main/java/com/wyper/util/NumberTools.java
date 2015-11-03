package com.wyper.util;

import java.util.Random;

/**
 * 工具类
 * @author xuyao
 *
 */
public class NumberTools {
	
	/**
	 * 
	 * @Title: getRandomString2 
	 * @Description: 获取一个随机字符串 方法2 
	 * @param length
	 * @return 
	 * @throws
	 */
	public static String getRandomString2(int length) {
		Random random = new Random();
		StringBuffer sf = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(3);
			long result = 0;

			switch (number) {
			case 0:
				result = Math.round(Math.random() * 25 + 65);//A-Z
				sf.append(String.valueOf((char)result));
				break;

			case 1:
				result = Math.round(Math.random() * 25 + 97);//a-z
				sf.append(String.valueOf((char)result));
				break;
			case 2:
				sf.append(String.valueOf(new Random().nextInt(10)));//0-9
				break;

			}
		}

		return sf.toString();
	}

	/**
	 * 
	 * @Title: randomNumber 
	 * @Description: 手机验证码：生成N位随机数 
	 * @param length
	 * @return 
	 * 
	 * @throws
	 */
	public static String randomNumber(int length){
		Random random = new Random();
		StringBuffer sf = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(10);// 0~9
			sf.append(number);
		}
		return sf.toString();
	}
	
	public static Integer randomIneger(int length){
		Random random = new Random();
		Integer num = -1;
		for (int i = 0; i < length; i++) {
			num = random.nextInt(10);// 0~9
		}
		return num;
	}
	
	
	public static void main(String[] args) {
		try {
			System.out.println(randomNumber(6));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
