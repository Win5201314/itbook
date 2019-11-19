package com.zsl.jysc.service;

import com.zsl.jysc.entity.Order;

import java.util.List;

public interface IOrderService {

    Order addNewOrder(Order order);
    List<Order> selectOrderByUserIdAndOrderStatus(long userId, int orderStatus);
    Order updateOrder(Order order);
}
