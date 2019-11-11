package com.itbook.serviceImpl;

import com.itbook.entity.User;
import com.itbook.mapper.UserMapper;
import com.itbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    //查询出来的时候添加单个缓存
    @Cacheable(value="user", key="'user'+ #loginName + #password")
    @Override
    public User findUserByloginNameAndpassword(String loginName, String password) {
        return userMapper.findWithLoginNameAndPassword(loginName, password);
    }

    @Override
    public void insertNewUser(String loginName, String password) {
        userMapper.insertNewUser(loginName, password);
    }

}
