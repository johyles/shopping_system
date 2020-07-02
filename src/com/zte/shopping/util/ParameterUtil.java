package com.zte.shopping.util;

/**
 * 参数工具类
 * 
 * @author liyan
 *
 */
public class ParameterUtil
{
	public static boolean isnull(String s)
	{
		if ("".equals(s) || null == s)
		{
			return true;
		}
		return false;
	}
}
