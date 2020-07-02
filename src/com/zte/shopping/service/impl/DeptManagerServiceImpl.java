package com.zte.shopping.service.impl;

import com.zte.shopping.constant.StatusConstant;
import com.zte.shopping.entity.Dept;
import com.zte.shopping.entity.ProductType;
import com.zte.shopping.exception.DeptExistException;
import com.zte.shopping.exception.ProductTypeExistException;
import com.zte.shopping.exception.RequestParameterException;
import com.zte.shopping.mapper.IDeptManagerMapper;
import com.zte.shopping.service.IDeptManagerService;
import com.zte.shopping.util.ParameterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: shopping
 * @description:
 * @author: SkyCloud
 * @create: 2020-06-24 15:27
 **/
@Service
public class DeptManagerServiceImpl implements IDeptManagerService {

    @Autowired
    private IDeptManagerMapper iDeptManagerMapper;

    @Override
    public List<Dept> findAll() {
        return this.iDeptManagerMapper.selectAll();
    }

    @Override
    public int addDept(String name, String duty,String deptNum,Integer fatherDeptId)throws RequestParameterException, DeptExistException {
        if (ParameterUtil.isnull(name)||ParameterUtil.isnull(duty))
        {
            throw new RequestParameterException("不能为空");
        }

        Dept dept = iDeptManagerMapper.selectByName(name);

        if (dept != null)
        {
            throw new DeptExistException("已经存在");
        }

        return iDeptManagerMapper.insertDept(name,duty,deptNum,fatherDeptId, StatusConstant.DEPT_STATUS_ENABLE);
    }


    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Dept findById(String id) throws RequestParameterException {
        Dept dept;
        try {
            dept = iDeptManagerMapper.selectById(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            throw new RequestParameterException("商品类型Id只能是数字");
        }
        return dept;
    }

    @Override
    public int modifyName(String id, String name, String duty) throws ProductTypeExistException {
        // 1.当新的类型名称与原来的一致,则可以修改
        // 2.当新的类型名称在DB中不存在,则可以修改
        //Dept dept1 = iDeptManagerMapper.selectByName(name);
        //Dept dept2 = iDeptManagerMapper.selectById(Integer.parseInt(id));

        // !pt2.getName().equals(name)表示新的类型名称不是原来的,抛出异常
        // pt1 != null 表示DB中不存在该名称,抛出异常
       /* if (!dept1.getDeptName().equals(name) && dept2 != null)
        {
            throw new ProductTypeExistException("该类型名称已经存在!");
        }*/

        // 根据 商品类型的id  修改  商品类型名称
        return iDeptManagerMapper.updateName(Integer.parseInt(id), name,duty);
    }

    @Override
    public int modifyStatus(String id, String status) {
        int deptStatus = Integer.parseInt(status);

        if (deptStatus == StatusConstant.DEPT_STATUS_ENABLE) // 禁用   ---> 启用
        {
            deptStatus = StatusConstant.DEPT_STATUS_DISABLE;
        }else
        {
            deptStatus = StatusConstant.DEPT_STATUS_ENABLE;  // 启用  ---> 禁用
        }

        // 根据 商品类型的id  修改  商品类型状态
        return iDeptManagerMapper.updateStatus(Integer.parseInt(id), deptStatus);
    }

    public List<Dept> selectDeptAll() {
        return iDeptManagerMapper.selectDeptAll();
    }
}
