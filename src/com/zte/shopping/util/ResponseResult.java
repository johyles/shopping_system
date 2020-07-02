package com.zte.shopping.util;

public class ResponseResult 
{
	// 响应状态码,由程序员确定
    private Integer responseCode;
    
    // 响应信息
    private String message;
    
    // 返回值
    private Object returnObject;

	public Integer getResponseCode()
	{
		return responseCode;
	}

	public void setResponseCode(Integer responseCode)
	{
		this.responseCode = responseCode;
	}

	public String getMessage() 
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public Object getReturnObject()
	{
		return returnObject;
	}

	public void setReturnObject(Object returnObject) 
	{
		this.returnObject = returnObject;
	}
}
