package com.inxedu.os.edu.entity.dataclean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author www.inxedu.com
 *
 */
@Data
public class DataClean implements Serializable {
	private int id;//Id
	private String name;//名称
	private String desc;//描述
	private String sql;//sql
}
