package com.inxedu.os.edu.entity.order;

import lombok.Data;

import java.io.Serializable;

@Data
public class TrxorderDetailDTO implements Serializable {

	/**
	 * 订单流水课程
	 */
	private static final long serialVersionUID = 1L;
	private Long trxorderDetailId;//流水ID
	private Long courseId;//课程id
	private String currentPirce;// 当前价格
	private String courseImgUrl; // 课程图片
	private String courseName;// 课程名称
	private String courseTitle; // 课程描述
	private String lessionNum;//课时数
	private String sellType;//课程类型
	private String trxorderType;//订单类型 COURSE课程 MEMBER会员
}
