package com.zte.shopping.service;

import com.zte.shopping.entity.Dept;
import com.zte.shopping.exception.DeptExistException;
import com.zte.shopping.exception.ProductTypeExistException;
import com.zte.shopping.exception.RequestParameterException;


import java.util.List;

public interface IDeptManagerService {

    /**
     * 查询所有部门信息
     */
    public List<Dept> findAll();

    public int addDept(String name, String duty,String deptNum,Integer fatherDeptId)throws RequestParameterException, DeptExistException;

    public Dept findById(String id)throws RequestParameterException;

     public int  modifyName(String id, String name, String duty)throws ProductTypeExistException;

    public int modifyStatus(String id, String status);

    public List<Dept> selectDeptAll();
}
