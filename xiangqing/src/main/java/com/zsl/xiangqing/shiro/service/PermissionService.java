package com.zsl.xiangqing.shiro.service;

import com.zsl.xiangqing.mapper.RolePermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 权限服务
 */
@Component
public class PermissionService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    public Set<String> selectPermsByUserId(long userId) {
        //先查找用户所有的角色id
        List<Integer> roleIds = rolePermissionMapper.selectRoleIdByUserId(userId);
        Set<Integer> per = new HashSet<>();
        for (int roleId : roleIds) {
            List<Integer> permissionId = rolePermissionMapper.selectPermissionIdByRoleId(roleId);
            per.addAll(permissionId);
        }
        List<Integer> permissionIds = new ArrayList<>(per);
        List<String> permissionList = new ArrayList<>();
        for (int permissionId : permissionIds) {
            permissionList.add(rolePermissionMapper.selectPermissionById(permissionId));
        }
        return new HashSet<>(permissionList);
    }

}
