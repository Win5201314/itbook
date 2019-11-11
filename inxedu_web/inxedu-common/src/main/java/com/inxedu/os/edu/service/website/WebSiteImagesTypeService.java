package com.inxedu.os.edu.service.website;

import java.util.List;

import com.inxedu.os.edu.entity.website.WebSiteImagesType;

/**
 * @author www.inxedu.com
 *
 */
public interface WebSiteImagesTypeService {
	/**
	 * 创建图片类型
	 * @param type 类型
	 * @return 返回图片类型ID
	 */
    int createImageType(WebSiteImagesType type);
	
	/**
	 * 查询所有的图片类型
	 * @return List<WebSiteImagesType>
	 */
    List<WebSiteImagesType> queryAllTypeList();
	
	/**
	 * 删除图片类型
	 * @param typeId 类型ID
	 */
    void deleteTypeById(int typeId);
	
	/**
	 * 修改图片类型
	 * @param type 类型
	 */
    void updateType(WebSiteImagesType type);
}
