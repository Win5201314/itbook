package com.inxedu.os.edu.entity.user;

import com.inxedu.os.common.util.WebUtils;
import com.inxedu.os.edu.entity.userprofile.UserProfile;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 学员
 * @author www.inxedu.com
 */
@Data
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	/**学员ID*/
	private int userId;
	/**手机号*/
	private String mobile;
	/**邮箱*/
	private String email;
	/**密码*/
	private String password;
	/**学员名*/
	private String userName;
	/**显示名（昵称）*/
	private String showName;
	/**性别 1男	2女*/
	private int sex;
	/**年龄*/
	private int age;
	/**注册时间*/
	private Date createTime;
	/**状态 1正常	2冻结*/
	private int isavalible;
	/**用户头像*/
	private String picImg;
	/**个人中心个性图片URL*/
	private String bannerUrl;
	/** 站内信未读消息数*/
	private int msgNum;
	/**系统自动消息未读消息数*/
	private int sysMsgNum;
    /**上传统计系统消息时间*/
    private Date lastSystemTime;
	private String loginAccount;//登录账号
	private String registerFrom;//注册来源
	private String invitationCode;//邀请码
	private long loginTimeStamp;//登录时的当前时间戳
	private String courseName;//课程名称
	private String displayName;//前台展示用户名
	private List<UserProfile> userProfileList;//第三方登录类型

	public void setUserName(String userName){
		this.userName = WebUtils.replaceTagHTML(userName);
	}
	public void setShowName(String showName){
		this.showName = WebUtils.replaceTagHTML(showName);
	}

	/**手机号不为空*/
	private String mobileNotNull;
	/**邮箱不为空*/
	private String emailNotNull;
}