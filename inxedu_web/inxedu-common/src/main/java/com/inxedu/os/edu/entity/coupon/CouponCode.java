package com.inxedu.os.edu.entity.coupon;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 优惠卷编码类
 * @author Administrator
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CouponCode implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	/**
	 * 优惠卷id
	 */
    private Long couponId;
    /**
     * 优惠卷状态）1未使用2为已使用3过期4作废
     */
    private int status;
    /**
     * 订单request_id
     */
    private String requestId;
    /**
     * 订单id
     */
    private Long trxorderId;
    /**
     * 使用者id
     */
    private Long userId;
    /**
     * 优惠卷编码
     */
    private String couponCode;
    /**
     * 生成时间
     */
    private java.util.Date createTime;
    /**
     * 使用时间
     */
    private java.util.Date useTime;
    /**
     * 支付成功时间
     */
    private java.util.Date payTime;
    /**
     * 优惠券过期提示状态 INIT 未提示 ALREADY 已提示
     */
    private String remindStatus;


    /**
     * 优惠券所属者邮箱
     */
    private String email;
    /**
     * 优惠券所属者电话
     */
    private String mobile;
    /**
     * 优惠券所属者昵称
     */
    private String showName;
}
