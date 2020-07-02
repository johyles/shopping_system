package com.zte.shopping.service;

import com.zte.shopping.util.CartVo;
import com.zte.shopping.entity.Order;
import com.zte.shopping.entity.User;

import java.util.List;

public interface IOrderService {
    public List<Order> selectOrderByUser(Integer userid);


     //生成订单信息以及该订单名下的所有明细信息,添加一个订单,添加多个明细
    public String createOrder(User user, CartVo cartVo);

}
