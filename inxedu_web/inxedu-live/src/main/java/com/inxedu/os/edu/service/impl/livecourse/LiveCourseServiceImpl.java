package com.inxedu.os.edu.service.impl.livecourse;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.livecourse.LiveCourseDao;
import com.inxedu.os.edu.entity.course.Course;
import com.inxedu.os.edu.entity.course.QueryCourse;
import com.inxedu.os.edu.entity.kpoint.CourseKpoint;
import com.inxedu.os.edu.service.livecourse.LiveCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by lucky on 2017/1/9.
 */
@Service("liveCourseService")
public class LiveCourseServiceImpl implements LiveCourseService {

    @Autowired
    private LiveCourseDao liveCourseDao;
    @Override
    public List<Course> queryWebLiveCourseListPage(QueryCourse queryCourse, PageEntity page) {
        return liveCourseDao.queryWebLiveCourseListPage(queryCourse,page);
    }

    @Override
    public List<CourseKpoint> queryKpointByCourse(Map<String, Object> queryMap) {
        return liveCourseDao.queryKpointByCourse(queryMap);
    }
}
