package com.zsl.boss.demo.service;

import com.zsl.boss.demo.entity.*;

import java.util.Date;
import java.util.List;

/**
 * 处理公共事务
 */
public interface BaseService {

    /**
     * 更新用户头像
     * @param id 主键
     * @param headimgurl 头像地址
     * @param type 应聘方还是招聘方
     * @return 是否更新成功
     */
    boolean updateHeadimgurl(int id, String headimgurl, int type);

    /**
     * 更新名字
     * @param id 主键
     * @param name 名字
     * @param type 应聘方还是招聘方
     * @return 是否更新成功
     */
    boolean updateUserName(int id, String name, int type);

    /**
     * 更新性别
     * @param id 主键
     * @param sex 性别
     * @param type 应聘方还是招聘方
     * @return 是否更新成功
     */
    boolean updateSex(int id, String sex, int type);

    /**
     * 更新微信号
     * @param id 主键
     * @param wxAccount 微信号
     * @param type 应聘方还是招聘方
     * @return 是否更新成功
     */
    boolean updateWxAccount(int id, String wxAccount, int type);

    /**
     * 更新位置
     * @param id 主键
     * @param longitude 经度
     * @param latitude 纬度
     * @param type 应聘方还是招聘方 应聘方还要顺便把简历表weakTime这个字段更新下
     * @return 是否更新成功
     */
    boolean updateLocation(int id, float longitude, float latitude, int type, Date weakTime);

    /**
     * 更新openid
     * @param id 主键
     * @param openid 微信唯一标识
     * @param type 应聘方还是招聘方
     * @return 是否更新成功
     */
    boolean bindingOpenid(int id, String openid, int type);

    /**
     * 更新密码
     * @param id 主键
     * @param password 密码
     * @param type 应聘方还是招聘方
     * @return 是否更新成功
     */
    boolean updatePassword(int id, String password, int type);

    /**
     * 更新手机号
     * @param id 主键
     * @param phone 手机号
     * @param type 应聘方还是招聘方
     * @return 是否更新成功
     */
    boolean updatePhone(int id, String phone, int type);

    /**
     * 查找所有的打招呼用语
     * @param type 应聘方还是招聘方
     * @return 返回所有的打招呼用语
     */
    List<String> selectAllGreetWord(int type);

    /**
     * 沟通过的职位
     * @param id 应聘方主键
     * @param page 分页数
     * @return 沟通过的职位中间表集合
     */
    List<TalkedJob> selectTalkedJob(int id, int page);

    /**
     * 通过主键查找职位
     * @param id 职位主键
     * @return 返回职位
     */
    Job selectJobById(int id);

    /**
     * 沟通过的简历
     * @param id 招聘方主键
     * @param page 分页数
     * @return 沟通过的简历中间表集合
     */
    List<TalkedResume> selectTalkedResume(int id, int page);

    /**
     * 通过主键查找简历
     * @param id 简历主键
     * @return 返回简历
     */
    Resume selectResumeById(int id);

    /**
     * 查询面试
     * @param id 应聘或者招聘主键
     * @param page 分页
     * @param type 哪一方
     * @return 面试集合
     */
    List<Interview> selectInterviewById(int id, int page, int type);

    /**
     * 查找面试拓展对象
     * @param uid 应聘方
     * @param recruiterId 招聘方
     * @param jobId 职位主键
     * @return 面试拓展对象
     */
    InterviewExt selectInterviewExt(int uid, int recruiterId, int jobId);

    /**
     * 查询收藏的职业中间表
     * @param userId 应聘方主键
     * @param page 分页数
     * @return 收藏的职业中间表集合
     */
    List<CollectJob> selectCollectJobById(int userId, int page);

    /**
     * 查询 中间表CollectResume
     * @param id 招聘方id
     * @param page 分页数
     * @return 返回中间表集合
     */
    List<CollectResume> selectCollectResums(int id, int page);
}
