package com.inxedu.os.edu.dao.course;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.course.CourseFavorites;
import com.inxedu.os.edu.entity.course.FavouriteCourseDTO;

import java.util.List;
import java.util.Map;


/**
 * 课程收藏管理接口
 * @author www.inxedu.com
 */
public interface CourseFavoritesDao {
	/**
	 * 添加课程收藏
	 * @param cf
	 */
	void createCourseFavorites(CourseFavorites cf);
	
	/**
	 * 删除课程收藏
	 * @param courseFavorites
	 */
	void deleteCourseFavorites(CourseFavorites courseFavorites);
	
	/**
	 * 检测用户是否收藏过
	 * @param map
	 * @return int
	 */
	int checkFavorites(Map<String, Object> map);
	
	/**
	 * 分页查询用户收藏列表
	 * @param userId 用户ID
	 * @param page 分页条件
	 * @return List<FavouriteCourseDTO>
	 */
	List<FavouriteCourseDTO> queryFavoritesPage(int userId, PageEntity page);
    
    
}