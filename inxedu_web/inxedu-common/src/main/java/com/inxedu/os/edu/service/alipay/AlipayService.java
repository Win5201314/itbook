package com.inxedu.os.edu.service.alipay;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 支付宝 支付service
 * @author www.inxedu.com
 */
public interface AlipayService {

    /**
     * 跳转到支付宝支付页面
     */
    String gotoalipay(HttpServletRequest request, HttpServletResponse response, Map<String, String> sourceMap);
    /**
     * 获取支付宝 密钥
     */
    Map<String, String> getAlipayInfo();
    /**
     * 返回数据的校验
     */
    boolean getAlipayCheckInfo(HttpServletRequest request)throws Exception;
}