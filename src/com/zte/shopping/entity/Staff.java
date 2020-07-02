package com.zte.shopping.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 后台员工管理类
 * 
 * @author liyan
 *
 */
public class Staff implements Serializable
{
	private static final long serialVersionUID = 1L;

	public Staff(Integer staffId, String staffName, String loginName, String password, String phone, String email, Integer deptId, String role, Integer isValid, Date createDate, Integer createStaffId) {
		this.staffId = staffId;
		this.staffName = staffName;
		this.loginName = loginName;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.deptId = deptId;
		this.role = role;
		this.isValid = isValid;
		this.createDate = createDate;
		this.createStaffId = createStaffId;
	}

	// 后台员工ID
	private Integer staffId;
	
	// 后台员工的姓名
	private String staffName;
	
	// 账号
	private String loginName;
	
	// 密码
	private String password;
	
	// 手机号
	private String phone;
	
	// 邮箱
	private String email;

	//部门id
	private Integer deptId;

	// 部门
	private Dept dept;
	
	// 角色
	private String role;
	
	// 是否有效
	private Integer isValid;
	
	// 创建时间
	private Date createDate;

	//创建员工id
	private Integer createStaffId;



	// 创建员工
	private Staff createStaff;
	
	public Staff()
	{
		super();
	}
	
	public Integer getStaffId() 
	{
		return staffId;
	}
	
	public void setStaffId(Integer staffId) 
	{
		this.staffId = staffId;
	}
	
	public String getStaffName() 
	{
		return staffName;
	}
	
	public void setStaffName(String staffName) 
	{
		this.staffName = staffName;
	}
	
	public String getLoginName() 
	{
		return loginName;
	}
	
	public void setLoginName(String loginName) 
	{
		this.loginName = loginName;
	}
	
	public String getPassword() 
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public String getPhone()
	{
		return phone;
	}
	
	public void setPhone(String phone) 
	{
		this.phone = phone;
	}
	
	public String getEmail() 
	{
		return email;
	}
	
	public void setEmail(String email) 
	{
		this.email = email;
	}
	
	public Dept getDept() 
	{
		return dept;
	}
	
	public void setDept(Dept dept) 
	{
		this.dept = dept;
	}
	
	public String getRole()
	{
		return role;
	}
	
	public void setRole(String role) 
	{
		this.role = role;
	}
	
	public Integer getIsValid()
	{
		return isValid;
	}
	
	public void setIsValid(Integer isValid)
	{
		this.isValid = isValid;
	}
	
	public Date getCreateDate()
	{
		return createDate;
	}
	
	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}
	
	public Staff getCreateStaff() 
	{
		return createStaff;
	}
	
	public void setCreateStaff(Staff createStaff) 
	{
		this.createStaff = createStaff;
	}

	public Integer getDeptId() {
		return deptId;
	}


	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public Integer getCreateStaffId() {
		return createStaffId;
	}

	public void setCreateStaffId(Integer createStaffId) {
		this.createStaffId = createStaffId;
	}

	@Override
	public String toString() {
		return "Staff [staffId=" + staffId + ", staffName=" + staffName
				+ ", loginName=" + loginName + ", password=" + password
				+ ", phone=" + phone + ", email=" + email + ", dept=" + dept
				+ ", role=" + role + ", isValid=" + isValid + ", createDate="
				+ createDate + ", createStaff=" + createStaff + "]";
	}
	
	
}
