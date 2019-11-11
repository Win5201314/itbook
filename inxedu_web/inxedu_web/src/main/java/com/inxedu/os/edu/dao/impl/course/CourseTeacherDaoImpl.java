package com.inxedu.os.edu.dao.impl.course;


import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.edu.dao.course.CourseTeacherDao;
import com.inxedu.os.edu.entity.course.CourseDto;
import com.inxedu.os.edu.entity.course.QueryCourse;
import com.inxedu.os.edu.entity.teacher.Teacher;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author www.inxedu.com
 *
 */
@Repository("courseTeacherDao")
public class CourseTeacherDaoImpl extends GenericDaoImpl implements CourseTeacherDao {

	
	public void addCourseTeacher(String value) {
		this.insert("CourseTeacherMapper.createCourseTeacher", value);
		
	}

	
	public void deleteCourseTeacher(int courseId) {
		this.delete("CourseTeacherMapper.deleteCourseTeacher", courseId);
	}

	/**
	 * 根据课程获得课程的讲师list
	 * @param courses
	 * @return
	 */
	public Map<Long, List<Teacher>> getCourseTeacherListByCourse(List<Long> courses) {
		if(ObjectUtils.isNull(courses)){
			return null;
		}
		try {
			//此service加异常防止获取时出异常，用到的没有事务处理
			Map<Long, List<Teacher>> result = new HashMap<Long, List<Teacher>>();
			List<CourseDto> list = this.selectList("CourseTeacherMapper.getCourseTeacherListByCourse", courses);
			for (CourseDto courseDto  : list) {
				List<Teacher> teacherList = new ArrayList<Teacher>();
				String courseId = courseDto.getCourseId()+"";//map.get("courseId").toString();
				// byte[] teacherIds = (byte[]) map.get("teacherIds");
				String teacherIds=courseDto.getTitle();//map.get("teacherIds").toString();
				String teacherName =courseDto.getCourseName();// map.get("teacherNames").toString();
				String teacherPicPath =courseDto.getLogo();
				String teacherIsStar =courseDto.getSubjectName();
				String[] teaId = new String(teacherIds).split(",");
				String[] teaName = new String(teacherName).split(",");
				String[] teaPicPath = new String(teacherPicPath).split(",");
				String[] teaIsStar = new String(teacherIsStar).split(",");
				if (teaId.length == teaName.length) {
					for (int i = 0; i < teaId.length; i++) {
						Teacher teacher = new Teacher();
						teacher.setId(Integer.parseInt(teaId[i]));
						teacher.setName(teaName[i]);
						teacher.setPicPath(teaPicPath[i]);
						teacher.setIsStar(Integer.parseInt(teaIsStar[i]));
						teacherList.add(teacher);
					}
					result.put(Long.valueOf(courseId), teacherList);
				}

			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<Teacher> getTeachersByCourse(QueryCourse queryCourse) {
		return this.selectList("CourseTeacherMapper.getTeachersByCourse",queryCourse);
	}

}
