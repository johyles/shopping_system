package com.zte.shopping.exception;

/**
 * 该管理员账号已经存在自定义异常
 * 
 * @author liyan
 *
 */
public class StaffExistException extends Exception
{
	private static final long serialVersionUID = 1L;

	public StaffExistException()
	{
		
	}

	public StaffExistException(String message)
	{
		super(message);
	}

	public StaffExistException(Throwable cause) 
	{
		super(cause);
	}

	public StaffExistException(String message, Throwable cause) 
	{
		super(message, cause);
	}

}
