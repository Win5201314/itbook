package com.inxedu.os.edu.dao.impl.course;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.course.CourseDao;
import com.inxedu.os.edu.entity.course.Course;
import com.inxedu.os.edu.entity.course.CourseDto;
import com.inxedu.os.edu.entity.course.QueryCourse;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author www.inxedu.com
 *
 */
@Repository("courseDao")
public class CourseDaoImpl extends GenericDaoImpl implements CourseDao {

	
	public int addCourse(Course course) {
		this.insert("CourseMapper.createCourse", course);
		return course.getCourseId();
	}

	
	public List<CourseDto> queryCourseListPage(QueryCourse query, PageEntity page) {
		return this.queryForListPage("CourseMapper.queryCourseListPage", query, page);
	}

	
	public Course queryCourseById(int courseId) {
		return this.selectOne("CourseMapper.queryCourseById", courseId);
	}

	
	public void updateCourse(Course course) {
		this.update("CourseMapper.updateCourse", course);
	}

	
	public void updateAvaliableCourse(Map<String, Object> map) {
		this.update("CourseMapper.updateAvaliableCourse", map);
	}

	
	public List<CourseDto> queryRecommenCourseList() {
		return this.selectList("CourseMapper.queryRecommenCourseList", null);
	}

	
	public List<CourseDto> queryCourseList(QueryCourse query) {
		return this.selectList("CourseMapper.queryCourseList", query);
	}

	
	public List<CourseDto> queryWebCourseListPage(QueryCourse queryCourse,PageEntity page) {
		return this.queryForListPage("CourseMapper.queryWebCourseListPage", queryCourse, page);
	}

	
	public List<CourseDto> queryInterfixCourseList(Map<String, Object> map) {
		return this.selectList("CourseMapper.queryInterfixCourseList", map);
	}

	
	public List<CourseDto> queryMyCourseList(QueryCourse queryCourse,PageEntity page) {
		return this.queryForListPage("CourseMapper.queryMyCourseList", queryCourse, page);
	}

	
	public int queryAllCourseCount() {
		return this.selectOne("CourseMapper.queryAllCourseCount", null);
	}

	public List<CourseDto> queryRecommenCourseListByRecommendId(Long recommendId) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("recommendId", recommendId);
		return selectList("CourseMapper.queryRecommenCourseListByRecommendId", map);
	}

	public List<CourseDto> queryCourse(QueryCourse queryCourse) {
		return selectList("CourseMapper.queryCourse", queryCourse);
	}


	public List<Course> getCouponSubjectCourse(Long subjectId, String courseIds) {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("subjectId", subjectId);
		map.put("courseIds", courseIds);
		return this.selectList("CourseMapper.getCouponSubjectCourse", map);
	}

	/**
	 * 更新课程的数据数量（浏览数，购买数）
	 * @param type pageViewcount浏览数 pageBuycount购买数
	 * @param courseId 课程id
	 */
	public void updateCourseCount(String type,int courseId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);// 类型
		map.put("courseId", courseId);// 课程id
		this.update("CourseMapper.updateCourseCount", map);
	}

	/**
	 * 后台根据条件获取套餐下课程详细信息
	 *
	 * @param course
	 * @return
	 */
	public List<CourseDto> getPackageCourseListByCondition(Course course){
		return this.selectList("CourseMapper.getPackageCourseListByCondition", course);
	}

	/**
	 * 获取课程套餐的详细课程列表
	 * @param ids 查询条件
	 * @return List<Course>
	 */
	public List<CourseDto> getCourseListPackage(List<Long> ids){
		return this.selectList("CourseMapper.getCourseListPackage", ids);
	}

	@Override
	public List<CourseDto> getMyCourseList(QueryCourse queryCourse) {
		return this.selectList("CourseMapper.getMyCourseList",queryCourse);
	}

	@Override
	public CourseDto getMyCourseByCourseId(QueryCourse queryCourse) {
		return this.selectOne("CourseMapper.getMyCourseByCourseId",queryCourse);
	}

	@Override
	public List<CourseDto> queryMemberCourseListPage(QueryCourse queryCourse, PageEntity page) {
		return this.queryForListPage("CourseMapper.queryMemberCourseListPage", queryCourse, page);
	}
}
