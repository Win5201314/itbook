package com.zsl.boss.demo.service;

import java.util.Set;

public interface RoleService {

    /**
     * 根据角色编号得到角色标识符列表
     * @param roleIds 角色主键
     * @return 角色集合
     */
    Set<String> findRoles(Long... roleIds);

    /**
     * 根据角色编号得到权限字符串列表
     * @param roleIds 角色主键
     * @return 返回权限集合
     */
    Set<String> findPermissions(Long[] roleIds);
}
