package com.inxedu.os.edu.dao.impl.course;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.edu.dao.course.CourseKpointAtlasDao;
import com.inxedu.os.edu.entity.course.CourseKpointAtlas;
import org.springframework.stereotype.Repository;
/**
 * 图片集
 * @author www.inxedu.com
 *
 */
@Repository("courseKpointAtlasDao")
public class CourseKpointAtlasDaoImpl extends GenericDaoImpl implements CourseKpointAtlasDao {
    /**
     * 创建课节图片集
     *
     * @param courseKpointAtlas
     */
    public void createCourseKpointAtlas(CourseKpointAtlas courseKpointAtlas) {
        this.insert("CourseKpointAtlasMapper.createCourseKpointAtlas", courseKpointAtlas);
    }

    /**
     * 删除图片集
     *
     * @param ids
     */
    public void deleteCourseKpointAtlasById(String ids) {
        this.delete("CourseKpointAtlasMapper.deleteCourseKpointAtlasById", ids);
    }

    /**
     * 查询图片集
     *
     * @param id
     * @return
     */
    public CourseKpointAtlas queryKpointAtlasByKpointId(Long id) {
        return selectOne("CourseKpointAtlasMapper.queryKpointAtlasByKpointId", id);
    }

    /**
     * 修改图片集
     *
     * @param courseKpointAtlas
     */
    public void updateCourseKpointAtlas(CourseKpointAtlas courseKpointAtlas) {
        this.update("CourseKpointAtlasMapper.updateCourseKpointAtlas", courseKpointAtlas);
    }
}
