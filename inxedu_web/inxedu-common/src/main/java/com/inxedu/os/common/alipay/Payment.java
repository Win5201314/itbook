package com.inxedu.os.common.alipay;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

public class Payment {
	public static String CreateUrl(String paygateway, String service,
			String sign_type, String out_trade_no, String input_charset,
			String partner, String key, String show_url, String body,
			String total_fee, String payment_type, String seller_email,
			String subject, String notify_url, String return_url,
			String paymethod, String defaultbank, String extra_common_param) {
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("service", service);
		params.put("partner", partner);
		params.put("subject", subject);
		params.put("body", body);
		params.put("out_trade_no", out_trade_no);
		params.put("total_fee", total_fee);
		params.put("show_url", show_url);
		params.put("payment_type", payment_type);
		params.put("seller_email", seller_email);
		params.put("return_url", return_url);
		params.put("notify_url", notify_url);
		params.put("paymethod", paymethod);
		params.put("extra_common_param", extra_common_param);
		if ((defaultbank != null) && (!"".equals(defaultbank))) {
			params.put("defaultbank", defaultbank);
		}
		params.put("_input_charset", input_charset);
		String prestr = "";
		prestr = String.valueOf(prestr) + key;
		String sign = Md5Encrypt.md5(getContent(params, key));
		String parameter = "";
		parameter = String.valueOf(parameter) + paygateway;
		List<String> keys = new ArrayList<String>(params.keySet());
		for (int i = 0; i < keys.size(); i++) {
			try {
				parameter = String.valueOf(parameter) + keys.get(i) + "=" + URLEncoder.encode(params.get(keys.get(i)), input_charset) + "&";
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		parameter = String.valueOf(parameter) + "sign=" + sign + "&sign_type=" + sign_type;
		return parameter;
	}

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