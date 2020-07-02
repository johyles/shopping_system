package com.zte.shopping.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.springframework.util.CollectionUtils;

import com.alibaba.druid.util.StringUtils;

public class CreateCodeUtil 
{
	/**
	 * 生成4位的随机数(数字和字母组合)
	 */
	public static StringBuffer createCode(Random random)
	{
    	 String s = "1234567890qwertyuiopasdfghjklzxcvbnm";
    	 StringBuffer code = new StringBuffer();
    	 
    	 for (int i = 0; i < 4; i++)
    	 {
    		 code.append(s.charAt(random.nextInt(s.length())));
    	 }
    	 
		 return code;
	}
	
	/**
	 * 生成4位的纯数字验证码
	 */
	public static StringBuffer createCodeNum(Random random)
	{
    	 String s = "1234567890";
    	 StringBuffer code = new StringBuffer();
    	 
    	 for (int i = 0; i < 4; i++)
    	 {
    		 code.append(s.charAt(random.nextInt(s.length())));
    	 }
    	 
		 return code;
	}
	
	
	/**
	 * 生成 成语验证码
	 */
	public static String createCodeIdiom(Random random) throws IOException
	{
		 String randomStr = "";
    	 ArrayList<String> al = new ArrayList<String>();
    	 
         // 随机读取idiom.properties中的一行    其中任意的一行就是一个成语
    	 BufferedReader br = new BufferedReader(new FileReader("C:\\WorkSpace\\IDEA\\shopping\\src\\idiom.txt"));
    	 String str = "";
    	 while ((str = br.readLine()) != null)
    	 {
    		 al.add(str);
    	 }
    	 
    	 // if (al.size() != 0 && al != null)
    	 if (!CollectionUtils.isEmpty(al))
    	 {
    		 randomStr = al.get(random.nextInt(al.size()));
    	 }
    	 
		 return randomStr;
	}
	
	/*public static void main(String[] args) throws IOException
	{
		
        ArrayList<String> al = new ArrayList<String>();
    	 
         // 随机读取idiom.properties中的一行    其中任意的一行就是一个成语
    	 BufferedReader br = new BufferedReader(new FileReader("src/idiom.txt"));
    	 String str = "";
    	 while ((str = br.readLine()) != null)
    	 {
    		 al.add(str);
    	 }
    	 
    	 System.out.println( al.get(new Random().nextInt(al.size()))  );
	}*/
}



