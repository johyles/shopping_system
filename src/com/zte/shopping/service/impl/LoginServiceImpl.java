package com.zte.shopping.service.impl;

import com.zte.shopping.entity.Staff;
import com.zte.shopping.entity.User;
import com.zte.shopping.mapper.LoginMapper;
import com.zte.shopping.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: shopping
 * @description:
 * @author: SkyCloud
 * @create: 2020-06-23 19:25
 **/
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper LoginMapper;

    //登录验证
    public List<Staff> loginStaffCheck(String loginName, String password) {
        List<Staff> list= LoginMapper.loginStaffCheck(loginName,password);
        return list;
    }

    //登录验证
    public List<User> loginUserCheck(String loginName, String password) {
        List<User> list= LoginMapper.loginUserCheck(loginName,password);
        return list;
    }

}
