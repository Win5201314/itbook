package com.inxedu.os.edu.dao.account;


import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.account.UserAccount;
import com.inxedu.os.edu.entity.account.UserAccountDTO;
import com.inxedu.os.edu.entity.user.User;
import com.inxedu.os.edu.exception.StaleObjectStateException;

import java.util.List;

/**
 * @author www.inxedu.com
 */
public interface UserAccountDao {

    /**
     * 添加UserAccount
     *
     * @param userAccount 要添加的UserAccount
     * @return id
     */
    Long addUserAccount(UserAccount userAccount);

    /**
     * 修改UserAccount
     *
     * @param userAccount 要修改的UserAccount
     */
    void updateUserAccount(UserAccount userAccount) throws StaleObjectStateException;

    /**
     * 根据id获取单个UserAccount对象
     *
     * @param userId
     * @return UserAccount
     */
    UserAccount getUserAccountByUserId(Long userId);

    /**
     * 根据条件获取UserAccount列表
     *
     * @param userAccount 查询条件
     * @return List<UserAccount>
     */
    List<UserAccount> getUserAccountList(UserAccount userAccount);

    /**
     * 获取用户账户信息
     *
     * @param pageEntity
     * @return
     */
    List<UserAccountDTO> getuserAccountListByCondition(User user, PageEntity pageEntity) throws StaleObjectStateException;

    /**
     * 更新账户状态
     *
     * @param userId
     * @param status
     */
    void updateUserAccountStatus(Long userId, String status);

    /**
     * 根据用户id获得详情
     *
     * @param userId
     * @return
     */
    UserAccountDTO getuserAccountInfo(Long userId);
}