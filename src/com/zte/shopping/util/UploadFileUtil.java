package com.zte.shopping.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.util.StringUtils;

/**
 * 文件上传的工具类
 * 
 * @author liyan
 *
 */
public class UploadFileUtil
{
	 /**
	  * 
	  * 重命名上传文件的名称
	  * @param String orifileName  要上传的原始文件名称
	  * 
	  */
     public static String renameUploadFileName(String  orifileName)
     {
    	// NPE  NullPoninterException  ---> null.方法   or  null.属性
    	/*if (orifileName == null || orifileName.length() == 0)
    	{
    		return "";
    	}*/
    	 
    	if (StringUtils.isEmpty(orifileName))
     	{
     		return "";
     	}
    	
    	// int idx = orifileName.lastIndexOf(".");
    	// System.out.print("--------------" + idx);
    	// "123.png"  --->  ".png"
    	String suffix = orifileName.substring(orifileName.lastIndexOf("."));  // StringIndexOutOfBoundsException
    	// System.out.println("--------------" + suffix);
    	
    	
    	// "123.png"  --->  "123"
    	String prefixName = orifileName.substring(0, orifileName.lastIndexOf("."));
    	
    	String uuid = UUID.randomUUID().toString();
    	
    	String serverFileName = uuid + prefixName + suffix;
		return serverFileName;
     }
     
     public static void main(String[] args)
     {
    	 // String timeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date());
    	 String timeStr = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    	 System.out.print("--------------" + timeStr);  
     }
}
