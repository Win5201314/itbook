package com.zsl.xiangqing.shiro.service;

import com.zsl.xiangqing.mapper.RolePermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 角色服务
 */
@Service
public class RoleService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    public Set<String> selectRoleKeys(long userId) {
        List<Integer> roleIds = rolePermissionMapper.selectRoleIdByUserId(userId);
        List<String> roles = new ArrayList<>();
        for (int roleId : roleIds) {
            roles.add(rolePermissionMapper.selectRolesByRoleId(roleId));
        }
        return new HashSet<>(roles);
    }
}
