package com.inxedu.os.edu.service.website;


import com.inxedu.os.edu.entity.website.WebsiteProfile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * service
 * @author www.inxedu.com
 */
public interface WebsiteProfileService {
	/**
	 * 修改WebsiteProfile
	 */
	void updateWebsiteProfile(WebsiteProfile websiteProfile) throws Exception;

	/**
	 * 查询所有网站配置
	 */
	Map<String, Object> getWebsiteProfileList() throws Exception;
	/**
	 * 根据type查询网站配置
	 */
	Map<String,Object> getWebsiteProfileByType(String type);
	/**
	 * 获取后台系统设置的主题色,并根据设置取banner
	 */
	void setWebsiteThemeColor(HttpServletRequest request);

}