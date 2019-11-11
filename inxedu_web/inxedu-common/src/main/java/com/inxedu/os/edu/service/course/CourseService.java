package com.inxedu.os.edu.service.course;


import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.course.Course;
import com.inxedu.os.edu.entity.course.CourseDto;
import com.inxedu.os.edu.entity.course.CourseStudyhistory;
import com.inxedu.os.edu.entity.course.QueryCourse;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Course管理接口
 * @author www.inxedu.com
 */
public interface CourseService {
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
     * @param courseId 课程ID
     * @param type 1上架 2下架
     */
	void updateAvaliableCourse(int courseId, int type);
	
	/**
	 * 查询所有的推荐课程
	 * @return Map<String,List<CourseDto>>
	 */
	Map<String,List<CourseDto>> queryRecommenCourseList();
	
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
	 * @param subjectId 专业ID
	 * @param count 查询数量
	 * @param sellType 课程类型：COURSE(课程) LIVE(直播)
	 * @return List<Course>
	 */
	List<CourseDto> queryInterfixCourseLis(int subjectId, int count, int courseId, String sellType);
	
	/**
	 * 分页查询我的课程列表
	 * @param queryCourse
	 * @param page 分页条件
	 * @return List<CourseDto>
	 */
	List<CourseDto> queryMyCourseList(QueryCourse queryCourse, PageEntity page);
	
	/**
	 * 查询所有的课程个数
	 * @return 返回课程个数
	 */
	int queryAllCourseCount();
	
	/**
	 *  查询一部分已购买的课程，用户前台头部
	 * @param userId 用户ID
	 * @param count 查询条数
	 * @return List<Map<String,Object>>
	 */
	/*public List<Map<String,Object>> queryMyCourseListByMap(int userId, int count);*/
	
	/**
	 * 根据RecommendId		查询推荐课程
	 * @param recommendId		推荐类型Id
	 */
	List<CourseDto>queryRecommenCourseListByRecommendId(Long recommendId);
	/**
	 * 查询精品课程、最新课程、全部课程
	 */
	List<CourseDto> queryCourse(QueryCourse queryCourse);

	/**
	 * 获得项目专业限制的所有课程
	 */
	List<Course> getCouponSubjectCourse(Long subjectId, String courseIds);

	/**
	 * 更新课程的数据数量（浏览数，购买数）
	 */
	void updateCourseCount(String type, int courseId);

	/**
	 * 后台根据条件获取套餐下课程详细信息
	 */
	List<CourseDto> getPackageCourseListByCondition(Course course);

	/**
	 * 获取课程套餐的详细课程列表
	 */
	List<CourseDto> getCourseListPackage(List<Long> ids);

	/**
	 * 把传入的课程设置学习记录等信息
	 */
	void getCoursePutStudyhistory(List<CourseDto> courseList,int userId);

	/**
	 * excel批量赠送课程
	 */
	String updateGiveCoursesExcelExcel(HttpServletRequest request, MultipartFile myFile, Integer mark) throws Exception;
	/*查询课程最近被观看到章节*/
	CourseStudyhistory getCourseStudyhistory(HttpServletRequest request, Long courseId);
	/*查询课程集合*/
	List<CourseDto> getMyCourseList(QueryCourse queryCourse);
	//给直播set最近开始和结束直播的时间
	void setNearTime(List<CourseDto> courseDtos);
	/*查询我的课程*/
	CourseDto getMyCourseByCourseId(QueryCourse queryCourse);
	/**
	 * 分页查询我的课程列表
	 * @param queryCourse
	 * @param page 分页条件
	 * @return List<CourseDto>
	 */
	List<CourseDto> queryMemberCourseListPage(QueryCourse queryCourse,PageEntity page);
	/*判断用户是否有某课程的会员*/
	boolean hasMember(Long courseId,Long userId);
	/*个人中心查询所有课程*/
	List<CourseDto> querySelfCourse(QueryCourse queryCourse,PageEntity page);
}