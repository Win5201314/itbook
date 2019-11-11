package com.inxedu.os.edu.service.impl.yibaopay;


import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonParser;
import com.inxedu.os.common.constants.CommonConstants;
import com.inxedu.os.common.util.FastJsonUtil;
import com.inxedu.os.common.util.SingletonLoginUtils;
import com.inxedu.os.edu.constants.enums.WebSiteProfileType;
import com.inxedu.os.edu.service.website.WebsiteProfileService;
import com.inxedu.os.edu.service.yibaopay.YibaoPayService;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 易宝 支付service 实现
 * @author www.inxedu.com
 */
@Service("yibaoPayService")
public class YibaoPayServiceImpl implements YibaoPayService {

    private static final Logger logger = LoggerFactory.getLogger(YibaoPayServiceImpl.class);


    protected static final String ERROR = "/common/error";

    // 易宝支付信息
    private String yeepay = "/inxedu/web/order/yibaopay";

    @Autowired
    private WebsiteProfileService websiteProfileService;

    @Getter
    @Setter
    private static String encodingCharset = "UTF-8";
    /**
     * 易宝支付信息
     */
    @Override
    public String gotoyp(HttpServletRequest request, HttpServletResponse response, Map<String, String> sourceMap) {
        try {
            logger.info("+++ gotoyp sourceMap" + sourceMap);
            request.setCharacterEncoding("GBK");
            Map<String, String> websitemap = getYeePayInfo();// 获得支付宝配置
            String keyValue = websitemap.get("keyValue"); // 易宝valueKey
            String p1_MerId = websitemap.get("p1_MerId"); // 易宝P1MerId
            String requestId = sourceMap.get("requestId");// 订单支付订单号
            String bankId = request.getParameter("bankId");
            String payUrl = "https://www.yeepay.com/app-merchant-proxy/node";
            String p8_Url = CommonConstants.contextPath + "/order/ybReturn";
            String pa_MP = SingletonLoginUtils.getLoginUserId(request)+","+requestId;

            // 易宝参数定义
           /* YbParamInfo ybParamInfo = new YbParamInfo();
            ybParamInfo.setP0_Cmd("Buy");// 固定类型 切勿改动
            ybParamInfo.setP1_MerId(p1_MerId);
            ybParamInfo.setPayUrl("https://www.yeepay.com/app-merchant-proxy/node");// 支付地址了
            ybParamInfo.setP2_Order(requestId);
            ybParamInfo.setP3_Amt(sourceMap.get("bankAmount"));
            ybParamInfo.setP3_Amt(sourceMap.get("amount"));
            ybParamInfo.setP4_Cur("CNY");// 固定类型 切勿改动
            ybParamInfo.setP8_Url(CommonConstants.contextPath + "/order/ybReturn"); // 回调地址
            ybParamInfo.setPa_MP(SingletonLoginUtils.getLoginUserId(request) + "," + requestId);
            ybParamInfo.setPr_NeedResponse("1");
            ybParamInfo.setP9_SAF("0");
            if (bankId != null) {
                ybParamInfo.setPd_FrpId(bankId);
            }*/

            // 密钥生成
            String hmac = getReqMd5HmacForOnlinePayment("Buy", p1_MerId, requestId, sourceMap.get("amount"), "CNY", "", "", "", p8_Url,
                    "0", pa_MP, bankId, "", "", "1", "",
                    "", "", "", "", "", keyValue);
            /*ybParamInfo.setHmac(hmac);
            System.out.println(hmac);
            request.setAttribute("ybParamInfo", ybParamInfo);*/
            return "redirect:"+payUrl
                    +"?p0_Cmd=Buy"
                    +"&p1_MerId="+p1_MerId
                    +"&p2_Order="+requestId
                    +"&p3_Amt="+sourceMap.get("amount")
                    +"&p4_Cur=CNY"
                    +"&p5_Pid="
                    +"&p6_Pcat="
                    +"&p7_Pdesc="
                    +"&p8_Url="+p8_Url
                    +"&p9_SAF=0"
                    +"&pa_MP="+pa_MP
                    +"&pd_FrpId="+bankId
                    +"&pm_Period="
                    +"&pn_Unit="
                    +"&pr_NeedResponse=1"
                    +"&pt_UserName="
                    +"&pt_PostalCode="
                    +"&pt_Address="
                    +"&pt_TeleNo="
                    +"&pt_Mobile="
                    +"&pt_Email="
                    +"&hmac="+hmac;
            //return yeepay;
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
     * 获取易宝密钥
     */
    public Map<String, String> getYeePayInfo() {
        System.out.println(WebSiteProfileType.yee.toString());
        Map<String, Object> map = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.yee.toString());
        JsonParser jsonParser = new JsonParser();
        // 获得详细info
        JSONObject jsonObject = FastJsonUtil.obj2JsonObject(map.get(WebSiteProfileType.yee.toString()));
        if (!jsonObject.isEmpty()) {
            Map<String, String> websitemap = new HashMap<String, String>();
            websitemap.put("p1_MerId", jsonObject.get("p1_MerId").toString());// 支付宝key
            websitemap.put("keyValue", jsonObject.get("keyValue").toString());// 支付宝合作伙伴id
            return websitemap;
        }
        return null;
    }

    /**
     * 生成hmac方法 业务类型
     *
     * @param p0_Cmd
     *            商户编号
     * @param p1_MerId
     *            商户订单号
     * @param p2_Order
     *            支付金额
     * @param p3_Amt
     *            交易币种
     * @param p4_Cur
     *            商品名称
     * @param p5_Pid
     *            商品种类
     * @param p6_Pcat
     *            商品描述
     * @param p7_Pdesc
     *            商户接收支付成功数据的地址
     * @param p8_Url
     *            送货地址
     * @param p9_SAF
     *            商户扩展信息
     * @param pa_MP
     *            银行编码
     * @param pd_FrpId
     *            订单有效期
     * @param pm_Period
     *            订单有效期单位
     * @param pn_Unit
     *            应答机制
     * @param pr_NeedResponse
     *            考生/学员姓名
     * @param pt_UserName
     *            身份证号
     * @param pt_PostalCode
     *            地区
     * @param pt_Address
     *            报考序号
     * @param pt_TeleNo
     *            手机号
     * @param pt_Mobile
     *            邮件地址
     * @param pt_Email
     *            商户密钥
     * @param keyValue
     * @return
     */
    public static String getReqMd5HmacForOnlinePayment(String p0_Cmd, String p1_MerId, String p2_Order, String p3_Amt, String p4_Cur, String p5_Pid, String p6_Pcat, String p7_Pdesc, String p8_Url, String p9_SAF, String pa_MP, String pd_FrpId, String pm_Period, String pn_Unit, String pr_NeedResponse, String pt_UserName, String pt_PostalCode, String pt_Address, String pt_TeleNo, String pt_Mobile, String pt_Email, String keyValue) {
        StringBuffer sValue = new StringBuffer();
        // 业务类型
        sValue.append(p0_Cmd);
        // 商户编号
        if (p1_MerId == null) {
            p1_MerId = "";
        }
        sValue.append(p1_MerId);
        // 商户订单号
        if (p2_Order == null) {
            p2_Order = "";
        }
        sValue.append(p2_Order);
        // 支付金额
        sValue.append(p3_Amt);
        // 交易币种
        sValue.append(p4_Cur);
        if (p5_Pid == null) {
            p5_Pid = "";
        }
        // 商品名称
        sValue.append(p5_Pid);
        // 商品种类
        if (p6_Pcat == null) {
            p6_Pcat = "";
        }
        sValue.append(p6_Pcat);
        // 商品描述
        if (p7_Pdesc == null) {
            p7_Pdesc = "";
        }
        sValue.append(p7_Pdesc);
        // 商户接收支付成功数据的地址
        if (p8_Url == null) {
            p8_Url = "";
        }
        sValue.append(p8_Url);
        // 送货地址
        if (p9_SAF == null) {
            p9_SAF = "";
        }
        sValue.append(p9_SAF);
        // 商户扩展信息
        if (pa_MP == null) {
            pa_MP = "";
        }
        sValue.append(pa_MP);
        // 银行编码
        if (pd_FrpId == null) {
            pd_FrpId = "";
        }
        sValue.append(pd_FrpId);
        // 有效期
        if (pm_Period == null) {
            pm_Period = "";
        }
        sValue.append(pm_Period);
        // 有效期单位
        if (pn_Unit == null) {
            pn_Unit = "";
        }
        sValue.append(pn_Unit);
        // 应答机制
        sValue.append(pr_NeedResponse);
        // 考生/学员姓名
        if (pt_UserName == null) {
            pt_UserName = "";
        }
        sValue.append(pt_UserName);
        // 身份证号
        if (pt_PostalCode == null) {
            pt_PostalCode = "";
        }
        sValue.append(pt_PostalCode);
        // 地区
        if (pt_Address == null) {
            pt_Address = "";
        }
        sValue.append(pt_Address);
        // 报考序号
        if (pt_TeleNo == null) {
            pt_TeleNo = "";
        }
        sValue.append(pt_TeleNo);
        // 手机号
        if (pt_Mobile == null) {
            pt_Mobile = "";
        }
        sValue.append(pt_Mobile);
        // 邮件地址
        if (pt_Email == null) {
            pt_Email = "";
        }
        sValue.append(pt_Email);

        String sNewString = null;

		/* System.out.println(sValue.toString()); */
        sNewString = hmacSign(sValue.toString(), keyValue);
        return (sNewString);
    }


    public static String hmacSign(String aValue, String aKey) {
        byte k_ipad[] = new byte[64];
        byte k_opad[] = new byte[64];
        byte keyb[];
        byte value[];
        try {
            keyb = aKey.getBytes(encodingCharset);
            value = aValue.getBytes(encodingCharset);
        } catch (UnsupportedEncodingException e) {
            keyb = aKey.getBytes();
            value = aValue.getBytes();
        }

        Arrays.fill(k_ipad, keyb.length, 64, (byte) 54);
        Arrays.fill(k_opad, keyb.length, 64, (byte) 92);
        for (int i = 0; i < keyb.length; i++) {
            k_ipad[i] = (byte) (keyb[i] ^ 0x36);
            k_opad[i] = (byte) (keyb[i] ^ 0x5c);
        }

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {

            return null;
        }
        md.update(k_ipad);
        md.update(value);
        byte dg[] = md.digest();
        md.reset();
        md.update(k_opad);
        md.update(dg, 0, 16);
        dg = md.digest();
        return toHex(dg);
    }

    public static String toHex(byte input[]) {
        if (input == null)
            return null;
        StringBuffer output = new StringBuffer(input.length * 2);
        for (int i = 0; i < input.length; i++) {
            int current = input[i] & 0xff;
            if (current < 16)
                output.append("0");
            output.append(Integer.toString(current, 16));
        }

        return output.toString();
    }


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
    public boolean verifyCallback(String hmac, String p1_MerId, String r0_Cmd, String r1_Code, String r2_TrxId, String r3_Amt, String r4_Cur, String r5_Pid, String r6_Order, String r7_Uid, String r8_MP, String r9_BType, String keyValue) {
        StringBuffer sValue = new StringBuffer();
        // 商户编号
        sValue.append(p1_MerId);
        // 业务类型
        sValue.append(r0_Cmd);
        // 支付结果
        sValue.append(r1_Code);
        // 易宝支付交易流水号
        sValue.append(r2_TrxId);
        // 支付金额
        sValue.append(r3_Amt);
        // 交易币种
        sValue.append(r4_Cur);
        // 商品名称
        sValue.append(r5_Pid);
        // 商户订单号
        sValue.append(r6_Order);
        // 易宝支付会员ID
        sValue.append(r7_Uid);
        // 商户扩展信息
        sValue.append(r8_MP);
        // 交易结果返回类型
        sValue.append(r9_BType);
        String sNewString = null;
        sNewString = hmacSign(sValue.toString(), keyValue);

        return hmac.equals(sNewString);
    }
}
