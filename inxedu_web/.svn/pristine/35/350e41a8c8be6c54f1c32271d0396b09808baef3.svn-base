package com.inxedu.os.edu.service.impl.website;


import com.inxedu.os.common.cache.CacheUtil;
import com.inxedu.os.common.constants.CacheConstans;
import com.inxedu.os.common.util.FastJsonUtil;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.StringUtils;
import com.inxedu.os.edu.constants.enums.WebSiteProfileType;
import com.inxedu.os.edu.dao.website.WebsiteProfileDao;
import com.inxedu.os.edu.entity.website.WebsiteImages;
import com.inxedu.os.edu.entity.website.WebsiteProfile;
import com.inxedu.os.edu.service.website.WebsiteImagesService;
import com.inxedu.os.edu.service.website.WebsiteProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 网站管理配置service
 * @author www.inxedu.com
 */
@Service("websiteProfileService")
public class WebsiteProfileServiceImpl implements WebsiteProfileService {

	@Autowired
	private WebsiteProfileDao websiteProfileDao;
	@Autowired
	private WebsiteImagesService websiteImagesService;


	
	public void updateWebsiteProfile(WebsiteProfile websiteProfile) throws Exception {
		websiteProfileDao.updateWebsiteProfile(websiteProfile);
		CacheUtil.remove(CacheConstans.WEBSITE_PROFILE+websiteProfile.getType());
	}
	
	public Map<String, Object> getWebsiteProfileList() throws Exception {
		//获得所有配置
		@SuppressWarnings("unchecked")
		List<String> websitesStr=(List<String>) CacheUtil.get(CacheConstans.WEBSITE_PROFILE);
		if(ObjectUtils.isNull(websitesStr)||websitesStr.size()==0){
			websitesStr = new ArrayList<>();
			List<WebsiteProfile> websiteProfiles=websiteProfileDao.getWebsiteProfileList();
			for(WebsiteProfile websiteProfile:websiteProfiles){
				//转json字符串
				websitesStr.add(FastJsonUtil.obj2JsonString(websiteProfile));
			}
			CacheUtil.set(CacheConstans.WEBSITE_PROFILE, websitesStr, CacheConstans.WEBSITE_PROFILE_TIME);
		}
		Map<String,Object> webSiteMap = new HashMap<String,Object>();
		if(ObjectUtils.isNotNull(websitesStr)&&websitesStr.size()>0){
			for(String websiteStr:websitesStr){
				//转回对象
				WebsiteProfile websiteProfile = FastJsonUtil.obj2Object(websiteStr,WebsiteProfile.class);
				String desciption=websiteProfile.getDesciption();
				Map<String,Object> map1=FastJsonUtil.json2Map(checkString(desciption));
				map1.put("explain", websiteProfile.getExplain());//描述
				webSiteMap.put(websiteProfile.getType(),map1);
			}
		}
		return webSiteMap;
	}
	/**
	 * 检查字符串空的方法
	 */
	private String checkString(Object str) { 
		if (ObjectUtils.isNotNull(str) && !"null".equals(str.toString())) {
			return str.toString();
		} else {
			return "";
		}
	}
	/**
	 * 根据type查询网站配置
	 */
	public Map<String, Object> getWebsiteProfileByType(String type) {
		//根据类型获得数据 从cache获取
		String websiteProfileStr=(String) CacheUtil.get(CacheConstans.WEBSITE_PROFILE+type);
		if(ObjectUtils.isNull(websiteProfileStr)){//cache为空查询数据库
			WebsiteProfile websiteProfile=websiteProfileDao.getWebsiteProfileByType(type);
			websiteProfileStr=FastJsonUtil.obj2JsonString(websiteProfile);//websiteProfileStr json串
			CacheUtil.set(CacheConstans.WEBSITE_PROFILE+type, websiteProfileStr, CacheConstans.WEBSITE_PROFILE_TIME);//设置key 时间一天
		}
		WebsiteProfile websiteProfile=FastJsonUtil.obj2Object(websiteProfileStr, WebsiteProfile.class);//转回对象
		Map<String,Object> webSiteMap = new HashMap<String,Object>();
		if(ObjectUtils.isNull(websiteProfile)){
			return webSiteMap;
		}
		String desciption=websiteProfile.getDesciption();
		//把json数据转化为Map
		Map<String,Object> map1=FastJsonUtil.json2Map(checkString(desciption));

		webSiteMap.put(websiteProfile.getType(), map1);
		return webSiteMap;
	}
	/**
	 * 获取后台系统设置的主题色,并根据设置取banner
	 */
	public void setWebsiteThemeColor(HttpServletRequest request){
		// 获得banner图
		Map<String, List<WebsiteImages>> websiteImages = websiteImagesService.queryImagesByType();
		request.setAttribute("websiteImages", websiteImages);
//不同的主题显示不同的颜色
		Map<String, Object>  map = getWebsiteProfileByType(WebSiteProfileType.ThemeColor.toString());
		String cacheColor=((Map)map.get(WebSiteProfileType.ThemeColor.toString())).get("inxedu_index_theme_color")+"";
		if(StringUtils.isEmpty(cacheColor)){
			cacheColor = "orange";
		}
		if("blue".equals(cacheColor)){
			List<WebsiteImages> websiteImagesList = websiteImages.get("type_16");
			request.setAttribute("websiteImagesList", websiteImagesList);
		}
		if("green".equals(cacheColor)){
			List<WebsiteImages> websiteImagesList = websiteImages.get("type_17");
			request.setAttribute("websiteImagesList", websiteImagesList);
		}
		if("orange".equals(cacheColor)){
			List<WebsiteImages> websiteImagesList = websiteImages.get("type_1");
			request.setAttribute("websiteImagesList", websiteImagesList);
		}
		request.setAttribute("theme_color", cacheColor);
	}
}