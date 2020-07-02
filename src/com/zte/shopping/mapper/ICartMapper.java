package com.zte.shopping.mapper;

import com.zte.shopping.entity.Item;

public interface ICartMapper {

    /**
     *  添加订单明细信息
     * @param item
     */
    void insertItem(Item item);
}
