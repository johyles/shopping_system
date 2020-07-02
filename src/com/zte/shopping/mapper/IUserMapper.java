package com.zte.shopping.mapper;


import com.zte.shopping.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserMapper
{

	/**
	 * 添加会员
	 */
	public void insertUser(User user);
	public List<User> logincheck(@Param("username") String username, @Param("pwd") String pwd);

	//模糊查询--也可以是总的查询
	public List<User> selectUserListByUser(User user);

	//用户修改
	public int modifyuser(User user);

	/**
	 * 添加会员
	 */

	public User selectByName(@Param("name") String name);

	public int modifyPasswordById(@Param("userId") Integer userId,@Param("newPassword")  String newPassword);;

	public int addUser(@Param("rUserName") String rUserName, @Param("rLoginName")String rLoginName,
					   @Param("rPassword")String rPassword, @Param("rPhone")String rPhone,
					   @Param("rAddress")String rAddress,@Param("isValid")int isValid);

	public int modifyMessage(@Param("userId")Integer userId, @Param("phone")String phone,
							 @Param("address") String address);


}
