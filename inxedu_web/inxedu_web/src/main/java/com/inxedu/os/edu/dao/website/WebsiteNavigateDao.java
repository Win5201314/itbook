package com.inxedu.os.edu.dao.website;


import com.inxedu.os.edu.entity.website.WebsiteNavigate;

import java.util.List;

/**
 * WebsiteNavigateTbl管理接口
 * @author www.inxedu.com
 */
public interface WebsiteNavigateDao {
	
	/**
	 * 导航列表
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
	 * 查询未冻结的导航列表
	 */
	List<WebsiteNavigate> getWebNavigate();
	/**
	 * 查询最大排序数的导航
	 */
	WebsiteNavigate getMaxOrdernumNav();
}