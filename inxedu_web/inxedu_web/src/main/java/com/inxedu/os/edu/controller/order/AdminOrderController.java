package com.inxedu.os.edu.controller.order;

import com.inxedu.os.common.cache.CacheUtil;
import com.inxedu.os.common.constants.CacheConstans;
import com.inxedu.os.common.constants.CommonConstants;
import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.sysLog.SystemLog;
import com.inxedu.os.common.util.DateUtils;
import com.inxedu.os.common.util.FileExportImportUtil;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.SingletonLoginUtils;
import com.inxedu.os.edu.constants.enums.MsgType;
import com.inxedu.os.edu.constants.enums.OrderState;
import com.inxedu.os.edu.constants.enums.account.AccountBizType;
import com.inxedu.os.edu.constants.enums.account.AccountHistoryType;
import com.inxedu.os.edu.constants.enums.account.AccountType;
import com.inxedu.os.edu.constants.enums.order.AuthStatus;
import com.inxedu.os.edu.constants.enums.order.OrderType;
import com.inxedu.os.edu.constants.enums.order.TrxOrderStatus;
import com.inxedu.os.edu.entity.account.UserAccount;
import com.inxedu.os.edu.entity.coupon.CouponCode;
import com.inxedu.os.edu.entity.coupon.CouponCodeDTO;
import com.inxedu.os.edu.entity.member.MemberRecordDTO;
import com.inxedu.os.edu.entity.member.MemberSale;
import com.inxedu.os.edu.entity.order.Order;
import com.inxedu.os.edu.entity.order.QueryOrder;
import com.inxedu.os.edu.entity.order.QueryTrxorderDetail;
import com.inxedu.os.edu.entity.order.TrxorderDetail;
import com.inxedu.os.edu.entity.system.SysUser;
import com.inxedu.os.edu.entity.user.User;
import com.inxedu.os.edu.service.account.UserAccountService;
import com.inxedu.os.edu.service.coupon.CouponCodeService;
import com.inxedu.os.edu.service.coupon.CouponService;
import com.inxedu.os.edu.service.course.CourseService;
import com.inxedu.os.edu.service.letter.MsgReceiveService;
import com.inxedu.os.edu.service.member.MemberRecordService;
import com.inxedu.os.edu.service.member.MemberSaleService;
import com.inxedu.os.edu.service.order.OrderService;
import com.inxedu.os.edu.service.order.TrxorderDetailService;
import com.inxedu.os.edu.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author www.inxedu.com
 *
 */
@Controller
@RequestMapping("/admin/order")
public class AdminOrderController extends BaseController{
	private static Logger logger = LoggerFactory.getLogger(AdminOrderController.class);

	@Autowired
	private OrderService orderService;
	@Autowired(required = false)
	private CouponCodeService couponCodeService;
	@Autowired(required = false)
	private CouponService couponService;
	@Autowired
	private TrxorderDetailService trxorderDetailService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private UserService userService;
	@Autowired
	private MsgReceiveService msgReceiveService;
	@Autowired(required=false)
	private MemberRecordService memberRecordService;
	@Autowired(required=false)
	private MemberSaleService memberSaleService;
	@Autowired(required=false)
	private UserAccountService userAccountService;
	@InitBinder({"order"})
	public void initBinderorder(WebDataBinder binder){
		binder.setFieldDefaultPrefix("order.");
	}
	@InitBinder({"queryOrder"})
	public void initBinderQueryOrder(WebDataBinder binder){
		binder.setFieldDefaultPrefix("queryOrder.");
	}
	@InitBinder({"trxorderDetail"})
	public void initBinderTrxorderDetail(WebDataBinder binder){
		binder.setFieldDefaultPrefix("trxorderDetail.");
	}
	
