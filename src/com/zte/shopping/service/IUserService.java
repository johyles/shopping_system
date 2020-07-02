package com.zte.shopping.service;

import com.zte.shopping.entity.User;

import java.util.List;

public interface IUserService {

    public void insertUser(User user);

    public List<User> logincheck(String username, String pwd);

    //模糊查询--也可以是总的查询
    public List<User> selectUserListByUser(User user);

    //用户修改
    public boolean modifyuser(User user);

    public User selectByName(String name);

    public int  modifyPasswordById(Integer userId, String newPassword);

    public int addUser(String rUserName, String rLoginName, String rPassword, String rPhone, String rAddress,int isValid);

    public int modifyMessage(Integer userId, String phone, String address);

}
