package com.inxedu.os.edu.dao.impl.member;


import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.edu.dao.member.MemberTypeDao;
import com.inxedu.os.edu.entity.member.MemberType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * MemberType
 * User: qinggang.liu bis@foxmail.com
 * Date: 2014-09-26
 */
 @Repository("memberTypeDao")
public class MemberTypeDaoImpl extends GenericDaoImpl implements MemberTypeDao {


    public void updateMemberType(MemberType memberType) {
        this.update("MemberTypeMapper.updateMemberType",memberType);
    }

    public MemberType getMemberTypeById(Long id) {
        return this.selectOne("MemberTypeMapper.getMemberTypeById",id);
    }
    /**
     * 会员类型集合
     * @return
     */
    public List<MemberType> getMemberTypes(){
    	return this.selectList("MemberTypeMapper.getMemberTypes",0);
    }
    /**
     * Web会员类型集合
     * @return
     */
    public List<MemberType> getWebMemberTypes(){
    	return this.selectList("MemberTypeMapper.getWebMemberTypes", 0);
    }
    /**
     * 添加会员类型
     * @param MemberType
     */
    public void addMemberType(MemberType memberType){
    	this.insert("MemberTypeMapper.addMemberType",memberType);
    }
    /**
     * 停用启用会员类型
     * @param id
     */
    public void updateMemberTypeStatus(MemberType memberType){
    	this.update("MemberTypeMapper.updateMemberTypeStatus", memberType);
    }
    /**
     * 课程的会员类型集合
     * @return
     */
    public List<MemberType> getMemberTypesBycourse(Long courseId){
    	return this.selectList("MemberTypeMapper.getMemberTypesBycourse", courseId);
    }

    @Override
    public void delMemberType(Long id) {
        this.delete("MemberTypeMapper.delMemberType", id);
    }
}
