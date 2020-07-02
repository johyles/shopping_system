package com.zte.shopping.service.impl;

import com.zte.shopping.constant.StatusConstant;
import com.zte.shopping.entity.Product;
import com.zte.shopping.util.ProductParameter;
import com.zte.shopping.exception.ProductTypeExistException;
import com.zte.shopping.exception.RequestParameterException;
import com.zte.shopping.mapper.IProductMapper;
import com.zte.shopping.service.IProductService;
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
 * @create: 2020-06-24 14:47
 **/
@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductMapper iProductMapper;


    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Product> findAll() {
        return this.iProductMapper.selectAll();
    }

    /**
     * 添加商品类型
     */
    public void addProduct(String product_name,Integer price,Integer product_type_id) throws RequestParameterException, ProductTypeExistException
    {
        if (ParameterUtil.isnull(product_name))
        {
            throw new RequestParameterException("商品名称不能为空");
        }

        Product selectProduct = iProductMapper.selectByName(product_name);

        if (selectProduct != null)
        {
            throw new ProductTypeExistException("该商品已经存在");
        }

        iProductMapper.insertProduct(product_name,price,product_type_id, StatusConstant.PRODUCT_TYPE_STATUS_ENABLE);
    }

    //修改
    public void modifyStatus(String id, String status)
    {
        int typeStatus = Integer.parseInt(status);

        if (typeStatus == StatusConstant.PRODUCT_TYPE_STATUS_DISABLE) // 禁用   ---> 启用
        {
            typeStatus = StatusConstant.PRODUCT_TYPE_STATUS_ENABLE;
        }else
        {
            typeStatus = StatusConstant.PRODUCT_TYPE_STATUS_DISABLE;  // 启用  ---> 禁用
        }

        // 根据 商品类型的id  修改  商品类型状态
        iProductMapper.updateStatus(Integer.parseInt(id), typeStatus);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Product findById(String id) throws RequestParameterException
    {
        Product type;
        try
        {
            type = iProductMapper.selectById(Integer.parseInt(id));
        } catch (NumberFormatException e)
        {
            throw new RequestParameterException("商品类型Id只能是数字");
        }
        return type;
    }

    /**
     * 修改 商品类型的名称
     * 修改时,商品类型的名称不能与数据库中其他名称重复
     * 如果商品类型的名称不是原来的且数据库总已经存在了,则抛出异常
     *
     */

    public void modifyName(String id,String product_name, Integer price,Integer type) throws ProductTypeExistException
    {
        // 1.当新的类型名称与原来的一致,则可以修改
        // 2.当新的类型名称在DB中不存在,则可以修改
        Product pt1 = iProductMapper.selectByName(product_name);
        Product pt2 = iProductMapper.selectById(Integer.parseInt(id));

        // !pt2.getName().equals(name)表示新的类型名称不是原来的,抛出异常
        // pt1 != null 表示DB中不存在该名称,抛出异常
        if (!pt2.getName().equals(product_name) && pt1 != null)
        {
            throw new ProductTypeExistException("该类型名称已经存在!");
        }

        // 根据 商品类型的id  修改  商品类型名称
        iProductMapper.updateName(Integer.parseInt(id), product_name,price,type);
    }

    /**
     *  根据条件组合查询商品列表(支持模糊查询)
     *  param	可选
     * 	minPrice	最低价格
     *  maxPrice	最高价格
     *  productTypeId	商品类型
     *  status		商品类型的状态		必带条件
     *  List<Product>  符合条件的商品列表
     */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Product> findProductFuzzyParamList(ProductParameter parameter)
    {
        // StatusConstant.PRODUCT_TYPE_STATUS_ENABLE 商品类型的状态为启用状态,值为1
        parameter.setStatus(StatusConstant.PRODUCT_TYPE_STATUS_ENABLE);

        List<Product> productList = iProductMapper.selectByParamList(parameter);

        return productList;
    }

}
