package com.zsl.boss.demo.service;

import com.zsl.boss.demo.entity.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 处理应聘的事务
 */
public interface UserService {

    /**
     * 注册登录时候，如果新用户就是插入，不是则更新对应的数据
     * @param phone
     * @return 是否成功
     */
    boolean insertOrUpdateUser(String phone);

    /**
     * 注册登录时候，如果新用户就是插入，不是则更新对应的数据
     * @param phone
     * @param openid
     */
    boolean insertOrUpdateUserWithOpenid(String phone, String openid);

    /**
     * 是否存在openid，没有则是新用户，需要绑定手机号
     * @param openid
     * @return
     */
    boolean isHaveOpenid(String openid);

    /**
     * 通过手机号判定是否存在该用户
     * @param phone 手机号
     * @return 用户信息对象
     */
    Users selectByPhone(String phone);

    /**
     * 通过手机号查找对应数据的主键
     * @param phone 手机号
     * @return 对应数据的主键
     */
    int selectIdByPhone(String phone);

    /**
     * 通过用户主键查找对应的角色集合
     * @param id 用户主键
     * @return 对应的角色集合
     */
    Set<Role> findRoleIdByUid(int id);

    /**
     * 通过角色主键查找对应角色的名字
     * @param roleId 角色主键
     * @return 对应角色的名字
     */
    String findRoleByRoleId(int roleId);

    /**
     * 通过角色主键查找权限主键集合
     * @param roleId 角色主键
     * @return 权限主键集合
     */
    Set<Integer> findPermissionIdByRoleId(int roleId);

    /**
     * 通过权限主键查找对应权限对象
     * @param permissionId 权限主键
     * @return 对应权限对象
     */
    Permission findPermissionById(int permissionId);

    /**
     * 更新参加工作年月
     * @param id 主键
     * @param joinWorkTime 年月
     * @return 返回是否更新成功
     */
    boolean updateJoinWorkTime(int id, String joinWorkTime);

    /**
     * 更新出生年月
     * @param id 主键
     * @param dateOfBirth 出生年月
     * @return 返回是否更新成功
     */
    boolean updateDateOfBirth(int id, String dateOfBirth);

    /**
     * 更新我的优势
     * @param id 主键
     * @param superiority 我的优势
     * @return 返回是否更新成功
     */
    boolean updateSuperiority(int id, String superiority);

    /**
     * 更新求职状态
     * @param id 主键
     * @param jobStatus 求职状态
     * @return 返回是否更新成功
     */
    boolean updateJobStatus(int id, Integer jobStatus);

    /**
     * 更新是否隐藏简历
     * @param id 主键
     * @param isHideResume 是否隐藏
     * @return 返回是否更新成功
     */
    boolean updateIsHideResume(int id, boolean isHideResume);

    /**
     * 更新是否对猎头隐藏简历
     * @param id 主键
     * @param isHideResumeToHeadhunter 是否隐藏
     * @return 返回是否更新成功
     */
    boolean updateIshideResumeToHeadhunter(int id, boolean isHideResumeToHeadhunter);

    /**
     * 更新是否查看猎头职位
     * @param id 主键
     * @param isLookHeadhunterJobs 是否查看
     * @return 返回是否更新成功
     */
    boolean updateIsLookHeadhunterJobs(int id, boolean isLookHeadhunterJobs);

    /**
     * 更新是否允许加密电话联系我
     * @param id 主键
     * @param isEncryptionTelephoneCanCallMe 是否允许加密电话联系我
     * @return 返回是否更新成功
     */
    boolean updateIsEncryptionTelephoneCanCallMe(int id, boolean isEncryptionTelephoneCanCallMe);

    /**
     * 添加屏蔽公司
     * @param shieldCompany 屏蔽公司
     * @return 返回是否添加成功
     */
    boolean addShieldCompany(ShieldCompany shieldCompany);

    /**
     * 查询屏蔽的公司
     * @param id 主键
     * @param page 分页数字
     * @return 返回屏蔽公司的集合
     */
    List<ShieldCompany> selectShieldCompany(int id, int page);

