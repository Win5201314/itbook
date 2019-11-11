package com.inxedu.os.common.service;

import java.util.Map;



/**
 * webservice接口
 */
public interface WebHessianService {
	/**
	 * 查询用户信息
	 * @param cusId
	 * @return
	 */
	Map<String,String> getUserInfo(Long cusId);

	/**
	 * 批量查询用户信息
	 * @param ids
	 * @return
	 */
	Map<String,String> getUserInfoByUids(String ids);
	/**
	 * 小组  分页查询用户
	 * @return
	 */
	Map<String,String> queryUserExpandDtoPage(Map<String, String> map);
	/**
	 * 传入key获得缓存对象
	 */
	Object getEHCache(String key);

}
