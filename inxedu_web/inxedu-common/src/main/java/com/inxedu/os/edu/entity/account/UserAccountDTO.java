package com.inxedu.os.edu.entity.account;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserAccountDTO implements Serializable{	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long userId;// 用户id
    private java.util.Date createTime;// 创建时间
    private java.util.Date lastUpdateTime;// 最后更新时间
    private java.math.BigDecimal balance;// 账户余额
    private java.math.BigDecimal forzenAmount;// 冻结金额
    private java.math.BigDecimal cashAmount;// 银行入账
    private java.math.BigDecimal vmAmount;// 课程卡入账
    private String accountStatus;// 账户状态
    private String backAmount;// 反现
    private String email;		//电子邮箱
    private String nickName;	//昵称
    private String userName;	//账号
    private String mobile;      //手机号

}
