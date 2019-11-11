package com.inxedu.os.edu.dao.member;
import com.inxedu.os.edu.entity.member.MemberType;

import java.util.List;


/**
 * MemberType管理接口
 * User: qinggang.liu
 * Date: 2014-09-26
 */
public interface MemberTypeDao {

	/**
     * 修改MemberType
     * @param memberType 要修改的MemberType
     */
    void updateMemberType(MemberType memberType);

    /**
     * 根据id获取单个MemberType对象
     * @param id 要查询的id
     * @return MemberType
     */
    MemberType getMemberTypeById(Long id);

    /**
     * 添加会员类型
     * @param memberType
     */
    void addMemberType(MemberType memberType);

    /**
     * 停用启用会员类型
     * @param memberType
     */
    void updateMemberTypeStatus(MemberType memberType);

    /**
     * 会员类型集合
     * @return
     */
    List<MemberType> getMemberTypes();

    /**
     * Web会员类型集合
     * @return
     */
    List<MemberType> getWebMemberTypes();

    /**
     * 课程的会员类型集合
     * @return
     */
    List<MemberType> getMemberTypesBycourse(Long courseId);

    /**
     * 删除会员类型
     * @param id
     */
    void delMemberType(Long id);
}