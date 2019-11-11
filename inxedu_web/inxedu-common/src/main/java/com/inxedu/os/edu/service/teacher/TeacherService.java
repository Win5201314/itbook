package com.inxedu.os.edu.service.teacher;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.teacher.QueryTeacher;
import com.inxedu.os.edu.entity.teacher.Teacher;

import java.util.List;
import java.util.Map;

/**
 * Teacher管理接口
 * @author www.inxedu.com
 */
public interface TeacherService {
	/**
     * 添加Teacher
     */
    int addTeacher(Teacher teacher);
	/**
     * 删除讲师
     */
    void deleteTeacherById(int tcId);
	/**
     * 修改讲师信息
     */
    void updateTeacher(Teacher teacher);
	/**
     * 通过讲师ID，查询讲师数据
     */
    Teacher getTeacherById(int tcId);
	/**
     * 分页查询讲师列表
     */
    List<Teacher> queryTeacherListPage(QueryTeacher query, PageEntity page);
	/**
     * 查询课程讲师列表
     */
    List<Map<String,Object>> queryCourseTeacerList(int courseId);
	/**
     * 条件查询老师列表
     */
    List<Teacher> queryTeacherList(QueryTeacher queryTeacher);
}