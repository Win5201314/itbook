package com.inxedu.os.edu.entity.member;


import com.inxedu.os.edu.entity.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class MemberRecordDTO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 会员类型
     */
    private Long memberType;
    /**
     * 会员类型名称
     */
    private String memberTitle;
    /**
     * 首次开通时间
     */
    private java.util.Date beginDate;
    /**
     * 会员到期时间
     */
    private java.util.Date endDate;
    /**
     * 描述
     */
    private String description;
    /**
     * 状态 success 为打开  closed 为关闭
     */
    private String status;
    /**
     * 会员商品id
     */
    private Long memberSaleId;
    /**
     * 会员商品名称
     */
    private String memberSaleName;
    /**
     * 购买月数
     */
    private int month;

    private String account;
    private User user;
    /*订单id*/
    private long orderId;
    /**
     * 价格
     */
    private java.math.BigDecimal price;
    /*会员图片*/
    private String imageUrl;
    /*订单号*/
    private String orderNo;
    /*搜索关键字*/
    private String keyWord;
    /*按时间排序 asc升序 desc降序*/
    private String orderByTime;
}