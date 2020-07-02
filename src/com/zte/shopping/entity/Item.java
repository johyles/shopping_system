package com.zte.shopping.entity;

import java.io.Serializable;


/**
 * 订单明细信息实体bean
 */
public class Item implements Serializable
{
	private static final long serialVersionUID = 1L;

	// 订单明细ID
	private Integer id;
	
	// 商品数量
	private Integer num;
	
	// 商品总价
	private Double price;
	
	// 商品ID
	private Product product;
	
	// 订单ID
	private Order order;
	
	public Item() 
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
	
	public Integer getNum()
	{
		return num;
	}
	
	public void setNum(Integer num) 
	{
		this.num = num;
	}
	
	public Double getPrice()
	{
		return price;
	}
	
	public void setPrice(Double price) 
	{
		this.price = price;
	}
	
	public Product getProduct()
	{
		return product;
	}
	
	public void setProduct(Product product) 
	{
		this.product = product;
	}
	
	public Order getOrder()
	{
		return order;
	}
	
	public void setOrder(Order order) 
	{
		this.order = order;
	}
	
}
