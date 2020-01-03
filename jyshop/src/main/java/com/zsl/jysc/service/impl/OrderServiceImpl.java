package com.zsl.jysc.service.impl;

import com.zsl.jysc.entity.Order;
import com.zsl.jysc.mapper.OrderMapper;
import com.zsl.jysc.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Order addNewOrder(Order order) {
        return null;
    }

    @Override
    @Transactional
    public List<Order> selectOrderByUserIdAndOrderStatus(long userId, int orderStatus) {
        return orderMapper.selectOrderByUserIdAndOrderStatus(userId, orderStatus);
    }

    @Override
    public Order updateOrder(Order order) {
        return null;
    }

    //订单号生成规则  1--
    //select for update 2
    //@Transactional 2
    //SOA架构  1--
    //多表之间的关系 @manyToOne 2
    //Kotlin--

    //40万亿  14亿

    //数据库语句实战 + 高并发 后面再看
}
