package com.inxedu.os.edu.controller.websiteehcache;

import com.inxedu.os.common.cache.CacheUtil;
import com.inxedu.os.common.constants.CacheConstans;
import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.sysLog.SystemLog;
import com.inxedu.os.edu.controller.user.AdminUserController;
import com.inxedu.os.edu.entity.websiteehcache.WebsiteEhcache;
import com.inxedu.os.edu.service.websiteehcache.WebsiteEhcacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 缓存管理
 * @author www.inxedu.com
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminWebsiteEhcacheController extends BaseController{
	
	private static Logger logger = LoggerFactory.getLogger(AdminUserController.class);
	
	private static final String toWebsiteEhcacheList = getViewPath("/admin/websiteehcache/website_ehcache_list");// Ehcache列表页
	private static final String toAddwebsiteEhcache = getViewPath("/admin/websiteehcache/website_ehcache_add");// Ehcache添加页
	private static final String toUpdatewebsiteEhcache = getViewPath("/admin/websiteehcache/website_ehcache_update");// Ehcache添加页
	
	@Autowired
	private WebsiteEhcacheService websiteEhcacheService;
	
	@InitBinder("websiteEhcache")
	public void initBinderEmail(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("websiteEhcache.");
	}
	
	/**
	 * 列表
	 */
	@RequestMapping("/ehcache/queryWebsiteEhcacheList")
	public String queryWebsiteEhcacheList(HttpServletRequest request,@ModelAttribute WebsiteEhcache websiteEhcache,@ModelAttribute("page") PageEntity page){
		try{
			List<WebsiteEhcache> websiteEhcacheList=websiteEhcacheService.queryWebsiteEhcacheList(websiteEhcache, page);
			request.setAttribute("websiteEhcacheList", websiteEhcacheList);
			request.setAttribute("page", page);
			request.setAttribute("websiteEhcache", websiteEhcache);
		}catch(Exception e){
			logger.error("AdminEhcacheController.queryWebsiteEhcacheList", e);
		}
		return toWebsiteEhcacheList;
	}
	
	/**
	 * 添加页面
	 */
	@RequestMapping("/ehcache/toAdd")
	public String toAdd(){
		return toAddwebsiteEhcache;
	}
	
	/**
	 * 添加
	 */
	@RequestMapping("/ehcache/addWebsiteEhcache")
	@ResponseBody
	public Map<String, Object> addWebsiteEhcache(WebsiteEhcache websiteEhcache){
		Map<String, Object> json=null;
		try{
			if(websiteEhcache!=null){
				boolean flag=websiteEhcacheService.queryWebsiteEhcacheIsExsit(websiteEhcache.getEhcacheKey().replaceAll(" ", ""));
				if(flag==false){
					websiteEhcacheService.addWebsiteEhcache(websiteEhcache);
					json=setJson(true, "true", null);
				}else{
					json=setJson(false, "已存在", null);
				}
			}
		}catch(Exception e){
			logger.error("AdminEhcacheController.addWebsiteEhcache", e);
			json=setJson(false, "erro", null);
		}
		return json;
	}
	
	/**
	 * 删除
	 */
	@SystemLog(type = "del",operation = "清空单个缓存")
	@RequestMapping("/ehcache/delWebsiteEhcache/{id}")
	@ResponseBody
	public Map<String, Object> delWebsiteEhcache(@PathVariable Long id){
		Map<String, Object> json=null;
		try{
			Long num=websiteEhcacheService.delWebsiteEhcache(id);
			if(num!=null&&num>0){
				json=setJson(true, "true", null);
			}else{
				json=setJson(false, "false", null);
			}
		}catch(Exception e){
			logger.error("AdminEhcacheController.delWebsiteEhcache", e);
			json=setJson(false, "error", null);
		}
		return json;
	}
	
	/**
	 * 清空单个缓存
	 */
	@SystemLog(type = "del",operation = "清空单个缓存")
	@RequestMapping("/ehcache/removeEHCache/{id}")
	@ResponseBody
	public Map<String, Object> removeEHCache(@PathVariable Long id){
		Map<String, Object> json=null;
		try{
			WebsiteEhcache websiteEhcache=websiteEhcacheService.getWebsiteEhcacheById(id);
			if(websiteEhcache!=null){
				CacheUtil.remove(CacheConstans.MEMFIX+""+websiteEhcache.getEhcacheKey());
				json=setJson(true, "true", null);
			}
		}catch(Exception e){
			logger.error("AdminEhcacheController.removeEHCache", e);
			json=setJson(false, "error", null);
		}
		return json;
	}
	
	/**
	 * 清空所有缓存
	 */
	@SystemLog(type = "del",operation = "清空所有缓存")
	@RequestMapping("/ehcache/removeAllEHCache")
	@ResponseBody
	public Map<String, Object> removeAllEHCache(){
		Map<String, Object> json=null;
		try{
			CacheUtil.removeAll();
			json=setJson(true, "true", null);
		}catch(Exception e){
			logger.error("AdminEhcacheController.removeAllEHCache", e);
			json=setJson(false, "error", null);
		}
		return json;
	}
	
	/**
	 * 缓存修改页
	 */
	@RequestMapping("/ehcache/updateWebsiteEhcachePage/{id}")
	public ModelAndView updateWebsiteEhcachePage(@PathVariable Long id){
		ModelAndView modelAndView=new ModelAndView(toUpdatewebsiteEhcache);
		try{
			if(id!=null&&id>0){
				WebsiteEhcache websiteEhcache=websiteEhcacheService.getWebsiteEhcacheById(id);
				modelAndView.addObject("websiteEhcache", websiteEhcache);
			}
		}catch(Exception e){
			logger.error("AdminEhcacheController.updateWebsiteEhcachePage",e);
		}
		return modelAndView;
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/ehcache/updateWebsiteEhcache")
	@SystemLog(type = "update",operation = "修改缓存")
	@ResponseBody
	public Map<String, Object> updateWebsiteEhcache(WebsiteEhcache websiteEhcache){
		Map<String, Object> json=null;
		try{
			WebsiteEhcache websiteEhcache2=websiteEhcacheService.getWebsiteEhcacheById(websiteEhcache.getId());
			if(websiteEhcache!=null){
				boolean flag=false;
				if(websiteEhcache2!=null&&websiteEhcache.getEhcacheKey().equals(websiteEhcache2.getEhcacheKey())){
					
				}else{
					flag=websiteEhcacheService.queryWebsiteEhcacheIsExsit(websiteEhcache.getEhcacheKey());
				}
				if(flag==false){
					websiteEhcacheService.updateWebsiteEhcache(websiteEhcache);
					json=setJson(true, "true", null);
				}else{
					json=setJson(false, "已存在", null);
				}
			}
		}catch(Exception e){
			logger.error("AdminEhcacheController.updateWebsiteEhcache", e);
			json=setJson(false, "erro", null);
		}
		return json;
	}
	
}
