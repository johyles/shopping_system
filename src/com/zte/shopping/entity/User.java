package com.zte.shopping.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * ContextLoaderListener
 * 前台会员表
 * @author liyan
 *
 */
public class User implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Integer userId;
	
	// 姓名
	private String userName;
	
	// 帐号
	private String loginName;
	
	// 密码
	private String password;
	
	// 电话
	private String phone;
	
	// 地址
	private String address;
	
	// 状态
	private Integer isValid;//0无效，1有效
	
	// 会员注册时间
	private Date registDate;
	
	public User() 
	{
		super();
	}

	public User(Integer userId, String userName, String loginName, String password, String phone, String address, Integer isValid, Date registDate) {
		this.userId = userId;
		this.userName = userName;
		this.loginName = loginName;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.isValid = isValid;
		this.registDate = registDate;
	}

	public Integer getUserId()
	{
		return userId;
	}
	
	public void setUserId(Integer userId) 
	{
		this.userId = userId;
	}
	
	public String getUserName() 
	{
		return userName;
	}
	
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	
	public String getLoginName() 
	{
		return loginName;
	}
	
	public void setLoginName(String loginName) 
	{
		this.loginName = loginName;
	}
	
	public String getPassword() 
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public String getPhone() 
	{
		return phone;
	}
	
	public void setPhone(String phone) 
	{
		this.phone = phone;
	}
	
	public String getAddress()
	{
		return address;
	}
	
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	public Integer getIsValid()
	{
		return isValid;
	}
	
	public void setIsValid(Integer isValid)
	{
		this.isValid = isValid;
	}
	
	public Date getRegistDate()
	{
		return registDate;
	}
	
	public void setRegistDate(Date registDate) 
	{
		this.registDate = registDate;
	}
}
