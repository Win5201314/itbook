package com.inxedu.os.edu.service.impl.weixin;


import com.inxedu.os.common.constants.CommonConstants;
import com.inxedu.os.common.util.DateUtils;
import com.inxedu.os.common.util.HttpUtil;
import com.inxedu.os.edu.constants.enums.WebSiteProfileType;
import com.inxedu.os.edu.constants.enums.order.OrderType;
import com.inxedu.os.edu.entity.order.Order;
import com.inxedu.os.edu.entity.userprofile.UserProfile;
import com.inxedu.os.edu.service.website.WebsiteProfileService;
import com.inxedu.os.edu.service.weixin.WeixinPayService;
import com.inxedu.os.edu.util.weixin.MessageUtil;
import com.inxedu.os.edu.util.weixin.PackageUtil;
import com.inxedu.os.edu.util.weixin.XMLParse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 微信支付 service
 * @author Administrator
 *
 */
@Service("weixinPayService")
public class WeixinPayServiceImpl implements WeixinPayService {
	private static final Logger logger = LoggerFactory.getLogger(WeixinPayServiceImpl.class);

	@Autowired
	private WebsiteProfileService websiteProfileService;

	/**
     * 微信支付请求
     * @return
     */
	public String getWxpayUrl(String requestId,String type) {
        Map<String,String> websitemap=getWxpayInfo();//获得微信支付配置
    	String timeStanmp= PackageUtil.getTimeStamp();//timestamp 时间点
    	String nonceStr= PackageUtil.getNonceStr();//noncestr 随机字符串
    	SortedMap<String, String> signParams = new TreeMap<String, String>();
    	signParams.put("appid",websitemap.get("appid"));//qppid
    	signParams.put("mch_id",websitemap.get("mchid"));//商户号id
    	signParams.put("time_stamp",timeStanmp);//时间戳
    	signParams.put("nonce_str",nonceStr);//随机字符串
    	signParams.put("product_id",requestId+"#"+type);// 对外支付订单号
	
    	String sign=PackageUtil.createSign(signParams,websitemap.get("payKey"));
		String params="sign="+sign+"&appid="+websitemap.get("appid")+"&mch_id="+websitemap.get("mchid")+"&product_id="+requestId+"#"+type+"&time_stamp="+timeStanmp+"&nonce_str="+nonceStr;
		return "weixin://wxpay/bizpayurl?"+params;
    }

	/**
	 * 微信预支付请求参数
	 * @param openid 公众号支付 用户关注的openid
     * @param trade_type 交易类型 JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付
     * @return
     */
	public SortedMap<String, String> createPackage(String content, String requestId, String productId, BigDecimal amount, int userId, String reqIp, String url, Map<String, String> websitemap,String openid,String trade_type) {

		String notify_url = CommonConstants.contextPath + url;// 微信支付通知url
		String noncestr = PackageUtil.getNonceStr();// 随机字符串
		// 设置package订单参数
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		// 必须字段
		packageParams.put("appid", websitemap.get("appid")); // appid
		packageParams.put("mch_id", websitemap.get("mchid")); // 商户号id
		packageParams.put("body", content); // 商品描述
		packageParams.put("notify_url", notify_url); // 通知地址
		packageParams.put("openid", openid); // 公众号支付 用户关注的openid
		packageParams.put("trade_type", trade_type); // 交易类型 JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付
		packageParams.put("out_trade_no", getOrderNum(userId));// 商户网站订单（也就是外部订单号，是通过客户网站传给支付宝，不可以重复）
		String total_fee = amount.multiply(new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP).toString();
		packageParams.put("total_fee", total_fee); // 订单总金额，单位为分，不能带小数点
		packageParams.put("spbill_create_ip", reqIp); // 订单生成的机器IP，指用户浏览器端IP
		packageParams.put("product_id", productId);
		packageParams.put("nonce_str", noncestr);
		// 非必需字段
		// 附加数据 订单id 用户id
		packageParams.put("attach", requestId + "," + userId);
		Date date = new Date();
		SimpleDateFormat s = new SimpleDateFormat("yyyyMMddHHmmss");
		packageParams.put("time_start", s.format(date)); // 订单生成时间，格式为yyyyMMddHHmmss

		// 订单3天后失效
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DATE, c.get(Calendar.DATE) + 3);
		packageParams.put("time_expire", s.format(c.getTime())); // 订单失效时间，格式为yyyyMMddHHmmss

