package com.zsl.xiangqing.service;

import com.zsl.xiangqing.entity.Users;

import java.util.List;

public interface IUserService {

    Users selectUserByLoginName(String username);

    Users selectUserByPhoneNumber(String username);

    Users selectUserByEmail(String username);

    Users selectUserByOpenid(String openid);

    List<Users> selectUsersByHeight(Float height);

    boolean updateUserInfo(Users user);

    boolean isHaveOpenid(String openid);

    boolean isHavePhoneNumber(String username);

    boolean addNewUsers(Users users);

    boolean updatePasswordByUsername(String username, String password);
}
