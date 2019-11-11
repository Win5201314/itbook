package com.inxedu.os.edu.service.livecourse;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.course.Course;
import com.inxedu.os.edu.entity.course.QueryCourse;
import com.inxedu.os.edu.entity.kpoint.CourseKpoint;

import java.util.List;
import java.util.Map;

/**
 * Created by lucky on 2017/1/9.
 */
public interface LiveCourseService {
    /**
     * 前台查询直播课程列表
     * @param queryCourse 查询条件
     * @param page 分页条件
     * @return List<CourseDto>
     */
    List<Course> queryWebLiveCourseListPage(QueryCourse queryCourse, PageEntity page);

    /**
     * 前台查询直播课程列表
     * @param queryMap 查询条件
     * @return List<CourseKpoint>
     */
    List<CourseKpoint> queryKpointByCourse(Map<String,Object> queryMap);
}
