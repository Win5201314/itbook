package com.inxedu.os.edu.controller.livebaijiayun;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.*;
import com.inxedu.os.edu.entity.kpoint.CourseKpoint;
import com.inxedu.os.edu.entity.livegensee.LiveGensee;
import com.inxedu.os.edu.entity.user.User;
import com.inxedu.os.edu.service.course.CourseKpointService;
import com.inxedu.os.edu.service.website.WebsiteProfileService;
import com.inxedu.os.edu.util.baijiayun.BajiayunUtil;
import com.inxedu.os.edu.entity.livebaijiayun.LiveBaijiayun;
import com.inxedu.os.edu.entity.system.SysUser;
import com.inxedu.os.edu.service.livegensee.LiveGenseeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @author www.inxedu.com
 * @description 展视互动直播 后台LiveGenseeController
 */
@Controller
@RequestMapping("/admin")
public class AdminLiveBaijiayunController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(AdminLiveBaijiayunController.class);

    @Autowired
    private CourseKpointService courseKpointService;
    @Autowired
    private WebsiteProfileService websiteProfileService;
    // 绑定属性 封装参数
    @InitBinder("liveRoom")
    public void initLiveRoom(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("liveRoom.");
    }


    /**
     * 添加vid_live_room
     */
    @RequestMapping("/liveRoom/add")
    @ResponseBody
    public Object addLiveRoom(HttpServletRequest request, @ModelAttribute("liveRoom") LiveBaijiayun liveRoom) {
        Map<String, Object> json = new HashMap<>();
        try {
            /*整理创建房间的信息*/
            SortedMap<String,String> message = new TreeMap<>();
            message.put("end_time",liveRoom.getEndTime().getTime()/1000+"");
            message.put("max_users",liveRoom.getMaxUsers()+"");
            message.put("start_time",liveRoom.getStartTime().getTime()/1000+"");
            liveRoom.setCreateTime(new Date());
            message.put("timestamp",liveRoom.getCreateTime().getTime()/1000+"");
            message.put("title",liveRoom.getRoomTitle());
            message.put("type",liveRoom.getType()+"");
            message.put("sign", BajiayunUtil.createSign(message,websiteProfileService));
            /*向白家云发送请求创建房间 roomMsg为返回信息*/
            String roomMsg = HttpClientUtil.doPost("http://api.baijiacloud.com/openapi/room/create",message,"utf-8");

            Map<String,Object> room = FastJsonUtil.json2Map(roomMsg);

            int code = new BigDecimal(room.get("code").toString()).intValue();
            /*如果code是0说明创建成功*/
            if (code==0){
                /*本地添加房间信息*/
                Map<String,Object> roomData = FastJsonUtil.json2Map(room.get("data").toString());
                /*百家云返回的房间id*/
                liveRoom.setRoomId(new BigDecimal(roomData.get("room_id").toString())+"");
            }else {
                json = this.setJson(false,room.get("msg").toString(),null);
                return json;
            }
            json = this.setJson(true,liveRoom.getRoomId(),null);
            System.out.println("返回参数"+roomMsg);
        } catch (Exception e) {
            logger.error("LiveRoomController.addLiveRoom()--error", e);
            json = this.setJson(false, "系統错误", null);
        }
        return json;
    }
    /**
     * 进入live_room
     */
    @RequestMapping("/liveRoom/toLiveRoom")
    @ResponseBody
    public Object toLiveRoom(HttpServletRequest request,ModelAndView model) {
        Map<String, Object> json = new HashMap<String, Object>();
        try {
            String roomId = request.getParameter("id");
            String type = request.getParameter("type");
            String userType = request.getParameter("role");
            /*获取房间链接*/
            String msg = BajiayunUtil.roomUrl(request,roomId.split("\\|")[0],type,userType,websiteProfileService);
            json = this.setJson(true,msg, null);
        } catch (Exception e) {
            logger.error("LiveRoomController.toLiveRoom()--error", e);
            json = this.setJson(false, "系统异常", null);
        }
        return json;
    }

    /**
     * 打开更新vid_live_room弹窗
     */
    @RequestMapping("/liveRoom/selectRoomInfo")
    @ResponseBody
    public Object selectRoomInfo(HttpServletRequest request) {
        Map<String, Object> json = new HashMap<>();
        try {
             /*查询房间信息*/
            String roomMsg = roomInfo(request,request.getParameter("id"));

            Map<String,Object> room = FastJsonUtil.json2Map(roomMsg);

            int code = new BigDecimal(room.get("code").toString()).intValue();
            /*如果code是0说明查询成功*/
            if (code!=0){
                json = this.setJson(false,room.get("msg").toString(),null);
                return json;
            }
            json = this.setJson(true,"",room.get("data"));
            System.out.println("返回参数"+roomMsg);
        } catch (Exception e) {
            logger.error("LiveRoomController.selectRoomInfo()--error", e);
            json = this.setJson(false, "系統错误", null);
        }
        return json;
    }

    public String roomInfo(HttpServletRequest request,String roomId){
         /*查询房间信息*/
        SortedMap<String,String> message = new TreeMap<>();
        String arr[] = roomId.split("\\|");
        message.put("room_id",arr[0]);
        message.put("timestamp",new Date().getTime()/1000+"");
        message.put("sign",BajiayunUtil.createSign(message,websiteProfileService));
            /*向白家云发送请求创建房间 roomMsg为返回信息*/
        String roomMsg = HttpClientUtil.doPost("http://api.baijiacloud.com/openapi/room/info",message,"utf-8");
        return roomMsg;
    }
    /**
     * 更新live_room
     */
    @RequestMapping("/liveRoom/updateLiveRoom")
    @ResponseBody
    public Object updateLiveRoom(HttpServletRequest request,@ModelAttribute("liveRoom") LiveBaijiayun liveRoom) {
        Map<String, Object> json = new HashMap<String, Object>();
        try {
            /*整理房间的信息*/
            SortedMap<String,String> message = new TreeMap<>();
            message.put("end_time",liveRoom.getEndTime().getTime()/1000+"");
            message.put("room_id",liveRoom.getRoomId()+"");
            message.put("max_users",liveRoom.getMaxUsers()+"");
            message.put("start_time",liveRoom.getStartTime().getTime()/1000+"");
            liveRoom.setCreateTime(new Date());
            message.put("timestamp",liveRoom.getCreateTime().getTime()/1000+"");
            message.put("title",liveRoom.getRoomTitle());
            message.put("sign",BajiayunUtil.createSign(message,websiteProfileService));
            /*向白家云发送请求创建房间 roomMsg为返回信息*/
            String roomMsg = HttpClientUtil.doPost("http://api.baijiacloud.com/openapi/room/update",message,"utf-8");

            Map<String,Object> room = FastJsonUtil.json2Map(roomMsg);
            int code = new BigDecimal(room.get("code").toString()).intValue();
            /*如果code是0说明创建成功*/
            if (code!=0){
                json = this.setJson(false,room.get("msg").toString(),null);
                return json;
            }
            json = this.setJson(true,"",null);
            System.out.println("返回参数"+roomMsg);
        } catch (Exception e) {
            logger.error("LiveRoomController.updateLiveRoom()--error", e);
            json = this.setJson(false, "系统异常", null);
        }
        return json;
    }
    /**
     * 根据章节id判断是否是百家云直播
     */
    @RequestMapping("/ajax/getLiveBaijiayunByKpointid/{kpointId}")
    @ResponseBody
    public Map<String,Object> getLiveBaijiayunByKpointid(HttpServletRequest request, @PathVariable("kpointId") Long kpointId) {
        Map<String, Object> json = new HashMap<String, Object>(4);
        try {
            //判断是否是百家云直播
            CourseKpoint courseKpoint = courseKpointService.queryCourseKpointById(kpointId.intValue());
            if (ObjectUtils.isNotNull(courseKpoint)&&"baijiayun".equals(courseKpoint.getSupplier())&&StringUtils.isNotEmpty(courseKpoint.getVideoUrl())){
                json = this.setJson(true,"",null);
                return json;
            }
            json = this.setJson(false,"",null);

        } catch (Exception e) {
            logger.error("AdminLiveGenseeController.getLiveBaijiayunByKpointid()---error", e);
            json=this.setAjaxException(json);
        }
        return json;
    }
    /*查询百家云直播列表*/
    @RequestMapping("/liveRoom/selectBaijiayunRoom")
    public String selectBaijiayunRoom(HttpServletRequest request, @ModelAttribute("user") User user, @ModelAttribute("page") PageEntity page) {
        try {
            SortedMap<String,String> message = new TreeMap<>();
            message.put("page",page.getCurrentPage()+"");
            message.put("limit",page.getPageSize()+"");
            message.put("timestamp",new Date().getTime()/1000+"");
            message.put("sign",BajiayunUtil.createSign(message,websiteProfileService));
            SortedMap<String,String> count = new TreeMap<>();
            count.put("timestamp",new Date().getTime()/1000+"");
            count.put("sign",BajiayunUtil.createSign(count,websiteProfileService));
              /*查询房间列表*/
            String countMsg = HttpClientUtil.doPost("http://api.baijiacloud.com/openapi/room/list",count,"utf-8");
            Map<String,Object> countMap= FastJsonUtil.json2Map(countMsg);
            Map<String,Object> countList = FastJsonUtil.json2Map(countMap.get("data").toString());
            List list = FastJsonUtil.obj2JsonList(countList.get("list").toString());
            if (list.size()%page.getPageSize()==0){
                page.setTotalPageSize(list.size()/page.getPageSize());
            }else {
                page.setTotalPageSize(list.size()/page.getPageSize()+1);
            }
             /*查询房间列表*/
            String roomMsg = HttpClientUtil.doPost("http://api.baijiacloud.com/openapi/room/list",message,"utf-8");
            Map<String,Object> map= FastJsonUtil.json2Map(roomMsg);
            Map<String,Object> roomList = FastJsonUtil.json2Map(map.get("data").toString());
            request.setAttribute("roomList",roomList.get("list"));
        } catch (Exception e) {
            logger.error("AdminCouponController.selectBaijiayunRoom--error", e);
        }
        return getViewPath("/live/admin/baijiayun/select_baijiayun_list");
    }
    /**
     * 打开更新vid_live_room弹窗
     */
    @RequestMapping("/liveRoom/selectRoomFromList")
    @ResponseBody
    public Object selectRoomFromList(HttpServletRequest request) {
        Map<String, Object> json = new HashMap<>();
        try {
             /*查询房间信息*/
            String roomMsg = roomInfo(request,request.getParameter("roomId"));

            Map<String,Object> room = FastJsonUtil.json2Map(roomMsg);
            int code = new BigDecimal(room.get("code").toString()).intValue();
            /*如果code是0说明查询成功*/
            if (code!=0){
                json = this.setJson(false,room.get("msg").toString(),null);
                return json;
            }
            json = this.setJson(true,"",room.get("data"));
            System.out.println("返回参数"+roomMsg);
        } catch (Exception e) {
            logger.error("LiveRoomController.selectRoomInfo()--error", e);
            json = this.setJson(false, "系統错误", null);
        }
        return json;
    }
    /**
     * 删除房间
     */
    @RequestMapping("/liveRoom/deleteRoom")
    @ResponseBody
    public Object deleteRoom(HttpServletRequest request) {
        Map<String, Object> json = new HashMap<>();
        try {
            String roomId = request.getParameter("roomId");
            SortedMap<String,String> message = new TreeMap<>();
            message.put("room_id",roomId+"");
            message.put("timestamp",new Date().getTime()/1000+"");
            message.put("sign",BajiayunUtil.createSign(message,websiteProfileService));
            String roomMsg = HttpClientUtil.doPost("http://api.baijiacloud.com/openapi/room/delete",message,"utf-8");
            Map<String,Object> info = FastJsonUtil.json2Map(roomMsg);
            if (Integer.parseInt(info.get("code").toString())!=0){
                json = this.setJson(false,info.get("msg")+"",null);
                return json;
            }
            json = this.setJson(true,"",null);
        } catch (Exception e) {
            logger.error("LiveRoomController.deleteRoom()--error", e);
            json = this.setJson(false, "系統错误", null);
        }
        return json;
    }
}