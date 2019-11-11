package com.zsl.boss.demo.service;

import com.zsl.boss.demo.entity.*;

import java.util.Date;
import java.util.List;

/**
 * 处理招聘方的事务
 */
public interface RecruitersService {

    /**
     * 更新电子邮箱
     * @param id 主键
     * @param email 邮箱
     * @return 是否更新成功
     */
    boolean updateEmail(int id, String email);

    /**
     * 更新职务
     * @param id 主键
     * @param duty 职务
     * @return 是否更新成功
     */
    boolean updateDuty(int id, String duty);

    /**
     * 更新团队介绍
     * @param id 主键
     * @param teamIntroduction 团队介绍
     * @return 是否更新成功
     */
    boolean updateTeamIntroduction(int id, String teamIntroduction);

    /**
     * 更新团队亮点
     * @param id 主键
     * @param teamLightspot 团队亮点
     * @return 是否更新成功
     */
    boolean updateTeamLightspot(int id, String teamLightspot);

    /**
     * 添加职位
     * @param job 职位对象
     * @return 是否添加成功
     */
    boolean addJobs(Job job);

    /**
     * 查询发布职位
     * @param id 主键
     * @param page 分页数
     * @param status 查询哪个状态
     * @return 符合条件的职位集合
     */
    List<Job> selectJobsByStatus(int id, int page, int status);

    /**
     * 查询所有发布的职位
     * @param id 发布者主键
     * @param page 分页数
     * @return 发布者发布的所有职位
     */
    List<Job> selectAllJobsById(int id, int page);

    /**
     * 查询招聘方道具
     * @param id 招聘方主键
     * @param page 分页数
     * @return 道具集合
     */
    List<Prop> selectMyPropByRecruterId(int id, int page);

    /**
     * 添加新的面试
     * @param interview 面试对象
     * @return 是否添加成功
     */
    boolean addInterview(Interview interview);

    /**
     * 添加收藏简历
     * @param collectResume 简历对象
     * @return 是否添加成功
     */
    boolean addCollectResume(CollectResume collectResume);

    /**
     * 查询符合条件的牛人简历
     * @param name 职位名字 模糊查询
     * @param workAge 工作年数 工作经验
     * @param education 学历级别
     * @param salaryRange 薪资要求
     * @param page 分页数
     * @param weakTime 再次使用软件时间
     * @return 符合条件的牛人简历集合
     */
    List<Resume> selectResumeByNameAndworkAgeAndeducationAndsalaryRange(String name, Integer workAge, Integer education,
                                                                        Integer salaryRange, Integer page, Date weakTime);
}
