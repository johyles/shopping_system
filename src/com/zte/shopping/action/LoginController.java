package com.zte.shopping.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zte.shopping.entity.*;
import com.zte.shopping.util.CartVo;
import com.zte.shopping.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zte.shopping.service.LoginService;

/**
 * 后台 --- 管理员管理Hanlder
 * 
 * @author liyan
 *
 */


@Controller
@RequestMapping("/login")
public class LoginController
{
	@Autowired
    private LoginService loginService;

	/**
	 * 登录,验证码功能后期再做
	 */
	@RequestMapping("/login.html")
	public ModelAndView login(@RequestParam(value ="username") String username,
							  @RequestParam(value ="pwd")String pwd,
							  @RequestParam(value ="state")String state,
							  @RequestParam(value ="inputcode")String inputcode,
							  HttpSession session,
							  HttpServletResponse response)throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		ModelAndView  modelAndView = new ModelAndView();
		String pwdmd5= MD5Util.md5(pwd);
		List<User> list_user=loginService.loginUserCheck(username,pwdmd5);
		List<Staff> list_staff= loginService.loginStaffCheck(username,pwdmd5);
		try
		{
			String code=(String)session.getAttribute("code");
			if (code.equals(inputcode)){
				if(state.equals("2002")){
					if(list_user.size()>0 && list_user!=null){
						System.out.println("存入session的user"+list_user.get(0));
						session.setAttribute("user",list_user.get(0));
						out.flush();
						out.println("<script>alert('学生登录成功！');</script>");
						modelAndView.setViewName("backend/main");
					}
					else {
						out.flush();
						out.println("<script>alert('学生登录失败！');</script>");
						modelAndView.setViewName("backend/login");
					}
				}else if(state.equals("2001")){
					if(list_staff.size()>0 && list_staff!=null){
						session.setAttribute("staff",list_staff.get(0));
						out.flush();
						out.println("<script>alert('管理员登陆成功！');</script>");
						modelAndView.setViewName("backend/main");
					}else {
						System.out.println("管理员登录失败");
						out.flush();
						out.println("<script>alert('不能为空，请重新输入！');</script>");
						modelAndView.setViewName("backend/login");
					}
				}
			} else {
				out.flush();
				out.println("<script>alert('验证码错误！');</script>");
				modelAndView.setViewName("backend/login");
			}

		}  catch (Exception e)
		{
			System.out.println("异常");
			modelAndView.addObject("loginMsg", "服务器内部异常");
			modelAndView.setViewName("backend/login");

		}

		return modelAndView;
	}
}
