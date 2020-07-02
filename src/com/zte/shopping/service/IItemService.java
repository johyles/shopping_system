package com.zte.shopping.service;

import com.zte.shopping.entity.Item;

import java.util.List;

public interface IItemService {
    public List<Item> selectItem(String orderNo);
}
