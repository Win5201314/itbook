package com.inxedu.os.edu.entity.kpoint;

import com.inxedu.os.edu.entity.teacher.Teacher;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author www.inxedu.com
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CourseKpointDto extends CourseKpoint{
	private static final long serialVersionUID = -5911245722257969805L;
	private String teacherName;
	private String courseName;//课程名
	private String courseLogo;//课程图片
	private int courseBuycount;//销售数量
	private BigDecimal currentPrice;//课程销售价格（实际支付价格）设置为0则可免费观看
	private Date courseLiveBeginTime;//课程直播开始时间
	private Date courseLiveEndTime;//课程直播结束时间
	private List<Teacher> teacherList;//该课程 下的老师list
}
