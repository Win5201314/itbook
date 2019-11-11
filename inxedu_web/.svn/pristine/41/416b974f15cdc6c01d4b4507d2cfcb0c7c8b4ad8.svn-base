package com.inxedu.os.edu.entity.course;

import com.inxedu.os.edu.entity.kpoint.CourseKpoint;
import com.inxedu.os.edu.entity.teacher.Teacher;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author www.inxedu.com
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Course implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int courseId;
    private String courseName;//课程名称
    private int isavaliable;//1 正常　２　下架   3删除
    private int subjectId;//课程专业ID
    private Date addTime;//课程添加时间
    private java.math.BigDecimal sourcePrice;//课程原价格（只显示）
    private java.math.BigDecimal currentPrice;//课程销售价格（实际支付价格）设置为0则可免费观看
    private String title;//课程简介
    private String context;//课程详情
    private int lessionNum;//课时
    private String logo;//课程图片
    private Date updateTime;
    private int pageBuycount;//销售数量
    private int pageViewcount;//浏览数量
    private Date endTime;//有效结束时间
    private int loseType;//有效期类型，0：到期时间，1：按天数
    private String loseTime;//有效期:商品订单过期时间点
    private String sellType;//课程类型：COURSE(课程) LIVE(直播)
    private Date liveBeginTime;//直播开始时间
    private Date liveEndTime;//直播结束时间
    private Date nearestLiveBeginTime;//下次直播开始时间
    private Date nearestLiveEndTime;//下次直播结束时间

    private String studyPercent;//课程学习进度百分比
    private List<Teacher> teacherList;//该课程 下的老师list
    private List<CourseDto> courseList;//套餐下的 课程list
    private List<CourseKpoint> courseKpointList;//课程下的章节list
    private CourseStudyhistory courseStudyhistory;//学习记录

}
