package com.inxedu.os.edu.entity.livegensee;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author www.inxedu.com
 * @description 展视互动直播
 */
@Data
public class LiveGensee implements Serializable {
	private static final long serialVersionUID = 3148176768559230877L;

	/** id */
	private Long id;
	/** 对应的直播id */
	private Long liveId;
	/** 实时课堂主题（长度：1-250） */
	private String subject;
	/** 老师加入口令（长度：6-15）（会自动生 成随机数） */
	private String teacherToken;
	/** Web端学生加入口令（长度：最大 15） */
	private String studentToken;
	/** 客户端学生加入口令 */
	private String studentClientToken;
	/** 开始日期 */
	private Date startDate;
	/** 失效时间 */
	private Date invalidDate;
	/** 助教加入口令（长度：6-15）（会自动生 成随机数） */
	private String assistantToken;
	/** 老师介绍 */
	private String speakerInfo;
	/** 课程介绍 */
	private String scheduleInfo;
	/** 是否支持Web端学生加入,值为true或者 false。默认值为true */
	private String webJoin;
	/** 是否支持客户端端学生加入,值为 true或 者 false。默认值为true */
	private String clientJoin;
	/** 课堂介绍 */
	private String description;
	/** 课堂持续时长（单位为分钟） */
	private Long duration;
	/** Web 端学生界面设置(1 是三分屏，2是 文档/视频为主，3是两分屏，4：互动增 加) */
	private int uiMode;
	/** 三分屏颜色选择（blue, default, green）， 默认是default */
	private String uiColor;
	/** 0:大讲堂，1：小班课，默认值：0 */
	private int scene;
	/** uiMode等于 2时候，设置是否显示小窗 口。 默认为false */
	private Boolean uiWindow;
	/** uiMode等于 2时候，设置是否视频为主。 默认为false */
	private Boolean uiVideo;
	/** 是否允许web升级到客户端 */
	private Boolean upgrade;
	/** true:表示密码是经过加密的。 */
	private String sec;
	/** true:表示实时的，false：表示非实时的， 默认是false */
	private Boolean realtime;
	/** 课堂最大并发数。 只有站点开启指定并发数功能，才能够设 置。否则即使传入数据也无效。 */
	private Long maxAttendees;
	/** 返回实时课堂ID */
	private String genseeId;
	/** 返回课堂编号 */
	private String number;
	/** 返回 老师和助教加入URL */
	private String teacherJoinUrl;
	/** 学员加入URL */
	private String studentJoinUrl;

}