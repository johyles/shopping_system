package com.zte.shopping.service;

import java.util.List;
import com.zte.shopping.entity.ProductType;
import com.zte.shopping.exception.ProductTypeExistException;
import com.zte.shopping.exception.RequestParameterException;

public interface IProductTypeService 
{
	 /**
	  * 查询所有商品类型的列表
	  */
	 public List<ProductType> findAll();
	 
	
	 /**
	  * 添加商品类型
	  * 
	  * 判断商品类型是否存在
	  * 若存在,抛出异常
	  * 若不存在,则进行添加操作
	  * 默认设置商品状态为启用
	  * 
	  */
     public void addType(String name) throws RequestParameterException, ProductTypeExistException;

     
    /**
 	 * 改变商品类型的状态:启用/禁用
 	 *   当选择的商品类型为启用,则改为禁用
 	 *   当选择的商品类型为禁用,则改为启用
 	 */
	 public void modifyStatus(String id, String status);

	 /**
	  * 根据 商品类型ID 查询   商品类型信息
	  */
	 public ProductType findById(String id) throws RequestParameterException;

     /**
      * 修改 商品类型的名称
      * 修改时,商品类型的名称不能与数据库中其他名称重复
      * 如果商品类型的名称不是原来的且数据库总已经存在了,则抛出异常
      * 
      */
	 public void modifyName(String id, String name) throws ProductTypeExistException;


	 /**
	  * 查询所有有效的商品类型
	  */
	 public List<ProductType> findEnableProductTypeList();
}
