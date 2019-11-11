package com.inxedu.os.edu.dao.course;


import com.inxedu.os.edu.entity.kpoint.CourseKpoint;
import com.inxedu.os.edu.entity.kpoint.CourseKpointDto;

import java.util.List;
import java.util.Map;

/**
 * CourseKpoint管理接口
 * @author www.inxedu.com
 */
public interface CourseKpointDao {
    /**
     * 添加视频节点
     */
    int addCourseKpoint(CourseKpoint courseKpoint);
    
    /**
     * 通过课程ID，查询课程所属视频
     * @param courseId 课程ID
     * @return List<CourseKpoint>
     */
    List<CourseKpoint> queryCourseKpointByCourseId(int courseId);
    
    /**
     * 通过ID，查询视频详情
     * @param kpointId 视频ID
     * @return CourseKpointDto
     */
    CourseKpointDto queryCourseKpointById(int kpointId);
    
    /**
     * 修改视频节点
     * @param kpoint
     */
    void updateKpoint(CourseKpoint kpoint);
    
    /**
     * 删除视频节点
     * @param ids ID串
     */
    void deleteKpointByIds(String ids);
    
    /**
     * 修改视频节点父ID
     * @param map
     */
    void updateKpointParentId(Map<String, Integer> map);
    
    /**
     * 获取课程的 二级视频节点总数(只支持二级)
     */
    int getSecondLevelKpointCount(Long courseId);

    /**
     * 更新课程章节的播放数量
     */
    void updCourseKpointCount(int courseKpointId);

    /**
     * 根据条件查询章节
     */
    List<CourseKpointDto> queryKpointList(CourseKpoint courseKpoint);
    /**
     * 首页查询直播课程
     */
    List<CourseKpointDto> queryCourseNearestKpointList(CourseKpoint courseKpoint);

}