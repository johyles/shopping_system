package com.zte.shopping.util;

public class DataUtil 
{
     public static String stringSpace(String s)
     {
    	 if (!ParameterUtil.isnull(s))
    	 {
    		 char[] chrArray = s.toCharArray();
    		 
    		 StringBuffer sb = new StringBuffer();
    		 
    		 for (int i = 0; i < chrArray.length; i++)
    		 {
    			 sb.append("/" + chrArray[i]);
    		 }
    		 
    		 //   /a/d/m/i/n
    		 return sb.toString();
    	 }
		 return s;
     }
     
     public static void main(String[] args) 
     {
		   System.out.println(stringSpace("admin"));
	 }
}
