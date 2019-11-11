package com.inxedu.os.edu.controller.pay;

import com.inxedu.os.common.constants.CacheConstans;
import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.SingletonLoginUtils;
import com.inxedu.os.common.util.StringUtils;
import com.inxedu.os.common.util.WebUtils;
import com.inxedu.os.edu.constants.enums.PayType;
import com.inxedu.os.edu.constants.enums.ProfileType;
import com.inxedu.os.edu.constants.enums.order.OrderType;
import com.inxedu.os.edu.constants.enums.order.TrxOrderStatus;
import com.inxedu.os.edu.constants.order.OrderConstans;
import com.inxedu.os.edu.entity.coupon.CouponCode;
import com.inxedu.os.edu.entity.coupon.CouponCodeDTO;
import com.inxedu.os.edu.entity.coupon.QueryCouponCode;
import com.inxedu.os.edu.entity.course.Course;
import com.inxedu.os.edu.entity.member.MemberSale;
import com.inxedu.os.edu.entity.member.MemberType;
import com.inxedu.os.edu.entity.order.Order;
import com.inxedu.os.edu.entity.order.QueryTrxorderDetail;
import com.inxedu.os.edu.entity.order.TrxorderDetail;
import com.inxedu.os.edu.entity.userprofile.UserProfile;
import com.inxedu.os.edu.service.account.UserAccountService;
import com.inxedu.os.edu.service.alipay.AlipayService;
import com.inxedu.os.edu.service.coupon.CouponCodeService;
import com.inxedu.os.edu.service.member.MemberSaleService;
import com.inxedu.os.edu.service.member.MemberTypeService;
import com.inxedu.os.edu.service.order.OrderService;
import com.inxedu.os.edu.service.order.TrxHessianService;
import com.inxedu.os.edu.service.order.TrxorderDetailService;
import com.inxedu.os.edu.service.userprofile.UserProfileService;
import com.inxedu.os.edu.service.weixin.WeixinPayService;
import com.inxedu.os.edu.service.yibaopay.YibaoPayService;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 支付
 * @author www.inxedu.com
 */
