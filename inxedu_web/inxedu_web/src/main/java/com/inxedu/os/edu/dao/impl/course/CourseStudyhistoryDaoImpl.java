package com.inxedu.os.edu.dao.impl.course;


import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.course.CourseStudyhistoryDao;
import com.inxedu.os.edu.entity.course.CourseStudyhistory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * CourseStudyhistory 管理接口实现
 * @author www.inxedu.com
 */
 @Repository("courseStudyhistoryDao")
public class CourseStudyhistoryDaoImpl extends GenericDaoImpl implements CourseStudyhistoryDao{

    public Long addCourseStudyhistory(CourseStudyhistory courseStudyhistory) {
        return this.insert("CourseStudyhistoryMapper.createCourseStudyhistory",courseStudyhistory);
    }


    public void updateCourseStudyhistory(CourseStudyhistory courseStudyhistory) {
        this.update("CourseStudyhistoryMapper.updateCourseStudyhistory",courseStudyhistory);
    }

    public List<CourseStudyhistory> getCourseStudyhistoryList(CourseStudyhistory courseStudyhistory) {
        return this.selectList("CourseStudyhistoryMapper.getCourseStudyhistoryList",courseStudyhistory);
    }

	@Override
	public List<CourseStudyhistory> getCourseStudyhistoryListByCouId(Long courseId) {
		return this.selectList("CourseStudyhistoryMapper.getCourseStudyhistoryListByCouId", courseId);
	}

	@Override
	public int getCourseStudyhistoryCountByCouId(Long courseId) {
		return (Integer)this.selectOne("CourseStudyhistoryMapper.getCourseStudyhistoryCountByCouId", courseId);
	}

    @Override
    public CourseStudyhistory getRecentStudyhistoryByUserId(Long userId) {
        return this.selectOne("CourseStudyhistoryMapper.getRecentStudyhistoryByUserId",userId);
    }

    @Override
    public List<CourseStudyhistory> queryCourseStudyHistoryListPage(CourseStudyhistory courseStudyhistory, PageEntity pageEntity) {
        return this.queryForListPage("CourseStudyhistoryMapper.queryCourseStudyHistoryListPage",courseStudyhistory,pageEntity);
    }
}
