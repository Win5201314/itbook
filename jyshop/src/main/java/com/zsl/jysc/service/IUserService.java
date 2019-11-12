package com.zsl.jysc.service;

import com.zsl.jysc.entity.Users;

public interface IUserService {

    boolean isExistOpenid(String openid);
    boolean addNewUsers(Users users);
    boolean updateUsernameAndAvatar(String openid, String username, String avatar);
    long selectIdByOpenid(String openid);
}
