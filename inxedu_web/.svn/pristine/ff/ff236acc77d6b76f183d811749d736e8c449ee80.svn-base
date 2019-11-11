package com.inxedu.os.edu.dao.order;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.course.Course;
import com.inxedu.os.edu.entity.order.Order;
import com.inxedu.os.edu.entity.order.QueryOrder;

import java.util.List;
import java.util.Map;

/**
 * @author www.inxedu.com
 *
 */
public interface OrderDao {
	
	/**
	 * 创建订单 
	 * @param order  订单实体
	 * @return 订单ID
	 */
	int createOrder(Order order);
	
	/**
	 * 更新订单状态为支付成功状态
	 * @param order 订单实体
	 */
	void updateOrderSuccess(Order order);
	
	/**
	 * 取消或恢复订单 
	 * @param order 订单实体
	 */
	void updateOrderState(Order order);
	
	/**
	 * 分页查询订单列表
	 * @param query 查询条件
	 * @param page 分页条件
	 * @return List<Map<String,Object>>
	 */
	List<Map<String,Object>> queryOrderListPage(QueryOrder query, PageEntity page);

	/**
	 * 测试用户是否购买过某个课程
	 * @param map
	 * @return
	 */
	//public int checkUserCursePay(Map<String,Object> map);
	
	/**
	 * 查询用户不同状态的订单数量
	 * @param order 查询条件
	 * @return 返回数量
	 */
	int queryOrderStateCount(Order order);
	
	/**
	 * 通过ID，查询订单数据
	 * @param orderId 订单ID
	 * @return 返回 Order
	 */
	Order queryOrderById(int orderId);
	/**
	 * 根据条件查询订单数量
	 */
	int queryOrderCount(Order order);
	/**
	 * 根据条件查询订单
	 */
	List<Order> queryOrder(Order order);
	/**
	 * 所有的订单数量
	 * @return
	 */
	int queryAllOrderCount();
	/**
	 * 已支付和为支付的订单数量
	 * @param states
	 * @return
	 */
	int queryOrderSuccessCount(String states);
	/**
	 * 更新订单
	 * @param order 订单实体
	 */
	void updateOrder(Order order);

	/**
	 * 订单id查询流水的课程集合
	 *
	 * @param orderId
	 * @return
	 */
	List<Course> getTrxCourseByRequestId(String requestId);

	/**
	 * 根据requestId获取Trxorder
	 *
	 * @param 列表
	 * @return Trxorder
	 */
	Order getTrxorderByRequestId(String requestId);


	/**
	 * 更新订单状态为成功
	 *
	 * @param trxorder
	 */
	Long updateTrxorderStatusSuccess(Order trxorder);

	/**
	 * 订单分页查询 ,根据条件
	 *
	 * @return List
	 */
	List<Order> queryOrderForUc(QueryOrder queryTrxorder, PageEntity page);
}
