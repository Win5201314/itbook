package com.inxedu.os.edu.service.impl.course;


import com.inxedu.os.edu.entity.course.Course;
import com.inxedu.os.edu.entity.course.QueryCourse;
import com.inxedu.os.edu.entity.teacher.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inxedu.os.edu.dao.course.CourseTeacherDao;
import com.inxedu.os.edu.service.course.CourseTeacherService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * CourseTeacher管理接口
 * @author www.inxedu.com
 */
@Service("courseTeacherService")
public class CourseTeacherServiceImpl implements CourseTeacherService {

 	@Autowired
    private CourseTeacherDao courseTeacherDao;

	public void addCourseTeacher(String value) {
		courseTeacherDao.addCourseTeacher(value);
	}

	public void deleteCourseTeacher(int courseId) {
		courseTeacherDao.deleteCourseTeacher(courseId);
	}
	/**
	 * 添加课程与讲师的关联数据
	 */
	public void addCourseTeacher(HttpServletRequest request, Course course) {
		deleteCourseTeacher(course.getCourseId());
		String teacherIds = request.getParameter("teacherIdArr");
		if(teacherIds!=null && teacherIds.trim().length()>0){
			String[] tcIdArr = teacherIds.split(",");
			//string去重
			List list = Arrays.asList(tcIdArr);
			Set set = new HashSet(list);
			tcIdArr=(String [])set.toArray(new String[0]);
			StringBuffer val = new StringBuffer();
			for(int i=0;i<tcIdArr.length;i++){
				if(i<tcIdArr.length-1){
					val.append("("+course.getCourseId()+","+tcIdArr[i]+"),");
				}else{
					val.append("("+course.getCourseId()+","+tcIdArr[i]+")");
				}
			}
			addCourseTeacher(val.toString());
		}
	}

	@Override
	public List<Teacher> getTeachersByCourse(QueryCourse queryCourse) {
		return courseTeacherDao.getTeachersByCourse(queryCourse);
	}
}