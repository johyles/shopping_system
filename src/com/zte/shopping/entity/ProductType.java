package com.zte.shopping.entity;

import java.io.Serializable;

/**
 * 商品类型
 * 
 * @author liyan
 *
 */
public class ProductType implements Serializable 
{
	/**
	 * 序列化版本号
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	// 商品类型
	private String name;   
	
	// 商品类型的状态   0禁用   1启用
	private Integer status;
	
	public ProductType()
	{
		super();
	}
	
	public Integer getId()
	{
		return id;
	}
	
	public void setId(Integer id)
	{
		this.id = id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public Integer getStatus() 
	{
		return status;
	}
	
	public void setStatus(Integer status)
	{
		this.status = status;
	}
}
