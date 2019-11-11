package com.zsl.xiangqing.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RolePermissionMapper {

    List<Integer> selectRoleIdByUserId(long userId);

    String selectRolesByRoleId(long roleId);

    List<Integer> selectPermissionIdByRoleId(long roleId);

    String selectPermissionById(long permissionId);
}
