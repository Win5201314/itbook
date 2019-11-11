package com.inxedu.os.edu.entity.course;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @ClassName com.inxedu.os.inxedu.entity.course.CourseStudyhistory
 * @description 记录播放记录
 * @author www.inxedu.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CourseStudyhistory implements Serializable {
	/**
     * 
     */
    private static final long serialVersionUID = 5434482371608343070L;
    private Long id;
    private Long userId;//播放次数
    private Long courseId;//播放次数
    private Long kpointId;//播放次数
    private Long playercount;//播放次数
    private String courseName;//课程名称
    private String kpointName;//节点名称
    private String databack;//playercount小于20时记录,备注观看的时间，叠加
    private java.util.Date updateTime;//更新时间
    private String logo;	//图片
    private String teacherName;	//教师名称

    private String userShowName;//用户名
    private String userMobile;//用户手机
    private String userName;//用户账号
    private String userEmail;//用户邮箱
    private String userImg;//用户头像
    private String keyword;//姓名手机邮箱昵称查询关键字
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date beginCreateTime;//查询 开始添加时间
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endCreateTime;//查询 结束添加时间
}
