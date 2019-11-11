package com.inxedu.os.edu.service.shopcart;

import com.inxedu.os.edu.entity.course.Course;
import com.inxedu.os.edu.entity.shopcart.Shopcart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author www.inxedu.com
 */
public interface ShopcartService {
	/**
	 * 添加Shopcart
	 * @return id
	 */
    Map<String,Object> addShopcart(Long goodsId, Long type, Long userId);

	/**
	 * 根据id删除一个Shopcart
	 *
	 * @param id
	 *            要删除的id
	 */
    void deleteShopcartById(Long id, Long userId);

	/**
	 * 查询用户的购物车
	 *
	 * @param
	 *            userId 用户id
	 * @return List<Shopcart>
	 */
    List<Shopcart> getShopcartList(Long userId, Long type);
	/**
	 * 查询购物车的课程集合(优惠券专用)
	 *
	 * @param
	 *            userId 用户id
	 * @return List<Shopcart>
	 */
    List<Course> getShopcartCourseList(Long userId);

	/**
	 * cookie中添加购物车
	 * @param goodsId
	 * @param type
	 * @return
	 */
    String addTempShopcart(Long goodsId, Long type, String json);

	/**
	 * cookie中删除购物车
	 * @param goodsId
	 * @param type
	 */
    String deleteTempShopcart(Long goodsId, Long type, String json);

	/**
	 * 获取未登陆时用户的购物车
	 * @param json
	 * @return
	 */
    List<Shopcart> getTempShopcartList(String json) ;

	/**
	 * 添加cookie中的购物车到数据库中
	 * @param request
	 * @param response
	 */
    void addTempShopCart(HttpServletRequest request, HttpServletResponse response, Long userId);

	/**
	 * 清空数据库的购物车
	 *
	 * @param type
	 *            要删除的类型
	 */
    void deleteShopcartByType(Long type, Long userId);

}
