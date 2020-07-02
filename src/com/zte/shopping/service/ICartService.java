package com.zte.shopping.service;

import com.zte.shopping.util.CartVo;
import com.zte.shopping.exception.RequestParameterException;
import com.zte.shopping.exception.UserNotLoginException;

public interface ICartService {

    /**
     * 添加购物车
     */
    public void addCart(CartVo cartVo, Integer productId) throws RequestParameterException, UserNotLoginException;

    //修改数量
    public void modifyNum(String productId, String num, CartVo cartVo) throws RequestParameterException;


    // 根据  商品id  删除购物车中的明细信息

    public void removeByProductId(String productId, CartVo cartVo) throws RequestParameterException;
}
