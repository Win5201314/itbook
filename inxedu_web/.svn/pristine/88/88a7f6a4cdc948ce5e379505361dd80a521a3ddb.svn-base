package com.inxedu.os.edu.dao.course;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.course.CourseNote;

import java.util.List;

/**
 * CourseNote管理接口
 * @author www.inxedu.com
 */
public interface CourseNoteDao {

	/**
	 * 添加CourseNote
	 * 
	 * @param courseNote
	 *            要添加的CourseNote
	 * @return id
	 */
	Long addCourseNote(CourseNote courseNote);


	/**
	 * 修改CourseNote
	 * 
	 * @param courseNote
	 *            要修改的CourseNote
	 */
	void updateCourseNote(CourseNote courseNote);


	/**
	 * 根据用户id和节点id查询笔记
	 * 
	 * @return CourseNote
	 */
	CourseNote getCourseNoteByKpointIdAndUserId(Long kpointId, Long userId);
	/**
	 * 查询笔记
	 *
	 * @return CourseNote
	 */
	List<CourseNote> queryCourseNoteForUc(CourseNote courseNote, PageEntity pageEntity);
	/*根据id删除笔记*/
	void delNote( Long courseNoteId);

}