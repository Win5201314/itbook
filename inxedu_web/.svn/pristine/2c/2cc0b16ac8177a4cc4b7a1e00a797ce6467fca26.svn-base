package com.inxedu.os.edu.entity.coupon;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class CouponLimit implements Serializable{
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long couponId;	//优惠券ID
    private Long courseId;	//课程id
    private int used;	//优惠券发放 0 不启用 1启用
    private String couponName;	//优惠券名称
    private int giveType;	//优惠券发放类型 1 普通发放 2 分享注册 3 购买课程
}