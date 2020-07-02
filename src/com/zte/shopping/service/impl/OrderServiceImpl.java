package com.zte.shopping.service.impl;

import com.zte.shopping.util.CartVo;
import com.zte.shopping.constant.DictConstant;
import com.zte.shopping.entity.Item;
import com.zte.shopping.entity.Order;
import com.zte.shopping.entity.Sequence;
import com.zte.shopping.entity.User;
import com.zte.shopping.mapper.IItemMapper;
import com.zte.shopping.mapper.IOrderMapper;
import com.zte.shopping.mapper.ISequenceMapper;
import com.zte.shopping.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private IOrderMapper orderMapper;
    @Autowired
    private IItemMapper itemMapper;
    @Autowired
    private ISequenceMapper sequenceMapper;

    @Override
    public List<Order> selectOrderByUser(Integer userid) {
        return orderMapper.selectOrderByUser(userid);
    }

    @Override
    public String createOrder(User user, CartVo cartVo) {
        Order order = new Order();
        order.setPrice(cartVo.getPrice());
        order.setUser(user);

        // 生成订单号
        // 订单号生成规则:  DD + 年月日  + 序列化
        // 根据订单号前缀去Sequence表中查询对应信息
        // 其中  DictConstant.ORDER_NO_PREFIX 表示   订单编号前缀
        Sequence selectSequence = sequenceMapper.selectByName(DictConstant.ORDER_NO_PREFIX);

        // 若不存在,则新建
        if (selectSequence == null)
        {
            Sequence sequence = new Sequence();

            sequence.setName(DictConstant.ORDER_NO_PREFIX);
            sequence.setValue(DictConstant.ORDER_NO_SEQUENCE_MIN);

            sequenceMapper.insertSequence(sequence);

            order.setNo(DictConstant.ORDER_NO_PREFIX + new SimpleDateFormat("yyyyMMdd").format(new Date()) + sequence.getValue());
        }else
        {
            // 若存在,则修改
            String value = selectSequence.getValue();

            if (DictConstant.ORDER_NO_SEQUENCE_MAX.equals(value))
            {
                // 如果原来的序列号达到了最大值
                // 则初始化为最小值,从头开始
                value = DictConstant.ORDER_NO_SEQUENCE_MIN;
            }else
            {
                // 将原来的序列号 + 1
                value = String.format("%06d", Integer.parseInt(value) + 1);
            }

            selectSequence.setValue(value);

            sequenceMapper.updateSquenceValue(DictConstant.ORDER_NO_PREFIX, value);

            order.setNo(DictConstant.ORDER_NO_PREFIX + new SimpleDateFormat("yyyyMMdd").format(new Date()) + value);
        }


        // 生成订单信息
        orderMapper.insertOrder(order);

        // 对该订单名下的所有的明细信息进行保存
        // 购物车中每一条明细对应一条数据

        List<Item> items = cartVo.getItems();
        for (Item item : items)
        {
            item.setOrder(order);

            itemMapper.insertItem(item);
        }

        return order.getNo();
    }

}
