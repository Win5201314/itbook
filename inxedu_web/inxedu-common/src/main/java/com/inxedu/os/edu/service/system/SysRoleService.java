package com.inxedu.os.edu.service.system;

import java.util.List;

import com.inxedu.os.edu.entity.system.SysRole;

/**
 * @author www.inxedu.com
 *
 */
public interface SysRoleService {
	/**
	 * 创建角色
	 * @param sysRole
	 * @return 角色ID
	 */
    int createRoel(SysRole sysRole);
	
	/**
	 * 修改角色
	 * @param sysRole
	 */
    void updateRole(SysRole sysRole);
	
	/**
	 * 查询所有的角色
	 * @return List<SysRole>
	 */
    List<SysRole> queryAllRoleList();
	
	/**
	 * 删除角色
	 * @param ids
	 */
    void deleteRoleByIds(String ids);
	
	/**
	 * 通过角色ID删除角色权限关联
	 * @param roleId
	 */
    void deleteRoleFunctionByRoleId(int roleId);
	
	/**
	 * 通过权限ID，删除角色权限关联
	 * @param functionId
	 */
    void deleteRoleFunctionByFunctionId(String functionId);
	
	/**
	 * 创建角色权限关联
	 * @param value
	 */
    void createRoleFunction(String value);
	
	/**
	 * 获取角色对应的权限ID
	 * @param roleId
	 * @return
	 */
    List<Integer> queryRoleFunctionIdByRoleId(int roleId);
}
