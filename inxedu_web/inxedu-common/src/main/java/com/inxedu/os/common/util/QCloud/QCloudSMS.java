package com.inxedu.os.common.util.QCloud;

import lombok.Data;
import java.util.List;

/**
 *
 * 腾讯云短信工具类
 */
@Data
public class QCloudSMS {

	private int result;
	private String msg;
	private int count;
	private List<QCloudSMSDate> data;

}