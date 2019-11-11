
package com.inxedu.os.edu.service.yibaopay;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 易宝 支付service
 * @author www.inxedu.com
 */
public interface YibaoPayService {

    /**
     * 易宝支付信息
     *
     * @param request
     * @param response
     * @param sourceMap
     * @return
     */
    String gotoyp(HttpServletRequest request, HttpServletResponse response, Map<String, String> sourceMap);

    /**
     * 返回校验hmac方法
     *
     * @param hmac
     *            商户编号
     * @param p1_MerId
     *            业务类型
     * @param r0_Cmd
     *            支付结果
     * @param r1_Code
     *            易宝支付交易流水号
     * @param r2_TrxId
     *            支付金额
     * @param r3_Amt
     *            交易币种
     * @param r4_Cur
     *            商品名称
     * @param r5_Pid
     *            商户订单号
     * @param r6_Order
     *            易宝支付会员ID
     * @param r7_Uid
     *            商户扩展信息
     * @param r8_MP
     *            交易结果返回类型
     * @param r9_BType
     *            交易结果返回类型
     * @param keyValue
     * @return
     */
    boolean verifyCallback(String hmac, String p1_MerId, String r0_Cmd, String r1_Code, String r2_TrxId, String r3_Amt, String r4_Cur, String r5_Pid, String r6_Order, String r7_Uid, String r8_MP, String r9_BType, String keyValue);

    /**
     * 获取易宝密钥
     *
     * @return
     */
    Map<String, String> getYeePayInfo();

}