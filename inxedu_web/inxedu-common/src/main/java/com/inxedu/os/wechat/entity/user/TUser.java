package com.inxedu.os.wechat.entity.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户实体
 * @author lisheng
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TUser implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 838152101251081714L;
    private String id;			// 主键  UUID
    private String name;		// 用户姓名
    private int sex;			// 性别
    private String phone;		//电话
    private String password;		//密码
    private String qq;			//qq
    private String weixin;		//微信
    private String email;		// 邮箱
    private String schoolId;	// 学校id
    private String tagId;		// 子标签，用,隔开
    private String openid;   //微信小程序   用户唯一标识
}
