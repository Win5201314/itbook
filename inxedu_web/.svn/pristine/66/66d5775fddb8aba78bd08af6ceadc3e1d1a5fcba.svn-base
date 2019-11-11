package com.inxedu.os.edu.service.impl.course;

import com.inxedu.os.edu.dao.course.CourseKpointAtlasDao;
import com.inxedu.os.edu.entity.course.CourseKpointAtlas;
import com.inxedu.os.edu.service.course.CourseKpointAtlasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 图片集
 * @author www.inxedu.com
 *
 */
@Service("courseKpointAtlasService")
public class CourseKpointAtlasServiceImpl implements CourseKpointAtlasService {
    @Autowired
    private CourseKpointAtlasDao courseKpointAtlasDao;

    /**
     * 创建课节图片集
     */
    public void createCourseKpointAtlas(CourseKpointAtlas courseKpointAtlas) {
        courseKpointAtlasDao.createCourseKpointAtlas(courseKpointAtlas);
    }

    /**
     * 删除图片集
     */
    public void deleteCourseKpointAtlasById(String ids) {
        courseKpointAtlasDao.deleteCourseKpointAtlasById(ids);
    }

    /**
     * 查询图片集
     */
    public CourseKpointAtlas queryKpointAtlasByKpointId(Long id) {
        return courseKpointAtlasDao.queryKpointAtlasByKpointId(id);
    }

    /**
     * 修改图片集
     */
    public void updateCourseKpointAtlas(CourseKpointAtlas courseKpointAtlas) {
        courseKpointAtlasDao.updateCourseKpointAtlas(courseKpointAtlas);
    }
}