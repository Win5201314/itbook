package com.zsl.xiangqing.service;

import com.zsl.xiangqing.entity.LoginInformation;
import com.zsl.xiangqing.mapper.LoginInformationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 记录登录日志
 */
@Service
public class LoginInformationService {

    @Autowired
    private LoginInformationMapper loginInformationMapper;

    public void insertLoginInformation(LoginInformation loginInformation) {
            loginInformationMapper.insertLoginInformation(loginInformation);
    }
}
