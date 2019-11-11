package com.zsl.boss.demo.service.serviceImpl;

import com.zsl.boss.demo.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    @Override
    public Set<String> findRoles(Long... roleIds) {
        return null;
    }

    @Override
    public Set<String> findPermissions(Long[] roleIds) {
        return null;
    }
}
