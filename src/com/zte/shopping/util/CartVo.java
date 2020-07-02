package com.zte.shopping.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.zte.shopping.entity.Item;

/**
 * "我的购物车"页面中的信息,存放在session中, 不会经常改变
 * 
 * Cart 
 * 
 * Cart创建时机
 *    用户登录成功时,创建一个购物车,将购物车放入到session中(而不是放到DB中)
 * Cart销毁
 *   结算  或者  注销时Cart销毁
 *   
 * Cart中属性
 *   (1)订单明细的集合 --> List<Item> items 或者  Map<Integer,Item> items
 *   (2)price(商品单价,这个信息随时可能发生改变)
 *   
 * Cart中方法
 *      add(Integer id)
 *      removeByProductId(Integer id)
 *      removeByProductIds(Integer id)
 *      clear()
 *      modify(Integer id,Integer num)  根据商品ID改变商品的数量
 * 
 * 订单及订单明细信息
 * VO:凡是涉及到多表的查询就要用用VO
 *    为了简化开发我们把在页面上做查询的时候所到的多个表中的字段,
 *    此时我们可以把这些用到的多个字段当成一个对象
 *    放在一个VO对象中
 *  
 */
public class CartVo implements Serializable
{
	private static final long serialVersionUID = 1L;

	// 订单明细的集合
	private List<Item> items = new ArrayList<Item>();
	
	// !!! 注意这里的总价   要加默认值  不写默认值就是null  
	private Double price = 0.0;
	
	public List<Item> getItems()
	{
		return items;
	}
	
	public void setItems(List<Item> items)
	{
		this.items = items;
	}
	
	public Double getPrice() 
	{
		return price;
	}
	
	public void setPrice(Double price) 
	{
		this.price = price;
	}

}
