package com.zsl.xiangqing.service.impl;

import com.zsl.xiangqing.properties.UserProperties;
import com.zsl.xiangqing.entity.Users;
import com.zsl.xiangqing.mapper.SearchMapper;
import com.zsl.xiangqing.mapper.UserMapper;
import com.zsl.xiangqing.service.IUserService;
import com.zsl.xiangqing.shiro.ShiroUtils;
import com.zsl.xiangqing.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IUserServiceImpl implements IUserService {

    private Logger logger = LoggerFactory.getLogger(IUserServiceImpl.class);

    private UserProperties userProperties;
    @Autowired
    public void setUserProperties(UserProperties userProperties) {
        this.userProperties = userProperties;
    }

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SearchMapper searchMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Users selectUserByLoginName(String username) { return userMapper.selectUserByLoginName(username); }

    @Override
    public Users selectUserByPhoneNumber(String username) {
        logger.info("---------------------------------drgjdrgrd");
        //先从缓存中取，加入有的话
        Users users = (Users) redisUtil.get(username);
        if (users != null) return users;
        logger.info("==============================");
        //没有则在去数据库里面取
        users = userMapper.selectUserByPhoneNumber(username);
        //并及时更新缓存数据
        redisUtil.set(username, users);
        return users;
    }

    @Override
    public Users selectUserByEmail(String username) {
        return userMapper.selectUserByEmail(username);
    }

    @Override
    public Users selectUserByOpenid(String openid) {
        return userMapper.selectUserByOpenid(openid);
    }

    @Override
    public List<Users> selectUsersByHeight(Float height) {
        return searchMapper.selectUsersByHeight(height);
    }

    @Transactional
    @Override
    public boolean updateUserInfo(Users user) {
        return userMapper.updateUserInfo(user) > 0;
    }

    @Override
    public boolean isHaveOpenid(String openid) {
        return userMapper.checkOpenid(openid) > 0;
    }

    @Override
    public boolean isHavePhoneNumber(String username) {
        return userMapper.checkPhoneNumber(username) > 0;
    }

    @Transactional
    @Override
    public boolean addNewUsers(Users users) {
        //用户名 + 明文密码 + 盐值 一起Md5
        String password = ShiroUtils.Md5HashPassword(users.getUsername(), users.getPassword());
        users.setPassword(password);
        //然后存进数据库
        return userMapper.addNewUsers(users) > 0;
    }

    @Transactional
    @Override
    public boolean updatePasswordByUsername(String username, String password) {
        Users users = new Users();
        users.setUsername(username);
        //用户名 + 明文密码 + 盐值 一起Md5
        password = ShiroUtils.Md5HashPassword(username, password);
        users.setPassword(password);
        boolean flag = userMapper.updateUserInfo(users) > 0;
        //测试事务
        //int i = 1 / 0;
        return flag;
    }
}
