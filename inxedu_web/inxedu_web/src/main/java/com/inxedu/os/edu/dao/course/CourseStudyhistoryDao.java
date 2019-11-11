package com.inxedu.os.edu.dao.course;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.course.CourseStudyhistory;

import java.util.List;


/**
 * CourseStudyhistory管理接口
 * @author www.inxedu.com
 */
public interface CourseStudyhistoryDao {

    /**
     * 添加CourseStudyhistory
     * @param courseStudyhistory 要添加的CourseStudyhistory
     * @return id
     */
    Long addCourseStudyhistory(CourseStudyhistory courseStudyhistory);
    
    
    


    /**
     * 修改CourseStudyhistory
     * @param courseStudyhistory 要修改的CourseStudyhistory
     */
    void updateCourseStudyhistory(CourseStudyhistory courseStudyhistory);


    /**
     * 根据条件获取CourseStudyhistory列表
     * @param courseStudyhistory 查询条件
     * @return List<CourseStudyhistory>
     */
    List<CourseStudyhistory> getCourseStudyhistoryList(CourseStudyhistory courseStudyhistory);
    

	/**
	 * 查看 课程下的 所有的学习记录
	 */
    List<CourseStudyhistory> getCourseStudyhistoryListByCouId(Long courseId);
	
	/**
	 * 查看 课程下的学习记录 总人数
	 */
    int getCourseStudyhistoryCountByCouId(Long courseId);
    /**
     * 查看 某人的最近一条学习记录
     */
    CourseStudyhistory getRecentStudyhistoryByUserId(Long userId);
    /*
    分页查询学习记录
    */
    List<CourseStudyhistory> queryCourseStudyHistoryListPage (CourseStudyhistory courseStudyhistory , PageEntity pageEntity);
}