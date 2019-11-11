package com.inxedu.os.edu.entity.common;

import com.inxedu.os.common.util.StringUtils;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author www.inxedu.com
 *
 */
@Data
public class Comment implements Serializable {
	private int commentId;//评论Id
	private int userId;//用户id
	private int pCommentId;//父级评论id // 为0 则是一级评论 不为0则是评论的回复
	private String content;//评论内容
	private Date addTime;//发送时间
	private int otherId;//相关Id
	private int praiseCount;//点赞数
	private int replyCount;//回复数
	private int type;//类型 1文章 2课程 3直播 4播放大厅课程章节评论

	private String email;//用户Email
	private String mobile;//用户Mobile
	private String userName;//用户昵称
	private String courseName;//课程名
	private int courseId;//课程id
	private String picImg;//用户头像
	private int commentNumber;//回复数
	private int limitNumber;//查询数
	private int beginNum;//limit开始数
	private String keyWord;
	private String order ="commentId";// commentId 降序  praiseCount 降序
	private String displayName;//前台展示用户名
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date beginCreateTime;//查询 开始时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date endCreateTime;//查询 结束时间
	public void setUserName(String userName){
		this.userName = userName;
		if(StringUtils.isNotEmpty(userName)){
			this.displayName = userName;
		}
	}
	public void setEmail(String email){
		this.email = email;
		if (StringUtils.isEmpty(userName)){
			if(StringUtils.isNotEmpty(email)){
				this.displayName = email;
			}
		}
	}
	public void setMobile(String mobile){
		this.mobile = mobile;
		if (StringUtils.isEmpty(userName)&&StringUtils.isEmpty(email)){
			if(StringUtils.isNotEmpty(mobile)){
				this.displayName = mobile;
			}
		}
	}

}
