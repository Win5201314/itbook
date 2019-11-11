package com.inxedu.os.common.alipay;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;


/**
 * 个人支付宝支付(信息控制)
 * 一些不可空的信息
 * 1.接口名称
 * 2.合作者id
 * 3.参数编码字符集
 * 4.签名方式
 * 5.key
 * 6.商品唯一订单号
 * 7.商品名称
 * 8.收款类型
 * 9.物流类型
 * 10.物流费用
 * 11.物流支付类型
 * 12.卖家支付宝账号 
 * 13.商品单价
 * 14.商品数量
 * */
public class PaymentPersonal {
	private static String paygateway="http://mapi.alipay.com/gateway.do?";
	public static String createUrl(String service,String partner,String input_charset ,String sign_type,String key,String out_trade_no,String subject,String payment_type,String logistics_fee,String logistics_payment,String seller_email,String price,String quantity,String notify_url,String return_url,String logistics_type){
		Map<String,String> params= new HashMap<String,String>();
		params.put("_input_charset", input_charset);
		params.put("logistics_fee", logistics_fee);
		params.put("logistics_payment", logistics_payment);
		params.put("notify_url", notify_url);
		params.put("logistics_type", logistics_type);
		params.put("out_trade_no", out_trade_no);
		params.put("partner", partner);
		params.put("payment_type", payment_type);
		params.put("price", price);
		params.put("quantity", quantity);
		params.put("return_url", return_url);
		params.put("seller_email", seller_email);
		params.put("service", service);
		params.put("subject", subject);
		//生成签名
		String sign = Md5Encrypt.md5(getContent(params,key));
		String sendMessage = "";
		sendMessage = sendMessage + paygateway;
		List<String> keys = new ArrayList<String>(params.keySet());
	    for (int i = 0; i < keys.size(); i++) {
	        try
	        {
	        	sendMessage = sendMessage + keys.get(i) + "=" + URLEncoder.encode(params.get(keys.get(i)), input_charset) + "&";
	        }
	        catch (UnsupportedEncodingException e)
	        {
	          e.printStackTrace();
	        }
	      }
	    sendMessage = sendMessage+ "sign=" + sign + "&sign_type=" + sign_type;
	    return sendMessage;
	}
	
	 /**
	 * 支付宝加密
	 * @param params
	 * @return
	 */
	private static String getContent(Map<String,String> params, String privateKey) {
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		String prestr = "";
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);
			if (i == keys.size() - 1){
				prestr = String.valueOf(prestr) + key + "=" + value;
			}else {
				prestr = String.valueOf(prestr) + key + "=" + value + "&";
			}
		}
		return String.valueOf(prestr) + privateKey;
	}
}
