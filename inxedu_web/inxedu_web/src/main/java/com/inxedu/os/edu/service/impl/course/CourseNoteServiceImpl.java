package com.inxedu.os.edu.service.impl.course;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.edu.dao.course.CourseNoteDao;
import com.inxedu.os.edu.entity.course.CourseNote;
import com.inxedu.os.edu.service.course.CourseNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CourseNoteService管理接口实现
 * @author www.inxedu.com
 */
@Service("courseNoteService")
public class CourseNoteServiceImpl implements CourseNoteService {

	@Autowired
	private CourseNoteDao courseNoteDao;

	/**
	 * 添加CourseNote
	 *  要添加的CourseNote
	 */
	public String addCourseNote(CourseNote courseNote) {
		if (ObjectUtils.isNull(getCourseNoteByKpointIdAndUserId(courseNote.getKpointId(), courseNote.getUserId()))) {
			// 更新课程的笔记数量
			courseNoteDao.addCourseNote(courseNote);
		} else {
			courseNoteDao.updateCourseNote(courseNote);
		}
		return "success";
	}

	/**
	 * 根据用户id和节点id查询笔记
	 */
	public CourseNote getCourseNoteByKpointIdAndUserId(Long kpointId, Long userId) {
		return courseNoteDao.getCourseNoteByKpointIdAndUserId(kpointId, userId);
	}
	@Override
	public List<CourseNote> queryCourseNoteForUc(CourseNote courseNote, PageEntity pageEntity) {
		return courseNoteDao.queryCourseNoteForUc(courseNote, pageEntity);
	}

	@Override
	public void delNote(Long courseNoteId) {
		courseNoteDao.delNote(courseNoteId);
	}
}