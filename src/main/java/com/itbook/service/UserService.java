package com.itbook.service;

import com.itbook.entity.User;

public interface UserService {

    /**
     *判断用户是否注册过
     * @param loginName
     * @param password
     * @return 返回User,没有找到返回bull
     */
    User findUserByloginNameAndpassword(String loginName, String password);

    /**
     * 注册 插入一个新账号记录
     * @param loginName
     * @param password
     */
    void insertNewUser(String loginName, String password);

}
