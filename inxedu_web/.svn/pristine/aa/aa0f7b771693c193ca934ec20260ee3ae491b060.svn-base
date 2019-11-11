package com.inxedu.os.edu.service.website;


import java.util.List;
import java.util.Map;

import com.inxedu.os.common.entity.PageEntity;

import com.inxedu.os.edu.entity.website.WebsiteImages;

/**
 *广告图service 接口
 *@author www.inxedu.com
 */
public interface WebsiteImagesService {
	/**
	 * 添加图片
	 * @param image
	 * @return 返回图片ID
	 */
    int creasteImage(WebsiteImages image);
	
	/**
	 * 分页查询广告图片
	 * @param image 查询条件
	 * @param page 分页条件
	 * @return List<WebsiteImages>
	 */
    List<Map<String,Object>> queryImagePage(WebsiteImages image, PageEntity page);
	
	/**
	 * 通过图片ID，查询图片详情信息
	 * @param imageId
	 * @return
	 */
    WebsiteImages queryImageById(int imageId);
	
	/**
	 * 删除图片
	 * @param imageIds
	 */
    void deleteImages(String imageIds);
	
	/**
	 * 修改图片
	 * @param image
	 */
    void updateImage(WebsiteImages image);
	
	/**
	 * 查询所有图片列表
	 * @return Map<String,List<WebsiteImages>>
	 */
    Map<String,List<WebsiteImages>> queryImagesByType();
}
