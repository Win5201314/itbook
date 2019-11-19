package com.zsl.jysc.controller;

import com.zsl.jysc.common.ServerResponse;
import com.zsl.jysc.common.controller.BaseController;
import com.zsl.jysc.common.page.TableDataInfo;
import com.zsl.jysc.constant.OrderStatus;
import com.zsl.jysc.constant.PayStatus;
import com.zsl.jysc.entity.Order;
import com.zsl.jysc.exception.VerifyTokenException;
import com.zsl.jysc.service.impl.OrderServiceImpl;
import com.zsl.jysc.service.impl.UserServiceImpl;
import com.zsl.jysc.util.JWTUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Api(value = "订单模块")
@RestController
public class OrderController extends BaseController {

    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private UserServiceImpl userService;

    @ApiOperation(value = "查询订单")
    @PostMapping("/selectOrder")
    public ServerResponse<TableDataInfo> selectOrder(@ApiParam(name = "token", value = "口令", required = true) String token,
                                                       @ApiParam(name = "pageNum", value = "当前记录起始索引,前端要自己记住再传递过来", required = true) Integer pageNum,
                                                       @ApiParam(name = "pageSize", value = "每页显示记录数,默认10条", required = false, defaultValue = "10") Integer pageSize,
                                                       @ApiParam(name = "isAsc", value = "降序", required = false, defaultValue = "desc") String isAsc,
                                                       @ApiParam(name = "orderByColumn", value = "根据主键", required = false, defaultValue = "id") String orderByColumn,
                                                       @ApiParam(name = "orderStatus", value = "订单状态", required = true) Integer orderStatus) throws VerifyTokenException {
        Map<String, Object> map = JWTUtil.verifyToken(token);
        if (map == null) throw new VerifyTokenException("令牌验证失败");
        Long userId = (Long) map.get("userId");
        String openid = (String) map.get("openid");
        if (userService.isExistOpenidAndUserId(openid, userId)) {
            //分页查询 根据userId查询
            //设置分页信息
            startPage();
            //查询数据
            List<Order> orderList = orderService.selectOrderByUserIdAndOrderStatus(userId, orderStatus);
            //包装查询的数据
            TableDataInfo tableDataInfo = getDataTable(orderList);
            return ServerResponse.createBySuccess(tableDataInfo);
        } else {
            throw new VerifyTokenException("令牌验证失败");
        }
    }

    @ApiOperation(value = "添加订单")
    @PostMapping("/addNewOrder")
    public ServerResponse<Order> addNewOrder(@ApiParam(name = "token", value = "口令", required = true) String token,
                                             @ApiParam(name = "order", value = "订单", required = true) Order order) throws VerifyTokenException {
        Map<String, Object> map = JWTUtil.verifyToken(token);
        if (map == null) throw new VerifyTokenException("令牌验证失败");
        Long userId = (Long) map.get("userId");
        String openid = (String) map.get("openid");
        if (userService.isExistOpenidAndUserId(openid, userId)) {
            order.setUserId(userId);
            //设置状态信息
            order.setOrderStatus(OrderStatus.waitForPay);
            order.setPayStatus(PayStatus.nonPayment);
            //添加订单
            Order order1 = orderService.addNewOrder(order);
            return ServerResponse.createBySuccess(order1);
        } else {
            throw new VerifyTokenException("令牌验证失败");
        }
    }

    //更新订单  订单状态 订单支付状态
    @ApiOperation(value = "更新订单")
    @PostMapping("/updateOrder")
    public ServerResponse<Order> updateOrder(@ApiParam(name = "token", value = "口令", required = true) String token,
                                             @ApiParam(name = "order", value = "订单", required = true) Order order) throws VerifyTokenException {
        Map<String, Object> map = JWTUtil.verifyToken(token);
        if (map == null) throw new VerifyTokenException("令牌验证失败");
        Long userId = (Long) map.get("userId");
        String openid = (String) map.get("openid");
        if (userService.isExistOpenidAndUserId(openid, userId)) {
            order.setUserId(userId);
            Order order1 = orderService.updateOrder(order);
            return ServerResponse.createBySuccess(order1);
        } else {
            throw new VerifyTokenException("令牌验证失败");
        }
    }
    //删除订单 客户端不做，订单要永久保留
}
