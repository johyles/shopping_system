package com.zte.shopping.exception;

/**
 * 请求参数不能为空  自定义异常
 * 
 * @author liyan
 *
 */
public class RequestParameterException extends Exception
{
	private static final long serialVersionUID = 1L;

	public RequestParameterException() 
	{
		
	}

	public RequestParameterException(String message)
	{
		super(message);
	}

	public RequestParameterException(Throwable cause) 
	{
		super(cause);
	}

	public RequestParameterException(String message, Throwable cause) 
	{
		super(message, cause);
	}

}
