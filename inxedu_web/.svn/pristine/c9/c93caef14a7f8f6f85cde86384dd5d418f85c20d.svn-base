package com.inxedu.os.edu.service.impl.alipay;


import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonParser;
import com.inxedu.os.common.alipay.Payment;
import com.inxedu.os.common.constants.CommonConstants;
import com.inxedu.os.common.util.FastJsonUtil;
import com.inxedu.os.common.util.SingletonLoginUtils;
import com.inxedu.os.common.util.StringUtils;
import com.inxedu.os.edu.constants.enums.WebSiteProfileType;
import com.inxedu.os.edu.constants.order.OrderConstans;
import com.inxedu.os.edu.service.website.WebsiteProfileService;
import com.inxedu.os.edu.service.alipay.AlipayService;
import com.inxedu.os.edu.util.alipay.CheckURL;
import com.inxedu.os.edu.util.alipay.SignatureHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 支付宝 支付service 实现
 * @author www.inxedu.com
 */
@Service("alipayService")
public class AlipayServiceImpl implements AlipayService {

	private static final Logger logger = LoggerFactory.getLogger(AlipayServiceImpl.class);



	protected static final String ERROR = "/common/error";
	@Autowired
	private WebsiteProfileService websiteProfileService;
	@Override
	public String gotoalipay(HttpServletRequest request, HttpServletResponse response, Map<String, String> sourceMap) {
		try {
			logger.info("+++gotoalipay sourceMap:" + sourceMap);
			Map<String, String> websitemap = getAlipayInfo();// 获得支付宝配置
			String requestId = sourceMap.get("requestId");// 订单支付订单号
			String paygateway = "https://mapi.alipay.com/gateway.do?"; // 支付接口（不可以修改）
			String service = "create_direct_pay_by_user";// 快速付款交易服务（不可以修改）
			String sign_type = "MD5";// 文件加密机制（不可以修改）
			//String out_trade_no = guidGeneratorService.gainCode("PAY", true);// 商户网站订单（也就是外部订单号，是通过客户网站传给支付宝，不可以重复）
			String out_trade_no = "PAY"+System.currentTimeMillis();// guidGeneratorService.gainCode("PAY", true);// 商户网站订单（也就是外部订单号，是通过客户网站传给支付宝，不可以重复） 用时间戳代替了
			String input_charset = OrderConstans.alipay_input_charset; // （不可以修改）
			// partner和key提取方法：登陆签约支付宝账户--->点击“商家服务”就可以看到
			String partner = websitemap.get("alipaypartnerID"); // 支付宝合作伙伴id
			// (账户内提取)
			String key = websitemap.get("alipaykey"); // 支付宝安全校验码(账户内提取)
			String body = SingletonLoginUtils.getLoginUserId(request) + "-" + requestId + "-" + out_trade_no;
			// 商品描述，推荐格式：商品名称（订单编号：订单编号）
			String total_fee = sourceMap.get("bankAmount");// 订单总价,差价尚须银行支付的金额
			// total_fee = String.valueOf(total_fee); // 订单总价
			String payment_type = "1";// 支付宝类型.1代表商品购买（目前填写1即可，不可以修改）
			String seller_email = websitemap.get("sellerEmail"); // 卖家支付宝帐户
			// subject 商品名称
			String subject = OrderConstans.companyName + requestId;
			// 扩展信息,存用户id和requestId.重要，必须存
			String extra_common_param = SingletonLoginUtils.getLoginUserId(request) + "," + requestId;

			String show_url = "http://" + request.getContextPath() + "/"; // 商品地址，
			// 根据集成的网站而定
			// 回调的地址
			String path = CommonConstants.contextPath;
			String notify_url = path + "/zfbpay/order/alipaynotify/1"; // 通知接收URL(本地测试时，服务器返回无法测试)
			String return_url = path + "/zfbpay/order/alipaynotify/2"; // 支付完成后跳转返回的网址URL
			// 注意以上两个地址 要用 http://格式的完整路径
			/* 以下两个参数paymethod和defaultbank可以选择不使用，如果不使用需要注销，并在Payment类的方法中也要注销 */
			String defaultbank = request.getParameter("defaultbank");
			String paymethod = "directPay";
			if (StringUtils.isNotEmpty(defaultbank)) {
				paymethod = "bankPay";
			} else {
				defaultbank = null;
				paymethod = "directPay";
			}

			String submiturl = Payment.CreateUrl(paygateway, service, sign_type, out_trade_no, input_charset, partner, key, show_url, body, total_fee, payment_type, seller_email, subject, notify_url, return_url, paymethod, defaultbank, extra_common_param);
			logger.info("+++ submiturl:" + submiturl);
			return "redirect:" + submiturl;
		} catch (Exception e) {
			logger.error(request.getContextPath(), e);
			StackTraceElement[] messages = e.getStackTrace();
			if (messages != null && messages.length > 0) {
				StringBuffer buffer = new StringBuffer();
				buffer.append(e.toString()).append("<br/>");
				for (int i = 0; i < messages.length; i++) {
					buffer.append(messages[i].toString()).append("<br/>");
				}
				request.setAttribute("myexception", buffer.toString());
			}
			return ERROR;
		}
	}

