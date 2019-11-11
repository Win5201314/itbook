package com.inxedu.os.edu.entity.order;

import com.inxedu.os.edu.entity.teacher.Teacher;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class TrxorderDetail implements Serializable{
    /**
     * 流水
     */
    private static final long serialVersionUID = 7081379317366445288L;
    private Long id;
    private Long userId;//用户id
    private Long courseId;//相关联的课程id（前台快照）
    private Long trxorderId;//交易订单ID
    private Long membertype;//会员观看类型（前台快照）
    private int losetype;//有效期类型（前台快照）
    private java.util.Date loseAbsTime;//订单过期时间段（前台快照）
    private String loseTime;//订单过期时间点（前台快照）
    private java.util.Date authTime;//课程过期时间
    private java.util.Date createTime;//下单时间
    private java.util.Date payTime;//支付成功时间
    private java.math.BigDecimal sourcePrice;//原价格（前台快照）
    private java.math.BigDecimal currentPrice;//销售价格（前台快照）
    private String courseName;//课程名称（前台goods快照）
    private String authStatus;//课程状态（INIT，SUCCESS，REFUND，CLOSED，LOSED,DELETE(个人删除课程)）
    private String requestId;//订单请求号
    private String description;//描述
    private Long version=1L;//乐观锁版本号
    private java.util.Date lastUpdateTime;//最后更新时间
    private List<Teacher> teacherList;//最后更新时间
    private String delStatus;//个人订单中心删除课程
    private java.util.Date beginTime;//商品的使用开始时间
    private String trxorderType;//订单类型 COURSE课程 MEMBER会员
    //辅助属性
    /**课程标题*/
    private String courseTitle;
    /**课程图片*/
    private String courseLogo;
}
