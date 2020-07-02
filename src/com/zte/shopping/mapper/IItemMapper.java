package com.zte.shopping.mapper;

import com.zte.shopping.entity.Item;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IItemMapper {

    //订单明细里的商品
    public List<Item> selectItem(@Param("orderNo") String orderNo);


    //插入商品-
   public void  insertItem(Item item);

}
