package com.inxedu.os.edu.service.impl.member;


import com.inxedu.os.edu.dao.member.MemberTypeDao;
import com.inxedu.os.edu.entity.member.MemberType;
import com.inxedu.os.edu.service.member.MemberTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * MemberType管理接口
 * User: qinggang.liu
 * Date: 2014-09-26
 */
@Service("memberTypeService")
public class MemberTypeServiceImpl implements MemberTypeService {

 	@Autowired
    private MemberTypeDao memberTypeDao;
 	/**
     * 修改MemberType
     * @param MemberType 要修改的MemberType
     */
    public void updateMemberType(MemberType memberType){
     	memberTypeDao.updateMemberType(memberType);
    }

    /**
     * 根据id获取单个MemberType对象
     * @param id 要查询的id
     * @return MemberType
     */
    public MemberType getMemberTypeById(Long id){
    	return memberTypeDao.getMemberTypeById( id);
    }
    /**
     * 会员类型集合
     * @return
     */
    public List<MemberType> getMemberTypes(){
    	return memberTypeDao.getMemberTypes();
    }
    /**
     * Web会员类型集合
     * @return
     */
    public List<MemberType> getWebMemberTypes(){
    	return memberTypeDao.getWebMemberTypes();
    }
    /**
     * 添加会员类型
     * @param MemberType
     */
    public void addMemberType(MemberType memberType){
    	memberTypeDao.addMemberType(memberType);
    }
    /**
     * 停用启用会员类型
     * @param id
     */
    public void updateMemberTypeStatus(MemberType memberType){
    	memberTypeDao.updateMemberTypeStatus(memberType);
    }

    @Override
    public void delMemberType(Long id) {
        memberTypeDao.delMemberType(id);
    }

    /**
     * 课程的会员类型集合
     * @return
     */
    public List<MemberType> getMemberTypesBycourse(Long courseId){
    	return memberTypeDao.getMemberTypesBycourse(courseId);
    }
}