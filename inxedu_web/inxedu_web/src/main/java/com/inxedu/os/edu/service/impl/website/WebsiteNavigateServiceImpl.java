package com.inxedu.os.edu.service.impl.website;


import com.inxedu.os.common.cache.CacheUtil;
import com.inxedu.os.common.constants.CacheConstans;
import com.inxedu.os.common.util.FastJsonUtil;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.edu.dao.website.WebsiteNavigateDao;
import com.inxedu.os.edu.entity.website.WebsiteNavigate;
import com.inxedu.os.edu.service.website.WebsiteNavigateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * WebsiteNavigateTbl管理接口
 * @author www.inxedu.com
 */
@Service("websiteNavigateService")
public class WebsiteNavigateServiceImpl implements WebsiteNavigateService {
 	@Autowired
    private WebsiteNavigateDao websiteNavigateDao;


	public List<WebsiteNavigate> getWebsiteNavigate(WebsiteNavigate websiteNavigate){
		return websiteNavigateDao.getWebsiteNavigate(websiteNavigate);
	}
    
	public void addWebsiteNavigate(WebsiteNavigate websiteNavigate){
		websiteNavigateDao.addWebsiteNavigate(websiteNavigate);
		CacheUtil.remove(CacheConstans.WEBSITE_NAVIGATE);
	}

	public void freezeWebsiteNavigate(WebsiteNavigate websiteNavigate){
		websiteNavigateDao.freezeWebsiteNavigate(websiteNavigate);
		CacheUtil.remove(CacheConstans.WEBSITE_NAVIGATE);
	}

	public void delWebsiteNavigate(int id){
		websiteNavigateDao.delWebsiteNavigate(id);
		CacheUtil.remove(CacheConstans.WEBSITE_NAVIGATE);
	}

	public void updateWebsiteNavigate(WebsiteNavigate websiteNavigate){
		websiteNavigateDao.updateWebsiteNavigate(websiteNavigate);
		CacheUtil.remove(CacheConstans.WEBSITE_NAVIGATE);
	}

	public WebsiteNavigate getWebsiteNavigateById(int id){
		return websiteNavigateDao.getWebsiteNavigateById(id);
	}
    
	@SuppressWarnings("unchecked")
	public Map<String,Object> getWebNavigate(){
		Map<String,Object> navigateMap=new HashMap<String, Object>();
		Map<String,List<String>> navigatesMapJson= (Map<String, List<String>>) CacheUtil.get(CacheConstans.WEBSITE_NAVIGATE);
        if (navigatesMapJson!=null&&navigatesMapJson.size()>0) {//多类型导航json存在
        	for(Entry<String,List<String>> entry:navigatesMapJson.entrySet()){
        		List<WebsiteNavigate> navigates=new ArrayList<WebsiteNavigate>();//单类型导航json
        		List<String> navigatesJson = entry.getValue();
				for(String navigateJson:navigatesJson){
					navigates.add(FastJsonUtil.obj2Object(navigateJson,WebsiteNavigate.class));//单个导航
        		}
				navigateMap.put(entry.getKey(), navigates);
        	}
            return navigateMap;
        } 
        navigatesMapJson=new HashMap<String, List<String>>();
			List<WebsiteNavigate> navigates=websiteNavigateDao.getWebNavigate();
        List<String> navigatesJson=new ArrayList<String>();//json
        List<WebsiteNavigate> navigateList=new ArrayList<WebsiteNavigate>();//对象
        String type = navigates.get(0).getType();
        for(WebsiteNavigate navigate : navigates){
            if (!type.equalsIgnoreCase(navigate.getType())) {
            	navigateMap.put(type, navigateList);//导航集合
            	navigateList=new ArrayList<WebsiteNavigate>();
            	
            	navigatesMapJson.put(type,navigatesJson);//导航json集合
            	navigatesJson=new ArrayList<String>();
                
                navigateList.add(navigate);//导航对象
                navigatesJson.add(FastJsonUtil.obj2JsonString(navigate));//导航json
                type = navigate.getType();
            } else {
            	navigateList.add(navigate);//导航对象
            	navigatesJson.add(FastJsonUtil.obj2JsonString(navigate));//导航json
            }
        }
        if (ObjectUtils.isNotNull(navigatesJson)) {
        	navigatesMapJson.put(type,navigatesJson);//导航json集合
        } 
        CacheUtil.set(CacheConstans.WEBSITE_NAVIGATE,navigatesMapJson, CacheConstans.WEBSITE_NAVIGATE_TIME);
		return navigateMap;
	}

	@Override
	public WebsiteNavigate getMaxOrdernumNav() {
		return websiteNavigateDao.getMaxOrdernumNav();
	}
}