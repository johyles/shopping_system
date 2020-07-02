package com.zte.shopping.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.zte.shopping.constant.StatusConstant;
import com.zte.shopping.entity.ProductType;
import com.zte.shopping.exception.ProductTypeExistException;
import com.zte.shopping.exception.RequestParameterException;
import com.zte.shopping.mapper.IProductTypeMapper;
import com.zte.shopping.service.IProductTypeService;
import com.zte.shopping.util.ParameterUtil;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ProductTypeServiceImpl implements IProductTypeService 
{
	@Autowired
    private IProductTypeMapper productTypeDao;
    
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ProductType> findAll()
	{
		return productTypeDao.selectAll();
	}

	/**
	 * 添加商品类型
	 */
	public void addType(String name) throws RequestParameterException, ProductTypeExistException
	{
		if (ParameterUtil.isnull(name))
		{
			throw new RequestParameterException("商品类型名称不能为空");
		}
		
		ProductType selectType = productTypeDao.selectByName(name);
		
		if (selectType != null)
		{
			throw new ProductTypeExistException("该商品类型已经存在");
		}
		
		productTypeDao.insertProductType(name, StatusConstant.PRODUCT_TYPE_STATUS_ENABLE);
	}

	@Override
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
		productTypeDao.updateStatus(Integer.parseInt(id), typeStatus);
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ProductType findById(String id) throws RequestParameterException 
	{
		ProductType type;
		try 
		{
			type = productTypeDao.selectById(Integer.parseInt(id));
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
	@Override
	public void modifyName(String id, String name) throws ProductTypeExistException
	{
		 // 1.当新的类型名称与原来的一致,则可以修改
		 // 2.当新的类型名称在DB中不存在,则可以修改
		 ProductType pt1 = productTypeDao.selectByName(name);
		 ProductType pt2 = productTypeDao.selectById(Integer.parseInt(id));
		 
		 // !pt2.getName().equals(name)表示新的类型名称不是原来的,抛出异常
		 // pt1 != null 表示DB中不存在该名称,抛出异常
		 if (!pt2.getName().equals(name) && pt1 != null)
		 {
			 throw new ProductTypeExistException("该类型名称已经存在!");
		 }
		 
		 // 根据 商品类型的id  修改  商品类型名称
		 productTypeDao.updateName(Integer.parseInt(id), name);
	}

	/**
	 * 进入 添加商品页面  后 加载的 商品类型列表数据(已经禁用的商品不能显示在下拉列表中)
	 */
	@Override
	public List<ProductType> findEnableProductTypeList()
	{
		List<ProductType> typeList = productTypeDao.selectByProductTypeStatus(StatusConstant.PRODUCT_TYPE_STATUS_ENABLE);
		
		return typeList;
	}

}
