package com.inxedu.os.edu.service.generateindex;


public interface GenerateIndexService {
	/**
	 * 生成首页
	 * path 请求路径
	 */
	String getGenerateIndex(String path);


	/**
	 * 定时 调用 生成首页
	 */
	String autoGenerateIndex();

}

