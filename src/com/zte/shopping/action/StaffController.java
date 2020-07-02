package com.zte.shopping.action;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zte.shopping.constant.DictConstant;
import com.zte.shopping.entity.Dept;
import com.zte.shopping.entity.Staff;
import com.zte.shopping.exception.*;
import com.zte.shopping.service.IDeptManagerService;
import com.zte.shopping.util.ParameterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zte.shopping.constant.ResponseCodeConstant;
import com.zte.shopping.service.IStaffService;
import com.zte.shopping.util.ResponseResult;

/**
 * 后台 --- 管理员管理Hanlder
 * 
 * @author liyan
 *
 */
@Controller
@RequestMapping("/staff")
public class StaffController 
{
	@Autowired
	private IStaffService staffService;
	@Autowired
	private IDeptManagerService deptService;

	@RequestMapping(value = "/main.html")
	public String gostaff_main(){
		return "backend/main";
	}

	/**
	 * 动态  模糊  查询   管理员信息
	 */
	@RequestMapping("/staffManager.html")
	public ModelAndView findFuzzyByParamList(Staff staffParameter, String deptId, String pageNo, String pageSize)
	{
		ModelAndView  modelAndView = new ModelAndView();

		if (ParameterUtil.isnull(pageNo))
		{
			pageNo = DictConstant.PAGE_NO;
		}

		if (ParameterUtil.isnull(pageSize))
		{
			pageSize = DictConstant.PAGE_SIZE;
		}

		PageHelper.startPage(Integer.parseInt(pageNo), Integer.parseInt(pageSize));

		List<Staff> staffList = staffService.findFuzzyByParamList(staffParameter, deptId);

		PageInfo<Staff> pageStaffList = new PageInfo<Staff>(staffList);

		//查询所有部门列表
		List<Dept> deptList = deptService.selectDeptAll();

		modelAndView.addObject("deptList", deptList);
		modelAndView.addObject("staffList", pageStaffList);

		modelAndView.setViewName("backend/staffManager");

		return modelAndView;
	}



	/**
	 * 添加管理员
	 *
	 * 员工账号不能重复
	 * 密码需要使用MD5加密
	 * 创建人      为    当前登录员工
	 * 创建时间   为   当前时间
	 *
	 * 默认田间的员工为有效状态
	 *
	 */
	@ResponseBody
	@RequestMapping("/addStaff")
	public ResponseResult addStaff(Staff staff, String deptId, HttpSession session)
	{
		ResponseResult result = new ResponseResult();

		try
		{
			staffService.addStaff(staff, deptId, session);

			result.setMessage("成功");
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);

		} catch (StaffExistException e)
		{
			result.setMessage(e.getMessage());
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);

		} catch (LoginDisabledException e)
		{
			result.setMessage(e.getMessage());
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_LOGIN_TIMEOUT);

		} catch (NoPromissionException e)
		{
			result.setMessage(e.getMessage());
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_NO_PROMISSON);
		} catch(Exception e)
		{
			e.printStackTrace();
			result.setMessage("服务器内部异常");
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
		}

		return result;
	}

	/**
	 * 做修改管理员操作时  查询出修改页面的管理员信息
	 *
	 * 根据id查询员工信息
	 */
	@ResponseBody
	@RequestMapping("/findById")
	public ResponseResult findById(String staffId)
	{
		ResponseResult result = new ResponseResult();

		try
		{
			Staff staff = staffService.findById(staffId);

			result.setMessage("成功");
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);

			result.setReturnObject(staff);

		} catch (RequestParameterException e)
		{
			result.setMessage(e.getMessage());
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_REQUEST_PARAMETER_ERROR);

		} catch(Exception e)
		{

			result.setMessage("服务器内部异常");
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
		}

		return result;
	}
	/**
	 * 修改员工管理员
	 */
	@ResponseBody
	@RequestMapping("/modifyStaff")
	public ResponseResult modifyStaff(Staff staff, String deptId)
	{
		ResponseResult result = new ResponseResult();

		try
		{
			staffService.modifyStaff(staff, deptId);

			result.setMessage("成功");
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);

		} catch(RequestParameterException e)
		{
			result.setMessage(e.getMessage());
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_REQUEST_PARAMETER_ERROR);

		} catch (Exception e)
		{
			e.printStackTrace();
			result.setMessage("服务器内部异常");
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
		}

		return result;
	}

	//管理员禁用启用转换部分--已完成
	@RequestMapping(value = "/modifyStaffStatus")
	public ModelAndView modifyStaffStatus(String staffId,String isValid){
		ModelAndView mv = new ModelAndView();
		Staff staff=new Staff();
		int validcode= Integer.parseInt(isValid);
		int id=Integer.parseInt(staffId);
		staff.setStaffId(id);
		if(validcode==1){
			staff.setIsValid(0);
			if(staffService.modifyStaff(staff)){
				System.out.println("禁用成功");
			}else {
				System.out.println("禁用失败");
			}
		}else if(validcode==0){
			staff.setIsValid(1);
			if(staffService.modifyStaff(staff)){
				System.out.println("启用成功");
			}else {
				System.out.println("启用失败");
			}
		}else{
			System.out.println("validcode异常");
		}
		mv.setViewName("redirect:/staff/staffManager.html");
		return mv;
	}

}
