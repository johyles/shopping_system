package com.zte.shopping.service.impl;

import com.zte.shopping.entity.Item;
import com.zte.shopping.mapper.IItemMapper;
import com.zte.shopping.service.IItemService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements IItemService {

    @Autowired
    IItemMapper itemMapper;

    //订单明细里的商品（只能通过订单号）--包括数量，数数记录数多少条就可以
    @Override
    public List<Item> selectItem(String orderNo) {
        return itemMapper.selectItem(orderNo);
    }
}
