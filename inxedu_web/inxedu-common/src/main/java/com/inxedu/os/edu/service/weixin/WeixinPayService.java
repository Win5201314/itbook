package com.inxedu.os.edu.service.weixin;

import com.inxedu.os.edu.entity.order.Order;
import com.inxedu.os.edu.entity.userprofile.UserProfile;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.util.Map;
import java.util.SortedMap;

/**
 * 微信常规回复设置service
 * @author Administrator
 *
 */
public interface WeixinPayService {
	/**
     * 微信支付请求(扫码支付)
     * @return
     */
    String getWxpayUrl(String requestId, String type);


    /**
     * 微信预支付请求参数
     * @param content
     * @param requestId
     * @param productId
     * @param amount
     * @param userId
     * @param reqIp
     * @param url
     * @param websitemap
     * @param openid 公众号支付 用户关注的openid
     * @param trade_type 交易类型 JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付
     * @return
     */
    SortedMap<String, String> createPackage(String content, String requestId, String productId, BigDecimal amount, int userId, String reqIp, String url, Map<String, String> websitemap, String openid, String trade_type);

    /**
     * 获取微信 密钥
     * @return
     */
    Map<String,String> getWxpayInfo();

    /**
     * 微信公众号支付请求(jspi支付) 发送预支付订单返回参数
     * @param model
     * @param trxorder
     * @param userProfile
     * @return
     * @throws Exception
     */
    Model JSAPI(Model model, Order trxorder, UserProfile userProfile) throws Exception;
    /**
     * 根据订单type 返回 支付提示内容
     * @return
     */
    String getPayContent(String orderType);
}


