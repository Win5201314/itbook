package com.inxedu.os.edu.service.impl.course;

import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.StringUtils;
import com.inxedu.os.edu.dao.course.CoursePackageDao;
import com.inxedu.os.edu.entity.course.CoursePackage;
import com.inxedu.os.edu.service.course.CoursePackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * CoursePackageServiceImpl
 * @author www.inxedu.com
 */
@Service("coursePackageService")
public class CoursePackageServiceImpl implements CoursePackageService {
    @Autowired
    private CoursePackageDao classCourseDao;
    /**
     * 添加CoursePackage
     */
    public void addCoursePackageBatch(String ids,Long courseId){
        if(ObjectUtils.isNotNull(courseId)&& StringUtils.isNotEmpty(ids)){
            String[] courseList= ids.split(",");
            List<CoursePackage> classCourseList = new ArrayList<CoursePackage>();
            //筛选已经添加过的课程
            for(String str:courseList){
                if(StringUtils.isNotEmpty(str)){
                    CoursePackage classCourse = new CoursePackage();
                    classCourse.setCourseId(Long.valueOf(str));
                    classCourse.setMainCourseId(courseId);
                    classCourse.setOrderNum(0L);
                    if(ObjectUtils.isNull(classCourseDao.queryCoursePackage(classCourse))){
                        classCourseList.add(classCourse);
                    }
                }
            }
            //批量添加课程包关联的课程
            if(ObjectUtils.isNotNull(classCourseList)){
                classCourseDao.addCoursePackageBatch(classCourseList);
            }
        }
    }
    /**
     * 删除CoursePackage
     */
    public void delCoursePackage(CoursePackage classCourse){
        classCourseDao.delCoursePackage(classCourse);
    }

    /**
     * 修改套餐课程下的课程排序
     * @param classCourse
     */
    public void updateCoursePackageOrderNum(CoursePackage classCourse) {
        classCourseDao.updateCoursePackageOrderNum(classCourse);
    }
    public List<CoursePackage> quePackageByCouId(Long courseId) {
        return classCourseDao.quePackageByCouId(courseId);
    }

    @Override
    public List<CoursePackage> queryCoursePackageList(CoursePackage coursePackage) {
        return classCourseDao.queryCoursePackageList(coursePackage);
    }
}