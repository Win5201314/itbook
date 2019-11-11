package com.inxedu.os.edu.service.impl.order;


import com.inxedu.os.common.cache.CacheUtil;
import com.inxedu.os.common.constants.CacheConstans;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.exception.BaseException;
import com.inxedu.os.common.util.DateUtils;
import com.inxedu.os.common.util.FastJsonUtil;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.StringUtils;
import com.inxedu.os.edu.constants.enums.MsgType;
import com.inxedu.os.edu.constants.enums.PayType;
import com.inxedu.os.edu.constants.enums.ReqChanle;
import com.inxedu.os.edu.constants.enums.WebSiteProfileType;
import com.inxedu.os.edu.constants.enums.account.AccountBizType;
import com.inxedu.os.edu.constants.enums.account.AccountHistoryType;
import com.inxedu.os.edu.constants.enums.account.AccountStatus;
import com.inxedu.os.edu.constants.enums.order.AuthStatus;
import com.inxedu.os.edu.constants.enums.order.OrderType;
import com.inxedu.os.edu.constants.enums.order.TrxOrderStatus;
import com.inxedu.os.edu.constants.order.OrderConstans;
import com.inxedu.os.edu.dao.order.OrderDao;
import com.inxedu.os.edu.dao.order.TrxorderDetailDao;
import com.inxedu.os.edu.dao.shopcart.ShopcartDao;
import com.inxedu.os.edu.entity.account.UserAccount;
import com.inxedu.os.edu.entity.coupon.CouponCode;
import com.inxedu.os.edu.entity.coupon.CouponCodeDTO;
import com.inxedu.os.edu.entity.course.Course;
import com.inxedu.os.edu.entity.course.CourseDto;
import com.inxedu.os.edu.entity.course.QueryCourse;
import com.inxedu.os.edu.entity.member.MemberRecordDTO;
import com.inxedu.os.edu.entity.member.MemberSale;
import com.inxedu.os.edu.entity.order.*;
import com.inxedu.os.edu.entity.shopcart.Shopcart;
import com.inxedu.os.edu.entity.user.User;
import com.inxedu.os.edu.exception.AccountException;
import com.inxedu.os.edu.exception.StaleObjectStateException;
import com.inxedu.os.edu.service.account.UserAccountService;
import com.inxedu.os.edu.service.coupon.CouponCodeService;
import com.inxedu.os.edu.service.coupon.CouponService;
import com.inxedu.os.edu.service.course.CourseService;
import com.inxedu.os.edu.service.invitationrecord.InvitationRecordService;
import com.inxedu.os.edu.service.letter.MsgReceiveService;
import com.inxedu.os.edu.service.member.MemberRecordService;
import com.inxedu.os.edu.service.member.MemberSaleService;
import com.inxedu.os.edu.service.order.OrderService;
import com.inxedu.os.edu.service.order.TrxorderDetailService;
import com.inxedu.os.edu.service.shopcart.ShopcartService;
import com.inxedu.os.edu.service.user.UserService;
import com.inxedu.os.edu.service.website.WebsiteProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * @author www.inxedu.com
 *
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private ShopcartDao shopcartDao;
	@Autowired
	private ShopcartService shopcartService;
	@Autowired(required = false)
	private CouponCodeService couponCodeService;
	@Autowired
	private TrxorderDetailService trxorderDetailService;
	@Autowired
	private TrxorderDetailDao trxorderDetailDao;
	@Autowired
	private CourseService courseService;
	@Autowired(required = false)
	private CouponService couponService;
	@Autowired
	private MsgReceiveService msgReceiveService;
	@Autowired
	private WebsiteProfileService websiteProfileService;
	@Autowired
	private UserService userService;
	@Autowired(required=false)
	private MemberRecordService memberRecordService;
	@Autowired(required=false)
	private MemberSaleService memberSaleService;
	@Autowired(required=false)
	private UserAccountService userAccountService;
	@Autowired(required = false)
	private InvitationRecordService invitationRecordService;
	public int createOrder(Order order) {
		return orderDao.createOrder(order);
	}

	public void updateOrderSuccess(Order order) {
		orderDao.updateOrderSuccess(order);
	}
	
	public void updateOrderState(Order order) {
		orderDao.updateOrderState(order);
	}

	public List<Map<String, Object>> queryOrderListPage(QueryOrder query, PageEntity page) {
		return orderDao.queryOrderListPage(query, page);
	}

	public boolean checkUserCursePay(int userId, int courseId) {
		Course course = courseService.queryCourseById(courseId);
		if (course != null) {
			// 如果课程价格为0则可以观看
			if (course.getCurrentPrice().compareTo(new BigDecimal(0))==0) {// 免费的课程可以直接试听
				return true;
			}
		}
		if(userId==0){//不再往下判断
			return false;
		}
		// 如果传入的参数为空则不可观看该课程
		if (ObjectUtils.isNull(courseId) || ObjectUtils.isNull(userId)) {
			return false;
		}
		Boolean isOk = (Boolean) CacheUtil.get(CacheConstans.USER_CANLOOK + "_" + courseId + "_" + userId);
		if (ObjectUtils.isNotNull(isOk)&&isOk) {
			return true;
		}

		// 查询购买过的课程
		List<TrxorderDetail> trxorderDetailList = trxorderDetailService.getTrxorderDetailListBuy(Long.valueOf(userId));

		if (ObjectUtils.isNotNull(trxorderDetailList)) {
			List<Long> ids = new ArrayList<Long>();
			for (TrxorderDetail detail : trxorderDetailList) {
				ids.add(detail.getCourseId());
				if (detail.getCourseId().longValue() == courseId) {
					CacheUtil.set(CacheConstans.USER_CANLOOK + "_" + courseId + "_" + userId, true, CacheConstans.USER_CANLOOK_TIME);
					return true;
				}
			}

			// 再查询购买的课程中是否是套餐。查询套餐下是否包含该课程
			List<CourseDto> courseDtos = courseService.getCourseListPackage(ids);
			if (ObjectUtils.isNotNull(courseDtos)) {
				for (CourseDto courseDto : courseDtos) {
					if (courseDto.getCourseId() == courseId) {
						CacheUtil.set(CacheConstans.USER_CANLOOK + "_" + courseId + "_" + userId, true, CacheConstans.USER_CANLOOK_TIME);
						return true;
					}
				}
			}
		}

		//查询所有免费的套餐  免费套餐下的课程可以免费观看
		QueryCourse queryCourse=new QueryCourse();
		queryCourse.setSellType("PACKAGE");
		queryCourse.setIsFree("true");
		List<CourseDto> courseDtoList=courseService.queryCourseList(queryCourse);
		if (ObjectUtils.isNotNull(courseDtoList)) {
			List<Long> ids = new ArrayList<Long>();
			for (CourseDto courseDto : courseDtoList) {
				ids.add(Long.valueOf(courseDto.getCourseId()));
			}
			//查询套餐下是否包含该课程
			List<CourseDto> courseDtos = courseService.getCourseListPackage(ids);
			if (ObjectUtils.isNotNull(courseDtos)) {
				for (CourseDto courseDto : courseDtos) {
					if (courseDto.getCourseId() == courseId) {
						CacheUtil.set(CacheConstans.USER_CANLOOK + "_" + courseId + "_" + userId, true, CacheConstans.USER_CANLOOK_TIME);
						return true;
					}
				}
			}
		}
		Map<String,Object> webSwitch=(Map<String,Object>)websiteProfileService.getWebsiteProfileByType("serviceSwitch").get("serviceSwitch");

		/*如果用户有该课程的某个会员*/
		if ("ON".equals(webSwitch.get("member"))&&courseService.hasMember(new Long(courseId),Long.valueOf(userId))){
			CacheUtil.set(CacheConstans.USER_CANLOOK + "_" + courseId + "_" + userId, true, CacheConstans.USER_CANLOOK_TIME);
			return true;
		}
		return false;
	}
	public int queryOrderStateCount(Order order) {
		return orderDao.queryOrderStateCount(order);
	}
	public Order queryOrderById(int orderId) {
		return orderDao.queryOrderById(orderId);
	}
	/**
	 * 根据条件查询订单数量
	 */
	public int queryOrderCount(Order order){
		return orderDao.queryOrderCount(order);
	}
	/**
	 * 根据条件查询订单
	 */
	public List<Order> queryOrder(Order order){
		return orderDao.queryOrder(order);
	}
	/**
	 * 所有订单数
	 */
	@Override
	public int queryAllOrderCount() {
		return orderDao.queryAllOrderCount();
	}
	/**
	 * 已支付和为支付的订单数量
	 */
	@Override
	public int queryOrderSuccessCount(String states) {
		return orderDao.queryOrderSuccessCount(states);
	}

	@Override
	public void updateOrder(Order order) {
		orderDao.updateOrder(order);
	}

	/**
	 * 订单id查询流水的课程集合
	 * @return
	 */
	public List<Course> getTrxCourseByRequestId(String requestId){
		return orderDao.getTrxCourseByRequestId(requestId);
	}
	/**
	 * 添加Trxorder
	 */
	public Map<String, Object> addTrxorder(Map<String, String> sourceMap) throws Exception {

		Map<String, Object> result = new HashMap<String, Object>();

		// 检查参数
		TrxReqData trxReqData = checkorderparam(sourceMap);
		if (trxReqData == null) {// 参数验证失败
			result.put("msg", "param");
			return result;
		}
		// 拼装订单数据
		Order trxorder = new Order();
		// 下单时间
		Date date = new Date();
		trxorder.setCreateTime(date);// 下单时间
		trxorder.setUserId(Integer.parseInt(trxReqData.getUserId()+""));
		trxorder.setOrderNo(this.getOrderNum(trxReqData.getUserId()));// 交易请求号
		trxorder.setStates(TrxOrderStatus.INIT.toString());// 交易装态
		trxorder.setPayType(trxReqData.getPayType().toString());// 支付类型
		trxorder.setReqChannel(trxReqData.getReqChannel());// 请求渠道
		trxorder.setReqIp(trxReqData.getReqIp());
		trxorder.setOrderType(trxReqData.getOrderType());//商品类型
		trxorder.setDescription("无");

		// 原始金额
		BigDecimal orderAmount = new BigDecimal(0);
		/*有otherId的商品不走购物车*/
		if (StringUtils.isEmpty(trxReqData.getOtherId())){
			// 获得该用户购物车
			List<Shopcart> shopcartlist = shopcartDao.getShopcartListByUserId(trxReqData.getUserId(), trxReqData.getType());
			if (ObjectUtils.isNull(shopcartlist)) {
				return result;
			}

			// 循环该用户购物车中的课程全部的价格
			for (Shopcart shopcart : shopcartlist) {
				Course cou = shopcart.getCourse();
				// 查询出的课程不为空则添加
				if (ObjectUtils.isNotNull(cou)) {
					orderAmount = orderAmount.add(cou.getCurrentPrice());
					Date authDate = null;
					// 到期时间
					if (cou.getLoseType() == 0) {
						authDate = cou.getEndTime();
					}
					// 按天数计算
					if (cou.getLoseType() == 1) {//
						// 按所写时间推移过期时间
						Calendar now = Calendar.getInstance();
						now.setTime(date);
						now.set(Calendar.DATE, now.get(Calendar.DATE) + Integer.valueOf(cou.getLoseTime()));
						authDate = now.getTime();
					}
					// 如果课程已经过期，将课程从购物车中删除，并重新加载购物车
					if (date.getTime() > authDate.getTime()) {
						shopcartService.deleteShopcartById(shopcart.getId(), trxReqData.getUserId());
						result.put("msg", "courselosedate");
						return result;
					}
				}
			}
		}else {
			/*如果为不走购物车订单 通过otherId查询商品价格*/
			String otherId = trxReqData.getOtherId();
			/*如果是开通会员*/
			if ((OrderType.MEMBER+"").equals(trxReqData.getOrderType())){
				MemberSale memberSale = memberSaleService.getMemberSaleById(Long.parseLong(otherId));
				/*订单金额为会员销售价格*/
				orderAmount = memberSale.getPrice();
			}

			/*如果是充值*/
			if ((OrderType.ACCOUNT+"").equals(trxReqData.getOrderType())){
				orderAmount = new BigDecimal(otherId);
			}
		}
		trxorder.setOrderAmount(orderAmount); // 原始金额
		// 是否使用优惠券，判断优惠券是否在规则内
		// 优惠金额
		BigDecimal couponAmount = new BigDecimal(0);


		if("ON".equals(CacheConstans.COUPON_IS_OPEN) && ObjectUtils.isNotNull(couponCodeService)) {
			// 查询优惠券编码
			if (sourceMap.get("couponcode") != null && sourceMap.get("couponcode") != "") {// 使用优惠券
				// 查询优惠券编码
				CouponCodeDTO couponCodeDTO = couponCodeService.getCouponCodeByCode(sourceMap.get("couponcode"));
				List<Course> courses = shopcartService.getShopcartCourseList(Long.valueOf(trxorder.getUserId() + ""));//课程集合用于优惠券编码验证
				// 验证优惠券编码
				Map<String, Object> map = couponCodeService.checkCode(couponCodeDTO,courses, trxReqData.getUserId());


				//优惠券不在冻结状态                      /**coupon_frozen_temp**/
				if (map.get("msg") == "true" && couponCodeDTO.getStatus() != 6) {// 验证通过
					if (map.get("limitCourseIds") != null) {// 订单描述，限制的课程id字符串
						trxorder.setDescription("课程限制：" + map.get("limitCourseIds").toString());
					} else {
						trxorder.setDescription("课程限制：所有课程");
					}
					trxorder.setCouponCodeId(couponCodeDTO.getId());// 优惠券编码id

					couponAmount = couponCodeDTO.getAmount();// 定额优惠金额

				}
			}
		}

		trxorder.setCouponAmount(couponAmount);
		// 实际需要支付的金额,四舍五去取2位
		BigDecimal amount = orderAmount.subtract(couponAmount).setScale(2, RoundingMode.HALF_UP);
		if (amount.doubleValue() <= 0) {
			result.put("msg", "amount");
			return result;
		}
		trxorder.setSumMoney(amount);// 实际支付金额
		// 添加订单
		orderDao.createOrder(trxorder);
		if("ON".equals(CacheConstans.COUPON_IS_OPEN) && ObjectUtils.isNotNull(couponCodeService)) {
			/**coupon_frozen_temp**/
			//冻结优惠券
			CouponCodeDTO couponCodeDTOTemp = couponCodeService.getCouponCodeDTO(trxorder.getCouponCodeId());
			//非无限次使用权的优惠券编码才更新状态
			if (sourceMap.get("couponcode") != null && sourceMap.get("couponcode") != "" && couponCodeDTOTemp != null&& couponCodeDTOTemp.getUseType() == 2 ) {// 使用优惠券
				// 查询优惠券编码
				CouponCode couponCode = couponCodeService.getCouponCodeById(couponCodeDTOTemp.getId());
				couponCode.setStatus(6);//6冻结 （优惠券已在订单中使用，但未支付）
				couponCode.setTrxorderId(Long.valueOf(trxorder.getOrderId()));
				couponCode.setRequestId(trxorder.getOrderNo());
				couponCodeService.updateCouponCode(couponCode);
			}
			/**coupon_frozen_temp**/
		}
		// 添加流水表
		List<TrxorderDetail> trxorderDetailList = new ArrayList<TrxorderDetail>();
		if(StringUtils.isEmpty(trxReqData.getOtherId())){
			// 获得该用户购物车
			List<Shopcart> shopcartlist = shopcartDao.getShopcartListByUserId(trxReqData.getUserId(), trxReqData.getType());
			if (ObjectUtils.isNull(shopcartlist)) {
				return result;
			}
			for (Shopcart shopcart : shopcartlist) {
				Course cou = shopcart.getCourse();
				if (ObjectUtils.isNull(cou)) {
					continue;
				}
				// 创建流水
				TrxorderDetail trxorderDetail = new TrxorderDetail();
				// 用户id
				trxorderDetail.setUserId(trxReqData.getUserId());
				// 课程id
				trxorderDetail.setCourseId(shopcart.getGoodsid());
				// 订单id
				trxorderDetail.setTrxorderId(Long.valueOf(trxorder.getOrderId()+""));

				// 有效期类型
				trxorderDetail.setLosetype(cou.getLoseType());
				// 订单过期时间段
				trxorderDetail.setLoseAbsTime(cou.getEndTime());
				// 订单过期时间点
				trxorderDetail.setLoseTime(cou.getLoseTime());
				// 时间段
				if (cou.getLoseType() == 0) {
					trxorderDetail.setAuthTime(cou.getEndTime());
				}
				// 时间点
				if (cou.getLoseType() == 1) {
					// 按所写时间推移过期时间
					Calendar now = Calendar.getInstance();
					now.setTime(date);
					now.set(Calendar.DATE, now.get(Calendar.DATE) + Integer.valueOf(cou.getLoseTime()));
					trxorderDetail.setAuthTime(now.getTime());
				}

				// 下单时间
				trxorderDetail.setCreateTime(new Date());
				// 课程原始价格
				trxorderDetail.setSourcePrice(cou.getSourcePrice());
				// 课程实际支付价格
				trxorderDetail.setCurrentPrice(cou.getCurrentPrice());
				// 课程名
				trxorderDetail.setCourseName(cou.getCourseName());
				// 课程状态
				trxorderDetail.setAuthStatus(AuthStatus.INIT.toString());
				// 订单请求号
				trxorderDetail.setRequestId(trxorder.getOrderNo());
				trxorderDetail.setLastUpdateTime(date);
				trxorderDetail.setDescription("");
				/*流水为课程流水*/
				trxorderDetail.setTrxorderType(OrderType.COURSE+"");
				trxorderDetailList.add(trxorderDetail);
			}
		}else {
			// 创建流水
			TrxorderDetail trxorderDetail = new TrxorderDetail();
			// 用户id
			trxorderDetail.setUserId(trxReqData.getUserId());
			// 订单id
			trxorderDetail.setTrxorderId(Long.valueOf(trxorder.getOrderId()+""));


			/*如果是开通会员*/
			if ((OrderType.MEMBER+"").equals(trxReqData.getOrderType())){
				// 相关id
				trxorderDetail.setCourseId(Long.parseLong(trxReqData.getOtherId()));
				// 有效期类型
				trxorderDetail.setLosetype(1);
				MemberSale memberSale = memberSaleService.getMemberSaleById(Long.parseLong(trxReqData.getOtherId()));
				/*流水为开通会员流水*/
				trxorderDetail.setTrxorderType((OrderType.MEMBER+""));
				// 订单过期时间点
				trxorderDetail.setLoseTime(memberSale.getDays()+"");
				// 按所写时间推移过期时间
				Calendar now = Calendar.getInstance();
				now.setTime(date);

				now.set(Calendar.MONTH, now.get(Calendar.MONTH) + Integer.valueOf(memberSale.getDays()));
				trxorderDetail.setAuthTime(now.getTime());

				// 下单时间
				trxorderDetail.setCreateTime(new Date());
				// 会员原始价格
				trxorderDetail.setSourcePrice(memberSale.getPrice());
				// 会员实际支付价格
				trxorderDetail.setCurrentPrice(memberSale.getPrice());
				// 会员名
				trxorderDetail.setCourseName(memberSale.getName());
				// 订单状态
				trxorderDetail.setAuthStatus(AuthStatus.INIT.toString());
				// 订单请求号
				trxorderDetail.setRequestId(trxorder.getOrderNo());
				trxorderDetail.setLastUpdateTime(date);
				trxorderDetail.setDescription("");

				trxorderDetailList.add(trxorderDetail);
			}

			/*如果是充值*/
			if ((OrderType.ACCOUNT+"").equals(trxReqData.getOrderType())){
				// 相关id
				trxorderDetail.setCourseId(0L);
				// 有效期类型
				trxorderDetail.setLosetype(0);
				/*流水为开通会员流水*/
				trxorderDetail.setTrxorderType(OrderType.ACCOUNT+"");
				// 订单过期时间点
				trxorderDetail.setLoseTime("");
				// 按所写时间推移过期时间
				trxorderDetail.setAuthTime(new Date());

				//下单时间
				trxorderDetail.setCreateTime(new Date());
				//原始价格
				trxorderDetail.setSourcePrice(trxorder.getOrderAmount());
				//实际支付价格
				trxorderDetail.setCurrentPrice(trxorder.getOrderAmount());
				trxorderDetail.setCourseName("");
				// 订单状态
				trxorderDetail.setAuthStatus(AuthStatus.INIT.toString());
				// 订单请求号
				trxorderDetail.setRequestId(trxorder.getOrderNo());
				trxorderDetail.setLastUpdateTime(date);
				trxorderDetail.setDescription("");

				trxorderDetailList.add(trxorderDetail);
			}
		}

		// 批量添加流水
		trxorderDetailService.addBatchTrxorderDetail(trxorderDetailList);
		result.put("order", trxorder);
		return result;
	}

	/**
	 * 订单检查请求参数
	 */
	public TrxReqData checkorderparam(Map<String, String> sourceMap) {
		TrxReqData reqData = new TrxReqData();
		String typestr = sourceMap.get("type").toString();// 购物类型 1 是课程
		// 下单类型
		if (StringUtils.isNotEmpty(typestr) && Long.valueOf(typestr).longValue() > 0) {
			reqData.setType(Long.valueOf(typestr));
		} else {
			return null;
		}
		String couponcode = sourceMap.get("couponcode"); // 优惠卷编码
		if (StringUtils.isNotEmpty(couponcode)) {
			reqData.setCouponCode(couponcode);
		}
		String orderType = sourceMap.get("orderType"); // 商品类型
		if (StringUtils.isNotEmpty(orderType)) {
			reqData.setOrderType(orderType);
		}
		String otherId = sourceMap.get("otherId"); // 不走购物车的商品相关id
		if (StringUtils.isNotEmpty(otherId)) {
			reqData.setOtherId(otherId);
		}
		String userid = sourceMap.get("userid");// 用户id
		if (StringUtils.isNotEmpty(userid) && Long.valueOf(userid).longValue() > 0) {
			reqData.setUserId(Long.valueOf(userid));
		} else {
			return null;
		}

		String reqchanle = sourceMap.get("reqchanle");// 用户id
		if (StringUtils.isNotEmpty(reqchanle)) {
			reqData.setReqChannel(reqchanle);
		} else {
			return null;
		}

		String payType = sourceMap.get("payType");// 支付类型
		if (StringUtils.isNotEmpty(payType)) {

			if(PayType.class != null && !"".equals(payType)) {
				try {
					reqData.setPayType(Enum.valueOf(PayType.class, payType.trim()));
				} catch (IllegalArgumentException var3) {
					var3.printStackTrace();
				}
			}
			else {
				reqData.setPayType(null);
			}
		} else {
			return null;
		}

		String reqIp = sourceMap.get("reqIp");// 用户reqIp
		if (StringUtils.isNotEmpty(reqIp)) {
			reqData.setReqIp(reqIp);
		} else {
			reqData.setReqIp("");
		}
		return reqData;
	}

	/**
	 * 生成订单号 当前用户id+毫秒数
	 */
	public String getOrderNum(Long userId) {
		return userId + DateUtils.toString(new Date(), "yyyyMMddHHmmssSSS");
	}

	/**
	 * 根据requestId获取Trxorder
	 */
	public Order getTrxorderByRequestId(String requestId) {
		return orderDao.getTrxorderByRequestId(requestId);
	}

	/**
	 * 订单回调,余额支付订单操作 事物开启
	 */
	public Map<String, String> updateCompleteOrder(TrxReqData trxReqData) throws AccountException, StaleObjectStateException {
		logger.info("updateCompleteOrder trxReqData:" + trxReqData);
		Map<String, String> res = new HashMap<String, String>();

		UserAccount userAccount=null;
		// 计算此次订单使用的cash金额和vm金额
		BigDecimal amount = trxReqData.getAmount();// 订单所需支付的金额
		BigDecimal balance = new BigDecimal(0);// 账户余额
		BigDecimal useCashAmoun = new BigDecimal(0);//使用银行充值的金额
		BigDecimal useVmAmount = new BigDecimal(0);//使用充值卡的金额
		BigDecimal useBackAmount = new BigDecimal(0);//使用返现的金额
		if(/*"ON".equals(CacheConstans.ACCOUNT_IS_OPEN) &&*/ ObjectUtils.isNotNull(userAccountService)){
			// userAccount要重新查询一次，否则乐观锁版本号异常
			userAccount = userAccountService.getUserAccountByUserId(trxReqData.getUserId());
			balance = userAccount.getBalance();// 账户余额
			if (!AccountStatus.ACTIVE.toString().equals(userAccount.getAccountStatus().toString())) {
				res.put(OrderConstans.RESCODE, "您的账户已冻结");
				res.put("requestId", trxReqData.getRequestId());
				logger.info("updateCompleteOrder no balance,RequestId:" + trxReqData.getRequestId() + ",BankAmount:" + trxReqData.getBankAmount() + ",Balance:" + userAccount.getBalance());
				return res; // 账户被冻结
			}
			if (balance.compareTo(amount) < 0) {// 账户余额不足
				res.put(OrderConstans.RESCODE, "balance");
				res.put("amount", amount.floatValue()+"");
				res.put("balance", balance.toString());
				res.put("bankAmount", amount.subtract(balance).toString());
				res.put("requestId", trxReqData.getRequestId());
				logger.info("updateCompleteOrder no balance,RequestId:" + trxReqData.getRequestId() + ",BankAmount:" + trxReqData.getBankAmount() + ",Balance:" + userAccount.getBalance());
				return res;
			}
			if (userAccount.getVmAmount().compareTo(amount) >= 0) {// vm余额大于等于扣款的金额,vm足够支付
				useVmAmount = amount;
			} else if ( (userAccount.getVmAmount().add(userAccount.getBackAmount()) ).compareTo(amount) >= 0) {// vm余额+ back余额（返现余额） 大于等于扣款的金额,足够支付
				useVmAmount = userAccount.getVmAmount();
				useBackAmount = amount.subtract(userAccount.getVmAmount());
			} else {// 不够的时候 再扣除cash的余额
				useVmAmount = userAccount.getVmAmount();//
				useBackAmount = userAccount.getBackAmount();//
				useCashAmoun = amount.subtract(useVmAmount.add(useBackAmount));// 需要扣除的cash的金额
			}
		}


		Order trxorder = getTrxorderByRequestId(trxReqData.getRequestId().split("#")[0]);
		trxorder.setCashAmount(useCashAmoun);
		trxorder.setVmAmount(useVmAmount);
		trxorder.setBackAmount(useBackAmount);
		trxorder.setStates(TrxOrderStatus.SUCCESS.toString());
		trxorder.setPayTime(trxReqData.getCreateTime());
		trxorder.setPayType(trxReqData.getPayType().toString());
		trxorder.setOutTradeNo(trxReqData.getOut_trade_no());//第三方商户订单号
		List<Course> courses=getTrxCourseByRequestId(trxorder.getOrderNo());//订单课程集合
		//验证优惠券信息
		if(trxorder.getCouponCodeId()>0){//订单使用了优惠券
			CouponCodeDTO couponCodeDTO=couponCodeService.getCouponCodeDTO(trxorder.getCouponCodeId());

			//验证优惠券编码
			Map<String,Object> map = couponCodeService.checkCode(couponCodeDTO,courses,null);
			if(map.get("msg")!="true"){//验证不通过，返回优惠券编码错误信息
				res.put(OrderConstans.RESCODE, map.get("msg").toString());
				res.put("amount", amount.toString());
				res.put("requestId", trxReqData.getRequestId());
				return res;
			}
		}

		//判断订单类型，如果是充值，不扣款
		if(/*"ON".equals(CacheConstans.ACCOUNT_IS_OPEN) &&*/ ObjectUtils.isNotNull(userAccountService) && !(OrderType.ACCOUNT+"").equals(trxorder.getOrderType())){
			// 扣款
			userAccountService.debit(userAccount, trxReqData.getAmount(), AccountHistoryType.SALES, trxReqData.getUserId(), trxReqData.getTrxorderId(), trxReqData.getRequestId(), trxReqData.getCreateTime(), trxReqData.getDescription(), true, AccountBizType.COURSE);
		}

		// 更新订单的状态
		updateTrxorderStatusSuccess(trxorder);


		/**
		 * 分销消费 返现给邀请人
		 * @param order 订单参数
		 */
		if (ObjectUtils.isNotNull(invitationRecordService)){
			invitationRecordService.consumeCashback(trxorder);
		}

		// 遍历订单课程集合，获得课程编号集合字符串
		String ids = "";
		String courseName="";
		if (courses != null) {
			for (Course course : courses) {
				if (ids.equals("")) {
					ids = ids + course.getCourseId();
					courseName=courseName+course.getCourseName();
				} else {
					ids = "," + course.getCourseId();
					courseName=","+course.getCourseName();
				}
				// 根据ids修改所买课程的购买数量
				courseService.updateCourseCount("pageBuycount",course.getCourseId());
			}
		}
		//更改优惠券信息
		if(trxorder.getCouponCodeId()>0){//订单使用了优惠券
			CouponCodeDTO couponCodeDTO=couponCodeService.getCouponCodeDTO(trxorder.getCouponCodeId());
			couponService.updateCouponUserNum(couponCodeDTO.getCouponId());//更新优惠券使用数
			if(couponCodeDTO.getUseType()==2) {//非无限次使用权的优惠券编码才更新状态
				CouponCode couponCode = new CouponCode();
				couponCode.setPayTime(new Date());
				couponCode.setStatus(2);//已使用
				couponCode.setTrxorderId(Long.valueOf(trxorder.getOrderId()));//订单id
				couponCode.setRequestId(trxorder.getOrderNo());//订单请求号
				couponCode.setUserId(Long.valueOf(trxorder.getUserId()));//用户id
				couponCode.setUseTime(new Date());//使用时间
				couponCode.setId(trxorder.getCouponCodeId());
				couponCodeService.updateCouponCode(couponCode);//更新优惠券编码使用后的信息
			}
		}
		try{
			//发送站内信
			msgReceiveService.sendMessage(trxorder.getUserId(),"订单支付成功提示",MsgType.paySuccess.toString(), true, trxReqData.getRequestId());

		}catch(Exception e){
			e.printStackTrace();
		}
		res.put(OrderConstans.RESCODE, OrderConstans.SUCCESS);
		logger.info("updateCompleteOrder trxReqData success");
		return res;
	}
	/**
	 * 更新订单状态为成功,网银的回调
	 */
	public void updateTrxorderStatusSuccess(Order trxorder) throws StaleObjectStateException {
		// 更新订单表状态
		Long cnt = orderDao.updateTrxorderStatusSuccess(trxorder);
		QueryTrxorderDetail queryTrxorderDetail = new QueryTrxorderDetail();
		queryTrxorderDetail.setTrxorderId(new Long(trxorder.getOrderId()));
		List<QueryTrxorderDetail> queryTrxorderDetailList = trxorderDetailService.getTrxorderDetailCourseList(queryTrxorderDetail);
		// 更新流水的状态
		TrxorderDetail trxorderDetail = new TrxorderDetail();
		trxorderDetail.setPayTime(trxorder.getPayTime());
		trxorderDetail.setAuthStatus(AuthStatus.SUCCESS.toString());
		trxorderDetail.setRequestId(trxorder.getOrderNo());
		/*结束天数不为空*/
		if (StringUtils.isNotEmpty(queryTrxorderDetailList.get(0).getLoseTime())){
			/*结束天数*/
			int days = Integer.parseInt(queryTrxorderDetailList.get(0).getLoseTime());
			Calendar now = Calendar.getInstance();
			/*支付时间*/
			now.setTime(trxorder.getPayTime());
			/*开始时间为当前时间*/
			trxorderDetail.setBeginTime(now.getTime());
			if (!(OrderType.MEMBER+"").equals(trxorder.getOrderType())){
				now.set(Calendar.DATE,now.get(Calendar.DATE)+days);
			}
			/*会员结束时间按月计算*/
			if ((OrderType.MEMBER+"").equals(trxorder.getOrderType())){
				now.set(Calendar.MONTH,now.get(Calendar.MONTH)+days);
			}
			now.set(Calendar.HOUR_OF_DAY,23);
			now.set(Calendar.MINUTE,59);
			now.set(Calendar.SECOND,59);
			/*更新订单到期时间支付时间加结束天数*/
			trxorderDetail.setAuthTime(now.getTime());

		}
		/*如果是开通的会员*/
		if ((OrderType.MEMBER+"").equals(trxorder.getOrderType())){

			MemberSale memberSale = memberSaleService.getMemberSaleById(queryTrxorderDetailList.get(0).getCourseId());
			Calendar now = Calendar.getInstance();
			/*查询会员状态*/
			MemberRecordDTO memberRecordDTO = new MemberRecordDTO();
			memberRecordDTO.setUserId(new Long(trxorder.getUserId()));
			memberRecordDTO.setMemberType(memberSale.getType());
			memberRecordDTO = memberRecordService.getUserMember(memberRecordDTO);
			/*如果有开通会员记录则为续费*/
			if (ObjectUtils.isNotNull(memberRecordDTO)){
				/*如果是续费开始时间为会员的结束时间*/
				trxorderDetail.setBeginTime(memberRecordDTO.getEndDate());
				/*到期时间为当前会员的结束时间加结束月数*/
				now.setTime(memberRecordDTO.getEndDate());
				now.set(Calendar.MONTH,now.get(Calendar.MONTH)+Integer.parseInt(queryTrxorderDetailList.get(0).getLoseTime()));
				now.set(Calendar.HOUR_OF_DAY,23);
				now.set(Calendar.MINUTE,59);
				now.set(Calendar.SECOND,59);
				trxorderDetail.setAuthTime(now.getTime());
			}
		}
		/*如果是充值*/
		if ((OrderType.ACCOUNT+"").equals(trxorder.getOrderType())){
			trxorderDetail.setBeginTime(new Date());
			trxorderDetail.setAuthTime(new Date());
		}
		Long cnt2 = trxorderDetailDao.updateTrxorderDetailStatusSuccess(trxorderDetail);

		if (cnt.longValue() == 0 || cnt2.longValue() == 0) {
			throw new StaleObjectStateException(StaleObjectStateException.OPTIMISTIC_UPDATE_NONE);
		}
	}

	/**
	 * 个人中心订单查询
	 * */
	public List<Order> queryOrderForUc(QueryOrder queryTrxorder, PageEntity page) {
		return orderDao.queryOrderForUc(queryTrxorder, page);
	}


	/**
	 * 免费赠送订单操作
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> addFreeTrxorder(Map<String, String> sourceMap) throws Exception {

		Map<String, Object> result = new HashMap<String, Object>();

		// 检查参数
		TrxReqData trxReqData = checkorderparam(sourceMap);
		if (trxReqData == null) {// 参数验证失败
			result.put("msg", "parame-rror");
			return result;
		}

		Course course = courseService.queryCourseById(Integer.parseInt(sourceMap.get("courseId")));
		if (course == null) {
			result.put("msg", "couser-is-null");
			return result;
		}

		// 已经添加过无需重复添加
		List<TrxorderDetail> buyDetailList = trxorderDetailService.getTrxorderDetailListBuy(trxReqData.getUserId());
		if (buyDetailList != null && buyDetailList.size() > 0) {
			for (TrxorderDetail detail : buyDetailList) {
				if (detail.getCourseId().equals(course.getCourseId())) {
					result.put("msg", "course-already-success");
					return result;
				}
			}
		}
		// 拼装订单数据
		Order trxorder = new Order();
		// 下单时间
		Date date = new Date();
		trxorder.setCreateTime(date);// 下单时间
		trxorder.setUserId(Integer.parseInt(trxReqData.getUserId()+""));
		trxorder.setOrderNo(this.getOrderNum(trxReqData.getUserId()));// 交易请求号
		trxorder.setStates(TrxOrderStatus.SUCCESS.toString());// 交易装态,成功状态的
		trxorder.setPayType(trxReqData.getPayType().toString());// 支付类型 免费
		trxorder.setReqChannel(trxReqData.getReqChannel());
		trxorder.setReqIp(trxReqData.getReqIp());
		trxorder.setOrderType(OrderType.COURSE+"");
		trxorder.setOrderAmount(course.getCurrentPrice()); // 原始金额
		trxorder.setCouponAmount(course.getCurrentPrice());// 优惠金额
		trxorder.setSumMoney(new BigDecimal(0));// 实际支付金额
		trxorder.setDescription(FastJsonUtil.obj2JsonString(sourceMap));
		trxorder.setPayTime(date);// 支付时间
		// 添加订单
		orderDao.createOrder(trxorder);
		// 添加流水表
		List<TrxorderDetail> trxorderDetailList = new ArrayList<TrxorderDetail>();
		// 创建流水
		TrxorderDetail trxorderDetail = new TrxorderDetail();
		// 用户id
		trxorderDetail.setUserId(trxReqData.getUserId());
		// 课程id
		trxorderDetail.setCourseId(Long.valueOf(course.getCourseId()));
		// 订单id
		trxorderDetail.setTrxorderId(Long.valueOf(trxorder.getOrderId()));
		// 有效期类型
		trxorderDetail.setLosetype(course.getLoseType());
		// 订单过期时间段
		trxorderDetail.setLoseAbsTime(course.getEndTime());
		// 订单过期时间点
		trxorderDetail.setLoseTime(course.getLoseTime());

		// 赠送的课程，到期时间
		if (course.getLoseType() == 0) {
			trxorderDetail.setAuthTime(course.getEndTime());
		} else if (course.getLoseType() == 1) { // 按天数计算
			// 按所写时间推移过期时间
			Calendar now = Calendar.getInstance();
			now.setTime(date);
			now.set(Calendar.DATE, now.get(Calendar.DATE) + Integer.valueOf(course.getLoseTime()));
			trxorderDetail.setAuthTime(now.getTime());
		}
		// 如果课程已经过期，将课程从购物车中删除，并重新加载购物车
		if (date.getTime() > trxorderDetail.getAuthTime().getTime()) {
			result.put("msg", "courselosedate");
			throw new BaseException("课程已经过期");
		}

		// 下单时间
		trxorderDetail.setCreateTime(new Date());
		// 课程原始价格
		trxorderDetail.setSourcePrice(course.getCurrentPrice());
		// 课程实际支付价格
		trxorderDetail.setCurrentPrice(new BigDecimal(0));
		// 课程名
		trxorderDetail.setCourseName(course.getCourseName());
		// 课程状态（未开始）
		trxorderDetail.setAuthStatus(AuthStatus.SUCCESS.toString());
		// 订单请求号
		trxorderDetail.setRequestId(trxorder.getOrderNo());
		trxorderDetail.setLastUpdateTime(date);
		// 支付时间
		trxorderDetail.setPayTime(date);
		trxorderDetail.setBeginTime(date);
		trxorderDetail.setTrxorderType(OrderType.COURSE+"");
		trxorderDetail.setDescription("");
		// 批量添加流水
		trxorderDetailList.add(trxorderDetail);
		trxorderDetailService.addBatchTrxorderDetail(trxorderDetailList);

		for(TrxorderDetail trxorderDetailTemp:trxorderDetailList){
			//更新课程的购买量
			courseService.updateCourseCount("pageBuycount",Integer.parseInt(trxorderDetailTemp.getCourseId()+""));
		}

		result.put("msg", "success");
		result.put("requestId", trxorder.getOrderNo());//订单号
		result.put("orderId",trxorder.getOrderId());//订单ID

		//发送消息
		User user=userService.queryUserById(Integer.parseInt(trxReqData.getUserId()+""));
		Map<String, Object> websitemap = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.web.toString());
		Map<String, Object> web = (Map<String, Object>) websitemap.get("web");
		return result;

	}
	/**
	 * 某个用户免费赠送课程
	 */
	public Map<String, Object> addOrderMsg(int userId, int courseId) throws Exception{
		Map<String, String> sourceMap = new HashMap<String, String>();
		sourceMap.put("type", "1");// 涓嬪崟绫诲瀷
		sourceMap.put("couponcode", "");// 浼樻儬鍗风紪鐮�
		sourceMap.put("userid", userId + "");
		sourceMap.put("reqchanle", ReqChanle.WEB.toString());
		sourceMap.put("reqIp", "");
		sourceMap.put("courseId", courseId+"");
		sourceMap.put("payType", "FREE");
		return addFreeTrxorder(sourceMap);
	}
	/**
	 * 添加 课程卡订单信息 流水信息
	 */
	@Override
	public String addCourseCardOrder(Map<String, String> sourceMap) throws Exception {
		String returnMsg;

		TrxReqData trxReqData = checkorderparam(sourceMap);
		// 拼装订单数据
		Order order = new Order();
		// 下单时间
		Date date = new Date();
		order.setCreateTime(date);// 下单时间
		order.setUserId(Integer.parseInt(trxReqData.getUserId()+""));
		order.setOrderNo(this.getOrderNum(trxReqData.getUserId()));// 交易请求号
		order.setStates(TrxOrderStatus.SUCCESS.toString());// 交易装态,成功状态的
		order.setPayType(PayType.CARD.toString());// 支付类型 免费
		order.setReqChannel(trxReqData.getReqChannel());
		order.setReqIp(trxReqData.getReqIp());
		order.setOrderType("COURSE");//订单类型
		order.setOrderAmount(new BigDecimal(0)); // 原始金额
		order.setCouponAmount(new BigDecimal(0));// 优惠金额
		order.setSumMoney(new BigDecimal(0));// 实际支付金额
		order.setDescription(FastJsonUtil.obj2JsonString(sourceMap));
		order.setPayTime(date);// 支付时间
		orderDao.createOrder(order);
		// 添加订单
		returnMsg = order.getOrderId() + "," + order.getOrderNo();
		String[] courseArray = sourceMap.get("courseIds").split(",");
		// 添加流水表
		List<TrxorderDetail> orderDetailList = new ArrayList<TrxorderDetail>();
		for (int i = 0; i < courseArray.length; i++) {
			Course cou = courseService.queryCourseById(Integer.parseInt(courseArray[i]));
			if (ObjectUtils.isNull(cou)) {
				continue;
			}
			// 创建流水
			TrxorderDetail trxorderDetail = new TrxorderDetail();
			// 用户id
			trxorderDetail.setUserId(trxReqData.getUserId());
			// 课程id
			trxorderDetail.setCourseId(new Long(courseArray[i]));
			// 订单id
			trxorderDetail.setTrxorderId(Long.valueOf(order.getOrderId()));
			// 有效期类型
			trxorderDetail.setLosetype(cou.getLoseType());
			// 订单过期时间段
			trxorderDetail.setLoseAbsTime(cou.getEndTime());
			// 订单过期时间点
			trxorderDetail.setLoseTime(cou.getLoseTime());
			/*商品类型*/
			trxorderDetail.setTrxorderType("COURSE");
			// 到期时间
			if (cou.getLoseType() == 0) {
				trxorderDetail.setAuthTime(cou.getEndTime());
			}
			// 按天数计算
			if (cou.getLoseType() == 1) {
				// 按所写时间推移过期时间
				Calendar now = Calendar.getInstance();
				now.setTime(date);
				now.set(Calendar.DATE, now.get(Calendar.DATE) + Integer.valueOf(cou.getLoseTime()));
				trxorderDetail.setAuthTime(now.getTime());
			}

			// 下单时间
			trxorderDetail.setCreateTime(new Date());
			// 课程原始价格
			trxorderDetail.setSourcePrice(cou.getSourcePrice());
			// 课程实际支付价格
			trxorderDetail.setCurrentPrice(cou.getCurrentPrice());
			// 课程名
			trxorderDetail.setCourseName(cou.getCourseName());
			// 课程状态
			trxorderDetail.setAuthStatus(AuthStatus.SUCCESS.toString());
			trxorderDetail.setPayTime(new Date());
			// 订单请求号
			trxorderDetail.setRequestId(order.getOrderNo());
			trxorderDetail.setLastUpdateTime(date);
			trxorderDetail.setDescription("");
			orderDetailList.add(trxorderDetail);
		}
		// 批量添加流水
		trxorderDetailService.addBatchTrxorderDetail(orderDetailList);
		return returnMsg;
	}

	@Override
	public void addFreeMember(Long userId, Long memberSaleId, TrxorderDetail prevTrxorderDetail) {
		/*会员商品*/
		MemberSale memberSale = memberSaleService.getMemberSaleById(memberSaleId);
		// 拼装订单数据
		Order trxorder = new Order();
		// 下单时间
		Date date = new Date();
		trxorder.setCreateTime(date);// 下单时间
		trxorder.setUserId(Integer.parseInt(userId+""));
		trxorder.setOrderNo(this.getOrderNum(userId));// 交易请求号
		trxorder.setStates(TrxOrderStatus.SUCCESS.toString());// 交易装态,成功状态的
		trxorder.setPayType("FREE");// 支付类型 免费
		trxorder.setReqChannel(ReqChanle.WEB.toString());
		trxorder.setReqIp("");
		trxorder.setOrderType(OrderType.MEMBER+"");
		trxorder.setOrderAmount(memberSale.getPrice()); // 原始金额
		trxorder.setCouponAmount(memberSale.getPrice());// 优惠金额
		trxorder.setSumMoney(new BigDecimal(0));// 实际支付金额
		trxorder.setDescription("后台免费赠送会员");
		trxorder.setPayTime(date);// 支付时间
		// 添加订单
		orderDao.createOrder(trxorder);

		// 添加流水表
		List<TrxorderDetail> trxorderDetailList = new ArrayList<TrxorderDetail>();
		// 创建流水
		TrxorderDetail trxorderDetail = new TrxorderDetail();
		// 用户id
		trxorderDetail.setUserId(userId);
		// 商品id
		trxorderDetail.setCourseId(Long.valueOf(memberSaleId));
		// 订单id
		trxorderDetail.setTrxorderId(Long.valueOf(trxorder.getOrderId()));
		// 有效期类型
		trxorderDetail.setLosetype(1);
		// 订单过期时间段
		trxorderDetail.setLoseAbsTime(null);
		// 订单过期时间点
		trxorderDetail.setLoseTime(memberSale.getDays()+"");


		// 按所写时间推移过期时间
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.set(Calendar.MONTH, now.get(Calendar.MONTH) + Integer.valueOf(memberSale.getDays()));
		now.set(Calendar.HOUR_OF_DAY,23);
		now.set(Calendar.MINUTE,59);
		now.set(Calendar.SECOND,59);
		trxorderDetail.setAuthTime(now.getTime());
		/*到期时间*/
		trxorderDetail.setAuthTime(now.getTime());
		// 下单时间
		trxorderDetail.setCreateTime(new Date());
		// 会员原始价格
		trxorderDetail.setSourcePrice(memberSale.getPrice());
		// 会员实际支付价格
		trxorderDetail.setCurrentPrice(new BigDecimal(0));
		// 会员名
		trxorderDetail.setCourseName(memberSale.getName());
		// 会员状态（未开始）
		trxorderDetail.setAuthStatus(AuthStatus.SUCCESS.toString());
		// 订单请求号
		trxorderDetail.setRequestId(trxorder.getOrderNo());
		trxorderDetail.setLastUpdateTime(date);
		// 支付时间
		trxorderDetail.setPayTime(date);
		trxorderDetail.setBeginTime(date);
		trxorderDetail.setTrxorderType(OrderType.MEMBER+"");
		trxorderDetail.setDescription("");

		/*如果为续费*/
		if(ObjectUtils.isNotNull(prevTrxorderDetail)){
			/*续费时间*/
			Calendar addTime = Calendar.getInstance();
			/*上次开通记录时间*/
			addTime.setTime(prevTrxorderDetail.getAuthTime());
			trxorderDetail.setBeginTime(prevTrxorderDetail.getAuthTime());
			/*上次开通到期时间加本次会员开通时间*/
			addTime.set(Calendar.MONTH,addTime.get(Calendar.MONTH)+memberSale.getDays());
			now.set(Calendar.HOUR_OF_DAY,23);
			now.set(Calendar.MINUTE,59);
			now.set(Calendar.SECOND,59);
			trxorderDetail.setAuthTime(addTime.getTime());
		}
		// 批量添加流水
		trxorderDetailList.add(trxorderDetail);
		trxorderDetailService.addBatchTrxorderDetail(trxorderDetailList);
	}

	/**
	 * 添加 学员卡订单信息 流水信息
	 */
	@Override
	public String addUserCardOrder(Map<String, String> sourceMap) throws Exception {
		String returnMsg;

		TrxReqData trxReqData = checkorderparam(sourceMap);
		// 拼装订单数据
		Order trxorder = new Order();
		// 下单时间
		Date date = new Date();
		trxorder.setCreateTime(date);// 下单时间
		trxorder.setUserId(Integer.parseInt(trxReqData.getUserId()+""));
		trxorder.setOrderNo(this.getOrderNum(trxReqData.getUserId()));// 交易请求号
		trxorder.setStates(TrxOrderStatus.SUCCESS.toString());// 交易装态,成功状态的
		trxorder.setPayType(PayType.USERCARD.toString());// 支付类型 学员卡
		trxorder.setOrderType("COURSE");//订单类型为开通课程
		trxorder.setReqChannel(trxReqData.getReqChannel());
		trxorder.setReqIp(trxReqData.getReqIp());
		trxorder.setOrderAmount(new BigDecimal(0)); // 原始金额
		trxorder.setCouponAmount(new BigDecimal(0));// 优惠金额
		trxorder.setSumMoney(new BigDecimal(0));// 实际支付金额
		trxorder.setDescription(FastJsonUtil.obj2JsonString(sourceMap));
		trxorder.setPayTime(date);// 支付时间
		orderDao.createOrder(trxorder);
		// 添加订单
		returnMsg = trxorder.getOrderId() + "," + trxorder.getOrderNo();
		String[] courseArray = sourceMap.get("courseIds").split(",");
		// 添加流水表
		List<TrxorderDetail> trxorderDetailList = new ArrayList<TrxorderDetail>();
		for (int i = 0; i < courseArray.length; i++) {
			Course cou = courseService.queryCourseById(Integer.parseInt(courseArray[i]));
			if (ObjectUtils.isNull(cou)) {
				continue;
			}
			// 创建流水
			TrxorderDetail trxorderDetail = new TrxorderDetail();
			// 用户id
			trxorderDetail.setUserId(trxReqData.getUserId());
			// 课程id
			trxorderDetail.setCourseId(new Long(courseArray[i]));
			// 订单id
			trxorderDetail.setTrxorderId(Long.valueOf(trxorder.getOrderId()));
			// 流水类型
			trxorderDetail.setTrxorderType("COURSE");
			// 有效期类型
			trxorderDetail.setLosetype(cou.getLoseType());
			// 订单过期时间段
			trxorderDetail.setLoseAbsTime(cou.getEndTime());
			// 订单过期时间点
			trxorderDetail.setLoseTime(cou.getLoseTime());
			// 到期时间
			if (cou.getLoseType() == 0) {
				trxorderDetail.setAuthTime(cou.getEndTime());
			}
			// 按天数计算
			if (cou.getLoseType() == 1) {
				// 按所写时间推移过期时间
				Calendar now = Calendar.getInstance();
				now.setTime(date);
				now.set(Calendar.DATE, now.get(Calendar.DATE) + Integer.valueOf(cou.getLoseTime()));
				trxorderDetail.setAuthTime(now.getTime());
			}

			// 下单时间
			trxorderDetail.setCreateTime(new Date());
			// 课程原始价格
			trxorderDetail.setSourcePrice(cou.getSourcePrice());
			// 课程实际支付价格
			trxorderDetail.setCurrentPrice(cou.getCurrentPrice());
			// 课程名
			trxorderDetail.setCourseName(cou.getCourseName());
			// 课程状态
			trxorderDetail.setAuthStatus(AuthStatus.SUCCESS.toString());
			trxorderDetail.setPayTime(new Date());
			// 订单请求号
			trxorderDetail.setRequestId(trxorder.getOrderNo());
			trxorderDetail.setLastUpdateTime(date);
			trxorderDetail.setDescription("");
			trxorderDetailList.add(trxorderDetail);
		}
		// 批量添加流水
		trxorderDetailService.addBatchTrxorderDetail(trxorderDetailList);
		return returnMsg;
	}

}
