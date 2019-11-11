package com.inxedu.os.edu.dao.website;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.website.WebsiteCourseDetail;
import com.inxedu.os.edu.entity.website.WebsiteCourseDetailDTO;

import java.util.List;
import java.util.Map;

/** 推荐课程关联Dao层 
 * @author www.inxedu.com
 * */
public interface WebsiteCourseDetailDao {
	/**
	 * 创建推荐课程 
	 * @param detail
	 */
	void createWebsiteCourseDetail(String detail);
	
	/**
	 * 分页查询推荐课程
	 * @param dto 查询条件
	 * @param page 分页条件
	 * @return List<WebsiteCourseDetailDTO>
	 */
	List<WebsiteCourseDetailDTO> queryCourseDetailPage(WebsiteCourseDetailDTO dto, PageEntity page);
	
	/**
	 * 删除推荐课程
	 * @param id 推荐课程ID
	 */
	void deleteDetailById(int id);
	
	/**
	 * 修改推荐课程排序
	 * @param map 修改条件
	 */
	void updateSort(Map<String, Integer> map);
	
	/**
	 * 通过类型ID，查询该类型的推荐课程
	 * @param recommendId 类型ID
	 * @return List<WebsiteCourseDetail>
	 */
	List<WebsiteCourseDetail> queryDetailListByrecommendId(int recommendId);
	/**
	 * 根据关联的课程id删除
	 */
	void deleteDetailByCourseId(int id);
}