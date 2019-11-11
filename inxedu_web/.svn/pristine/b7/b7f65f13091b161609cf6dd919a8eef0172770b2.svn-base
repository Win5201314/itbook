package com.inxedu.os.edu.service.course;


import com.inxedu.os.edu.entity.course.Course;
import com.inxedu.os.edu.entity.course.QueryCourse;
import com.inxedu.os.edu.entity.teacher.Teacher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * CourseTeacher管理接口
 * @author www.inxedu.com
 */
public interface CourseTeacherService {

	 /**
     * 添加课程与讲师的关联数
     */
     void addCourseTeacher(String value);
    
    /**
     * 删除课程与讲师的关联数据
     * @param courseId 课程ID
     */
    void deleteCourseTeacher(int courseId);
    /**
     * 添加课程与讲师的关联数据
     */
    void addCourseTeacher(HttpServletRequest request, Course course);

    /**
     * 根据课程获得 讲师(课程购买数、浏览数等)
     * @return List<CourseTeacher>
     */
    List<Teacher> getTeachersByCourse(QueryCourse queryCourse);
}