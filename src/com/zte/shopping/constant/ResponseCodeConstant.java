package com.zte.shopping.constant;

/**
 * 响应状态码常量接口
 * 
 * @author liyan
 *
 */
public interface ResponseCodeConstant 
{
	/**
	 * 响应状态码为成功,值为:1
	 */
    public static final int RESPONSE_CODE_SUCCESS = 1;
    
    /**
	 * 响应状态码为失败,值为:2
	 */
    public static final int RESPONSE_CODE_FAIL = 2;
    
    /**
	 * 响应状态码为请求参数有误,值为:3
	 */
    public static final int RESPONSE_CODE_REQUEST_PARAMETER_ERROR = 3;
    
	/**
	 * 响应状态码为登录失效,值为:4
	 */
    public static final int RESPONSE_CODE_LOGIN_TIMEOUT = 4;
    
    /**
	 * 响应状态码为权限不足,值为:5
	 */
    public static final int RESPONSE_CODE_NO_PROMISSON = 5;
}
