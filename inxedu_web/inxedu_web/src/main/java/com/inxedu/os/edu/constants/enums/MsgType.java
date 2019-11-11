package com.inxedu.os.edu.constants.enums;

/**
 * 消息开关类型
 * @author www.inxedu.com
 */
public enum MsgType {
    register,//学员注册消息
    outsideRegisterModel,//第三方第一次登陆发送信息
    auditingOrder,//后台开通订单
    cancleOrder,//后台取消订单
    recoveryOrder,//后台恢复订单
    refundOrder,//后台订单退款
    delayOrder,//后台订单详情延期
    closeCourseOrder,//后台订单详情课程关闭
    giveCouponCode,//后台赠送优惠券
    adminQuestionAccept,//后台问答 采纳最佳答案
    frontQuestionAccept,//前台问答 采纳最佳答案
    paySuccess,//订单回调 订单支付成功
    giveCourse,//后台赠送课程
    timeOverMsg,//课程过期提示
    timeOverCouponCodeMsg,//优惠券过期提示
    drawMoney//提现
}
