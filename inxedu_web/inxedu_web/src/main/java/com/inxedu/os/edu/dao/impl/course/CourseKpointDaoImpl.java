package com.inxedu.os.edu.dao.impl.course;


import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.edu.dao.course.CourseKpointDao;
import com.inxedu.os.edu.entity.kpoint.CourseKpoint;
import com.inxedu.os.edu.entity.kpoint.CourseKpointDto;
import lombok.ToString;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *
 * CourseKpoint
 * @author www.inxedu.com
 */
 @ToString
 @Repository("courseKpointDao")
public class CourseKpointDaoImpl extends GenericDaoImpl implements CourseKpointDao {

    public int addCourseKpoint(CourseKpoint courseKpoint) {
    	this.insert("CourseKpointMapper.createCourseKpoint",courseKpoint);
        return courseKpoint.getKpointId();
    }

	
	public List<CourseKpoint> queryCourseKpointByCourseId(int courseId) {
		return this.selectList("CourseKpointMapper.queryCourseKpointByCourseId", courseId);
	}

	
	public CourseKpointDto queryCourseKpointById(int kpointId) {
		return this.selectOne("CourseKpointMapper.queryCourseKpointById", kpointId);
	}

	
	public void updateKpoint(CourseKpoint kpoint) {
		this.update("CourseKpointMapper.updateKpoint", kpoint);
		
	}

	
	public void deleteKpointByIds(String ids) {
		this.delete("CourseKpointMapper.deleteKpointByIds", ids);
		
	}

	
	public void updateKpointParentId(Map<String, Integer> map) {
		this.update("CourseKpointMapper.updateKpointParentId", map);
	}


	@Override
	public int getSecondLevelKpointCount(Long courseId) {
		return this.selectOne("CourseKpointMapper.getSecondLevelKpointCount", courseId);
	}

	@Override
	public void updCourseKpointCount(int courseKpointId) {
		this.update("CourseKpointMapper.updCourseKpointCount",courseKpointId);
	}

	@Override
	public List<CourseKpointDto> queryKpointList(CourseKpoint courseKpoint) {
		return this.selectList("CourseKpointMapper.queryKpointList",courseKpoint);
	}

	@Override
	public List<CourseKpointDto> queryCourseNearestKpointList(CourseKpoint courseKpoint) {
		return this.selectList("CourseKpointMapper.queryCourseNearestKpointList",courseKpoint);
	}
}