		return packageParams;
	}

    /**
     * 获取微信 密钥
     * @return
     */
    public Map<String,String> getWxpayInfo(){
    	//获得微信支付配置
    	Map<String,Object> weixinMap=websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.weixin.toString());
    	//获得详细info
    	@SuppressWarnings("unchecked")
		Map<String,Object> map=(Map<String, Object>) weixinMap.get(WebSiteProfileType.weixin.toString());
    	if(map!=null){
    		Map<String,String> websitemap = new HashMap<String,String>();
    		websitemap.put("appid", map.get("wxAppID").toString());//appId
    		websitemap.put("payKey", map.get("wxPayKey").toString());//支付密钥
    		websitemap.put("mchid",map.get("wxMchId").toString() );//商户id
			websitemap.put("appSecret", map.get("wxAppSecret").toString());// 第三方用户唯一凭证密钥，即appsecret
    		return websitemap;
    	}
    	return null;
    }

	/**
	 * 微信公众号支付请求(jspi支付) 发送预支付订单返回参数
	 */
	public Model JSAPI(Model model,Order trxorder, UserProfile userProfile) throws Exception {
		String productId = trxorder.getOrderNo()+"#"+trxorder.getOrderType();
		Map<String, String> websitemap = this.getWxpayInfo();// 获得微信支付配置
		String payKey = websitemap.get("payKey");
		String requestId = productId.split("#")[0];
		// 请求参数包
		SortedMap<String, String> packageParams = this.createPackage(getPayContent(trxorder.getOrderType()), requestId, productId, trxorder.getSumMoney(), trxorder.getUserId(), trxorder.getReqIp(), "/order/course/wxpaynotify", websitemap,userProfile.getValueTwo(),"JSAPI");

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
				//微信内置 WeixinJSBridge 支付
				String timeMillis=System.currentTimeMillis()/1000+"";
				String nonceString=PackageUtil.getNonceStr();
				// 设置package订单参数
				SortedMap<String, String> paypackageParams = new TreeMap<String, String>();
				paypackageParams.put("appId", websitemap.get("appid")); // appid
				paypackageParams.put("timeStamp", timeMillis); //
				paypackageParams.put("nonceStr", nonceString); //
				paypackageParams.put("package", "prepay_id="+prepay_id); //
				paypackageParams.put("signType", "MD5"); //
				// 签名
				String paysign = PackageUtil.createSign(paypackageParams, payKey);
				model.addAttribute("appId",websitemap.get("appid"));
				model.addAttribute("timeStamp",timeMillis);
				model.addAttribute("nonceStr", nonceString);
				model.addAttribute("info_package","prepay_id="+prepay_id);
				model.addAttribute("paySign",paysign);
			}
		}
		return model;
	}

	/**
	 * 生成订单号 当前用户id+毫秒数
	 */
	public String getOrderNum(int userId) {
		return userId + DateUtils.toString(new Date(), "yyyyMMddHHmmssSSS");
	}

	@Override
	public String getPayContent(String orderType) {
		String content="购买商品";
		if (orderType.equals(OrderType.COURSE+"")) {
			content="购买课程";
		}
		else if (orderType.equals(OrderType.MEMBER+"")) {
			content="购买会员";
		}
		else if (orderType.equals(OrderType.ACCOUNT+"")) {
			content="账户充值";
		}
		return content;
	}

}