    /**
     * 查询公司
     * @param companyName 公司名字，大概名字，模糊查询
     * @param page 分页数字
     * @return 返回公司的集合
     */
    List<Company> selectCompanyByName(String companyName, int page);

    /**
     * 查询求职状态
     * @param id 主键
     * @return 返回求职状态
     */
    int selectJobStatus(int id);

    /**
     * 查询个人数据
     * @param id 主键
     * @return 返回个人数据对象
     */
    Users selectUserById(int id);

    /**
     * 添加求职期望
     * @param jobExpectation 求职期望对象
     * @return 返回是否添加成功
     */
    boolean addJobExpectation(JobExpectation jobExpectation);

    /**
     * 查询求职期望
     * @param id 主键
     * @param page 分页数
     * @return 返回求职期望集合
     */
    List<JobExpectation> selectJobExpectation(int id, int page);

    /**
     * 添加工作经历
     * @param workExperience 工作经历对象
     * @return 返回是否添加成功
     */
    boolean addWorkExperience(WorkExperience workExperience);

    /**
     * 查询工作经历
     * @param id 主键
     * @param page 分页数
     * @return 返回工作经历集合
     */
    List<WorkExperience> selectWorkExperience(int id, int page);

    /**
     * 添加项目经验
     * @param projectExpectation 项目经验对象
     * @return 返回是否添加成功
     */
    boolean addProjectExpectation(ProjectExpectation projectExpectation);

    /**
     * 查询项目经验
     * @param id 主键
     * @param page 分页数
     * @return 返回项目经验集合
     */
    List<ProjectExpectation> selectProjectExpectation(int id, int page);

    /**
     * 添加教育经历
     * @param educationExpectation 教育经历对象
     * @return 返回是否添加成功
     */
    boolean addEducationExpectation(EducationExpectation educationExpectation);

    /**
     * 查询教育经历
     * @param id 主键
     * @param page 分页数
     * @return 返回教育经历集合
     */
    List<EducationExpectation> selectEducationExpectation(int id, int page);

    /**
     * 添加个人社交主页对象
     * @param socialHomePage 社交主页对象
     * @return 返回是否添加成功
     */
    boolean addSocialHomePage(SocialHomePage socialHomePage);

    /**
     * 查询社交主页对象
     * @param id 主键
     * @param page 分页数
     * @return 返回社交主页对象集合
     */
    List<SocialHomePage> selectSocialHomePage(int id, int page);

    /**
     * 添加志愿服务对象
     * @param voluntary 志愿服务对象
     * @return 返回是否添加成功
     */
    boolean addVoluntary(Voluntary voluntary);

    /**
     * 查询志愿服务对象
     * @param id 主键
     * @param page 分页数
     * @return 返回志愿服务对象集合
     */
    List<Voluntary> selectVoluntary(int id, int page);

    /**
     * 查询 我关注的公司
     * @param id 主键
     * @param page 分页数
     * @return 返回关注公司的对象集合
     */
    List<AttentionCompany> selectAttentionCompany(int id, int page);

    /**
     * 返回公司对象
     * @param id 公司主键
     * @return 返回公司对象
     */
    Company selectCompanyById(int id);

    /**
     * 添加关注的公司
     * @param userId 应聘方主键
     * @param companyId 被关注公司的主键
     * @return 是否添加成功
     */
    boolean addAttentionCompany(int userId, int companyId);

    /**
     * 筛选公司，条件为：所属行业，公司规模，融资阶段
     * @param tmt 所属行业
     * @param scale 公司规模
     * @param financingStage financingStage
     * @return 返回符合条件的公司集合
     */
    List<Company> selectCompanyByTmtAndScaleAndFinancingStage(String tmt, String scale, String financingStage);

    /**
     *
     * @param workPlace
     * @param weakTime
     * @param name
     * @param education
     * @param salaryRange
     * @param experience
     * @param financingStage
     * @param scale
     * @return
     */
    List<Job> selectJobs(String workPlace, Date weakTime, String name, String education, Integer salaryRange,
                         Integer experience, Integer financingStage, Integer scale, Integer page);
}
