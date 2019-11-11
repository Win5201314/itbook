package com.inxedu.os.edu.dao.member;





import com.inxedu.os.edu.entity.member.CourseMember;
import com.inxedu.os.edu.entity.member.CourseMemberDTO;
import com.inxedu.os.edu.entity.member.MemberType;

import java.util.List;
import java.util.Map;


/**
 * CourseMember管理接口
 * User: qinggang.liu
 * Date: 2014-09-26
 */
public interface CourseMemberDao {


   
    
    /**
     * 添加课程所属会员
     * @param courseMembers
     */
    void addCourseMember(List<CourseMember> courseMembers);
    /**
     * 删除课程所属会员
     * @param courseMemberDTO
     */
    void delCourseMember(CourseMemberDTO courseMemberDTO);

    /**
     * 根据课程获得课程的会员list
     *
     * @param courses
     * @return
     */
    Map<Long, List<MemberType>> getCourseMemberTypeListByCourse(List<Long> courses);
    /*条件查询*/
    List<CourseMember> queryCourseMemberList(CourseMemberDTO courseMemberDTO);
}