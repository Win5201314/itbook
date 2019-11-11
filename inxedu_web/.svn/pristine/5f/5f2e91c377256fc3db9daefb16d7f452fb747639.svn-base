package com.inxedu.os.edu.entity.card;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 *主卡信息返回实体
 * author www.inxedu.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MainCardDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;	
    private String name;
    private java.math.BigDecimal money;
    private int type;	//卡类型(1课程卡2充值卡)  
    private Long num;
    private java.util.Date beginTime;
    private java.util.Date endTime;
    private String remark;
    private String createUser;
    private java.util.Date createTime;
    private String courseName;
    private String status;//课程卡过期状态
    private int usedCardCode;//已经使用的课程卡编码数量
}
