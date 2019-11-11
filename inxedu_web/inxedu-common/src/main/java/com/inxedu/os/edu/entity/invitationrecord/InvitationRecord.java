package com.inxedu.os.edu.entity.invitationrecord;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author www.inxedu.com
 * @description edu_invitation_record
 */
@Data
public class InvitationRecord implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;

	/** id */
	private Long id;
	/** 邀请人的用户id */
	private Long userId;
	/** 被邀请人用户id */
	private Long invitationUserId;
	/** 返现金额 */
	private BigDecimal cashback;
	/** 添加时间 */
	private Date addTime;

}

