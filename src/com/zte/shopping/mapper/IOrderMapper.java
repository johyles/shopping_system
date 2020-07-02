package com.zte.shopping.mapper;

import com.github.pagehelper.ISelect;
import com.zte.shopping.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IOrderMapper {
    public List<Order> selectOrderByUser(@Param("userid") Integer userid);

    /**
     * 生成订单信息
     */
    public void insertOrder(Order order);

    public List<Order> selectOrderByNo(@Param("no") String no);
}

