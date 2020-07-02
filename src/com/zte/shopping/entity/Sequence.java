package com.zte.shopping.entity;

import java.io.Serializable;

public class Sequence implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	// 商品编号前缀: SP
	private String name;
	
	// 商品编号的序列号最小值为000001 
	// 商品编号的序列号最大值为999999
	private String value;
	
	public Sequence() 
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
	
	public String getValue()
	{
		return value;
	}
	
	public void setValue(String value)
	{
		this.value = value;
	}
	
}
