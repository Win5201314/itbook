package com.inxedu.os.edu.util.baijiayun;

import com.inxedu.os.common.util.MD5;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.SingletonLoginUtils;
import com.inxedu.os.common.util.StringUtils;
import com.inxedu.os.edu.entity.livebaijiayun.LiveBaijiayun;
import com.inxedu.os.edu.entity.system.SysUser;
import com.inxedu.os.edu.entity.user.User;
import com.inxedu.os.edu.entity.website.WebsiteProfile;
import com.inxedu.os.edu.service.website.WebsiteProfileService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/3/7.
 */
public class BajiayunUtil {

    /*创建房间签名*/
    public static  String createSign(SortedMap<String,String> message,WebsiteProfileService websiteProfileService){
        Map<String,Object> baijiayun =  (Map<String,Object>)websiteProfileService.getWebsiteProfileByType("baijiayun").get("baijiayun");
        String origin_str = "";
        if (ObjectUtils.isNull(baijiayun.get("PartnerId"))||ObjectUtils.isNull(baijiayun.get("PartnerKey"))){
            message.put("partner_id","");
            origin_str = createBaijiayunSign(message,"");
        }else {
            message.put("partner_id",baijiayun.get("PartnerId").toString());
            origin_str = createBaijiayunSign(message,baijiayun.get("PartnerKey").toString());
        }
        return origin_str;
    }
    /**
     * 创建百家云签名
     */
    public static String createBaijiayunSign(SortedMap<String, String> packageParams,String key) {
        StringBuffer sb = new StringBuffer();
        Set<Map.Entry<String,String>> es = packageParams.entrySet();
        Iterator<Map.Entry<String,String>> it = es.iterator();
        while (it.hasNext()) {
            Map.Entry<String,String> entry = it.next();
            String k = entry.getKey();
            String v = entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k)&& !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        if (StringUtils.isNotEmpty(key)){
            sb.append("partner_key="+key);
        }
        String sign = MD5.getMD5(sb.toString());
        return sign;

    }
    /*获取百家云房间url*/
    public static String roomUrl(HttpServletRequest request,String roomId,String type,String userType,WebsiteProfileService websiteProfileService) throws Exception{
        LiveBaijiayun room1 = new LiveBaijiayun();


        SortedMap<String,String> map = new TreeMap<>();
        DecimalFormat df = new DecimalFormat("##0");
        map.put("room_id",roomId);
        if ("student".equals(userType)){
                 /*获取学员信息*/
            User user = SingletonLoginUtils.getLoginUser(request);
            map.put("user_number",user.getUserId()+"");
            map.put("user_name",user.getUserName());
            map.put("user_role",0+"");
        }else {
                /*获取老师信息*/
            SysUser user =  SingletonLoginUtils.getLoginSysUser(request);
            map.put("user_number",user.getUserId()+"");
            map.put("user_name",user.getUserName());
            map.put("user_role",1+"");
        }

        String sign =  createSign(map,websiteProfileService);
            /*参数*/
        String msg="";
            /*如果是WEB为网页端*/
        if ("WEB".equals(type)){
            msg = "http://api.baijiacloud.com/web/room/enter?"+BajiayunUtil.maptoString(map,sign);
            /*如果是APP为客户端*/
        }else if ("APP".equals(type)){
            msg = "http://api.baijiacloud.com/web/room/enter?"+BajiayunUtil.maptoString(map,sign);
                /*客户端请求需要url转码 和在请求前加baijiacloud://urlpath=*/
            msg = URLEncoder.encode(msg,"utf8");
            msg = "baijiacloud://urlpath="+msg+"&token=token&ts=ts";
        }
        return msg;
    }
    /**
     * map返回string
     */
    public static String maptoString(Map<String, String> packageParams,String key) {
        StringBuffer sb = new StringBuffer();
        Set<Map.Entry<String,String>> es = packageParams.entrySet();
        Iterator<Map.Entry<String,String>> it = es.iterator();
        while (it.hasNext()) {
            Map.Entry<String,String> entry = it.next();
            String k = entry.getKey();
            String v = entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k)&& !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("sign="+key);
        return sb.toString();

    }
}
