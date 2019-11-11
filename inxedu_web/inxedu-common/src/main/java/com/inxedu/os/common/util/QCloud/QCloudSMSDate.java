package com.inxedu.os.common.util.QCloud;

import lombok.Data;
/**
 *
 * 腾讯云短信工具类
 */
@Data
public class QCloudSMSDate {
	private int id;
	private String text;
	private int status;
	private int type;
}