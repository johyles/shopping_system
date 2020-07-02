package com.zte.shopping.mapper;

import java.util.List;
import com.zte.shopping.entity.ProductType;

public interface IProductTypeMapper 
{
	/**
	 * 查询所有商品类型信息
	 */
    public List<ProductType> selectAll();

    /**
     * 根据名称查询对应的商品类型信息
     */
	public ProductType selectByName(String name);

	/**
	 * 添加商品类型信息
	 */
	public void insertProductType(String name, int status);

	/**
	 * 根据 商品类型的id  修改  商品类型状态
	 */
	public void updateStatus(int parseInt, int typeStatus);

	/**
	 * 根据 商品类型的id  查询  商品类型信息
	 */
	public ProductType selectById(int parseInt);

	/**
	 * 根据 商品类型的id  修改  商品类型名称
	 */
	public void updateName(int parseInt, String name);

	/**
	 * 进入  添加商品页面 后 加载的 商品类型列表数据(已经禁用的商品不能显示在下拉列表中)
	 */
	public List<ProductType> selectByProductTypeStatus(int productTypeStatusEnable);
			
}
