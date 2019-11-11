package com.inxedu.os.edu.service.impl.website;


import com.inxedu.os.common.cache.CacheUtil;
import com.inxedu.os.common.constants.CacheConstans;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.website.WebsiteImagesDao;
import com.inxedu.os.edu.entity.website.WebsiteImages;
import com.inxedu.os.edu.service.website.WebsiteImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 广告图service 实现
 * @author www.inxedu.com
 *
 */
@Service("websiteImagesService")
public class WebsiteImagesServiceImpl implements WebsiteImagesService {
	@Autowired
	private WebsiteImagesDao websiteImagesDao;

	public int creasteImage(WebsiteImages image) {
		CacheUtil.remove(CacheConstans.BANNER_IMAGES);
		return websiteImagesDao.creasteImage(image);
	}

	public List<Map<String,Object>> queryImagePage(WebsiteImages image,
			PageEntity page) {
		return websiteImagesDao.queryImagePage(image, page);
	}

	public WebsiteImages queryImageById(int imageId) {
		return websiteImagesDao.queryImageById(imageId);
	}

	public void deleteImages(String imageIds) {
		websiteImagesDao.deleteImages(imageIds);
		CacheUtil.remove(CacheConstans.BANNER_IMAGES);
	}

	public void updateImage(WebsiteImages image) {
		websiteImagesDao.updateImage(image);
		CacheUtil.remove(CacheConstans.BANNER_IMAGES);
	}

	public Map<String,List<WebsiteImages>> queryImagesByType() {
		//从缓存中查询图片数据
		@SuppressWarnings("unchecked")
		Map<String,List<WebsiteImages>> imageMapList = (Map<String,List<WebsiteImages>>) CacheUtil.get(CacheConstans.BANNER_IMAGES);
		//如果缓存中不存在则重新查询
		if(imageMapList==null){
			List<WebsiteImages> imageList = websiteImagesDao.queryImagesByType();
			if(imageList!=null && imageList.size()>0){
				imageMapList = new HashMap<String, List<WebsiteImages>>();
				
				List<WebsiteImages> _list = new ArrayList<WebsiteImages>();
				int typeId =imageList.get(0).getTypeId();
				for(WebsiteImages _wi : imageList){
					if(typeId==_wi.getTypeId()){
						_list.add(_wi);
					}else{
						//list进行排序  正序
						sortWebsiteImages(_list);
						imageMapList.put("type_"+typeId, _list);
						 _list = new ArrayList<WebsiteImages>();
						 _list.add(_wi);
					}
					typeId = _wi.getTypeId();
				}
				//list进行排序  正序
				sortWebsiteImages(_list);
				//添加最后一条记录
				imageMapList.put("type_"+typeId, _list);
				CacheUtil.set(CacheConstans.BANNER_IMAGES, imageMapList,CacheConstans.BANNER_IMAGES_TIME);
			}
		}
		return imageMapList;
	}
	//list进行排序  正序
	public void sortWebsiteImages(List<WebsiteImages> _list){
		Collections.sort(_list, new Comparator<WebsiteImages>() {
			public int compare(WebsiteImages arg0, WebsiteImages arg1) {
				if(arg0.getSeriesNumber()>arg1.getSeriesNumber()){
					return 1;
				}
				if(arg0.getSeriesNumber()<arg1.getSeriesNumber()){
					return -1;
				}
				return 0;
			}
		});
	}

}
