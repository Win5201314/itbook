package com.inxedu.os.edu.service.course;

import com.inxedu.os.edu.entity.course.CoursePackage;

import java.util.List;

/**
 * CoursePackageService
 * @author www.inxedu.com
 */
public interface CoursePackageService {

	/**
	 * 添加CoursePackage
	 */
	void addCoursePackageBatch(String ids, Long courseId);

	/**
	 * 删除CoursePackage
	 */
	void delCoursePackage(CoursePackage classCourse);
	
	/**
    * 修改套餐课程下的课程排序
    * @param classCourse
    */
	void updateCoursePackageOrderNum(CoursePackage classCourse);
    
    /**
     * 查看课程所在的所有班级
     */
	List<CoursePackage> quePackageByCouId(Long courseId);
	/**
	 * 通过id查询CoursePackage
	 */
	List<CoursePackage> queryCoursePackageList(CoursePackage coursePackage);
}