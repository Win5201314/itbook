package com.inxedu.os.edu.dao.impl.course;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.edu.dao.course.CourseNoteDao;
import com.inxedu.os.edu.entity.course.CourseNote;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * CourseNoteDao接口实现类
 * @author www.inxedu.com
 */
@Repository("courseNoteDao")
public class CourseNoteDaoImpl extends GenericDaoImpl implements CourseNoteDao {
	/**
	 * 添加CourseNote
	 * 
	 * @param courseNote
	 *            要添加的CourseNote
	 * @return id
	 */
	public Long addCourseNote(CourseNote courseNote) {
		return this.insert("CourseNoteMapper.createCourseNote", courseNote);
	}

	/**
	 * 修改CourseNote
	 * 
	 * @param courseNote
	 *            要修改的CourseNote
	 */
	public void updateCourseNote(CourseNote courseNote) {
		this.update("CourseNoteMapper.updateCourseNote", courseNote);
	}


	/**
	 * 根据用户id和节点id查询笔记
	 * 
	 * @return CourseNote
	 */
	public CourseNote getCourseNoteByKpointIdAndUserId(Long kpointId, Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("kpointId", kpointId);
		map.put("userId", userId);
		List<CourseNote> courseNoteList = this.selectList("CourseNoteMapper.getCourseNoteByKpointIdAndUserId", map);
		// 如果返回为多个只取第一个
		if (ObjectUtils.isNotNull(courseNoteList) && courseNoteList.size() > 0) {
			return courseNoteList.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<CourseNote> queryCourseNoteForUc(CourseNote courseNote, PageEntity page) {
		return this.queryForListPageCount("CourseNoteMapper.queryCourseNoteForUcPage",courseNote,page);
	}

	@Override
	public void delNote( Long courseNoteId) {
		this.delete("CourseNoteMapper.delNote",courseNoteId);
	}
}
