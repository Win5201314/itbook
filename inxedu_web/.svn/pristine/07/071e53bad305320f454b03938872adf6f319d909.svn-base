package com.inxedu.os.edu.controller.weixinpay;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.util.HttpUtil;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.edu.constants.enums.PayType;
import com.inxedu.os.edu.constants.enums.order.TrxOrderStatus;
import com.inxedu.os.edu.constants.order.OrderConstans;
import com.inxedu.os.edu.entity.order.Order;
import com.inxedu.os.edu.service.order.OrderService;
import com.inxedu.os.edu.service.order.TrxHessianService;
import com.inxedu.os.edu.service.weixin.WeixinPayService;
import com.inxedu.os.edu.util.weixin.MessageUtil;
import com.inxedu.os.edu.util.weixin.PackageUtil;
import com.inxedu.os.edu.util.weixin.XMLParse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 微信支付
 * @author Administrator
 *
 */
@Controller
public class WeixinPayController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(WeixinPayController.class);


	@Autowired
	private OrderService orderService;
	@Autowired
	private TrxHessianService trxHessianService;
	@Autowired(required=false)
	private WeixinPayService weixinPayService;

	/**
	 * 订单验证是否支付成功
	 */
	@RequestMapping("/order/review")
	@ResponseBody
	public Map<String, Object> reviewTrxOrderByRequestId(HttpServletRequest request) {
		Map<String, Object> json = null;
		try {
			String requestId = request.getParameter("requestId");
			String trxStatus = "";
			Order trxOrder = orderService.getTrxorderByRequestId(requestId.split("#")[0]);
			trxStatus = trxOrder != null ? trxOrder.getStates() : "";
			if (trxStatus.equalsIgnoreCase(TrxOrderStatus.SUCCESS.toString())) {// 订单支付成功
				json = this.setJson(true, "true", null);
			} else {
				json = this.setJson(true, "false", null);
			}
		} catch (Exception e) {
			logger.error("eviewTrxOrderByRequestId---------e", e);
			json = this.setJson(false, "error", null);
		}
		return json;
	}

	/**
	 * 微信支付获取商品package
	 */
	@RequestMapping("/bizpaygetpackage")
	public void bizpaygetpackage(HttpServletRequest request, HttpServletResponse response) {
		try {
			System.out.println("bizpaygetpackage");
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			String productId = requestMap.get("product_id");
			Map<String, String> websitemap = weixinPayService.getWxpayInfo();// 获得微信支付配置
			String payKey = websitemap.get("payKey");
			if (checkSignature(requestMap, payKey)) {// 验证微信回调的签名
				String requestId = productId.split("#")[0];
				Order order = orderService.getTrxorderByRequestId(requestId);
				SortedMap<String, String> packageParams = weixinPayService.createPackage(weixinPayService.getPayContent(order.getOrderType()), productId, productId, order.getSumMoney(), order.getUserId(), order.getReqIp(), "/order/course/wxpaynotify", websitemap,"","NATIVE");

				// sign签名
				String sign = PackageUtil.createSign(packageParams, payKey);
				// 统一支付请求参数xml
				String xmlPackage = XMLParse.generateXmlPackage(packageParams, sign);
				// 预支付订单创建结果
				String resultXmlStr = HttpUtil.doPostXml("https://api.mch.weixin.qq.com/pay/unifiedorder", xmlPackage);
				Map<String, String> resultMap = MessageUtil.parseStringXml(resultXmlStr);
				String returnCode = resultMap.get("return_code");// 通信标识返回状态码
				String returnMsg = resultMap.get("return_msg");// 返回状态消息
				if (returnCode.equalsIgnoreCase("FAIL")) {
					logger.info("return_code-----fail:" + returnMsg);
				} else {
					String result_code = resultMap.get("result_code");// 业务结果
					if (result_code.equalsIgnoreCase("FAIL")) {
						logger.info("result_code-----fail:err_code" + resultMap.get("err_code") + "/n" + "err_code_des" + resultMap.get("result_code"));
						logger.info("err_code:" + resultMap.get("err_code"));
						logger.info("err_code_des:" + resultMap.get("err_code_des"));
					} else {
						String prepay_id = resultMap.get("prepay_id");
						// 返回微信消息
						// 请求参数包
						String trxStatus = "";
						Order trxOrder = orderService.getTrxorderByRequestId(requestId);
						trxStatus = trxOrder != null ? trxOrder.getStates() : "";
						SortedMap<String, String> returnPackageParams = returnPackage(requestId, prepay_id, trxStatus, websitemap);
						// sign签名
						String returnSign = PackageUtil.createSign(returnPackageParams, payKey);
						// 统一支付请求参数xml
						String xmlReturnPackage = XMLParse.returnXmlPackage(returnPackageParams, returnSign);
						OutputStream os = response.getOutputStream();
						BufferedWriter resBr = new BufferedWriter(new OutputStreamWriter(os));
						resBr.write(xmlReturnPackage);
						resBr.flush();
						resBr.close();
					}
				}
			}
		} catch (Exception e) {
			this.setExceptionRequest(request, e);
		}

	}

	/**
	 * 验证回调签名
	 */
	public boolean checkSignature(Map<String, String> requestMap, String payKey) {
		SortedMap<String, String> signParams = new TreeMap<String, String>();
		String signature = requestMap.get("sign");
		if (signature == null || signature.equals("")) {
			return false;
		}
		requestMap.remove("sign");
		for (Map.Entry<String, String> entry : requestMap.entrySet()) {// 循环放入树Map
			signParams.put(entry.getKey(), entry.getValue());
		}
		try {
			String sign = PackageUtil.createSign(signParams, payKey);
			if (signature.equalsIgnoreCase(sign)) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	/**
	 * 微信预支付订单返回参数
	 */
	public SortedMap<String, String> returnPackage(String requestId, String prepay_id, String trxStatus, Map<String, String> websitemap) {
		// 设置package参数
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("return_code", "SUCCESS");// 通信状态
		String noncestr = PackageUtil.getNonceStr();// 随机字符串
		packageParams.put("appid", websitemap.get("appid")); // appid
		packageParams.put("mch_id", websitemap.get("mchid")); // 商户号id
		packageParams.put("nonce_str", noncestr);
		packageParams.put("prepay_id", prepay_id);
		String retErrMsg = "";
		if (trxStatus.equals("")) {
			retErrMsg = "订单已失效";
		} else if (trxStatus.equals(TrxOrderStatus.SUCCESS.toString())) {// 支付成功
			retErrMsg = "订单已支付完成";
		} else if (trxStatus.equals(TrxOrderStatus.REFUND.toString())) {// 退款
			retErrMsg = "订单已退款";
		} else if (trxStatus.equals(TrxOrderStatus.CANCEL.toString())) {// 取消cancel
			retErrMsg = "订单已取消";
		}
		packageParams.put("err_code_des", retErrMsg); // 错误信息
		String resultCode = "SUCCESS";
		if (!retErrMsg.equals("")) {// 错误消息不为空，返回失败状态，直接显示错误消息
			resultCode = "FAIL";
		}
		packageParams.put("result_code", resultCode);
		return packageParams;
	}


	/**
	 * 课程订单微信支付通知
	 */
	@RequestMapping("/order/course/wxpaynotify")
	public void wxpayrtn(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			logger.info("-----------------/order/wxpaynotify");
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			Map<String, String> websitemap = weixinPayService.getWxpayInfo();// 获得微信支付配置
			// 验证签名，参数签名及xml签名、通信成功
			if (checkSignature(requestMap, websitemap.get("payKey")) && requestMap.get("return_code").equals("SUCCESS")) {
				if (requestMap.get("result_code").equals("SUCCESS")) {
					/* 可以在不同状态下获取订单信息，操作商户数据库使数据同步 */
					// 订单编号,非requestId，给微信的payId
					String out_trade_no = requestMap.get("out_trade_no");
					logger.info("++++ out_trade_no:" + out_trade_no);
					// 微信交易号
					String transaction_id = requestMap.get("transaction_id");
					logger.info("++++ transaction_id:" + transaction_id);
					// 总价 微信是分为单位，需要转化为元
					String total_fee = requestMap.get("total_fee");
					total_fee = new BigDecimal(total_fee).divide(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP).toString();
					// 附加数据 用户id，订单requestId,订单类型
					String attach = requestMap.get("attach");
					// 校验好状态,在这里可以写入订单的数据处理,
					Map<String, String> sourceMap = new HashMap<String, String>();
					sourceMap.put("total_fee", total_fee);
					sourceMap.put("out_trade_no", out_trade_no);
					sourceMap.put("requestId", attach.split(",")[0].split("#")[0]);
					sourceMap.put("userId", attach.split(",")[1]);
					sourceMap.put("payType", PayType.WEIXIN.toString());
					Order trxorder = orderService.getTrxorderByRequestId(attach.split(",")[0].split("#")[0]);
					if (trxorder.getStates().equals(TrxOrderStatus.SUCCESS.toString())) {
						sendXmlMessage(request, response, "SUCCESS");// 注意一定要返回给微信一个成功的信息(不包含HTML脚本语言)
					} else {
						// 必须校验支付的金额
						Map<String, String> res = trxHessianService.noTrxTrxOrderComplete(sourceMap);
						if (ObjectUtils.isNotNull(res)) {
							// 根据返回的结果，订单支付成功或者充值成功时，代表成功
							if (res.get(OrderConstans.BANKCODE).equalsIgnoreCase("success")) {
								sendXmlMessage(request, response, "SUCCESS");// 注意一定要返回给微信一个成功的信息(不包含HTML脚本语言)
							} else {
								logger.info("alipaynotify fail chongzhi error");
								sendXmlMessage(request, response, "FAIL");// 失败
							}
						} else {
							logger.info("alipaynotify fail updNoTrxTrxOrderComplete null");
							sendXmlMessage(request, response, "FAIL");// 失败
						}
					}

				} else {
					logger.info("wxpaynotify fail result_code error");
					logger.info("err_code:" + requestMap.get("return_code"));
					logger.info("err_code_des:" + requestMap.get("err_code_des"));
					sendXmlMessage(request, response, "FAIL");
				}
			} else {
				logger.info("wxpaynotify fail AppSignature or return_code error");
				redirectAttributes.addAttribute(OrderConstans.RESMSG, "请求异常");
				sendXmlMessage(request, response, "FAIL");
			}
		} catch (Exception e) {
			logger.error("order_wxpaynotify_error", e);
			try {
				this.sendXmlMessage(request, response, "FAIL");
			} catch (IOException e1) {

			}
		}
	}
	public void sendXmlMessage(HttpServletRequest request, HttpServletResponse response, String content) throws IOException {
		try {
			System.out.println("--------------------------------------------" + content);
			String contentXml = "<xml><return_code><![CDATA[" + content + "]]></return_code></xml>";
			OutputStream os = response.getOutputStream();
			BufferedWriter resBr = new BufferedWriter(new OutputStreamWriter(os));
			resBr.write(contentXml);
			resBr.flush();
			resBr.close();
		} catch (Exception e) {
			logger.error("sendMessage", e);
		}
	}
}