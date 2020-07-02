package com.zte.shopping.action;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zte.shopping.constant.DictConstant;
import com.zte.shopping.constant.ResponseCodeConstant;
import com.zte.shopping.entity.Item;
import com.zte.shopping.entity.Order;
import com.zte.shopping.entity.User;
import com.zte.shopping.service.FileUploadService;
import com.zte.shopping.service.IStaffService;
import com.zte.shopping.service.IUserService;
import com.zte.shopping.service.LoginService;
import com.zte.shopping.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UseController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IStaffService staffService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private FileUploadService fileUploadService;

    /**
     * 前台
     */

    @RequestMapping("/denglu")
    public String  userDenglu(String name, String password, HttpSession session){

        User user = userService.selectByName(name);
        System.out.println("输入的账号密码**********************"+name+"**********************"+password);
        System.out.println("得到的**********************"+user.getLoginName()+"**********************"+user.getPassword());

        String pwdmd5= MD5Util.md5(password);
        List<User> list_user=loginService.loginUserCheck(name,pwdmd5);
        if(user!=null){
            if(user.getPassword().equals(pwdmd5)){
                System.out.println("存入session的user"+list_user.get(0));
                session.setAttribute("user",list_user.get(0));

                // 登录后,创建一个空的购物
                List<Item> list=new ArrayList<>();
                Order order=new Order();
                CartVo cartVo=new CartVo();
                cartVo.setItems(list);
                Double sum=0.00;

                for(Item item:list){
                    sum+=item.getPrice();
                }

                cartVo.setPrice(sum);
                session.setAttribute("cart", cartVo);

                session.removeAttribute("user");
                session.setAttribute("user",user);
                System.out.println(session.getAttribute("user"));

                /* return "redirect:/center.html";*/
                String fangWenPath = fileUploadService.selectById(user.getUserId()).getFilePath();
                System.out.println("登录时获取的访问路径**********"+fangWenPath);
                session.removeAttribute("fangWenPath");
                session.setAttribute("fangWenPath",fangWenPath);
                return "center";
            }
        }
        return "main";
    }

    @RequestMapping("/tuichu")
    public String tuichu( HttpSession session)
    {
        System.out.println("enter*****退出功能");
        session.removeAttribute("user");
        return "main";
    }

    @RequestMapping("/modifypassword")
    public String modifypassword(String loginName, String oldPassword,String newPassword,String repeatPassword){
        User user = userService.selectByName(loginName);
        System.out.println("输入的账号密码**********************"+loginName+"**********************"+oldPassword);
        System.out.println("得到的**********************"+user.getLoginName()+"**********************"+user.getPassword());
        if(newPassword.equals(repeatPassword)&&user.getPassword().equals(oldPassword)){
            System.out.println("enter********修改密码功能");
            int i = userService.modifyPasswordById(user.getUserId(), newPassword);
            if(i!=0){
                System.out.println("修改密码成功!");
            }else{
                System.out.println("修改密码失败!");
            }
        }
        return "main";
    }

    @RequestMapping("/regist")
    public String regist(String rUserName, String rLoginName,String rPassword,String rPhone,String rAddress){
        User user = userService.selectByName(rLoginName);
        System.out.println("输入的账号密码**********************"+rLoginName+"**********************"+rPassword);
        //System.out.println("找到的账号密码**********************"+user.getUserId()+"**********************"+user.getLoginName());
        if(user==null){
            System.out.println("enter********注册功能");
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            int isValid=1;
            int i = userService.addUser(rUserName,rLoginName,rPassword,rPhone,rAddress,isValid);
            if(i!=0){
                System.out.println("注册成功!");
            }else{
                System.out.println("注册失败!");
            }
        }
        return "main";
    }

    @RequestMapping("/modifyMessage")
    public String modifyMessage(String loginName, String phone,String address, HttpSession session){
        User user = userService.selectByName(loginName);
        System.out.println("输入的账号**********************"+loginName);
        if(user!=null){
            System.out.println("enter********修改信息功能");
            int i = userService.modifyMessage(user.getUserId(),phone,address);
            if(i!=0){
                System.out.println("修改信息成功!");
                session.removeAttribute("user");
            }else{
                System.out.println("修改信息失败!");
            }
        }
        return "main";
    }

    /*
     * 后台
     */

    @RequestMapping("/main.html")
    public String gomain(HttpServletRequest request, HttpServletResponse response){
        return "main";
    }

    //员工查询
    @RequestMapping(value = "/userManager.html")//
    public ModelAndView gouserManager(String pageNo, String pageSize,String iuserName,String iloginName,String iphone,String iaddress,String iisValid){
        ModelAndView modelAndView = new ModelAndView();
        if (ParameterUtil.isnull(pageNo))
        {
            pageNo = DictConstant.PAGE_NO;
        }

        if (ParameterUtil.isnull(pageSize))
        {
            pageSize = DictConstant.PAGE_SIZE;
        }
        // 设置分页
        PageHelper.startPage(Integer.parseInt(pageNo),Integer.parseInt(pageSize));
        // 查询所有数据
        User user=new User();
       // System.out.println(iuserName);
        if(iuserName!=null ){
            user.setUserName(iuserName);
        }
        if(iloginName!=null){
            user.setLoginName(iloginName);
        }
        if(iphone!=null){
            user.setPhone(iphone);
        }
        if(iaddress!=null){
            user.setAddress(iaddress);
        }
        //System.out.println("code"+iisValid);
        if(iisValid!=null){
            int code=Integer.parseInt(iisValid);
            System.out.println("数字"+code);
            if(code==1 || code==0){
                user.setIsValid(code);
            }
        }
        List<User> list=userService.selectUserListByUser(user);
        // 将查询出的所有数据进行分页设置,封装为PageInfo对象
        PageInfo<User> userlist = new PageInfo<User>(list);

        /*if (!userlist.getList().isEmpty() && userlist.getPageNum() != -1)
        {
            for(int i = 0; i < userlist.getList().size(); i++)
            {
                System.out.println(userlist.getList().get(i).getUserName() + "---------");
            }
        }*/
        modelAndView.addObject("userlist", userlist);
        modelAndView.setViewName("backend/userManager");
        return modelAndView;
    }

    //查询员工修改信息
    @RequestMapping(value = "/findUserById")
    @ResponseBody
    public ResponseResult findUserById(String userId){
        ResponseResult result=new ResponseResult();
        User user=new User();
        Integer uid=Integer.parseInt(userId);
        System.out.println("用户编号"+uid);
        user.setUserId(uid);
        List<User> list=userService.selectUserListByUser(user);
        System.out.println(list.get(0).getUserName());
        try{
            if(list.size()>0){
                result.setReturnObject(list.get(0));
                result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);
            }else{
                result.setMessage("选择数据失败");
                result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_REQUEST_PARAMETER_ERROR);
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("服务器内部异常");
            result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
        }
        return result;
    }

    //员工修改
    @RequestMapping(value = "/modifyUser")
    @ResponseBody
    public ResponseResult modifyuser(String userId,String userName,String loginName,String phone,String address){
        ResponseResult rprs=new ResponseResult();
        int id=Integer.parseInt(userId);
        User user=new User(id,userName,loginName,null,phone,address,null,null);
        try{
            if(userService.modifyuser(user)){
                rprs.setMessage("修改成功");
                rprs.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);
            }else {
                rprs.setMessage("修改失败");
                rprs.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_REQUEST_PARAMETER_ERROR);
            }
        }catch (Exception e){
            e.printStackTrace();
            rprs.setMessage("服务器内部异常");
            rprs.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
        }
        return rprs;
    }

    //修改员工有效无效状态
    @RequestMapping(value = "/modifyUserStatus")
    public ModelAndView modifyUserStatus(String userId,String isValid,String pageNo){
        ModelAndView mv = new ModelAndView();
        User user=new User();
        int validcode= Integer.parseInt(isValid);
        int id=Integer.parseInt(userId);
        user.setUserId(id);
        if(validcode==1){
            user.setIsValid(0);
            if(userService.modifyuser(user)){
                System.out.println("禁用成功");
            }else {
                System.out.println("禁用失败");
            }
        }else if(validcode==0){
            user.setIsValid(1);
            if(userService.modifyuser(user)){
                System.out.println("启用成功");
            }else {
                System.out.println("启用失败");
            }
        }else{
            System.out.println("validcode异常");
        }
        mv.setViewName("redirect:/user/userManager.html?pageNo="+pageNo);
        return mv;
    }

}
