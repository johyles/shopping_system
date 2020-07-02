package com.zte.shopping.service;

import java.util.List;

import com.zte.shopping.entity.Staff;
import com.zte.shopping.entity.User;

/**
 * 
 * @author liyan
 *
 */

public interface LoginService
{
	/**
	 * 管理员登录
	 * 
	 * 判断验证码是否正确
	 * 判断账号、密码与角色是否正确
	 */
	public List<Staff> loginStaffCheck(String loginName, String password);

	public List<User> loginUserCheck(String loginName, String password);



}
