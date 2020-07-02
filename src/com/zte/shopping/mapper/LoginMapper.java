package com.zte.shopping.mapper;

import java.util.List;

import com.zte.shopping.entity.Staff;
import com.zte.shopping.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component(value ="LoginMapper")
public interface LoginMapper
{
	/**
	 * 根据账号、密码、是否有效、角色、查询有效的管理员信息
	 *
	 */

	public List<Staff> loginStaffCheck(@Param("loginName")String loginName,
									   @Param("password")String password);

	public List<User> loginUserCheck(@Param("loginName") String username,
									 @Param("password") String pwd);

}
