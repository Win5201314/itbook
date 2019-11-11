package com.inxedu.os.edu.service.website;


import com.inxedu.os.edu.entity.website.WebsiteNavigate;

import java.util.List;
import java.util.Map;

/**
 * WebsiteNavigateTbl管理接口
 * @author www.inxedu.com
 */
public interface WebsiteNavigateService {

	/**
	 * 首页导航列表
	 */
    List<WebsiteNavigate> getWebsiteNavigate(WebsiteNavigate websiteNavigate);
	/**
	 * 添加导航
	 */
    void addWebsiteNavigate(WebsiteNavigate websiteNavigate);
	/**
	 * 冻结、解冻导航
	 */
    void freezeWebsiteNavigate(WebsiteNavigate websiteNavigate);
	/**
	 * 删除导航
	 */
    void delWebsiteNavigate(int id);
	/**
	 * 更新导航
	 */
    void updateWebsiteNavigate(WebsiteNavigate websiteNavigate);
	/**
	 * id查询导航
	 */
    WebsiteNavigate getWebsiteNavigateById(int id);
	/**
	 * 导航列表
	 */
    Map<String,Object> getWebNavigate();
	/**
	 * 查询最大排序数的导航
	 */
	WebsiteNavigate getMaxOrdernumNav();
}