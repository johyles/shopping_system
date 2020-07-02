package com.zte.shopping.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.misc.BASE64Encoder;

/**
 * MD5加密 工具类
 * 
 * MD5加密算法在国外至今没有被破解
 * 但是到中国没多久就被中国的3个教授破解了
 */
public class MD5Util
{
    public static String md5(String str)
    {
    	try 
    	{
    		// 编译期异常 java.security.NoSuchAlgorithmException
			MessageDigest md = MessageDigest.getInstance("MD5");
			
			// str.getBytes("utf-8") 编译期异常 
			// java.io.UnsupportedEncodingException
			byte[] byteArr = md.digest(str.getBytes("utf-8"));
			
			// 虽然加密了,但是看上去像乱码(堄{溽觰驛?鮽堲)
			// return new String(byteArr);
			// 定义一个BASE64Encoder实例 BASE64Encoder sun公司的
			BASE64Encoder encoder = new BASE64Encoder();
			
			// HIjTe+Th03XzQdkG9YKI9A==
			return encoder.encode(byteArr);
		} catch (NoSuchAlgorithmException e) 
		{
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) 
		{
			e.printStackTrace();
		}
		return str;
    }
    
    /**
     * 以后用单元测试
     * 不要用main()方法
     */
    public static void main(String[] args)
    {
		System.out.println(md5("admin"));
	}
    
}
