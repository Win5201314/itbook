package com.inxedu.os.edu.service.impl.teacher;

import com.inxedu.os.common.cache.CacheUtil;
import com.inxedu.os.common.constants.CacheConstans;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.edu.dao.teacher.TeacherDao;
import com.inxedu.os.edu.entity.teacher.QueryTeacher;
import com.inxedu.os.edu.entity.teacher.Teacher;
import com.inxedu.os.edu.service.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Teacher管理接口
 * @author www.inxedu.com
 */
@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {
	@Autowired
	private TeacherDao teacherDao;

	public int addTeacher(Teacher teacher) {
		return teacherDao.addTeacher(teacher);
	}

	public void deleteTeacherById(int tcId) {
		teacherDao.deleteTeacherById(tcId);
	}

	public void updateTeacher(Teacher teacher) {
		teacherDao.updateTeacher(teacher);
	}

	public Teacher getTeacherById(int tcId) {
		return teacherDao.getTeacherById(tcId);
	}

	public List<Teacher> queryTeacherListPage(QueryTeacher query, PageEntity page) {
		return teacherDao.queryTeacherListPage(query, page);
	}

	public List<Map<String, Object>> queryCourseTeacerList(int courseId) {
		List<Map<String, Object>> courseTcList=(List<Map<String, Object>>) CacheUtil.get(CacheConstans.COURSE_TEACHER_LIST+"_"+courseId);
		if(ObjectUtils.isNull(courseTcList)){
			courseTcList = teacherDao.queryCourseTeacerList(courseId);
			//默认缓存30秒
			CacheUtil.set(CacheConstans.COURSE_TEACHER_LIST+"_"+courseId,courseTcList,30);
		}
		return courseTcList;
	}

	public List<Teacher> queryTeacherList(QueryTeacher queryTeacher) {
		return teacherDao.queryTeacherList(queryTeacher);
	}
}