package com.zsl.boss.demo.service.serviceImpl;

import com.zsl.boss.demo.entity.*;
import com.zsl.boss.demo.service.RecruitersService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RecruitersServiceImpl implements RecruitersService {

    @Override
    public boolean updateEmail(int id, String email) {
        return false;
    }

    @Override
    public boolean updateDuty(int id, String duty) {
        return false;
    }

    @Override
    public boolean updateTeamIntroduction(int id, String teamIntroduction) {
        return false;
    }

    @Override
    public boolean updateTeamLightspot(int id, String teamLightspot) {
        return false;
    }

    @Override
    public boolean addJobs(Job job) {
        return false;
    }

    @Override
    public List<Job> selectJobsByStatus(int id, int page, int status) {
        return null;
    }

    @Override
    public List<Job> selectAllJobsById(int id, int page) {
        return null;
    }

    @Override
    public List<Prop> selectMyPropByRecruterId(int id, int page) {
        return null;
    }

    @Override
    public boolean addInterview(Interview interview) {
        return false;
    }

    @Override
    public boolean addCollectResume(CollectResume collectResume) {
        return false;
    }

    @Override
    public List<Resume> selectResumeByNameAndworkAgeAndeducationAndsalaryRange(String name, Integer workAge, Integer education, Integer salaryRange, Integer page, Date weakTime) {
        return null;
    }
}
