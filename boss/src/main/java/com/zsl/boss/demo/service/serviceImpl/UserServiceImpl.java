package com.zsl.boss.demo.service.serviceImpl;

import com.zsl.boss.demo.common.CommonFunction;
import com.zsl.boss.demo.entity.*;
import com.zsl.boss.demo.mapper.UserMapper;
import com.zsl.boss.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean insertOrUpdateUser(String phone) {
        Users user = userMapper.selectUserByPhone(phone);
        if (user != null) {
            //已经注册的用户,不用处理
            return true;
        } else {
            //新用户插入数据表
            return CommonFunction.isEqualOne(userMapper.insertWithPhone(phone));
        }
    }

    @Override
    public boolean insertOrUpdateUserWithOpenid(String phone, String openid) {
        Users user = userMapper.selectUserByOpenid(openid);
        if (user != null) {
            //插入手机号, 绑定手机号
            return CommonFunction.isEqualOne(userMapper.updateWithPhoneByOpenid(phone, openid));
        } else {
            //插入手机号和openid,新用户
            return CommonFunction.isEqualOne(userMapper.insertWithPhoneAndOpenid(phone, openid));
        }
    }

    @Override
    public boolean isHaveOpenid(String openid) {
        Users user = userMapper.selectUserByOpenid(openid);
        return user != null;
    }

    @Override
    public Users selectByPhone(String phone) {
        return userMapper.selectByPhone(phone);
    }

    @Override
    public int selectIdByPhone(String phone) {
        return userMapper.selectIdByPhone(phone);
    }

    @Override
    public Set<Role> findRoleIdByUid(int id) {
        return null;
    }

    @Override
    public String findRoleByRoleId(int roleId) {
        return null;
    }

    @Override
    public Set<Integer> findPermissionIdByRoleId(int roleId) {
        return null;
    }

    @Override
    public Permission findPermissionById(int permissionId) {
        return null;
    }

    @Override
    public boolean updateJoinWorkTime(int id, String joinWorkTime) {
        return CommonFunction.isEqualOne(userMapper.updateJoinWorkTime(id, joinWorkTime));
    }

    @Override
    public boolean updateDateOfBirth(int id, String dateOfBirth) {
        return CommonFunction.isEqualOne(userMapper.updateDateOfBirth(id, dateOfBirth));
    }

    @Override
    public boolean updateSuperiority(int id, String superiority) {
        return CommonFunction.isEqualOne(userMapper.updateSuperiority(id, superiority));
    }

    @Override
    public boolean updateJobStatus(int id, Integer jobStatus) {
        return CommonFunction.isEqualOne(userMapper.updateJobStatus(id, jobStatus));
    }

    @Override
    public boolean updateIsHideResume(int id, boolean isHideResume) {
        return CommonFunction.isEqualOne(userMapper.updateIsHideResume(id, isHideResume));
    }

    @Override
    public boolean updateIshideResumeToHeadhunter(int id, boolean isHideResumeToHeadhunter) {
        return CommonFunction.isEqualOne(userMapper.updateIshideResumeToHeadhunter(id, isHideResumeToHeadhunter));
    }

    @Override
    public boolean updateIsLookHeadhunterJobs(int id, boolean isLookHeadhunterJobs) {
        return CommonFunction.isEqualOne(userMapper.updateIsLookHeadhunterJobs(id, isLookHeadhunterJobs));
    }

    @Override
    public boolean updateIsEncryptionTelephoneCanCallMe(int id, boolean isEncryptionTelephoneCanCallMe) {
        return CommonFunction.isEqualOne(userMapper.updateIsEncryptionTelephoneCanCallMe(id, isEncryptionTelephoneCanCallMe));
    }

    @Override
    public boolean addShieldCompany(ShieldCompany shieldCompany) {
        return false;
    }

    @Override
    public List<ShieldCompany> selectShieldCompany(int id, int page) {
        return null;
    }

    @Override
    public List<Company> selectCompanyByName(String companyName, int page) {
        return null;
    }

    @Override
    public int selectJobStatus(int id) {
        return userMapper.selectJobStatus(id);
    }

    @Override
    public Users selectUserById(int id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public boolean addJobExpectation(JobExpectation jobExpectation) {
        return false;
    }

    @Override
    public List<JobExpectation> selectJobExpectation(int id, int page) {
        return null;
    }

    @Override
    public boolean addWorkExperience(WorkExperience workExperience) {
        return false;
    }

    @Override
    public List<WorkExperience> selectWorkExperience(int id, int page) {
        return null;
    }

    @Override
    public boolean addProjectExpectation(ProjectExpectation projectExpectation) {
        return false;
    }

    @Override
    public List<ProjectExpectation> selectProjectExpectation(int id, int page) {
        return null;
    }

    @Override
    public boolean addEducationExpectation(EducationExpectation educationExpectation) {
        return false;
    }

    @Override
    public List<EducationExpectation> selectEducationExpectation(int id, int page) {
        return null;
    }

    @Override
    public boolean addSocialHomePage(SocialHomePage socialHomePage) {
        return false;
    }

    @Override
    public List<SocialHomePage> selectSocialHomePage(int id, int page) {
        return null;
    }

    @Override
    public boolean addVoluntary(Voluntary voluntary) {
        return false;
    }

    @Override
    public List<Voluntary> selectVoluntary(int id, int page) {
        return null;
    }

    @Override
    public List<AttentionCompany> selectAttentionCompany(int id, int page) {
        return null;
    }

    @Override
    public Company selectCompanyById(int id) {
        return null;
    }

    @Override
    public boolean addAttentionCompany(int userId, int companyId) {
        return false;
    }

    @Override
    public List<Company> selectCompanyByTmtAndScaleAndFinancingStage(String tmt, String scale, String financingStage) {
        return null;
    }

    @Override
    public List<Job> selectJobs(String workPlace, Date weakTime, String name, String education, Integer salaryRange, Integer experience, Integer financingStage, Integer scale, Integer page) {
        return null;
    }

}
