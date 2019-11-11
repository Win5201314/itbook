package com.inxedu.os.edu.service.impl.shopcart;

import com.inxedu.os.common.constants.CacheConstans;
import com.inxedu.os.common.util.WebUtils;
import com.inxedu.os.edu.constants.order.OrderConstans;
import com.inxedu.os.edu.dao.course.CourseTeacherDao;
import com.inxedu.os.edu.dao.shopcart.ShopcartDao;
import com.inxedu.os.edu.entity.course.Course;
import com.inxedu.os.edu.entity.shopcart.Shopcart;
import com.inxedu.os.edu.entity.teacher.Teacher;
import com.inxedu.os.edu.service.course.CourseService;
import com.inxedu.os.edu.service.shopcart.ShopcartService;
import com.inxedu.os.edu.util.shopcart.ShopCartUtil;
import org.springframework.beans.factory.annotation.Autowired;
import com.inxedu.os.common.cache.CacheUtil;
import org.springframework.stereotype.Service;
import com.inxedu.os.common.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
 * @author www.inxedu.com
 */
@Service("shopcartService")
public class ShopcartServiceImpl implements ShopcartService {

	@Autowired
	private ShopcartDao shopcartDao;
	@Autowired
	private CourseService courseService;
	@Autowired
	private CourseTeacherDao courseTeacherDao;

	/**
	 * 添加Shopcart
	 * @return id
	 */
	public Map<String,Object> addShopcart(Long goodsId, Long type, Long userId) {
		Map<String, Object> dataMap = new HashMap<>();
		//是否添加过
		boolean isexsits = false;
		Shopcart shopcart = new Shopcart();
		shopcart.setGoodsid(Long.valueOf(goodsId));
		shopcart.setType(Long.valueOf(type));
		shopcart.setUserid(userId);
		shopcart.setAddTime(new Date());
		List<Shopcart> list = getShopcartList(userId,type);

		if (ObjectUtils.isNull(list)) {
			// 购物车是空的,添加到购物车中
			shopcartDao.addShopcart(shopcart);
			list = new ArrayList<Shopcart>();
			// 查询课程信息
			shopcart.setCourse(courseService.queryCourseById(Integer.parseInt( shopcart.getGoodsid()+"")));
			List<Long> ids =new ArrayList<Long>();
			ids.add(shopcart.getGoodsid());
			// 获取讲师的list
			Map<Long, List<Teacher>> map = courseTeacherDao.getCourseTeacherListByCourse(ids);
			// 将讲师的list放到旧的list中
			shopcart.getCourse().setTeacherList(map.get(shopcart.getCourse().getCourseId()));
			list.add(shopcart);
		} else {

			for (Shopcart shopcart2 : list) {
				if (shopcart.getGoodsid().longValue() == shopcart2.getGoodsid().longValue() && shopcart.getType().longValue() == shopcart2.getType().longValue()) {
					isexsits = true;
					break;
				}
			}
			if (!isexsits) {
				// 不存在,添加到购物车中
				shopcartDao.addShopcart(shopcart);
				// 查询课程信息
				shopcart.setCourse(courseService.queryCourseById(Integer.parseInt(shopcart.getGoodsid()+"")));
				List<Long> ids =new ArrayList<Long>();
				ids.add(shopcart.getGoodsid());
				// 获取讲师的list
				Map<Long, List<Teacher>> map = courseTeacherDao.getCourseTeacherListByCourse(ids);
				// 将讲师的list放到旧的list中
				shopcart.getCourse().setTeacherList(map.get(shopcart.getCourse().getCourseId()));
				list.add(shopcart);
			}
		}
		CacheUtil.set(CacheConstans.SHOPCART + userId, list);

		dataMap.put("shopcartList", list);
		dataMap.put("isexsits", isexsits);
		return dataMap;

	}

	/**
	 * 根据id删除一个Shopcart
	 */
	public void deleteShopcartById(Long id, Long userId) {
		shopcartDao.deleteShopcartById(id);
		// 移除缓存中的购物车数据
		List<Shopcart> list = (List<Shopcart>) CacheUtil.get(CacheConstans.SHOPCART + userId);
		if (ObjectUtils.isNotNull(list)) {
			for (Shopcart shopcart : list) {
				if (shopcart.getId().intValue() == id.intValue()) {
					list.remove(shopcart);
					break;
				}
			}
			CacheUtil.set(CacheConstans.SHOPCART + userId, list);
		}
	}

