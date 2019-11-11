package com.inxedu.os.edu.controller.alipay;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.StringUtils;
import com.inxedu.os.edu.constants.enums.PayType;
import com.inxedu.os.edu.constants.enums.order.TrxOrderStatus;
import com.inxedu.os.edu.constants.order.OrderConstans;
import com.inxedu.os.edu.entity.order.Order;
import com.inxedu.os.edu.service.alipay.AlipayService;
import com.inxedu.os.edu.service.order.OrderService;
import com.inxedu.os.edu.service.order.TrxHessianService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author www.inxedu.com
 * 支付宝支付 controller
 */
@Controller
@RequestMapping("/zfbpay")
public class AlipayController extends BaseController{
	private static Logger logger = LoggerFactory.getLogger(AlipayController.class);

	//错误页面
	protected static final String ERROR = "/common/error";

	@Autowired
	private OrderService trxorderService;
	@Autowired
	private TrxHessianService trxHessianService;
	@Autowired
	private AlipayService alipayService;

	/**
	 * 支付宝回调
	 * @param rtype  异步，2同步
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/order/alipaynotify/{rtype}")
	public String alipayrtn(HttpServletRequest request, HttpServletResponse response, @PathVariable Long rtype, RedirectAttributes redirectAttributes) {
		logger.info("++++ alipaynotify rtype:" + rtype);
		try {
			// 支付宝返回数据的校验 最好是在异步做日志动作，可以记录mysign、sign、resposenTXT和其他值，方便以后查询错误。
			if (alipayService.getAlipayCheckInfo(request)) {
				/* 可以在不同状态下获取订单信息，操作商户数据库使数据同步 */
				// 订单编号,系统内的requestId
				String out_trade_no = request.getParameter("out_trade_no");
				String trade_no = request.getParameter("trade_no"); // 支付宝交易号
				logger.info("++++ trade_no:" + trade_no);
				// 总价
				String total_fee = request.getParameter("total_fee");
				// 订单名称
				// 订单说明
				String get_body = new String(request.getParameter("body"));
				logger.info("+++ get_body:" + get_body);
				get_body = new String(request.getParameter("body").getBytes("ISO-8859-1"), "UTF-8");
				String extra_common_param = request.getParameter("extra_common_param");// 扩展信息存用户的id
				// 注意一定要返回给支付宝一个成功的信息(不包含HTML脚本语言)
				if (request.getParameter("trade_status").equals("WAIT_BUYER_PAY")) {
					// 等待买家付款
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write("fail");
					logger.info("alipaynotify ,WAIT_BUYER_PAY");
				} else if (request.getParameter("trade_status").equals("TRADE_FINISHED") || request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
					// 校验好状态,在这里可以写入订单的数据处理,
					Map<String, String> sourceMap = new HashMap<String, String>();
					sourceMap.put("total_fee", total_fee);
					sourceMap.put("out_trade_no", out_trade_no);
					sourceMap.put("userId", extra_common_param.split(",")[0]);
					sourceMap.put("requestId", extra_common_param.split(",")[1]);
					sourceMap.put("payType", PayType.ALIPAY.toString());
					String[] orderArr = extra_common_param.split(",")[1].split("#");
					Order trxorder = trxorderService.getTrxorderByRequestId(orderArr[0]);
					if (trxorder.getStates().equals(TrxOrderStatus.SUCCESS.toString())) {
						if (rtype == 1) {
							return null;
						} else {
							redirectAttributes.addAttribute(OrderConstans.RESMSG, "支付成功");
							//订单号
							redirectAttributes.addAttribute("orderNo", trxorder.getOrderNo());
							return "redirect:/front/success";
						}
					}
					// 必须校验支付的金额
					Map<String, String> res = trxHessianService.noTrxTrxOrderComplete(sourceMap);
					logger.info("/order/alipaynotify/" + rtype + " res:" + res);
					if (ObjectUtils.isNotNull(res)) {
						redirectAttributes.addAttribute(OrderConstans.RESMSG, res.get(OrderConstans.RESMSG));
						// 根据返回的结果，订单支付成功或者充值成功时，代表成功
						if (StringUtils.isNotEmpty(res.get(OrderConstans.BANKCODE)) && res.get(OrderConstans.BANKCODE).equalsIgnoreCase(OrderConstans.SUCCESS)) {
							response.setCharacterEncoding("UTF-8");
							response.getWriter().write("success");
							redirectAttributes.addAttribute(OrderConstans.RESMSG, "支付成功");
							//订单号
							redirectAttributes.addAttribute("orderNo", trxorder.getOrderNo());
						} else {
							logger.info("alipaynotify fail chongzhi error");
							response.setCharacterEncoding("UTF-8");
							response.getWriter().write("fail");
							return "/common/error";
						}
					} else {
						logger.info("alipaynotify fail updNoTrxTrxOrderComplete null");
						response.setCharacterEncoding("UTF-8");
						response.getWriter().write("fail");
						return "/common/error";
					}
				}
			} else {
				logger.info("alipaynotify fail mysign error");
				redirectAttributes.addAttribute(OrderConstans.RESMSG, "请求异常");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("fail");
				return "/common/error";
			}
		} catch (Exception e) {
			logger.error("order_alipaynotify_error", e);
			try {
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("fail");
				return ERROR;
			} catch (IOException e1) {

			}
		}
		// 同步时跳转到成功页面
		if (rtype.longValue() == 2) {
			return "redirect:/front/success";
		}
		return null;
	}
}
