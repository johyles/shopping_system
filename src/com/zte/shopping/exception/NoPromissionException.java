package com.zte.shopping.exception;

/**
 * 没有权限自定义异常
 * 
 * @author liyan
 *
 */
public class NoPromissionException extends Exception
{
	private static final long serialVersionUID = 1L;

	public NoPromissionException() 
	{
		
	}

	public NoPromissionException(String message) 
	{
		super(message);
	}

	public NoPromissionException(Throwable cause)
	{
		super(cause);
	}

	public NoPromissionException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
