package com.zte.shopping.exception;

/**
 * shift + alt + s
 *     Generate Constructor from  Superclass
 *     
 * @author liyan
 *
 */
public class LoginDisabledException extends Exception 
{
	private static final long serialVersionUID = 1L;

	public LoginDisabledException() 
	{
		super();
	}

	public LoginDisabledException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) 
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public LoginDisabledException(String message, Throwable cause) 
	{
		super(message, cause);
	}

	public LoginDisabledException(String message)
	{
		super(message);
	}

	public LoginDisabledException(Throwable cause)
	{
		super(cause);
	}

}
