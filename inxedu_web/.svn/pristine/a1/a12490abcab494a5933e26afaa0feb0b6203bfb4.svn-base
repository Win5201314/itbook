package com.inxedu.os.edu.entity.course;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author www.inxedu.com
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QueryCourse implements Serializable{
    private static final long serialVersionUID = 4550896941810655734L;
    private Long courseId;
    private int subjectId;
    private String courseName;
    private int isavaliable;
    private int teacherId;
    private int count;
    private String order;
	private String isFree;//查询免费课程
	private String sellType;//课程类型：COURSE(课程) LIVE(直播) PACKAGE(套餐)
	private String sellType_cou_pag;//用于查询课程类型：COURSE(课程) + PACKAGE(套餐)

    private int queryLimit;//查询条数
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date beginCreateTime;//查询 开始添加时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date endCreateTime;//查询 结束添加时间
    private int userId;//用户id
    private String isoverdue;//是否过期
    private String sellTypeLive;//<!-- 查询时排除直播的课程-->
    private String sellTypePackage;//<!-- 查询时排除套餐的课程-->
    private Long memberTypeId;//会员类型id
    private String isLiveing;//<!-- 查询正则直播的-->
    private Date queryTime;//<!-- 查询时间-->
}
