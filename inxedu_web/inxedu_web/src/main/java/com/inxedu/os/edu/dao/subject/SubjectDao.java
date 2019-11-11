package com.inxedu.os.edu.dao.subject;

import com.inxedu.os.edu.entity.subject.QuerySubject;
import com.inxedu.os.edu.entity.subject.Subject;

import java.util.List;
import java.util.Map;

/**
 * 专业dao层接口
 * @author www.inxedu.com
 */
public interface SubjectDao {
	/**
	 * 创建专业
	 * @param subject
	 * @return 返回专业ID
	 */
	int createSubject(Subject subject);
	
	/**
	 * 查询专业列表
	 * @return List<Subject>
	 */
	List<Subject> getSubjectList(QuerySubject query);
	
	/**
	 * 修改专业父ID
	 * @param map
	 */
	void updateSubjectParentId(Map<String, Object> map);
	
	/**
	 * 修改专业
	 * @param subject
	 */
	void updateSubject(Subject subject);
	/**
	 * 修改排序
	 */
	void updateSubjectSort(Subject subject);
	/**
	 * 删除专业 
	 * @param subjectId 要删除的专业ID
	 */
	void deleteSubject(int subjectId);
	
	/**
     * 查询项目
     */
	Subject getSubjectBySubject(Subject subject);
    
    /**
     * 根据父级ID查找子项目集合
     */
	List<Subject> getSubjectListByOne(Long subjectId);
}