	/**
	 * 审核订单
	 */
	@RequestMapping("/auditing/{orderNo}")
	@ResponseBody
	@SystemLog(type="update",operation="后台审核订单")
	public Map<String,Object> auditing(HttpServletRequest request,@PathVariable("orderNo") String orderNo){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			Date nowDate = new Date();
			SysUser sysUser = SingletonLoginUtils.getLoginSysUser(request);
			Order order = orderService.getTrxorderByRequestId(orderNo);
			order.setSysUserId(sysUser.getUserId());
			order.setPayTime(nowDate);
			order.setStates(OrderState.SUCCESS.toString());
			orderService.updateOrderSuccess(order);

			if("ON".equals(CacheConstans.COUPON_IS_OPEN) && ObjectUtils.isNotNull(couponCodeService)) {
				// 更改优惠券信息
				if (order.getCouponCodeId() > 0) {// 订单使用了优惠券
					CouponCodeDTO couponCodeDTO = couponCodeService.getCouponCodeDTO(order.getCouponCodeId());
					couponService.updateCouponUserNum(couponCodeDTO.getCouponId());// 更新优惠券使用数
					if (couponCodeDTO.getUseType() == 2) {// 非无限次使用权的优惠券编码才更新状态
						CouponCode couponCode = new CouponCode();
						couponCode.setPayTime(new Date());
						couponCode.setStatus(2);// 已使用
						couponCode.setTrxorderId(Long.valueOf(order.getOrderId()));// 订单id
						couponCode.setRequestId(order.getOrderNo());// 订单请求号
						couponCode.setUserId(Long.valueOf(order.getUserId()));// 用户id
						couponCode.setUseTime(new Date());// 使用时间
						couponCode.setId(order.getCouponCodeId());
						couponCodeService.updateCouponCode(couponCode);// 更新优惠券编码使用后的信息
					}
				}
			}
			List<TrxorderDetail> trxDetailList = new ArrayList<TrxorderDetail>();
			TrxorderDetail queryorder = new TrxorderDetail();
			queryorder.setRequestId(order.getOrderNo());
			// 获取订单下的流水列表
			trxDetailList = trxorderDetailService.getTrxorderDetailList(queryorder);
			if (trxDetailList != null && trxDetailList.size() > 0) {
				for (TrxorderDetail orderDetail : trxDetailList) {
					orderDetail.setPayTime(order.getPayTime());
					orderDetail.setAuthStatus(AuthStatus.SUCCESS.toString());
					/*开通时间*/
					orderDetail.setBeginTime(new Date());
					/*如果是开通会员创建开通记录*/
					if ((OrderType.MEMBER+"").equals(order.getOrderType())){
						QueryTrxorderDetail queryTrxorderDetail = new QueryTrxorderDetail();
						queryTrxorderDetail.setTrxorderId(new Long(order.getOrderId()));
						List<QueryTrxorderDetail> queryTrxorderDetailList = trxorderDetailService.getTrxorderDetailCourseList(queryTrxorderDetail);
						MemberSale memberSale = memberSaleService.getMemberSaleById(queryTrxorderDetailList.get(0).getCourseId());
						Calendar now = Calendar.getInstance();
						/*查询会员状态*/
						MemberRecordDTO memberRecordDTO = new MemberRecordDTO();
						memberRecordDTO.setUserId(new Long(order.getUserId()));
						memberRecordDTO.setMemberType(memberSale.getType());
						memberRecordDTO = memberRecordService.getUserMember(memberRecordDTO);
						/*如果有开通会员记录*/
						if (ObjectUtils.isNotNull(memberRecordDTO)){
							/*到期时间为当前会员的结束时间加结束天数*/
							now.setTime(memberRecordDTO.getEndDate());
							now.set(Calendar.MONTH,now.get(Calendar.MONTH)+Integer.parseInt(queryTrxorderDetailList.get(0).getLoseTime()));
							now.set(Calendar.HOUR_OF_DAY,23);
							now.set(Calendar.MINUTE,59);
							now.set(Calendar.SECOND,59);
							orderDetail.setAuthTime(now.getTime());
							/*开始时间为当前会员的结束时间*/
							orderDetail.setBeginTime(memberRecordDTO.getEndDate());
						}
					}
					/*如果是ACCOUNT账户充值 执行充值*/
					if ((OrderType.ACCOUNT+"").equals(order.getOrderType())){
						UserAccount userAccount =  userAccountService.getUserAccountByUserId(Long.valueOf(orderDetail.getUserId()));
						userAccountService.credit(userAccount,orderDetail.getCurrentPrice() , AccountType.CASH, AccountHistoryType.CASHLOAD, Long.valueOf(orderDetail.getUserId()),Long.valueOf(orderDetail.getTrxorderId()), orderDetail.getRequestId(),
								order.getOutTradeNo(),  new Date(), "账户充值", true, AccountBizType.CASH);//充值订单
					}
					trxorderDetailService.updateTrxorderDetail(orderDetail); // 修改流水状态
					//更新课程的购买数量
					courseService.updateCourseCount("pageBuycount",Integer.parseInt(orderDetail.getCourseId()+""));
				}

			}
			//发送站内信
			msgReceiveService.sendMessage(order.getUserId(), "开通订单", MsgType.auditingOrder.toString(), true, order.getOrderNo());
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("order", order);
			map.put("sysUser", sysUser.getLoginName());
			map.put("payTime", DateUtils.dateToStr(nowDate, "dd/MM/yyyy HH:mm:ss"));
			json = this.setJson(true, null, map);
		}catch (Exception e) {
			this.setAjaxException(json);
			logger.error("auditing()--error",e);
		}
		return json;
	}
	
	/**
	 * 取消或恢复订单
	 * @param order 订单数据
	 */
	@RequestMapping("/cancelOrRegain")
	@ResponseBody
	@SystemLog(type="update",operation="取消或恢复订单")
	public Map<String,Object> cancelOrRegain(@ModelAttribute("order") Order order){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			orderService.updateOrderState(order);
			json = this.setJson(true, null, null);
			if("ON".equals(CacheConstans.COUPON_IS_OPEN) && ObjectUtils.isNotNull(couponCodeService)) {
				/**coupon_frozen_temp**/
				//优惠券 解冻
				// 查询优惠券编码
				CouponCode couponCodeTemp = couponCodeService.getCouponCodeById(order.getCouponCodeId());
				if (couponCodeTemp != null && couponCodeTemp.getStatus() == 6 && order.getStates().equals("CANCEL")) {//冻结才修改
					couponCodeTemp.setStatus(1);//1未使用
					couponCodeTemp.setRequestId("");
					couponCodeTemp.setTrxorderId(0L);
					couponCodeService.updateCouponCode(couponCodeTemp);
				}
				/**coupon_frozen_temp**/
			}
			order = orderService.queryOrderById(order.getOrderId());
			//发送站内信
			User user=userService.queryUserById(order.getUserId());
			String messageType=MsgType.cancleOrder.toString();
			String emailTitle="取消订单";
			if(order.getStates().equals(AuthStatus.INIT.toString())){
				messageType=MsgType.recoveryOrder.toString();
				emailTitle="恢复订单";
			}
			msgReceiveService.sendMessage(order.getUserId(), emailTitle,messageType, true, order.getOrderNo());
		}catch (Exception e) {
			this.setAjaxException(json);
			logger.error("cancelOrRegain()--error",e);
		}
		return json;
	}
	
	/**
	 * 显示订单列表
	 */
	@RequestMapping("/showorderlist")
	public ModelAndView showOrderList(HttpServletRequest request, @ModelAttribute("queryOrder") QueryOrder queryOrder, @ModelAttribute("page") PageEntity page){
		ModelAndView model = new ModelAndView();
		try{
			page.setPageSize(10);
			model.setViewName(getViewPath("/admin/order/order-list"));
			List<Map<String,Object>> orderList = orderService.queryOrderListPage(queryOrder, page);
			model.addObject("orderList", orderList);
			model.addObject("page", page);
		}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("showOrderList()--error",e);
		}
		return model;
	}

	/**
	 * 课程延期:只有订单状态为SUCCESS 才可以进行延期操作
	 */
	@RequestMapping("/delayorder")
	@ResponseBody
	@SystemLog(type="update",operation="课程延期")
	public Map<String, Object> delayOrder(@ModelAttribute("trxorderDetail") TrxorderDetail trxorderDetail, HttpServletRequest request) {
		Map<String,Object> json = new HashMap<String,Object>();
		try {
			if (trxorderDetail != null) {
				Date nowDate = new Date();
				TrxorderDetail trxorderDetailUpd=trxorderDetailService.getTrxorderDetailById(trxorderDetail.getId());
				if(!trxorderDetailUpd.getAuthStatus().equals("SUCCESS")){
					json=this.setJson(false, "该订单未付款,不能延期", "");
					return json;
				}else if (trxorderDetailUpd.getAuthTime().after(trxorderDetail.getAuthTime())) {
					json=this.setJson(false, "延期时间必须大于原来的到期时间", "");
					return json;
				}
				trxorderDetailUpd = trxorderDetailService.getTrxorderDetailById(trxorderDetail.getId());
				trxorderDetailUpd.setAuthStatus(AuthStatus.SUCCESS.toString());
				trxorderDetailUpd.setAuthTime(trxorderDetail.getAuthTime());
				trxorderDetailUpd.setLastUpdateTime(nowDate);
				trxorderDetailUpd.setDescription("课程延期");
				trxorderDetailService.updateTrxorderDetail(trxorderDetailUpd);

				//发送站内信
				String conent = "【<a class=\"course-title c-333\" target=\"_blank\" href=\""+CommonConstants.contextPath+"/front/couinfo/"+trxorderDetailUpd.getCourseId()+"\">"+trxorderDetailUpd.getCourseName()+"</a>】";
				msgReceiveService.sendMessage(trxorderDetailUpd.getUserId().intValue(), "订单延期提示", MsgType.delayOrder.toString(), true, conent);
				json=this.setJson(true, "延期成功", "");
			} else {
				json=this.setJson(false, "参数不能为空", null);
			}
		} catch (Exception e) {
			logger.error("delayOrder.error----", e);
			json=this.setJson(false, "系统异常,请稍后重试", null);
		}
		return json;
	}

	/**
	 * 关闭课程：更改状态为closed
	 */
	@RequestMapping("/closeCourse/{detailId}")
	@ResponseBody
	@SystemLog(type="update",operation="关闭课程")
	public Object closeCourse(@PathVariable("detailId") Long detailId, HttpServletRequest request) {
		Map<String,Object> json = new HashMap<String,Object>();
		try {
			if (detailId != null && !"".equals(detailId)) {
				Date nowDate = new Date();
				TrxorderDetail trxorderDetail = trxorderDetailService.getTrxorderDetailById(detailId);
				trxorderDetail.setAuthStatus(AuthStatus.CLOSED.toString());
				trxorderDetail.setDescription("后台关闭");
				trxorderDetail.setLastUpdateTime(nowDate);
				trxorderDetailService.updateTrxorderDetail(trxorderDetail);
				json=this.setJson(true, "关闭课程成功", "");
				//清除 用户是否购买课程的缓存
				CacheUtil.remove(CacheConstans.USER_CANLOOK + "_" + trxorderDetail.getCourseId() + "_" + trxorderDetail.getUserId());
				//发送站内信
				String conent = "【<a class=\"course-title c-333\" target=\"_blank\" href=\""+ CommonConstants.contextPath+"/front/couinfo/"+trxorderDetail.getCourseId()+"\">"+trxorderDetail.getCourseName()+"</a>】";
				msgReceiveService.sendMessage(trxorderDetail.getUserId().intValue(),"订单课程关闭提示" , MsgType.closeCourseOrder.toString(), true, conent);
			}
		} catch (Exception e) {
			logger.error("closeCourse.error---", e);
			json=this.setJson(false, "系统异常,请稍后重试", "");
		}
		return json;
	}


	/**
	 * 退费 ：修改订单和流水状态
	 */
	@RequestMapping("/refund")
	@ResponseBody
	@SystemLog(type="update",operation="订单退费")
	public Map<String, Object> refundOrder( @ModelAttribute("order")Order order) {
		Map<String, Object> json = null;
		try {
			Order trxorder = orderService.queryOrderById(order.getOrderId());
			if(ObjectUtils.isNull(trxorder)){
				json = this.setJson(false, "操作失败，参数异常！", null);
				return json;
			}
			if (!trxorder.getStates().equals(TrxOrderStatus.SUCCESS.toString())) {
				json = this.setJson(false, "操作失败，支付成功的订单，才能退款！", null);
				return json;
			}
			trxorder.setStates(TrxOrderStatus.REFUND.toString());
			trxorder.setDescription(order.getDescription());
			trxorder.setRefundAmount(order.getRefundAmount());
			orderService.updateOrder(trxorder);
			// 记录订单操作记录

			TrxorderDetail queryorder = new TrxorderDetail();
			queryorder.setRequestId(trxorder.getOrderNo());
			// 获取订单下的流水列表
			List<TrxorderDetail> trxDetailList = trxorderDetailService.getTrxorderDetailList(queryorder);
			if (trxDetailList != null && trxDetailList.size() > 0) {
				for (TrxorderDetail orderDetail : trxDetailList) {
					orderDetail.setAuthStatus(AuthStatus.REFUND.toString());
					orderDetail.setDescription(order.getDescription());
					orderDetail.setLastUpdateTime(new Date());
					trxorderDetailService.updateTrxorderDetail(orderDetail);
					//清除 用户是否购买课程的缓存
					CacheUtil.remove(CacheConstans.USER_CANLOOK + "_" + orderDetail.getCourseId() + "_" + orderDetail.getUserId());
					// 记录流水操作记录
					// 记录用户总操作记录

				}
				//发送站内信
				msgReceiveService.sendMessage(trxorder.getUserId(), "订单退款提示", MsgType.refundOrder.toString(), true, trxorder.getOrderNo());

				//退款入账
				UserAccount userAccount = userAccountService.getUserAccountByUserId(Long.valueOf(trxorder.getUserId()));
				String out_no = "timeOrder"+System.currentTimeMillis();//系统充值没有订单，用时间戳代替不同的订单号
				userAccountService.credit(userAccount, order.getRefundAmount(), AccountType.CASH, AccountHistoryType.ADMINLOAD, Long.valueOf(trxorder.getUserId()), 0L, out_no, out_no, new Date(), "订单退款入账", true, AccountBizType.ADMIN);
				json = this.setJson(true, "退款成功", null);
			} else {
				json = this.setJson(false, "操作失败，订单流水为空", null);
			}
		} catch (Exception e) {
			logger.error("refundOrder.error-----", e);
			json = this.setJson(false, "系统异常", null);
		}
		return json;
	}



	/**
	 * 到订单详情页面
	 */
	@RequestMapping("/orderinfo/{orderId}")
	public ModelAndView orderInfo(HttpServletRequest request, @PathVariable("orderId") int orderId) {
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName(getViewPath("/admin/order/order_info"));
			Order order = orderService.queryOrderById(orderId);
			model.addObject("order", order);
			User user=userService.queryUserById(Integer.parseInt(order.getUserId() + ""));
			model.addObject("user", user);

			if("ON".equals(CacheConstans.COUPON_IS_OPEN) && ObjectUtils.isNotNull(couponCodeService)) {
				CouponCodeDTO couponCodeDTO = couponCodeService.getCouponCodeDTO(order.getCouponCodeId());
				model.addObject("couponCodeDTO", couponCodeDTO);
			}
			TrxorderDetail queryorder = new TrxorderDetail();
			queryorder.setRequestId(order.getOrderNo());
			// 获取订单下的流水列表
			List<TrxorderDetail> trxDetailList = trxorderDetailService.getTrxorderDetailList(queryorder);
			model.addObject("trxDetailList", trxDetailList);
		} catch (Exception e) {
			logger.error("orderInfo.error---", e);
			return new ModelAndView(this.setExceptionRequest(request, e));
		}
		return model;
	}

	/**
	 * 订单列表导出excel
	 */
	@RequestMapping("/orderExport")
	public void orderExport(HttpServletRequest request,HttpServletResponse response, @ModelAttribute("queryOrder") QueryOrder queryOrder, @ModelAttribute("page") PageEntity page){
		try{
			//指定文件生成路径
			String dir = request.getSession().getServletContext().getRealPath("/")+"/excelfile/order";
			//文件名
			String expName = "订单列表_" + DateUtils.getStringDateShort();
			//表头信息
			String[] headName = { "订单号","学员邮箱","学员手机","金额","创建时间","状态","支付时间","支付类型", "对外订单号"};

			//拆分为一万条数据每Excel，防止内存使用太大
			page=new PageEntity();
			page.setPageSize(10000);
			orderService.queryOrderListPage(queryOrder, page);
			int num=page.getTotalPageSize();//总页数
			List<File> srcfile = new ArrayList<File>();//生成的excel的文件的list
			for(int i=1;i<=num;i++){//循环生成num个xls文件
				page.setCurrentPage(i);
				List<Map<String,Object>> orderList = orderService.queryOrderListPage(queryOrder, page);
				List<List<String>> list=dataJoint(orderList);
				File file = FileExportImportUtil.createExcel(headName, list, expName+"_"+i,dir);
				srcfile.add(file);
			}
			FileExportImportUtil.createRar(response, dir, srcfile, expName);//生成的多excel的压缩包
		}catch (Exception e) {
			logger.error("orderExport()--error",e);
		}
	}

	/**
	 * 订单信息excel格式拼接
	 * @return
	 */
	public List<List<String>> dataJoint(List<Map<String,Object>> orderList){
		List<List<String>> list = new ArrayList<List<String>>();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 0; i < orderList.size(); i++) {
			List<String> small = new ArrayList<String>();
			small.add(orderList.get(i).get("orderNo") + "");
			small.add(orderList.get(i).get("email")+"");
			small.add(orderList.get(i).get("mobile")+"");
			small.add(orderList.get(i).get("sumMoney")+"");
			small.add(format.format(orderList.get(i).get("createTime")));
			if ("INIT".equals(orderList.get(i).get("states")+"")){
				small.add("未支付");
			}
			else if ("SUCCESS".equals(orderList.get(i).get("states")+"")){
				small.add("已支付");
			}
			else if ("REFUND".equals(orderList.get(i).get("states")+"")){
				small.add("退款");
			}
			else if ("CANCEL".equals(orderList.get(i).get("states")+"")){
				small.add("已取消");
			}
			else if ("CLOSED".equals(orderList.get(i).get("states")+"")){
				small.add("已关闭");
			}
			if(ObjectUtils.isNotNull(orderList.get(i).get("payTime"))){
				small.add(format.format(orderList.get(i).get("payTime")));
			}else {
				small.add("--");
			}
			if ("ALIPAY".equals(orderList.get(i).get("payType")+"")){
				small.add("支付宝");
			}
			else if ("WEIXIN".equals(orderList.get(i).get("payType")+"")){
				small.add("微信");
			}
			else if ("FREE".equals(orderList.get(i).get("payType")+"")){
				small.add("赠送");
			}
			else if ("INTEGRAL".equals(orderList.get(i).get("payType")+"")){
				small.add("积分");
			}
			else if ("YEEPAY".equals(orderList.get(i).get("payType")+"")){
				small.add("易宝");
			}
			else if ("USERCARD".equals(orderList.get(i).get("payType")+"")){
				small.add("学员卡");
			}
			else if ("CARD".equals(orderList.get(i).get("payType")+"")){
				small.add("课程卡");
			}
			small.add(orderList.get(i).get("outTradeNo")+"");
			list.add(small);
		}
		return list;
	}
}
