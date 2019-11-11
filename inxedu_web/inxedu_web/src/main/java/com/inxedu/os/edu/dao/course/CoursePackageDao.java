package com.inxedu.os.edu.dao.course;


import com.inxedu.os.edu.entity.course.CoursePackage;

import java.util.List;

/**
 * CoursePackage管理接口
 * @author www.inxedu.com
 */
public interface CoursePackageDao {
	  /**
     * 添加CoursePackage
     */
      void addCoursePackageBatch(List<CoursePackage> coursePackageList);
    /**
     * 通过id查询CoursePackage
     */
    CoursePackage queryCoursePackage(CoursePackage coursePackage);
    /**
	 * 删除CoursePackage
	 */
    void delCoursePackage(CoursePackage coursePackage);
	
	/**
     * 修改套餐课程下的课程排序
     * @param coursePackage
     */
    void updateCoursePackageOrderNum(CoursePackage coursePackage);
    
    /**
     * 查看课程所在的所有班级
     */
    List<CoursePackage> quePackageByCouId(Long courseId);
    /**
     * 删除CoursePackage
     */
    void delCoursePackageByCourseId(CoursePackage coursePackage);

    List<CoursePackage> queryCoursePackageList(CoursePackage coursePackage);

}