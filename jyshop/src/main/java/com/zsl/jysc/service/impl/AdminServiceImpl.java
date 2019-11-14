package com.zsl.jysc.service.impl;

import com.zsl.jysc.mapper.AdminMapper;
import com.zsl.jysc.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public boolean isExitsAdmin(String username, String password) {
        return adminMapper.isExitsAdmin(username, password) >= 1;
    }
}
