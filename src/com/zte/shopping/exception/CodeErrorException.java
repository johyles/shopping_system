package com.zte.shopping.exception;

/**
 * 验证码错误   自定义异常
 * 
 * @author liyan
 *
 */
public class CodeErrorException extends Exception 
{
	private static final long serialVersionUID = 1L;

	public CodeErrorException() 
	{
		
	}

	public CodeErrorException(String message) 
	{
		super(message);
	}

	public CodeErrorException(Throwable cause) 
	{
		super(cause);
	}

	public CodeErrorException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