	/**
	 * 获取支付宝 密钥
	 */
	public Map<String, String> getAlipayInfo() {
		// 获得支付配置
		Map<String, Object> map = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.alipay.toString());
		JsonParser jsonParser = new JsonParser();
		// 获得详细info
		JSONObject jsonObject = FastJsonUtil.obj2JsonObject(map.get(WebSiteProfileType.alipay.toString()));

		if (!jsonObject.isEmpty()) {
			Map<String, String> websitemap = new HashMap<String, String>();
			websitemap.put("alipaykey", jsonObject.get("alipaykey").toString());// 支付宝key
			websitemap.put("alipaypartnerID", jsonObject.get("alipaypartnerID").toString());// 支付宝合作伙伴id
			websitemap.put("sellerEmail", jsonObject.get("sellerEmail").toString());// 商家
			return websitemap;
		}
		return null;
	}
	/**
	 * 支付宝返回数据的校验
	 */
	public boolean getAlipayCheckInfo(HttpServletRequest request) throws Exception{
		Map<String, String> websitemap = getAlipayInfo();// 获得支付宝配置
		String partner = websitemap.get("alipaypartnerID"); // 支付宝合作伙伴id
		String privateKey = websitemap.get("alipaykey"); // 支付宝安全校key
		String alipayNotifyURL = "http://notify.alipay.com/trade/notify_query.do?" + "partner=" + partner + "&notify_id=" + request.getParameter("notify_id");
		String responseTxt = CheckURL.check(alipayNotifyURL);
		//效验失败
		if(!responseTxt.equals("true")){
			logger.info("支付宝返回数据效验失败  ---responseTxt---"+responseTxt);
			return false;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		// 获得POST 过来参数设置到新的params中
		Map<String, String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			//中文商品名称 转码
			if ("subject".equals(name.trim())){
				valueStr= URLDecoder.decode(valueStr,"gb2312");
			}else {
			 // 乱码解决，这段代码在出现乱码时使用,但是不一定能解决所有的问题，所以建议写过滤器实现编码控制。
			 // 如果mysign和sign不相等也可以使用这段代码转化
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8"); // 乱码解决
			}
			params.put(name, valueStr);
		}
		// 验证加密签名
		String mysign = SignatureHelper.sign(params, privateKey);
		if(!mysign.equals(request.getParameter("sign"))){
			logger.info("验证加密签名  ---mysign---"+request.getParameter("sign"));
			logger.info("验证加密签名  ---params---"+params);
			return false;
		}
		return true;
	}
}