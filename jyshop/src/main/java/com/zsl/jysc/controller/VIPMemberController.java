package com.zsl.jysc.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "会员模块")
@RestController
public class VIPMemberController {

}
//1.购物车 2.商品 3.主页 4.会员(只做一个添加)    5.订单(微信支付 + RabbitMQ)
