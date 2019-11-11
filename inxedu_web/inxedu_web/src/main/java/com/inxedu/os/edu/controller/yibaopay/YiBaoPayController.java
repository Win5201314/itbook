package com.inxedu.os.edu.controller.yibaopay;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.StringUtils;
import com.inxedu.os.edu.constants.enums.PayType;
import com.inxedu.os.edu.constants.enums.order.TrxOrderStatus;
import com.inxedu.os.edu.constants.order.OrderConstans;
import com.inxedu.os.edu.entity.order.Order;
import com.inxedu.os.edu.service.order.OrderService;
import com.inxedu.os.edu.service.order.TrxHessianService;
import com.inxedu.os.edu.service.yibaopay.YibaoPayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 易宝支付
 * @author www.inxedu.com
 */
@Controller
public class YiBaoPayController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(YiBaoPayController.class);

	protected static final String ERROR = "/common/error";

	@Autowired
	private OrderService trxorderService;
	@Autowired
	private TrxHessianService trxHessianService;
	@Autowired
	private YibaoPayService yibaoPayService;
	/**
	 * 易宝回调函数
	 */
	@RequestMapping("/order/ybReturn")
	public String ybReturn(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		logger.info("++ybrtn: 易宝回调");
		try {
			// 易宝返回参数解析
			Map<String, String> yeePayInfo = yibaoPayService.getYeePayInfo();
			String keyValue = formatString(yeePayInfo.get("keyValue")); // 商家密钥
			String r0_Cmd = formatString(request.getParameter("r0_Cmd")); // 业务类型
			String p1_MerId = formatString(yeePayInfo.get("p1_MerId")); // 商户编号
			String r1_Code = formatString(request.getParameter("r1_Code"));// 支付结果
			String r2_TrxId = formatString(request.getParameter("r2_TrxId"));// 易宝支付交易流水号
			String r3_Amt = formatString(request.getParameter("r3_Amt"));// 支付金额
			String r4_Cur = formatString(request.getParameter("r4_Cur"));// 交易币种
			String r5_Pid = new String(formatString(request.getParameter("r5_Pid")).getBytes("iso-8859-1"), "gbk");// 商品名称
			String r6_Order = formatString(request.getParameter("r6_Order"));// 商户订单号
			String r7_Uid = formatString(request.getParameter("r7_Uid"));// 易宝支付会员ID
			String r8_MP = new String(formatString(request.getParameter("r8_MP")).getBytes("iso-8859-1"), "gbk");// 商户扩展信息
			String r9_BType = formatString(request.getParameter("r9_BType"));// 交易结果返回类型
			String hmac = formatString(request.getParameter("hmac"));// 签名数据
			boolean isOK = false;
			// 校验返回数据包
			isOK = yibaoPayService.verifyCallback(hmac, p1_MerId, r0_Cmd, r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid, r8_MP, r9_BType, keyValue);
			if (isOK) {
				// 在接收到支付结果通知后，判断是否进行过业务逻辑处理，不要重复进行业务逻辑处理
				if (r1_Code.equals("1")) {
					// 产品通用接口支付成功
					// 校验好状态,在这里可以写入订单的数据处理,

					Map<String, String> sourceMap = new HashMap<String, String>();
					sourceMap.put("total_fee", r3_Amt);
					sourceMap.put("out_trade_no", r6_Order);
					sourceMap.put("userId", r8_MP.split(",")[0]);
					sourceMap.put("requestId", r8_MP.split(",")[1]);
					sourceMap.put("payType", PayType.YEEPAY.toString());
					Order trxorder = trxorderService.getTrxorderByRequestId(r8_MP.split(",")[1]);
					if (trxorder.getStates().equals(TrxOrderStatus.SUCCESS.toString())) {
						redirectAttributes.addAttribute(OrderConstans.RESMSG, "支付成功");
						//订单号
						redirectAttributes.addAttribute("orderNo", trxorder.getOrderNo());
						return "redirect:/front/success";
					}
					// 必须校验支付的金额
					Map<String, String> res = trxHessianService.noTrxTrxOrderComplete(sourceMap);
					logger.info("/order/ybReturn res:" + res);
					if (ObjectUtils.isNotNull(res)) {

						redirectAttributes.addAttribute(OrderConstans.RESMSG, res.get(OrderConstans.RESMSG));

						// 根据返回的结果，订单支付成功或者充值成功时，代表成功
						if (StringUtils.isNotEmpty(res.get(OrderConstans.BANKCODE)) && res.get(OrderConstans.BANKCODE).equalsIgnoreCase(OrderConstans.SUCCESS)) {
							//sendMessage(request, response, "success");// 注意一定要返回给支付宝一个成功的信息(不包含HTML脚本语言)
							response.setCharacterEncoding("UTF-8");
							response.getWriter().write("success");

							//订单号
							redirectAttributes.addAttribute("orderNo", trxorder.getOrderNo());
							return "redirect:/front/success";
						} else {
							logger.info("ybrtn fail chongzhi error");
							//sendMessage(request, response, "fail");// 失败
							response.setCharacterEncoding("UTF-8");
							response.getWriter().write("fail");
							return ERROR;
						}
					} else {
						logger.info("ybrtn fail updNoTrxTrxOrderComplete null");
						//sendMessage(request, response, "fail");// 失败
						response.setCharacterEncoding("UTF-8");
						response.getWriter().write("fail");
						return ERROR;
					}

				}
			} else {//
				logger.info("ybrtn sign 交易签名被篡改!");
				//sendMessage(request, response, "fail");// 失败
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("fail");
				return ERROR;
			}

		} catch (Exception e) {
			logger.info("易宝回调error", e);
		}
		return null;
	}

	/**
	 * 字符串格式化
	 */
	String formatString(String text) {
		if (text == null) {
			return "";
		}
		return text;
	}



}
