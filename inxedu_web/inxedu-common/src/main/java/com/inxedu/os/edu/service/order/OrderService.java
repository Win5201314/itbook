package com.inxedu.os.edu.service.order;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.course.Course;
import com.inxedu.os.edu.entity.order.Order;
import com.inxedu.os.edu.entity.order.QueryOrder;
import com.inxedu.os.edu.entity.order.TrxReqData;
import com.inxedu.os.edu.entity.order.TrxorderDetail;
import com.inxedu.os.edu.exception.AccountException;
import com.inxedu.os.edu.exception.StaleObjectStateException;

import java.util.List;
import java.util.Map;

/**
 * @author www.inxedu.com
 *
 */
public interface OrderService {
	/**
	 * 创建订单 
	 * @param order  订单实体
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
	 */
	List<Map<String,Object>> queryOrderListPage(QueryOrder query, PageEntity page);
	
	/**
	 * 测试用户是否购买过某个课程
	 * @param userId 用户ID
	 * @param courseId 课程ID
	 */
	boolean checkUserCursePay(int userId, int courseId);
	/**
	 * 查询用户不同状态的订单数量
	 * @param order 查询条件
	 * @return 返回数量
	 */
	int queryOrderStateCount(Order order);
	/**
	 * 通过ID，查询订单数据
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
	 */
	int queryAllOrderCount();
	
	/**
	 * 已支付和为支付的订单数量
	 */
	int queryOrderSuccessCount(String states);
	/**
	 * 更新订单
	 */
	void updateOrder(Order order);

	/**
	 * 订单的课程集合
	 */
	List<Course> getTrxCourseByRequestId(String requestId);
	/**
	 * 下订单操作
	 */
	Map<String, Object> addTrxorder(Map<String, String> sourceMap) throws Exception;
	/**
	 * 根据requestId获取Trxorder
	 */
	Order getTrxorderByRequestId(String requestId);

	/**
	 * 订单回调支付成功操作
	 */
	Map<String, String> updateCompleteOrder(TrxReqData trxReqData)throws StaleObjectStateException, AccountException;

	/**
	 * 更新订单状态为成功,网银的回调
	 */
	void updateTrxorderStatusSuccess(Order trxorder) throws StaleObjectStateException;

	/**
	 * 个人中心订单查询
	 */
	List<Order> queryOrderForUc(QueryOrder queryTrxorder, PageEntity page);


	/**
	 * 免费赠送下订单操作
	 */
	Map<String, Object> addFreeTrxorder(Map<String, String> sourceMap) throws Exception;
	/**
	 * 某个用户免费赠送课程
	 */
	Map<String, Object> addOrderMsg(int userId, int courseId) throws Exception;

	/**
	 * 添加课程卡订单信息
	 */
	String addCourseCardOrder(Map<String, String> sourceMap) throws Exception;

	/**
	 * 添加 学员卡订单信息 流水信息
	 */
	String addUserCardOrder(Map<String, String> sourceMap) throws Exception;
	/**
	 * 免费开通会员
	 * userId 用户id
	 * memberSaleId 会员商品id
	 * trxorderDetail 结束时间最大 且大于当前时间的开通会员的流水记录
	 */
	void addFreeMember(Long userId, Long memberSaleId, TrxorderDetail trxorderDetail);
}
