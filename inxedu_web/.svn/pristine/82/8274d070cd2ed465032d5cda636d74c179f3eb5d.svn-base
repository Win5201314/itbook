package com.inxedu.os.edu.controller.order;

import com.inxedu.os.common.constants.CacheConstans;
import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.SingletonLoginUtils;
import com.inxedu.os.edu.constants.enums.order.TrxOrderStatus;
import com.inxedu.os.edu.entity.coupon.CouponCode;
import com.inxedu.os.edu.entity.order.Order;
import com.inxedu.os.edu.entity.order.QueryOrder;
import com.inxedu.os.edu.service.coupon.CouponCodeService;
import com.inxedu.os.edu.service.member.MemberTypeService;
import com.inxedu.os.edu.service.order.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 前台订单
 * @author www.inxedu.com
 */
@Controller
@RequestMapping("/uc/order")
public class UcOrderController extends BaseController{
	private static Logger logger = LoggerFactory.getLogger(UcOrderController.class);

	@Autowired
	private OrderService orderService;
	@Autowired(required = false)
	private CouponCodeService couponCodeService;
	@Autowired(required = false)
	private MemberTypeService memberTypeService;
	@InitBinder({"queryOrder"})
	public void initQueryOrder(WebDataBinder binder){
		binder.setFieldDefaultPrefix("queryOrder.");
	}

	/**
	 * 我的订单列表
	 */
	@RequestMapping("/myOrderList/{state}")
	public ModelAndView myOrderList(HttpServletRequest request, @PathVariable("state") String state, @ModelAttribute("queryOrder") QueryOrder queryOrder, @ModelAttribute("page") PageEntity page){
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(getViewPath("/web/ucenter/my-order-list"));
			page.setPageSize(5);
			queryOrder.setUserId(SingletonLoginUtils.getLoginUserId(request));
			/*如果不是查看全部set查询条件*/
			if (!"ALL".equals(state)){
				queryOrder.setStates(state);
			}
			if (ObjectUtils.isNull(memberTypeService)){
				queryOrder.setCloseMember("TRUE");
			}
			List<Order> orderList = orderService.queryOrderForUc(queryOrder, page);
			// 订单信息
			request.setAttribute("orderList", orderList);
			request.setAttribute("page", page);
			request.setAttribute("state", state);
		}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("myOrderList()---error",e);
		}
		return model;
	}

	/**
	 * 重新支付验证
	 */
	@RequestMapping("/front/repaycheck/{orderId}")
	@ResponseBody
	public Map<String, Object> repayCheck(@PathVariable int orderId) {
		Map<String, Object> json = null;
		try {
			// 查询订单
			Order trxorder = orderService.queryOrderById(orderId);
			if (trxorder == null) {// 为空
				json = this.setJson(false, "订单不存在", null);
				return json;
			} else if (!trxorder.getStates().equals(TrxOrderStatus.INIT.toString())) {// 订单状态不为INIT
				json = this.setJson(false, "订单状态异常，请稍后再试", null);
				return json;
			}
			json = this.setJson(true, "true", null);
		} catch (Exception e) {
			logger.error("TrxorderController.repayCheck", e);
			json = this.setJson(false, "error", null);
		}
		return json;
	}

	/**
	 * 取消订单
	 */
	@RequestMapping("/cancleoder/{orderId}")
	public String cancleOrder(@PathVariable("orderId") int orderId) {
		try {
			Order trxorder = orderService.queryOrderById(orderId);
			trxorder.setStates("CANCEL");
			orderService.updateOrderState(trxorder);
			/**coupon_frozen_temp**/
			//优惠券 解冻
			// 查询优惠券编码
			if("ON".equals(CacheConstans.COUPON_IS_OPEN) && ObjectUtils.isNotNull(couponCodeService)){
				CouponCode couponCodeTemp=couponCodeService.getCouponCodeById(trxorder.getCouponCodeId());
				if(couponCodeTemp!=null  &&  couponCodeTemp.getStatus()==6){//冻结才修改
					couponCodeTemp.setStatus(1);//1未使用
					couponCodeTemp.setRequestId("");
					couponCodeTemp.setTrxorderId(0L);
					couponCodeService.updateCouponCode(couponCodeTemp);
				}
				/**coupon_frozen_temp**/
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("取消订单", e);
		}
		return "redirect:/uc/order/myOrderList/ALL";
	}
}
