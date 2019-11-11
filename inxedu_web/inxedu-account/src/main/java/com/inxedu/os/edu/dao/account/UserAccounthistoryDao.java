package com.inxedu.os.edu.dao.account;


import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.account.QueryUserAccounthistory;
import com.inxedu.os.edu.entity.account.UserAccounthistory;

import java.util.List;


/**
 * UserAccounthistory管理接口
 * User: qinggang.liu
 * Date: 2014-05-27
 */
public interface UserAccounthistoryDao {

    /**
     * 添加UserAccounthistory
     *
     * @param userAccounthistory 要添加的UserAccounthistory
     * @return id
     */
    Long addUserAccounthistory(UserAccounthistory userAccounthistory);


    /**
     * 根据id获取单个UserAccounthistory对象
     *
     * @param id 要查询的id
     * @return UserAccounthistory
     */
    UserAccounthistory getUserAccounthistoryById(Long id);

    /**
     * 根据条件获取UserAccounthistory列表
     *
     * @param userAccounthistory 查询条件
     * @return List<UserAccounthistory>
     */
    List<UserAccounthistory> getUserAccounthistoryList(UserAccounthistory userAccounthistory);

    /**
     * 根据条件获取UserAccounthistory列表
     *
     * @param queryUserAccounthistory 查询条件
     * @return List<UserAccounthistory>
     */
    List<UserAccounthistory> getUserAccounthistoryListByCondition(QueryUserAccounthistory queryUserAccounthistory, PageEntity page);

    /**
     * 支付宝订单号查询账户历史，防止同一订单号多次充值
     *
     * @param outTradeNo
     */
    UserAccounthistory getUserAccounthistoryByOutTtradeNo(String outTradeNo);

    /**
     *
     * @param queryUserAccounthistory
     * @param page
     * @return
     */
    List<UserAccounthistory> getWebUserAccounthistoryListByCondition(QueryUserAccounthistory queryUserAccounthistory, PageEntity page);
    /*更新充值记录*/
    void updateUserAccountHistroy(UserAccounthistory userAccounthistory);

}