@Controller
public class PayController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(PayController.class);

	// 绑定变量名字和属性，参数封装进类
	@InitBinder("queryTrxorder")
	public void initBinderQueryTrxorder(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("queryTrxorder.");
	}
	protected static final String ERROR = "/common/error";

	//访问受限
	private static String visitLimit = getViewPath("/web/pay/visit-limit");

	@Autowired
	private OrderService trxorderService;
	@Autowired(required = false)
	private CouponCodeService couponCodeService;
	@Autowired
	private TrxorderDetailService trxorderDetailService;
	@Autowired(required=false)
	private AlipayService alipayService;
	@Autowired(required=false)
	private WeixinPayService weixinPayService;
	@Autowired(required=false)
	private YibaoPayService yibaoPayService;
	@Autowired(required=false)
	private UserProfileService userProfileService;
	@Autowired(required=false)
	private MemberTypeService memberTypeService;
	@Autowired(required=false)
	private MemberSaleService memberSaleService;
	@Autowired
	private TrxHessianService trxHessianService;
	@Autowired(required=false)
	private UserAccountService userAccountService;

	@Getter
	@Setter
	private static String encodingCharset = "UTF-8";

	/**
	 * 跳转到支付宝银行，企业支付宝，个人未开发 , 易宝支付
	 */
	@RequestMapping("/order/bank")
	public String gotobank(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "orderId", required = true) Long orderId, @RequestParam(value = "payType", required = true) String payType, RedirectAttributes redirectAttributes,Model model) {
		try {
			// 查询订单
			Order trxorder = trxorderService.queryOrderById(orderId.intValue());
			/*如果订单总金额等于零*//*
			if (trxorder.getSumMoney().compareTo(BigDecimal.ZERO)!=1){
				*//*调用支付完成更新订单方法*//*
				*//*订单支付时间*//*
				trxorder.setPayTime(new Date());
				*//*更改订单支付状态*//*
				trxorder.setStates("SUCCESS");
                trxorderService.updateTrxorderStatusSuccess(trxorder);
				*//*更新优惠券状态*//*
				CouponCode couponCode = couponCodeService.getCouponCodeById(trxorder.getCouponCodeId());
				couponCode.setStatus(2);
				couponCodeService.updateCouponCode(couponCode);
				*//*传入订单号，回调支付成功*//*
				redirectAttributes.addAttribute("orderNo", trxorder.getOrderNo());
				return "redirect:/front/success";
			}*/
			if (ObjectUtils.isNotNull(trxorder)) {
				//订单号
				String requestId =trxorder.getOrderNo();
				Map<String, String> res;
				if((OrderType.ACCOUNT+"").equals(trxorder.getOrderType()) || !"ON".equals(CacheConstans.ACCOUNT_IS_OPEN) || ObjectUtils.isNull(userAccountService) ){//账户充值
					//不走账户支付
					res = new HashMap<>();
					res.put(OrderConstans.RESCODE, "balance");
					res.put("amount", trxorder.getSumMoney()+"");
					res.put("balance", "0");
					res.put("bankAmount", trxorder.getSumMoney()+"");
					res.put("requestId", requestId);
				}else{
					// 先查询账户的金额是否足够支付本次订单的，如果够，直接走扣账流程
					Map<String, String> sourceMap = new HashMap<String, String>();
					sourceMap.put("total_fee", "0.00");// 充值金额，先设置为0.尝试账户余额直接支付
					sourceMap.put("requestId", requestId);
					sourceMap.put("userId", SingletonLoginUtils.getLoginUserId(request)+"");
					sourceMap.put("payType", PayType.ACCOUNT.toString());
					res = trxHessianService.noTrxTrxOrderComplete(sourceMap);
				}

				// 余额支付成功,直接返回支付成功页面
				if (res.get(OrderConstans.RESCODE).equals(OrderConstans.SUCCESS)) {
					redirectAttributes.addAttribute(OrderConstans.RESMSG, res.get(OrderConstans.RESMSG));
					//订单号
					redirectAttributes.addAttribute("orderNo", trxorder.getOrderNo());
					return "redirect:/front/success";
				} else if ("balance".equals(res.get(OrderConstans.RESCODE))) {// 余额不足，跳转到银行页面
					// 不够时，走银行流程，支付的金额为差的金额
					if (payType.equals(PayType.ALIPAY.toString())) {
						if("ON".equals(CacheConstans.ZHIFUBAO_IS_OPEN) && ObjectUtils.isNotNull(alipayService)){
							return alipayService.gotoalipay(request, response, res);
						}else{
							request.setAttribute("msg","您暂未购买【支付宝支付】功能，无法使用");
							return visitLimit;
						}
					} else if (payType.equals(PayType.YEEPAY.toString())) {
						if("ON".equals(CacheConstans.YIBAO_IS_OPEN) && ObjectUtils.isNotNull(yibaoPayService)){
							return yibaoPayService.gotoyp(request, response, res);
						}else{
							request.setAttribute("msg","您暂未购买【易宝支付】功能，无法使用");
							return visitLimit;
						}

					} else if (payType.equals(PayType.WEIXIN.toString())) {
						if("ON".equals(CacheConstans.WEIXIN_IS_OPEN) && ObjectUtils.isNotNull(weixinPayService)){
							//进行微信扫码支付
							String wxPayUrl = weixinPayService.getWxpayUrl(trxorder.getOrderNo(), trxorder.getOrderType());
							request.setAttribute("wxPayUrl", wxPayUrl);
							request.setAttribute("requestId", requestId);
							request.setAttribute("orderId", trxorder.getOrderId());
							request.setAttribute("type", "course");

							//判断是否是微信内置浏览器，
							String agent=request.getHeader("User-Agent");
							if(agent.toLowerCase().indexOf("micromessenger")>-1&& ObjectUtils.isNotNull(userProfileService)){
								request.setAttribute("weixinbrowser", "true");

								//内置浏览器  并且 用户的 公众号 openid 不为空  添加jspi支付
								//查询当前用户的 公众号 openid
								UserProfile userProfile=new UserProfile();
								userProfile.setUserid(Long.valueOf(SingletonLoginUtils.getLoginUserId(request)));
								userProfile.setProfiletype(ProfileType.WEIXIN.toString());
								List<UserProfile> userProfileList=userProfileService.getUserProfileList(userProfile);
								if (ObjectUtils.isNotNull(userProfileList) && StringUtils.isNotEmpty(userProfileList.get(0).getValueTwo())){
									//微信公众号支付请求(jspi支付)
									model = weixinPayService.JSAPI(model, trxorder, userProfileList.get(0));
								}
							}
							return getViewPath("/web/weixin/QRCode");
						}else{
							request.setAttribute("msg","您暂未购买【微信支付】功能，无法使用");
							return visitLimit;
						}
					}
				} else {// 优惠券错误信息
					redirectAttributes.addAttribute(OrderConstans.RESMSG, res.get(OrderConstans.RESMSG));
					//订单号
					redirectAttributes.addAttribute("orderNo", trxorder.getOrderNo());
					return "redirect:/front/success";
				}
			}
			return ERROR;
		} catch (Exception e) {
			this.setExceptionRequest(request, e);
			return ERROR;
		}
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
			Order trxorder = trxorderService.queryOrderById(orderId);
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
	 * 重新支付显示页面
	 */
	@RequestMapping("/front/repay/{orderId}")
	public String repay(HttpServletRequest request, @PathVariable int orderId) {
		try {
			// 查询订单
			Order trxorder = trxorderService.queryOrderById(orderId);
			if (ObjectUtils.isNotNull(trxorder) ) {// 不为空且订单状态为INIT
				request.setAttribute("trxorder", trxorder);
				request.setAttribute("originalPayType",request.getParameter("payType"));//支付方式
				TrxorderDetail trxorderDetail = new TrxorderDetail();
				trxorderDetail.setRequestId(trxorder.getOrderNo());
				/*trxorderDetail.setTrxorderType("COURSE");
				*//*如果没有传订单类型就查课程*//*
				if (StringUtils.isNotEmpty(request.getParameter("orderType"))){
					trxorderDetail.setTrxorderType(request.getParameter("orderType"));
				}*/
				// 订单流水 购物车显示
				List<TrxorderDetail> orderList = trxorderDetailService.getTrxorderDetailList(trxorderDetail);
				request.setAttribute("orderList", orderList);
				if (trxorder.getCouponCodeId() > 0) {// 查询优惠券编码信息
					// 查询优惠券编码
					CouponCode couponCode = couponCodeService.getCouponCodeById(trxorder.getCouponCodeId());
					request.setAttribute("couponCode", couponCode.getCouponCode());
				}
				// 需要支付的金额
				request.setAttribute("bankAmount", trxorder.getOrderAmount());

				if("ON".equals(CacheConstans.COUPON_IS_OPEN) && ObjectUtils.isNotNull(couponCodeService)){
					//查询我的优惠券 查询可以使用  并且达到优惠券最低限额的优惠券
					QueryCouponCode queryCouponCode=new QueryCouponCode();
					queryCouponCode.setUserId(Long.valueOf(SingletonLoginUtils.getLoginUserId(request)));
					queryCouponCode.setLimitAmount( trxorder.getOrderAmount());
					queryCouponCode.setStatus(1);
					queryCouponCode.setType(1);//查询课程优惠券（非会员优惠券）
					List<CouponCodeDTO> couponCodeList=couponCodeService.queryCouponCodeByUser(queryCouponCode);
					request.setAttribute("couponCodeList",couponCodeList);
				}

				//是否显示弹窗
				request.setAttribute("showPayDialog",request.getParameter("showPayDialog"));
				String payType = "支付宝";
				if("WEIXIN".equals(request.getParameter("payType"))){
					payType = "微信";
				}
				if("YEEPAY".equals(request.getParameter("payType"))){
					payType = "易宝";
				}
				request.setAttribute("payType",payType);
				//选择的银行
				request.setAttribute("paybank",request.getParameter("paybank"));

				if ((OrderType.MEMBER+"").equals(trxorder.getOrderType())&& ObjectUtils.isNotNull(memberSaleService)){
					/*订单对应的会员商品*/
					MemberSale memberSale = memberSaleService.getMemberSaleById(orderList.get(0).getCourseId());
					MemberType memberType = memberTypeService.getMemberTypeById(memberSale.getType());
					request.setAttribute("memberType",memberType);
					request.setAttribute("memberSale",memberSale);
					return getViewPath("/web/order/memberPay");
				}else if((OrderType.ACCOUNT+"").equals(trxorder.getOrderType())){
					request.setAttribute("recharge",trxorder.getSumMoney());
					return getViewPath("/web/account/account-rebuy");
				}

				if (ObjectUtils.isNotNull(userAccountService)){
					request.setAttribute("userAccount",userAccountService.getUserAccountByUserId(Long.valueOf(trxorder.getUserId())));
				}
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			return setExceptionRequest(request, e);
		}
		return getViewPath("/web/order/repay");
	}

	/**
	 * 重新支付修改订单信息
	 */
	@RequestMapping("/front/repayupdate")
	@ResponseBody
	public Map<String, Object> repay(HttpServletRequest request) {
		Map<String, Object> json = null;
		try {
			String couponCode = request.getParameter("couponcode");// 优惠编码
			String requestId = request.getParameter("requestId");// 订单请求号
			// 查询订单
			Order trxorder = trxorderService.getTrxorderByRequestId(requestId);
			CouponCodeDTO couponCodeDTO = null;
			Map<String, Object> mapCode = null;
			if (couponCode != null && couponCode != "") {// 重新支付使用了优惠券
				// 查询优惠券编码
				couponCodeDTO = couponCodeService.getCouponCodeByCode(couponCode);
				List<Course> courses = trxorderService.getTrxCourseByRequestId(trxorder.getOrderNo());// 订单课程集合
				// 验证优惠券编码
				mapCode = couponCodeService.checkCode(couponCodeDTO, courses,Long.valueOf(SingletonLoginUtils.getLoginUserId(request)));
				if (mapCode.get("msg") != "true") {// 优惠券编码验证不通过
					json = this.setJson(false, mapCode.get("msg").toString(), null);
					return json;
				}
				/**coupon_frozen_temp**/
				//优惠券冻结状态
				if(trxorder.getCouponCodeId().intValue()!=couponCodeDTO.getId().intValue()  &&  couponCodeDTO.getStatus()==6){
					json = this.setJson(false, "frozen", null);
					return json;
				}
				/**coupon_frozen_temp**/
			}
			// 优惠金额
			BigDecimal couponAmount = new BigDecimal(0);
			// 还需要支付的金额
			BigDecimal amount = new BigDecimal(0);
			// 计算金额，更新订单
			if (ObjectUtils.isNotNull(trxorder)) {// 不为空且订单状态为INIT
				if (trxorder.getCouponCodeId() <= 0 && couponCode.equals("")) {// 订单未使用优惠券,重新支付也未使用优惠券
																				// 不更新
					json = this.setJson(true, "true", null);
				}
				// 订单使用了优惠券并且未更改优惠券 不更新
				else if (trxorder.getCouponCodeId() > 0 && couponCodeDTO != null && couponCodeDTO.getId().equals(trxorder.getCouponCodeId())) {
					json = this.setJson(true, "true", null);
				} else {// 更新订单的优惠券列并重新计算价格
					if (couponCode.equals("")) {// 重新支付无优惠券
						/**coupon_frozen_temp**/
						//重新支付 不使用优惠券，之前使用优惠券
						//优惠券 解冻
						// 查询优惠券编码
						CouponCode couponCodeTemp=couponCodeService.getCouponCodeById(trxorder.getCouponCodeId());
						if(couponCodeTemp.getStatus()==6){//冻结才修改
							couponCodeTemp.setStatus(1);//1未使用
							couponCodeTemp.setRequestId("");
							couponCodeTemp.setTrxorderId(0L);
							couponCodeService.updateCouponCode(couponCodeTemp);
						}
						/**coupon_frozen_temp**/
						trxorder.setCouponCodeId(0L);// 优惠券编码id
						trxorder.setCouponAmount(new BigDecimal(0));// 优惠金额
						trxorder.setDescription("无");
						trxorder.setSumMoney(trxorder.getOrderAmount());// 实际支付金额等于订单金额
					} else {// 重新支付有优惠券
						/**coupon_frozen_temp**/
						//重新支付 不使用优惠券，之前使用优惠券
						//优惠券 解冻
						// 查询优惠券编码
						CouponCode couponCodeTemp=couponCodeService.getCouponCodeById(trxorder.getCouponCodeId());
						if(couponCodeTemp!=null  &&  couponCodeTemp.getStatus()==6){//冻结才修改
							couponCodeTemp.setStatus(1);//1未使用
							couponCodeTemp.setRequestId("");
							couponCodeTemp.setTrxorderId(0L);
							couponCodeService.updateCouponCode(couponCodeTemp);
						}
						//冻结优惠券 （使用了别的优惠券）
						if (couponCode != null && couponCode != "") {// 使用优惠券
							CouponCodeDTO couponCodeDTOTemp=couponCodeService.getCouponCodeByCode(couponCode);
							if(couponCodeDTOTemp!=null  &&  couponCodeDTOTemp.getUseType()==2) {//非无限次使用权的优惠券编码才更新状态
								// 查询优惠券编码
								couponCodeTemp=couponCodeService.getCouponCodeById(couponCodeDTOTemp.getId());
								couponCodeTemp.setStatus(6);//6冻结 （优惠券已在订单中使用，但未支付）
								couponCodeTemp.setRequestId(trxorder.getOrderNo());
								couponCodeTemp.setTrxorderId(Long.valueOf(trxorder.getOrderId()));
								couponCodeService.updateCouponCode(couponCodeTemp);
							}
						}
						/**coupon_frozen_temp**/
						trxorder.setCouponCodeId(couponCodeDTO.getId());// 优惠券编码id
						couponAmount = couponCodeDTO.getAmount();// 定额优惠金额
						trxorder.setCouponAmount(couponAmount);// 优惠金额
						// 实际需要支付的金额,四舍五去取2位
						amount = trxorder.getOrderAmount().subtract(couponAmount).setScale(2, RoundingMode.HALF_UP);
						trxorder.setSumMoney(amount);// 实际支付金额
						if (mapCode.get("limitCourseIds") != null) {// 订单描述，限制的课程id字符串
							trxorder.setDescription("课程限制：" + mapCode.get("limitCourseIds").toString());
						} else {
							trxorder.setDescription("课程限制：所有课程");
						}
					}
					trxorderService.updateOrder(trxorder);
				}
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("amount", trxorder.getSumMoney());
				json = this.setJson(true, "true", map);
			} else {
				json = this.setJson(false, "false", null);
			}
		} catch (Exception e) {
			logger.error("TrxorderController.repayupdate", e);
			json = this.setJson(false, "error", null);
		}
		return json;
	}


	/**
	 * 支付成功
	 */
	@RequestMapping("/front/success")
	public String paySuccess(Model model, HttpServletRequest request) {
		//订单号
		// 查询订单
		Order trxorder = trxorderService.getTrxorderByRequestId(request.getParameter("orderNo"));
		model.addAttribute("trxorder", trxorder);
		/*如果是课程订单*/
		if ((OrderType.COURSE+"").equals(trxorder.getOrderType())){
			//订单里的 课程
			List<Course> courseList=trxorderService.getTrxCourseByRequestId(request.getParameter("orderNo"));
			model.addAttribute("courseList", courseList);
		} else if ((OrderType.MEMBER+"").equals(trxorder.getOrderType())&& ObjectUtils.isNotNull(memberSaleService)){
			QueryTrxorderDetail queryTrxorderDetail = new QueryTrxorderDetail();
			queryTrxorderDetail.setUserId(new Long(SingletonLoginUtils.getLoginUserId(request)));
			queryTrxorderDetail.setTrxorderId(new Long(trxorder.getOrderId()));
			/*根据订单取流水*/
			List<QueryTrxorderDetail> queryTrxorderDetailList = trxorderDetailService.getTrxorderDetailCourseList(queryTrxorderDetail);
			if (ObjectUtils.isNotNull(queryTrxorderDetailList)){
				/*根据流水取会员类型*/
				MemberSale memberSale = memberSaleService.getMemberSaleById(queryTrxorderDetailList.get(0).getCourseId());
				model.addAttribute("memberSale",memberSale);
			}


		}
		model.addAttribute(OrderConstans.RESMSG, WebUtils.replaceTagHTML(request.getParameter(OrderConstans.RESMSG)));
		if (request.getParameter("type") != null) {
			model.addAttribute("type", WebUtils.replaceTagHTML(request.getParameter("type")));
		}
		return getViewPath("/web/order/paySucess");
	}
}