package com.zte.shopping.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.zte.shopping.entity.Staff;
import com.zte.shopping.exception.LoginDisabledException;
import com.zte.shopping.exception.NoPromissionException;
import com.zte.shopping.exception.RequestParameterException;
import com.zte.shopping.exception.StaffExistException;

/**
 * 
 * @author liyan
 *
 */
public interface IStaffService 
{


	/**
	 * 动态  模糊  查询   管理员信息
	 * @param staffParameter  用户输入的跟管理员相关的动态条件
	 * @param deptId     部门ID
	 * @return  List<Staff>   返回后台管理员信息列表
	 */
	public List<Staff> findFuzzyByParamList(Staff staffParameter, String deptId);

	//管理员修改
	public boolean modifyStaff(Staff staff);

	/**
	 * 添加管理员
	 *
	 * 员工账号不能重复
	 * 密码需要使用MD5加密
	 * 创建人      为    当前登录员工
	 * 创建时间   为   当前时间
	 *
	 * 默认田间的员工为有效状态
	 */
	public void addStaff(Staff staff, String deptId, HttpSession session) throws LoginDisabledException, NoPromissionException, StaffExistException;

	/**
	 * 做修改管理员操作时  查询出修改页面的管理员信息
	 */
	public Staff findById(String staffId) throws RequestParameterException;

	/**
	 * 修改员工管理员
	 */
	public void modifyStaff(Staff staff, String deptId) throws RequestParameterException;

}
