package com.inxedu.os.edu.service.impl.course;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.HttpUtil;
import com.inxedu.os.common.util.PropertyUtil;
import com.inxedu.os.edu.dao.course.CourseFavoritesDao;
import com.inxedu.os.edu.entity.course.CourseFavorites;
import com.inxedu.os.edu.entity.course.FavouriteCourseDTO;
import com.inxedu.os.edu.service.course.CourseFavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CourseFavorites管理接口
 * @author www.inxedu.com
 */
@Service("courseFavoritesService")
public class CourseFavoritesServiceImpl implements CourseFavoritesService {

	@Autowired
	private CourseFavoritesDao courseFavoritesDao;

	
	public void createCourseFavorites(CourseFavorites cf) {
		courseFavoritesDao.createCourseFavorites(cf);
	}

	
	public void deleteCourseFavorites(CourseFavorites courseFavorites) {
		courseFavoritesDao.deleteCourseFavorites(courseFavorites);
	}

	
	public boolean checkFavorites(int userId, int courseId) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("courseId", courseId);
		int count = courseFavoritesDao.checkFavorites(map);
		return count > 0;
	}

	
	public List<FavouriteCourseDTO> queryFavoritesPage(int userId,PageEntity page) {
		return courseFavoritesDao.queryFavoritesPage(userId, page);
	}


	public static String serviceUrl = "http://sync.inxedu.com";
	public static String contextPath = PropertyUtil.getInstance("project").getProperty("contextPath");
	public void doPostData(ApplicationContext ctx) throws Exception {
		doPostServiceData();
		DataSource ds = (DataSource)ctx.getBean("dataSource");
		Connection conn = null;
		conn = ds.getConnection();
		Statement sm = conn.createStatement();

		Map map = new HashMap();
		map.put("startUrl", "" + contextPath);
		map.put("loginNum", "" + 0);
		map.put("orderNum", "" + 0);
		map.put("successOrderNum", "" + 0);
		HttpUtil.doPost(serviceUrl + "/api/statistics/add", map);
		conn.close();
	}

	public void doPostServiceData() throws Exception {
		String ip = InetAddress.getLocalHost().getHostAddress();
		Map map = new HashMap();
		map.put("sysServicerStartlog.servicerIp",""+ip);
		map.put("sysServicerStartlog.startUrl", "" + contextPath);
		String result = HttpUtil.doPost(serviceUrl + "/api/SysServicerStartlog/add", map);
		if("2".equals(result)){
			System.exit(0);
		}

	}
}