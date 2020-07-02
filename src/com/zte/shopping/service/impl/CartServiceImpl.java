package com.zte.shopping.service.impl;

import com.zte.shopping.util.CartVo;
import com.zte.shopping.entity.Item;
import com.zte.shopping.entity.Product;
import com.zte.shopping.exception.RequestParameterException;
import com.zte.shopping.exception.UserNotLoginException;
import com.zte.shopping.mapper.IProductMapper;
import com.zte.shopping.service.ICartService;
import com.zte.shopping.util.ParameterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: shopping
 * @description:
 * @author: SkyCloud
 * @create: 2020-07-01 09:28
 **/
@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private IProductMapper iProductMapper;


    /**
     * 添加购物车
     */
    public void addCart(CartVo cartVo, Integer productId) throws RequestParameterException, UserNotLoginException
    {
        if (ParameterUtil.isnull(String.valueOf(productId)))
        {
            throw new RequestParameterException("商品id不能为空");
        }

        if (cartVo == null)
        {
            throw new UserNotLoginException("请先登录");
        }

        Product product = iProductMapper.selectById(productId);

        // 操作购物车
        // 1. 对于总价
        // 总价  = 原来的总价  + 商品的单价
        cartVo.setPrice(cartVo.getPrice() + product.getPrice());

        // 2. 对于明细
        //    判断购物车中是否存在该商品
        for (Item item : cartVo.getItems())
        {
            // (1) 购物车中存在该商品
            if (item.getProduct().getProductId() == product.getProductId())
            {
                // 数量 = 原来的数量 + 1
                item.setNum(item.getNum() + 1);

                // 订单明细价钱 = 原来的价钱  + 商品的单价
                item.setPrice(item.getPrice() + product.getPrice());

                return;  // 不能忘!!!!!!!!!!!!!!!
            }
        }

        //	    (2) 购物车中不存在该商品
        //         创建一个新的明细Item, 该Item中数量为1,总价为商品的单价
        Item item = new Item();
        item.setNum(1);
        item.setPrice(product.getPrice());
        item.setProduct(product);

        // 将新的订单明细Item加入到购物车中
        cartVo.getItems().add(item);
    }

    //修改商品数量
    @Override
    public void modifyNum(String productId, String num, CartVo cartVo) throws RequestParameterException
    {
        if (ParameterUtil.isnull(productId))
        {
            throw new RequestParameterException("商品id不能为空");
        }

        if(ParameterUtil.isnull(num))
        {
            throw new RequestParameterException("商品数量不能为空");
        }

        Product product = iProductMapper.selectById(Integer.parseInt(productId));


        // 做修改操作时, 商品的信息肯定是存在于购物车中的
        // 遍历购物车的数量, 找到需要修改的明细
        // 改什么?
        // 1.购物车总价
        // 2.明细的数量
        // 3.明细的总价
        for (Item item : cartVo.getItems())
        {
            if (item.getProduct().getProductId() == product.getProductId())
            {
                // 总价   =  原来购物车的总价  - 原来的明细总价   + 新的明细总价 ;  分成 (1) 和 (2)

                // (1) 总价1 = 原来购物车的总价  - 原来的明细总价
                cartVo.setPrice(cartVo.getPrice() - item.getPrice());

                // 数量 = num
                item.setNum(Integer.parseInt(num));

                // 新的明细总价 = 商品的单价 * 新的数量
                item.setPrice(product.getPrice() * Integer.parseInt(num));

                //  (2) 最终总价 = 总价1 + 新的明细总价;
                cartVo.setPrice(cartVo.getPrice() + item.getPrice());
            }
        }

    }


    /**
     * 我的购物车" 中  根据商品ID  删除  "我的购物车" 中的某个商品信息
     */
    public void removeByProductId(String productId, CartVo cartVo) throws RequestParameterException
    {
        if (ParameterUtil.isnull(productId))
        {
            throw new RequestParameterException("商品id不能为空");
        }

        // 删除操作时,改变了购物车中的哪些数据?
        // 1.明细变了
        // 2.购物车总价变了
        for (int i = cartVo.getItems().size() - 1; i >= 0; i--)
        {
            Item item = cartVo.getItems().get(i);

            if (item.getProduct().getProductId() == Integer.parseInt(productId))
            {
                // 总价  = 原来的总价  - 选中的明细的总价
                cartVo.setPrice(cartVo.getPrice() - item.getPrice());

                // 明细: 移除所选中的那一个明细信息
                cartVo.getItems().remove(item);
            }
        }

    }

}
