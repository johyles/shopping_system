package com.zte.shopping.exception;

/**
 * 原密码不正确
 * 
 * @author liyan
 *
 */
public class PasswordErrorException extends Exception 
{
	private static final long serialVersionUID = 1L;

	public PasswordErrorException() 
	{
		super();
	}

	public PasswordErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) 
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PasswordErrorException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public PasswordErrorException(String message) 
	{
		super(message);
	}

	public PasswordErrorException(Throwable cause)
	{
		super(cause);
	}

	

}
