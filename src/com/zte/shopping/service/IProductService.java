package com.zte.shopping.service;

import com.zte.shopping.entity.Product;
import com.zte.shopping.util.ProductParameter;
import com.zte.shopping.exception.ProductTypeExistException;
import com.zte.shopping.exception.RequestParameterException;

import java.util.List;

public interface IProductService {

    /**
     * 查询所有商品信息
     */
    public List<Product> findAll();

    /**
     * 添加商品
     *
     * 判断商品是否存在
     * 若存在,抛出异常
     * 若不存在,则进行添加操作
     * 默认设置商品状态为启用
     *
     */
    public void addProduct(String product_name,Integer price,Integer product_type_id) throws RequestParameterException, ProductTypeExistException;

    /**
     * 改变商品类型的状态:启用/禁用
     *   当选择的商品类型为启用,则改为禁用
     *   当选择的商品类型为禁用,则改为启用
     */
    public void modifyStatus(String id, String status);

    /**
     * 根据 商品类型ID 查询   商品类型信息
     */
    public Product findById(String id) throws RequestParameterException;

    /**
     * 修改 商品类型的名称
     * 修改时,商品类型的名称不能与数据库中其他名称重复
     * 如果商品类型的名称不是原来的且数据库总已经存在了,则抛出异常
     *
     */
    public void modifyName(String id,String product_name, Integer price,Integer type) throws ProductTypeExistException;

    /**
     * 根据条件查询商品信息
     * @param      minPrice	                           最低价格
     * @param      maxPrice	                           最高价格
     * @param      productTypeId      商品类型
     */
    public List<Product> findProductFuzzyParamList(ProductParameter parameter);


}
