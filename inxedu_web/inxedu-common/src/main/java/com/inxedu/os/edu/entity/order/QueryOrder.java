package com.inxedu.os.edu.entity.order;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author www.inxedu.com
 *
 */
@Data
public class QueryOrder implements Serializable {
	private String keyWord;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date beginCreateTime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date endCreateTime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date beginPayTime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date endPayTime;
	private int userId;
	private String states;
	private String payType;
	private String showName;
	private String courseName;
	/*订单类型 COURSE课程 MEMBER会员 ACCOUNT账户充值*/
	private String orderType;
	/*判断是否删除会员*/
	private String closeMember;
}
