package com.inxedu.os.edu.entity.member;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员服务
 * @author Administrator
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MemberType implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Long id;
    /**
     * 服务名称
     */
    private String title;
    /**
     * 状态0正常1删除
     */
    private int status;
    /**
     * 状态0正常1删除
     */
    private String imageUrl;
    /*用户该类型会员的结束时间*/
    private Date endTime;
}

