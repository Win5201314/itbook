package com.inxedu.os.edu.dao.impl.course;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.edu.dao.course.CoursePackageDao;
import com.inxedu.os.edu.entity.course.CoursePackage;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
 * CoursePackageDaoImpl
 * @author www.inxedu.com
 */
@Repository("coursePackageDao")
public class CoursePackageDaoImpl extends GenericDaoImpl implements CoursePackageDao {
	/**
	 * 添加CoursePackage
	 */
	public void addCoursePackageBatch(List<CoursePackage> coursePackageList) {
		insert("CoursePackageMapper.addCoursePackageBatch", coursePackageList);
	}

	/**
	 * 通过id查询CoursePackage
	 */
	public CoursePackage queryCoursePackage(CoursePackage coursePackage) {
		return selectOne("CoursePackageMapper.queryCoursePackage", coursePackage);
	}
	/**
	 * 删除CoursePackage
	 */
	public void delCoursePackage(CoursePackage coursePackage){
		delete("CoursePackageMapper.delCoursePackage", coursePackage);
	}
	
	 /**
     * 修改套餐课程下的课程排序
     * @param coursePackage
     */
    public void updateCoursePackageOrderNum(CoursePackage coursePackage){
    	this.update("CoursePackageMapper.updateCoursePackageOrderNum", coursePackage);
    }

	public List<CoursePackage> quePackageByCouId(Long courseId) {
		return this.selectList("CoursePackageMapper.quePackageByCouId", courseId);
	}
	/**
	 * 删除CoursePackage
	 */
	public void delCoursePackageByCourseId(CoursePackage coursePackage){
		delete("CoursePackageMapper.delCoursePackageByCourseId", coursePackage);
	}

	@Override
	public List<CoursePackage> queryCoursePackageList(CoursePackage coursePackage) {
		return selectList("CoursePackageMapper.queryCoursePackage", coursePackage);
	}
}
