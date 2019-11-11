package com.inxedu.os.edu.service.impl.member;


import com.inxedu.os.edu.dao.member.CourseMemberDao;
import com.inxedu.os.edu.entity.member.CourseMember;
import com.inxedu.os.edu.entity.member.CourseMemberDTO;
import com.inxedu.os.edu.service.member.CourseMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * CourseMember管理接口
 * User: qinggang.liu
 * Date: 2014-09-26
 */
@Service("courseMemberService")
public class CourseMemberServiceImpl implements CourseMemberService {
	
	@Autowired
	private CourseMemberDao courseMemberDao;


    
    /**
     * 添加课程所属会员
     * @param courseMembers
     */
    public void addCourseMember(List<CourseMember> courseMembers){
    	courseMemberDao.addCourseMember(courseMembers);
    }
    /**
     * 删除课程所属会员
     * @param courseMemberDTO
     */
    public void delCourseMember(CourseMemberDTO courseMemberDTO){
    	courseMemberDao.delCourseMember(courseMemberDTO);
    }

    @Override
    public List<CourseMember> queryCourseMemberList(CourseMemberDTO courseMemberDTO) {
        return courseMemberDao.queryCourseMemberList(courseMemberDTO);
    }
}