package com.inxedu.os.edu.dao.course;


import com.inxedu.os.edu.entity.course.QueryCourse;
import com.inxedu.os.edu.entity.teacher.Teacher;

import java.util.List;
import java.util.Map;

/**
 * CourseTeacher管理接口
 * @author www.inxedu.com
 */
public interface CourseTeacherDao {

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
     * 根据课程获得课程的讲师list
     * @return List<CourseTeacher>
     */
    Map<Long,List<Teacher>> getCourseTeacherListByCourse(List<Long> courses);

    /**
     * 根据课程获得 讲师(课程购买数、浏览数等)
     * @return List<CourseTeacher>
     */
    List<Teacher> getTeachersByCourse(QueryCourse queryCourse);
}