package com.inxedu.os.edu.dao.course;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.course.Course;
import com.inxedu.os.edu.entity.course.CourseDto;
import com.inxedu.os.edu.entity.course.QueryCourse;

import java.util.List;
import java.util.Map;

/**
 * Course管理接口
 * @author www.inxedu.com
 */
public interface CourseDao {
    /**
     * 添加Course
     */
	int addCourse(Course course);
    
    /**
     * 分页查询课程列表
     * @param query 条件条件
     * @param page 分页条件
     * @return List<CourseDto>
     */
	List<CourseDto> queryCourseListPage(QueryCourse query, PageEntity page);
    
    /**
     * 通过ID，查询课程数据
     * @param courseId 课程ID
     * @return Course
     */
	Course queryCourseById(int courseId);
    
    /**
     * 更新课程数据
     * @param course 课程
     */
	void updateCourse(Course course);

    /**
     * 上架或下架课程
     * @param map 修改条件
     */
	void updateAvaliableCourse(Map<String, Object> map);
	
	/**
	 * 查询所有的推荐课程
	 * @return
	 */
	List<CourseDto> queryRecommenCourseList();
	
	/**
	 * 根据不同条件查询课程列表
	 * @param query 查询条件
	 * @return List<CourseDto>
	 */
	List<CourseDto> queryCourseList(QueryCourse query);
	
	
	/**
	 * 前台查询课程列表
	 * @param queryCourse 查询条件
	 * @param page 分页条件
	 * @return List<CourseDto>
	 */
	List<CourseDto> queryWebCourseListPage(QueryCourse queryCourse, PageEntity page);
	
	/**
	 * 查询相关课程
	 * @param map 查询条件
	 * @return List<Course>
	 */
	List<CourseDto> queryInterfixCourseList(Map<String, Object> map);
	
	/**
	 * 分页查询我的课程列表
	 * @param queryCourse
	 * @param page 分页条件
	 * @return List<CourseDto>
	 */
	List<CourseDto> queryMyCourseList(QueryCourse queryCourse,PageEntity page);
	
	/**
	 * 查询所有的课程个数
	 * @return 返回课程个数
	 */
	int queryAllCourseCount();
	
	/**
	 *  查询一部分已购买的课程，用户前台头部
	 * @param map 查询条件
	 * @return List<Map<String,Object>>
	 */
	/*public List<Map<String,Object>> queryMyCourseListByMap(Map<String, Object> map);*/

	/**
	 * 根据RecommendId		查询推荐课程
	 * 
	 * @param recommendId		推荐类型Id
	 * @return
	 */
	List<CourseDto> queryRecommenCourseListByRecommendId(Long recommendId);
	/**
	 * 查询精品课程、最新课程、全部课程
	 * @param queryCourse
	 * @return
	 */
	List<CourseDto> queryCourse(QueryCourse queryCourse);

	/**
	 * 获得专业下的所有课程
	 *
	 * @param subjectId 专业id
	 * @return List<Course>
	 * @param　courseId　传此参数时，查询改课程相同专业下的课程
	 */
	List<Course> getCouponSubjectCourse(Long subjectId, String courseIds);

	/**
	 * 更新课程的数据数量（浏览数，购买数）
	 * @param type pageViewcount浏览数 pageBuycount购买数
	 * @param courseId 课程id
	 */
	void updateCourseCount(String type, int courseId);

	/**
	 * 后台根据条件获取套餐下课程详细信息
	 *
	 * @param course
	 * @return
	 */
	List<CourseDto> getPackageCourseListByCondition(Course course);

	/**
	 * 获取课程套餐的详细课程列表
	 * @param ids 查询条件
	 * @return List<Course>
	 */
	List<CourseDto> getCourseListPackage(List<Long> ids);

	/*按条件查询课程集合（个人中心课程查询）*/
	List<CourseDto> getMyCourseList(QueryCourse queryCourse);
	/*查询我的课程*/
	CourseDto getMyCourseByCourseId(QueryCourse queryCourse);

	/**
	 * 分页查询会员课程列表
	 * @param queryCourse
	 * @param page 分页条件
	 * @return List<CourseDto>
	 */
	List<CourseDto> queryMemberCourseListPage(QueryCourse queryCourse,PageEntity page);
}