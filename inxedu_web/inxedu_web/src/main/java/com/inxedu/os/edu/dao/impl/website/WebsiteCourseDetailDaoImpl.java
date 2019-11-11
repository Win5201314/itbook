package com.inxedu.os.edu.dao.impl.website;


import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.website.WebsiteCourseDetailDao;
import com.inxedu.os.edu.entity.website.WebsiteCourseDetail;
import com.inxedu.os.edu.entity.website.WebsiteCourseDetailDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *
 * 推荐课程关联Dao层
 * @author www.inxedu.com
 */
 @Repository("websiteCourseDetailDao")
public class WebsiteCourseDetailDaoImpl extends GenericDaoImpl implements WebsiteCourseDetailDao {

	
	public void createWebsiteCourseDetail(String detail) {
		this.insert("WebsiteCourseDetailMapper.createWebsiteCourseDetail", detail);
	}

	
	public List<WebsiteCourseDetailDTO> queryCourseDetailPage(WebsiteCourseDetailDTO dto, PageEntity page) {
		return this.queryForListPage("WebsiteCourseDetailMapper.queryCourseDetailPage", dto, page);
	}

	
	public void deleteDetailById(int id) {
		this.delete("WebsiteCourseDetailMapper.deleteDetailById", id);
	}

	
	public void updateSort(Map<String, Integer> map) {
		this.update("WebsiteCourseDetailMapper.updateSort", map);
	}

	
	public List<WebsiteCourseDetail> queryDetailListByrecommendId(int recommendId) {
		return this.selectList("WebsiteCourseDetailMapper.queryDetailListByrecommendId", recommendId);
	}
	/**
	 * 根据关联的课程id删除
	 */
	public void deleteDetailByCourseId(int id){
		this.delete("WebsiteCourseDetailMapper.deleteDetailByCourseId", id);
	}
}
