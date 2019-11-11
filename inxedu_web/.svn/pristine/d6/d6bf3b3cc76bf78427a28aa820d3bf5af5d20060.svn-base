package com.inxedu.os.edu.dao.impl.livecourse;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.livecourse.LiveCourseDao;
import com.inxedu.os.edu.entity.course.Course;
import com.inxedu.os.edu.entity.course.QueryCourse;
import com.inxedu.os.edu.entity.kpoint.CourseKpoint;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author www.inxedu.com
 */
@Repository("LiveCourseDao")
public class LiveCourseDaoImpl extends GenericDaoImpl implements LiveCourseDao {
    @Override
    public List<Course> queryWebLiveCourseListPage(QueryCourse queryCourse, PageEntity page) {
        return this.queryForListPage("LiveCourseMapper.queryWebLiveCourseListPage",queryCourse,page);
    }

    @Override
    public List<CourseKpoint> queryKpointByCourse(Map<String, Object> queryMap) {
        return this.selectList("LiveCourseMapper.queryKpointByCourse",queryMap);
    }
}
