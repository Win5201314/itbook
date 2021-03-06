package com.inxedu.os.edu.service.website;


import java.util.List;

import com.inxedu.os.edu.entity.website.WebsiteCourse;

/**
 * 推荐课程分类管理接口
 * @author www.inxedu.com
 */
public interface WebsiteCourseService {

	 /**
     * 推荐课程分类列表
     */
     List<WebsiteCourse> queryWebsiteCourseList();

    /**
     * 查询推荐课程分类
     */
    WebsiteCourse queryWebsiteCourseById(int id);
    /**
     * 修改推荐课程分类
     */
    void updateWebsiteCourseById(WebsiteCourse websiteCourse);
    /**
     * 添加推荐课程分类
     */
    void addWebsiteCourse(WebsiteCourse websiteCourse);

    /**
     * 删除推荐课程分类及分类下推荐课程
     */
    void deleteWebsiteCourseDetailById(int id);
}