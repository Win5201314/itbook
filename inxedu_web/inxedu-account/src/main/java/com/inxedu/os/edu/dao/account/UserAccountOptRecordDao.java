package com.inxedu.os.edu.dao.account;


import com.inxedu.os.edu.entity.account.UserAccountOptRecord;

import java.util.List;

/**
 * @author www.inxedu.com
 */
public interface UserAccountOptRecordDao {

    /**
     * 添加UserAccountOptRecord
     *
     * @param userAccountOptRecord 要添加的UserAccountOptRecord
     * @return id
     */
    Long addUserAccountOptRecord(UserAccountOptRecord userAccountOptRecord);

    /**
     * 根据id删除一个UserAccountOptRecord
     *
     * @param id 要删除的id
     */
    void deleteUserAccountOptRecordById(Long id);

    /**
     * 修改UserAccountOptRecord
     *
     * @param userAccountOptRecord 要修改的UserAccountOptRecord
     */
    void updateUserAccountOptRecord(UserAccountOptRecord userAccountOptRecord);

    /**
     * 根据id获取单个UserAccountOptRecord对象
     *
     * @param id 要查询的id
     * @return UserAccountOptRecord
     */
    UserAccountOptRecord getUserAccountOptRecordById(Long id);

    /**
     * 根据条件获取UserAccountOptRecord列表
     *
     * @param userAccountOptRecord 查询条件
     * @return List<UserAccountOptRecord>
     */
    List<UserAccountOptRecord> getUserAccountOptRecordList(UserAccountOptRecord userAccountOptRecord);
}