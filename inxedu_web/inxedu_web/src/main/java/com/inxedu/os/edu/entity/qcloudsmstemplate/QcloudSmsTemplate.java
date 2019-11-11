package com.inxedu.os.edu.entity.qcloudsmstemplate;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author www.inxedu.com
 * @description
 */
@Data
public class QcloudSmsTemplate implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;

	/** 主键 */
	private Long id;
	/** 类型template(模板) sign（前面） */
	private String type;
	/** 内容 */
	private String text;
	/** 相关id */
	private String otherId;
	/** 创建时间 */
	private Date createTime;
	/** 模板名称 */
	private String name;
	/** 申请说明 */
	private String explain;
	/** 审核状态 */
	private int status;
}

