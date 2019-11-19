package com.zsl.jysc.constant;

/**
 * 订单状态
 */
public interface OrderStatus {

    //待付款
    int waitForPay = 0;
    //待收货
    int waitForReceiving = 1;
    //待评价
    int waitForComment = 2;
    //售后
    int afterSale = 3;
}