	/**
	 * 根据条件获取Shopcart列表
	 */
	public List<Shopcart> getShopcartList(Long userId,Long type) {
		List<Shopcart> list = (List<Shopcart>) CacheUtil.get(CacheConstans.SHOPCART + userId);
		if (ObjectUtils.isNull(list)) {
			list = shopcartDao.getShopcartListByUserId(userId,type);
			if (ObjectUtils.isNotNull(list)) {
				List<Long> listParam = new ArrayList<Long>();
				for (Shopcart shopcart  : list) {
					//清空课程的 大内容后放进缓存
					shopcart.getCourse().setContext("");
					listParam.add(Long.valueOf(shopcart.getCourse().getCourseId()));
				}
				// 获取讲师的list
				Map<Long, List<Teacher>> map = courseTeacherDao.getCourseTeacherListByCourse(listParam);
				// 将讲师的list放到旧的list中
				for (Shopcart shopcart  : list) {
					shopcart.getCourse().setTeacherList(map.get(Long.valueOf(shopcart.getCourse().getCourseId())));
				}
			}
			if (ObjectUtils.isNotNull(list)) {
				CacheUtil.set(CacheConstans.SHOPCART + userId, list);
			}
		}
		return list;
	}
	/**
	 * 根据条件获取购物车的课程集合(优惠券专用)
	 */
	public List<Course> getShopcartCourseList(Long userId) {
		return  shopcartDao.getShopcartCourseList(userId);
	}
	/**
	 * cookie中添加购物车
	 */
	@Override
	public String addTempShopcart(Long goodsId, Long type,String json) {
		Shopcart  shopItem= new Shopcart();
		shopItem.setId(0L);
		shopItem.setGoodsid(goodsId);
		shopItem.setType(type);
		return ShopCartUtil.addItem(shopItem, json);
	}

	/**
	 * cookie中删除购物车
	 */
	@Override
	public String deleteTempShopcart(Long goodsId, Long type,String json) {
		return ShopCartUtil.remove(goodsId, type, json);
	}

	/**
	 * 根据条件获取Shopcart列表
	 */
	public List<Shopcart> getTempShopcartList(String json) {
		if(ShopCartUtil.isNull(json)){
			return null;
		}
		List<Shopcart> list2 =ShopCartUtil.query(json);
		if (ObjectUtils.isNotNull(list2)) {
			List<Long> listParam = new ArrayList<Long>();
			for(Shopcart  shopcart:list2){
				shopcart.setCourse(courseService.queryCourseById(Integer.parseInt(shopcart.getGoodsid()+"")));
				listParam.add(shopcart.getGoodsid());
			}
			// 获取讲师的list
			Map<Long, List<Teacher>> map = courseTeacherDao.getCourseTeacherListByCourse(listParam);
			// 将讲师的list放到旧的list中
			for (Shopcart shopcart  : list2) {
				shopcart.getCourse().setTeacherList(map.get(Long.valueOf(shopcart.getCourse().getCourseId())));
			}
		}
		return list2;
	}

	/**
	 * 添加cookie中的购物车到数据库中
	 */
	public void addTempShopCart(HttpServletRequest request, HttpServletResponse response, Long userId){
		String json = WebUtils.getCookie(request, OrderConstans.SHOP_CART);
		if (!ShopCartUtil.isNull(json)) {
			List<Shopcart> list = ShopCartUtil.query(json);
			if(ObjectUtils.isNotNull(list)){
				for(Shopcart shopcart:list){
					addShopcart(shopcart.getGoodsid(), shopcart.getType(), userId);
				}
			}
			WebUtils.deleteCookie(request, response, OrderConstans.SHOP_CART);
		}
	}
	/**
	 * 清空数据库的购物车
	 */
	public void deleteShopcartByType(Long type,Long userId){
		shopcartDao.deleteShopcartByType(type, userId);
		CacheUtil.remove(CacheConstans.SHOPCART+userId);
	}
}