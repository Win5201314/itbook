package com.zsl.xiangqing.shiro.service;

import com.zsl.xiangqing.constant.Constants;
import com.zsl.xiangqing.constant.ShiroConstants;
import com.zsl.xiangqing.entity.Users;
import com.zsl.xiangqing.exception.user.UserPasswordNotMatchException;
import com.zsl.xiangqing.exception.user.UserPasswordRetryLimitExceedException;
import com.zsl.xiangqing.manager.AsyncManager;
import com.zsl.xiangqing.manager.factory.AsyncFactory;
import com.zsl.xiangqing.properties.UserProperties;
import com.zsl.xiangqing.util.MessageUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.crazycake.shiro.RedisCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 登录密码方法
 */
@Component
public class PasswordService {

    private RedisCacheManager cacheManager;
    @Autowired
    public void setCacheManager(RedisCacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    private Cache<String, AtomicInteger> loginRecordCache;

    private UserProperties userProperties;
    @Autowired
    public void setUserProperties(UserProperties userProperties) {
        this.userProperties = userProperties;
    }

    @PostConstruct
    public void init() {
        loginRecordCache = cacheManager.getCache(ShiroConstants.LOGIN_RECORD_CACHE);
    }

    public void validate(Users user, String password) {

        String loginName = user.getUsername();

        //获取当前账号登录尝试次数
        AtomicInteger retryCount = loginRecordCache.get(loginName);

        if (retryCount == null) {
            retryCount = new AtomicInteger(0);
            loginRecordCache.put(loginName, retryCount);
        }

        //密码尝试超过限定数值
        if (retryCount.incrementAndGet() > Integer.parseInt(userProperties.getPasswordMaxRetryCount())) {
            AsyncManager.me().execute(AsyncFactory.recordLoginInformation(loginName, Constants.LOGIN_FAIL, MessageUtils.message("user.password.retry.limit.exceed", userProperties.getPasswordMaxRetryCount())));
            throw new UserPasswordRetryLimitExceedException(Integer.parseInt(userProperties.getPasswordMaxRetryCount()));
        }

        if (!matches(user, password)) {
            //密码对应的账号不匹配
            AsyncManager.me().execute(AsyncFactory.recordLoginInformation(loginName, Constants.LOGIN_FAIL, MessageUtils.message("user.password.retry.limit.count", retryCount)));
            loginRecordCache.put(loginName, retryCount);
            throw new UserPasswordNotMatchException();
        } else {
            //匹配，清除登录缓存
            clearLoginRecordCache(loginName);
        }
    }

    /**
     * 数据库里面存储的加密后的密码和当前登录明文密码比较，看是否匹配
     */
    private boolean matches(Users user, String newPassword) {
        return user.getPassword().equals(encryptPassword(user.getUsername(), newPassword, "zslzslzsl"));
    }

    private void clearLoginRecordCache(String username) {
        loginRecordCache.remove(username);
    }

    /**
     * 用户名 + 明文密码 + 盐值 一起Md5
     */
    private String encryptPassword(String username, String password, String salt) {
        return new Md5Hash(username + password).toHex();
    }

}
