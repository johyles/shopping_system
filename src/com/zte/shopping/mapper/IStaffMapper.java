package com.zte.shopping.mapper;

import com.zte.shopping.entity.Staff;

import java.util.List;

public interface IStaffMapper 
{

    //管理员修改部分
    public int modifyStaff(Staff staff);

    /**
     * 动态  模糊  查询   管理员信息
     */
    public List<Staff> selectFuzzyByParams(Staff staff);

    /**
     * 根据账号查询员工信息
     */
    public Staff selectByLoginName(String loginName);

    /**
     * 添加管理员
     */
    public void insertStaff(Staff staff);

    /**
     * 做修改管理员操作时  查询出修改页面的管理员信息
     */
    public Staff selectById(int parseInt);

    /**
     * 修改管理员
     */
    public void updateStaff(Staff staff);
}
