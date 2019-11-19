package com.zsl.jysc.constant;

/**
 * 订单支付状态 支付时候要用
 */
public interface PayStatus {

    //未支付
    int nonPayment = 1;
    //支付中
    int paying = 2;
    //支付完成
    int payDone = 3;
}
