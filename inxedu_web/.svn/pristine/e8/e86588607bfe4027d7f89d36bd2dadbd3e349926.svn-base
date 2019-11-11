package com.inxedu.os.edu.service.system;

import java.util.List;

import com.inxedu.os.edu.entity.system.SysFunction;

/**
 * @author www.inxedu.com
 *
 */
public interface SysFunctionService {

	/**
	 * 查询所有的权限列表
	 * @return List<SysFunction>
	 */
    List<SysFunction> queryAllSysFunction();
	
	/**
	 * 创建权限
	 * @param sysFunction 权限对象
	 * @return 权限ID
	 */
    int cresateSysFunction(SysFunction sysFunction);
	
	/**
	 * 修改权限
	 * @param sysFunction 权限实体
	 */
    void updateFunction(SysFunction sysFunction);
	
	/**
	 * 修改权限父ID
	 * @param paramrs 修改条件 parentId父ID ，functionId权限ID
	 */
    void updateFunctionParentId(int parentId, int functionId);
	
	/**
	 * 删除权限
	 * @param ids 权限ID串(12,13,14)
	 */
    void deleteFunctionByIds(String ids);
	
	/***
	 * 获取用户权限
	 * @param userId 用户ID
	 * @return
	 */
    List<SysFunction> querySysUserFunction(int userId);
	
}
