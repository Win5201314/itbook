package com.inxedu.os.edu.entity.user;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author www.inxedu.com
 *
 */
@Data
public class QueryUser implements Serializable {
	private int isavalible;
	private String keyWord;
	private String registerFrom;//注册来源
	private  String username;//用戶賬號
	private  String nameEmailMobile;//用戶賬號 邮箱 或者手机

	private String loginAccount;//登录账号
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date beginCreateTime;//查询 开始注册时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date endCreateTime;//查询 结束注册时间
	private String invitationCode;//邀请码
}
