package com.inxedu.os.edu.dao.impl.member;



import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.edu.dao.member.CourseMemberDao;
import com.inxedu.os.edu.entity.member.CourseMember;
import com.inxedu.os.edu.entity.member.CourseMemberDTO;
import com.inxedu.os.edu.entity.member.MemberType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * CourseMember管理接口
 * User: qinggang.liu
 * Date: 2014-09-26
 */
@Repository("courseMemberDao")
public class CourseMemberDaoImpl extends GenericDaoImpl implements CourseMemberDao {



    /**
     * 添加课程所属会员
     * @param courseMembers
     */
    public void addCourseMember(List<CourseMember> courseMembers){
    	this.insert("CourseMemberMapper.addCourseMember", courseMembers);
    }
    /**
     * 删除课程所属会员
     * @param courseMemberDTO
     */
    public void delCourseMember(CourseMemberDTO courseMemberDTO){
    	this.delete("CourseMemberMapper.delCourseMember", courseMemberDTO);
    }

    /**
     * 根据课程获得课程的会员list
     *
     * @param courses
     * @return
     */
    public Map<Long, List<MemberType>> getCourseMemberTypeListByCourse(List<Long> courses) {
        if(ObjectUtils.isNull(courses)){
            return null;
        }
        try {
            //此service加异常防止获取时出异常，用到的没有事务处理
            Map<Long, List<MemberType>> result = new HashMap<Long, List<MemberType>>();
            List<CourseMemberDTO> list = this.selectList("CourseMemberMapper.getCourseMemberListByCourse", courses);
            for (CourseMemberDTO courseMemberDTO  : list) {//课程会员list
                List<MemberType> memberTypeList = new ArrayList<MemberType>();
                String courseId = courseMemberDTO.getId().toString();//课程id
                String memberTypeIds=courseMemberDTO.getMemberTag();//会员id字符串
                String memberTypeTitle =courseMemberDTO.getMemberTitle();//会员名称字符串
                String[] memId = new String(memberTypeIds).split(",");
                String[] memTitle = new String(memberTypeTitle).split(",");
                if (memId.length == memTitle.length) {
                    for (int i = 0; i < memId.length; i++) {
                        MemberType memberType = new MemberType();
                        memberType.setId(Long.valueOf(memId[i]));
                        memberType.setTitle(memTitle[i]);
                        memberTypeList.add(memberType);
                    }
                    result.put(Long.valueOf(courseId), memberTypeList);
                }
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<CourseMember> queryCourseMemberList(CourseMemberDTO courseMemberDTO) {
        return this.selectList("CourseMemberMapper.queryCourseMemberList",courseMemberDTO);
    }
}