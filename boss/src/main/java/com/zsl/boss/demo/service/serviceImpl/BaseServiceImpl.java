package com.zsl.boss.demo.service.serviceImpl;

import com.zsl.boss.demo.common.CommonFunction;
import com.zsl.boss.demo.entity.*;
import com.zsl.boss.demo.mapper.BaseMapper;
import com.zsl.boss.demo.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BaseServiceImpl implements BaseService {

    @Autowired
    BaseMapper baseMapper;

    @Override
    public boolean updateHeadimgurl(int id, String headimgurl, int type) {
        return CommonFunction.isEqualOne(baseMapper.updateHeadimgurl(id, headimgurl, type));
    }

    @Override
    public boolean updateUserName(int id, String name, int type) {
        return false;
    }

    @Override
    public boolean updateSex(int id, String sex, int type) {
        return false;
    }

    @Override
    public boolean updateWxAccount(int id, String wxAccount, int type) {
        return false;
    }

    @Override
    public boolean updateLocation(int id, float longitude, float latitude, int type, Date weakTime) {
        return false;
    }

    @Override
    public boolean bindingOpenid(int id, String openid, int type) {
        return false;
    }

    @Override
    public boolean updatePassword(int id, String password, int type) {
        return false;
    }

    @Override
    public boolean updatePhone(int id, String phone, int type) {
        return false;
    }

    @Override
    public List<String> selectAllGreetWord(int type) {
        return null;
    }

    @Override
    public List<TalkedJob> selectTalkedJob(int id, int page) {
        return null;
    }

    @Override
    public Job selectJobById(int id) {
        return null;
    }

    @Override
    public List<TalkedResume> selectTalkedResume(int id, int page) {
        return null;
    }

    @Override
    public Resume selectResumeById(int id) {
        return null;
    }

    @Override
    public List<Interview> selectInterviewById(int id, int page, int type) {
        return null;
    }

    @Override
    public InterviewExt selectInterviewExt(int uid, int recruiterId, int jobId) {
        return null;
    }

    @Override
    public List<CollectJob> selectCollectJobById(int userId, int page) {
        return null;
    }

    @Override
    public List<CollectResume> selectCollectResums(int id, int page) {
        return null;
    }
}
