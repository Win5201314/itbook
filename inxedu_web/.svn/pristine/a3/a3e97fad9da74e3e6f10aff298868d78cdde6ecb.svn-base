package com.inxedu.os.common.dao;


import com.inxedu.os.common.entity.PageEntity;

import java.util.List;

public interface GenericDao {
	/**
	 * 公共添加记录
	 * @param paramString mybatis配置ID
	 * @param paramObject SQL查询条件
	 * @return 新增记录ID
	 */
    Long insert(String paramString, Object paramObject);

	/**
	 * 公共删除记录
	 * @param paramString mybatis配置ID
	 * @param paramObject SQL查询条件
	 * @return 删除记录ID
	 */
    Long delete(String paramString, Object paramObject);

	/**
	 * 公共更新记录
	 * @param paramString mybatis配置ID
	 * @param paramObject SQL查询条件
	 * @return 被更新记录ID
	 */
    Long update(String paramString, Object paramObject);

	/**
	 * 删除单条记录
	 * @param paramString mybatis配置ID
	 * @param paramObject SQL查询条件
	 * @return 返回记录对象
	 */
    <T> T selectOne(String paramString, Object paramObject);

	/**
	 * 查询记录列表
	 * @param paramString mybatis配置ID
	 * @param paramObject SQL查询条件
	 * @return 返回记录列表
	 */
    <T> List<T> selectList(String paramString,
                           Object paramObject);

	/**
	 * 分页查询
	 * @param paramString mybatis配置ID
	 * @param paramObject SQL查询条件
	 * @param paramPageEntity 分页条件
	 * @return 返回记录列表
	 */
    <T> List<T> queryForListPage(String paramString,
                                 Object paramObject, PageEntity paramPageEntity);
}
