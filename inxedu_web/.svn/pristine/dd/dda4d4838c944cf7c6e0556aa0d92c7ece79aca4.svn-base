package com.inxedu.os.edu.dao.shopcart;


import com.inxedu.os.edu.entity.course.Course;
import com.inxedu.os.edu.entity.shopcart.Shopcart;

import java.util.List;

/*
*@author www.inxedu.com
*/
public interface ShopcartDao {


	/**
	 * 添加Shopcart
	 *
	 * @param shopcart
	 *            要添加的Shopcart
	 * @return id
	 */
	public Long addShopcart(Shopcart shopcart);

	/**
	 * 根据id删除一个Shopcart
	 *
	 * @param id
	 *            要删除的id
	 */
	public void deleteShopcartById(Long id);

	/**
	 * 根据用户id
	 *
	 * @param
	 *            userId 用户ID
	 * @param
	 *            type 购物车类型
	 * @return List<Shopcart>
	 */
	public List<Shopcart> getShopcartListByUserId(Long userId, Long type);

	/**
	 * 根据条件获取Shopcart列表
	 *
	 * @param shopcart
	 *            查询条件
	 * @return List<Shopcart>
	 */
	public List<Shopcart> getShopcartList(Shopcart shopcart);

	/**
	 * 清空数据库的购物车
	 *
	 * @param type
	 *            要删除的类型
	 */
	public void deleteShopcartByType(Long type, Long userId);
	/**
	 * 根据条件获取购物车课程集合(优惠券专用)
	 *
	 * @param userId
	 *            查询条件
	 * @return List<Shopcart>
	 */
	 public List<Course> getShopcartCourseList(Long userId);

}
