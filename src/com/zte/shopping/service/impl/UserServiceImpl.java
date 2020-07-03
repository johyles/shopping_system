package com.zte.shopping.service.impl;

import com.zte.shopping.entity.User;
import com.zte.shopping.mapper.IUserMapper;
import com.zte.shopping.service.IUserService;
import com.zte.shopping.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DispatcherServlet
 * @author liyan
 *
 */
@Service
public class UserServiceImpl implements IUserService
{
    @Autowired
    private IUserMapper userMapper;

    @Override
    public boolean insertUser(User user) {
        String pwd= MD5Util.md5(user.getPassword());
        user.setPassword(pwd);
        try {
            userMapper.insertUser(user);
            return true;
        }catch (Exception r){
            r.printStackTrace();
            return false;
        }
    }


    @Override
    public List<User> logincheck(String username, String pwd) {
        List<User> list=userMapper.logincheck(username,pwd);
        /*if(list.size()>0 && list!=null){
            return true;
        }else {
            return false;
        }*/
        return list;
    }

    //模糊查询--也可以是总的查询
    @Override
    public List<User> selectUserListByUser(User user) {
        return userMapper.selectUserListByUser(user);
    }

    //用户修改
    @Override
    public boolean modifyuser(User user) {
        int count=userMapper.modifyuser(user);
        if(count>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public User selectByName(String name) {
        return userMapper.selectByName(name);
    }

    @Override
    public int modifyPasswordById(Integer userId, String newPassword) {
        if(!newPassword.equals("")){
            return userMapper.modifyPasswordById(userId,newPassword);
        }else{
            return 0;
        }

    }


    @Override
    public int addUser(String rUserName, String rLoginName, String rPassword, String rPhone, String rAddress,int isValid) {
        return userMapper.addUser(rUserName,rLoginName,rPassword,rPhone,rAddress,isValid);
    }

    @Override
    public int modifyMessage(Integer userId, String phone, String address) {
        return userMapper.modifyMessage(userId,phone,address);
    }
}
