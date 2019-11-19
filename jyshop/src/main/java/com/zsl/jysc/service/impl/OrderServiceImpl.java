package com.zsl.jysc.service.impl;

import com.zsl.jysc.entity.Order;
import com.zsl.jysc.mapper.OrderMapper;
import com.zsl.jysc.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Order> selectOrderByUserIdAndOrderStatus(long userId, int orderStatus) {
        return orderMapper.selectOrderByUserIdAndOrderStatus(userId, orderStatus);
    }

    @Override
    public Order updateOrder(Order order) {
        return null;
    }
}
