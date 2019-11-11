package com.inxedu.os.edu.entity.course;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author www.inxedu.com
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FavouriteCourseDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
    private int id;
	private String courseName; // 课程名字
	private int courseId; // 课程id
	private String logo;// 课程图片
	private java.math.BigDecimal currentPrice;//课程销售价格（实际支付价格）设置为0则可免费观看
	private int favouriteId; // 收藏课程id
	private Date addTime;//收藏时间
	private List<Map<String,Object>> teacherList;//该课程 下的老师list
}
