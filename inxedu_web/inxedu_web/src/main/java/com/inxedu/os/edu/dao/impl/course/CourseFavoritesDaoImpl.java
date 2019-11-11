package com.inxedu.os.edu.dao.impl.course;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.edu.dao.course.CourseFavoritesDao;
import com.inxedu.os.edu.entity.course.CourseFavorites;
import com.inxedu.os.edu.entity.course.FavouriteCourseDTO;
import com.inxedu.os.common.entity.PageEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *
 * CourseFavorites
 * @author www.inxedu.com
 */
 @Repository("courseFavoritesDao")
public class CourseFavoritesDaoImpl extends GenericDaoImpl implements CourseFavoritesDao {

	
	public void createCourseFavorites(CourseFavorites cf) {
		this.insert("CourseFavoritesMapper.createCourseFavorites", cf);
		
	}

	
	public void deleteCourseFavorites(CourseFavorites courseFavorites) {
		this.delete("CourseFavoritesMapper.deleteCourseFavoritesById", courseFavorites);
	}

	
	public int checkFavorites(Map<String, Object> map) {
		return this.selectOne("CourseFavoritesMapper.checkFavorites", map);
	}

	
	public List<FavouriteCourseDTO> queryFavoritesPage(int userId, PageEntity page) {
		return this.queryForListPage("CourseFavoritesMapper.queryFavoritesPage", userId, page);
	}

    
}
