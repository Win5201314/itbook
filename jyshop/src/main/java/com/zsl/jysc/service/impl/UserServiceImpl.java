package com.zsl.jysc.service.impl;

import com.zsl.jysc.entity.Users;
import com.zsl.jysc.mapper.UserMapper;
import com.zsl.jysc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean isExistOpenid(String openid) {
        return userMapper.isExistOpenid(openid) >= 1;
    }

    @Transactional
    @Override
    public boolean addNewUsers(Users users) {
        return userMapper.addNewUsers(users);
    }

    @Transactional
    @Override
    public boolean updateUsernameAndAvatar(String openid, String username, String avatar) {
        return userMapper.updateUsernameAndAvatar(openid, username, avatar) >= 1;
    }

    @Override
    public long selectIdByOpenid(String openid) {
        return userMapper.selectIdByOpenid(openid);
    }

}
