package com.zte.shopping.exception;

/**
 * 会员未登录异常
 * 
 * @author liyan
 *
 */
public class UserNotLoginException extends Exception
{
	private static final long serialVersionUID = 1L;

	public UserNotLoginException() 
	{
		super();
	}

	public UserNotLoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) 
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UserNotLoginException(String message, Throwable cause) 
	{
		super(message, cause);
	}

	public UserNotLoginException(String message)
	{
		super(message);
	}

	public UserNotLoginException(Throwable cause) 
	{
		super(cause);
	}
	
}
