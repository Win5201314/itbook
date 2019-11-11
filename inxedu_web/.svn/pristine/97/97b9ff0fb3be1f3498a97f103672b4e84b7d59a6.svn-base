package com.inxedu.os.edu.controller.livegensee;


import com.google.gson.reflect.TypeToken;
import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.*;
import com.inxedu.os.edu.constants.enums.WebSiteProfileType;
import com.inxedu.os.edu.entity.livegensee.LiveGensee;
import com.inxedu.os.edu.service.livegensee.LiveGenseeService;
import com.inxedu.os.edu.service.website.WebsiteProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author www.inxedu.com
 * @description 展视互动直播 后台LiveGenseeController
 */
@Controller
@RequestMapping("/admin")
public class AdminLiveGenseeController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(AdminLiveGenseeController.class);

    @Autowired
    private LiveGenseeService liveGenseeService;
    @Autowired
    private WebsiteProfileService websiteProfileService;


    // 绑定属性 封装参数
    @InitBinder("liveGensee")
    public void initLiveGensee(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("liveGensee.");
    }

    /**
     * 到展视互动直播添加页面
     */
    @RequestMapping("/liveGensee/toadd")
    public ModelAndView toAddLiveGensee(HttpServletRequest request,ModelAndView model) {
        model.setViewName(getViewPath("/live/admin/livegensee/livegensee_add"));
        try{
            model.addObject("liveId", request.getParameter("liveId"));
            model.addObject("kpointName", request.getParameter("kpointName"));
            if(StringUtils.isNotEmpty(request.getParameter("beginTime"))){
                model.addObject("beginTime", DateUtils.parseToDate(request.getParameter("beginTime"),"yyyy-MM-dd HH:mm:ss"));
            }
            if(StringUtils.isNotEmpty(request.getParameter("liveEndTime"))){
                model.addObject("liveEndTime", DateUtils.parseToDate(request.getParameter("liveEndTime"),"yyyy-MM-dd HH:mm:ss"));
            }
        }catch (Exception e) {
            model.setViewName(this.setExceptionRequest(request, e));
            logger.error("AdminLiveGenseeController.toAddLiveGensee()---error",e);
        }
        return model;
    }

    /**
     * 添加展视互动直播
     */
    @RequestMapping("/liveGensee/add")
    @ResponseBody
    public Object addLiveGensee(HttpServletRequest request, @ModelAttribute("liveGensee") LiveGensee liveGensee) {
        Map<String, Object> json = new HashMap<String, Object>(4);
        try {
            //subject是否存在 相同时会引起冲突
            LiveGensee queryLiveGensee=new LiveGensee();
            queryLiveGensee.setSubject(liveGensee.getSubject());
            if(ObjectUtils.isNotNull(liveGenseeService.queryLiveGenseeList(queryLiveGensee))){
                json = setJson(false, "课堂主题已存在，修改后重试！", null);
                return json;
            }

            //获得 展视互动直播 配置
            Map<String,Object> datamap=(Map<String,Object>)websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.genseeLive.toString()).get(WebSiteProfileType.genseeLive.toString());
            if(ObjectUtils.isNull(datamap)){
                json = setJson(false, "请在后头配置展视互动直播信息后重试！", null);
                return json;
            }

            Map map = new HashMap();
            map.put("subject",liveGensee.getSubject() );
            map.put("teacherToken",liveGensee.getTeacherToken());
            map.put("studentToken",liveGensee.getStudentToken() );
            map.put("studentClientToken",liveGensee.getStudentClientToken() );
            map.put("startDate",DateUtils.formatDate(liveGensee.getStartDate(),"yyyy-MM-dd HH:mm:ss"));
            if(ObjectUtils.isNotNull(liveGensee.getInvalidDate())){
                map.put("invalidDate",DateUtils.formatDate(liveGensee.getInvalidDate(),"yyyy-MM-dd HH:mm:ss"));
            }
            map.put("assistantToken",liveGensee.getAssistantToken());
            map.put("speakerInfo",liveGensee.getSpeakerInfo());
            map.put("scheduleInfo ",liveGensee.getScheduleInfo());
            map.put("webJoin",liveGensee.getWebJoin());
            map.put("clientJoin",liveGensee.getClientJoin());
            map.put("description",liveGensee.getDescription());
            if(ObjectUtils.isNotNull(liveGensee.getDuration())){
                map.put("duration",liveGensee.getDuration()+"");
            }
            map.put("uiMode",liveGensee.getUiMode()+"");
            map.put("uiColor",liveGensee.getUiColor());
            map.put("scene",liveGensee.getScene()+"");
            map.put("uiWindow",liveGensee.getUiWindow()+"");
            map.put("uiVideo",liveGensee.getUiVideo()+"");
            map.put("upgrade",liveGensee.getUpgrade()+"");
            map.put("sec",liveGensee.getSec());
            map.put("realtime",liveGensee.getRealtime()+"");
            if(ObjectUtils.isNotNull(liveGensee.getMaxAttendees())){
                map.put("maxAttendees",liveGensee.getMaxAttendees()+"");
            }

            map.put("loginName",datamap.get("loginName"));
            map.put("password",datamap.get("password"));

            //接口调用访问路径规则： {站点域名}/integration/site/{接口路径}
            String result= HttpUtil.doPost(datamap.get("siteDomain")+"/integration/site"+"/training/room/created", map);
            Map<String,Object> resultMap = FastJsonUtil.json2Map(result);

            int code=Integer.parseInt(resultMap.get("code").toString());
            if(code==0){//成功
                // 添加展视互动直播
                liveGensee.setGenseeId(resultMap.get("id").toString());
                liveGensee.setNumber(resultMap.get("number").toString());
                liveGensee.setAssistantToken(resultMap.get("assistantToken").toString());
                liveGensee.setStudentToken(resultMap.get("studentToken").toString());
                liveGensee.setTeacherToken(resultMap.get("teacherToken").toString());
                liveGensee.setStudentClientToken(resultMap.get("studentClientToken").toString());
                liveGensee.setStartDate(new Date((Long) resultMap.get("startDate")));
                //liveGensee.setStartDate(DateUtils.toDate(resultMap.get("startDate"),"yyyy-MM-dd HH:mm:ss"));
                liveGensee.setWebJoin(resultMap.get("webJoin")+"");
                liveGensee.setClientJoin(resultMap.get("clientJoin").toString());
                if(ObjectUtils.isNotNull(resultMap.get("invalidDate"))){
                    liveGensee.setInvalidDate(new Date((Long)resultMap.get("invalidDate")));
                }
                liveGensee.setTeacherJoinUrl(resultMap.get("teacherJoinUrl").toString());
                liveGensee.setStudentJoinUrl(resultMap.get("studentJoinUrl").toString());
                liveGensee.setScene((Integer)resultMap.get("scene"));
                liveGenseeService.addLiveGensee(liveGensee);
                json = setJson(true,"", liveGensee);
            }else if(code==-1){//失败
                json = setJson(false, "创建失败，"+resultMap.get("message"), null);
            }
            else if(code==101){//
                json = setJson(false, "参数错误，"+resultMap.get("message"), null);
            }
            else if(code==102){//
                json = setJson(false, "参数转换错误 ，"+resultMap.get("message"), null);
            }
            else if(code==200){//
                json = setJson(false, "认证失败  ，"+resultMap.get("message"), null);
            }
            else if(code==201){//
                json = setJson(false, "口令过期  ，"+resultMap.get("message"), null);
            }
            else if(code==300){//
                json = setJson(false, "系统错误  ，"+resultMap.get("message"), null);
            }
            else if(code==500){//
                json = setJson(false, "业务错误 ，"+resultMap.get("message"), null);
            }
            else if(code==501){//
                json = setJson(false, "业务错误 – 数据不存在 ，"+resultMap.get("message"), null);
            }
            else if(code==502){//
                json = setJson(false, "业务错误 – 重复数据 ，"+resultMap.get("message"), null);
            }
            else if(code==600){//
                json = setJson(false, "接口被禁用，请联系管理员 ，"+resultMap.get("message"), null);
            }
        } catch (Exception e) {
            logger.error("AdminLiveGenseeController.addLiveGensee", e);
            json=this.setAjaxException(json);
        }
        return json;
    }

    /**
     * 刪除展视互动直播
     */
    @RequestMapping("/liveGensee/delete/{id}")
    public String deleteLiveGensee(HttpServletRequest request, @PathVariable("id") Long id) {
        try {
            // 刪除展视互动直播
            liveGenseeService.delLiveGenseeById(id);
        } catch (Exception e) {
            logger.error("AdminLiveGenseeController.deleteLiveGensee()---error", e);
            return setExceptionRequest(request, e);
        }
        return "redirect:/admin/liveGensee/list";
    }

    /**
     * ajax刪除展视互动直播
     */
    @RequestMapping("/ajax/liveGenseeDel/{id}")
    @ResponseBody
    public Map<String,Object> ajaxDelLiveGensee(HttpServletRequest request, @PathVariable("id") Long id) {
        Map<String, Object> json = new HashMap<String, Object>(4);
        try {
            //获得 展视互动直播 配置
            Map<String,Object> datamap=(Map<String,Object>)websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.genseeLive.toString()).get(WebSiteProfileType.genseeLive.toString());
            if(ObjectUtils.isNull(datamap)){
                json = setJson(false, "请在后头配置展视互动直播信息后重试！", null);
                return json;
            }

            LiveGensee liveGensee=liveGenseeService.getLiveGenseeById(id);
            Map map = new HashMap();
            map.put("sec",liveGensee.getSec());

            map.put("loginName",datamap.get("loginName"));
            map.put("password",datamap.get("password"));
            map.put("roomId",liveGensee.getGenseeId());
            //接口调用访问路径规则： {站点域名}/integration/site/{接口路径}
            String result= HttpUtil.doPost(datamap.get("siteDomain")+"/integration/site"+"/training/room/deleted", map);
            Map<String,String> resultMap=FastJsonUtil.json2StringMap(result);
            int code=Integer.parseInt(resultMap.get("code").toString());
            if(code==0){//成功
                // 刪除展视互动直播
                liveGenseeService.delLiveGenseeById(id);
                json = setJson(true,"", liveGensee);
            }else if(code==-1){//失败
                json = setJson(false, "创建失败，"+resultMap.get("message"), null);
            }
            else if(code==101){//
                json = setJson(false, "参数错误，"+resultMap.get("message"), null);
            }
            else if(code==102){//
                json = setJson(false, "参数转换错误 ，"+resultMap.get("message"), null);
            }
            else if(code==200){//
                json = setJson(false, "认证失败  ，"+resultMap.get("message"), null);
            }
            else if(code==201){//
                json = setJson(false, "口令过期  ，"+resultMap.get("message"), null);
            }
            else if(code==300){//
                json = setJson(false, "系统错误  ，"+resultMap.get("message"), null);
            }
            else if(code==500){//
                json = setJson(false, "业务错误 ，"+resultMap.get("message"), null);
            }
            else if(code==501){//
                json = setJson(false, "业务错误 – 数据不存在 ，"+resultMap.get("message"), null);
            }
            else if(code==502){//
                json = setJson(false, "业务错误 – 重复数据 ，"+resultMap.get("message"), null);
            }
            else if(code==600){//
                json = setJson(false, "接口被禁用，请联系管理员 ，"+resultMap.get("message"), null);
            }
        } catch (Exception e) {
            logger.error("AdminLiveGenseeController.ajaxDelLiveGensee()---error", e);
            json=this.setAjaxException(json);
        }
        return json;
    }


    /**
     * ajax根据章节 刪除展视互动直播
     */
    @RequestMapping("/ajax/liveGenseeDelByKpointid/{ids}")
    @ResponseBody
    public Map<String,Object> ajaxLiveGenseeDelByKpointid(HttpServletRequest request, @PathVariable("ids") String ids) {
        Map<String, Object> json = new HashMap<String, Object>(4);
        try {
            //获得 展视互动直播 配置
            Map<String,Object> datamap=(Map<String,Object>)websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.genseeLive.toString()).get(WebSiteProfileType.genseeLive.toString());
            if(ObjectUtils.isNull(datamap)){
                json = setJson(false, "请在后头配置展视互动直播信息后重试！", null);
                return json;
            }

            if(ids!=null && ids.trim().length()>0){
                if(ids.trim().endsWith(",")){
                    ids = ids.trim().substring(0,ids.trim().length()-1);
                }
                String idArr []= ids.split(",");
                LiveGensee liveGensee=null;
                List<LiveGensee> liveGenseeList = new ArrayList<>();
                for (int i=0;i<idArr.length;i++){
                    liveGensee=new LiveGensee();
                    liveGensee.setLiveId(Long.valueOf(idArr[i]));
                    liveGenseeList=liveGenseeService.queryLiveGenseeList(liveGensee);
                    if(ObjectUtils.isNotNull(liveGenseeList)){
                        liveGensee=liveGenseeList.get(0);
                        Map map = new HashMap();
                        map.put("sec",liveGensee.getSec());

                        map.put("loginName",datamap.get("loginName"));
                        map.put("password",datamap.get("password"));
                        map.put("roomId",liveGensee.getGenseeId());
                        //接口调用访问路径规则： {站点域名}/integration/site/{接口路径}
                        String result= HttpUtil.doPost(datamap.get("siteDomain")+"/integration/site"+"/training/room/deleted", map);
                        Map<String,Object> resultMap = FastJsonUtil.json2Map(result);
                        int code=Integer.parseInt(resultMap.get("code").toString());
                        if(code==0){//成功
                            // 刪除展视互动直播
                            liveGenseeService.delLiveGenseeById(liveGensee.getId());
                            json = setJson(true,"", liveGensee);
                        }else if(code==-1){//失败
                            json = setJson(false, "创建失败，"+resultMap.get("message"), null);
                        }
                        else if(code==101){//
                            json = setJson(false, "参数错误，"+resultMap.get("message"), null);
                        }
                        else if(code==102){//
                            json = setJson(false, "参数转换错误 ，"+resultMap.get("message"), null);
                        }
                        else if(code==200){//
                            json = setJson(false, "认证失败  ，"+resultMap.get("message"), null);
                        }
                        else if(code==201){//
                            json = setJson(false, "口令过期  ，"+resultMap.get("message"), null);
                        }
                        else if(code==300){//
                            json = setJson(false, "系统错误  ，"+resultMap.get("message"), null);
                        }
                        else if(code==500){//
                            json = setJson(false, "业务错误 ，"+resultMap.get("message"), null);
                        }
                        else if(code==501){//
                            json = setJson(false, "业务错误 – 数据不存在 ，"+resultMap.get("message"), null);
                        }
                        else if(code==502){//
                            json = setJson(false, "业务错误 – 重复数据 ，"+resultMap.get("message"), null);
                        }
                        else if(code==600){//
                            json = setJson(false, "接口被禁用，请联系管理员 ，"+resultMap.get("message"), null);
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("AdminLiveGenseeController.ajaxLiveGenseeDelByKpointid()---error", e);
            json=this.setAjaxException(json);
        }
        return json;
    }


    /**
     * 根据id修改
     */
    @RequestMapping("/liveGensee/toUpdate/{id}")
    public ModelAndView toUpdateLiveGensee(HttpServletRequest request,ModelAndView model, @PathVariable("id") Long id) {
        model.setViewName(getViewPath("/live/admin/livegensee/livegensee_update"));
        try {
            // 查詢展视互动直播
            LiveGensee liveGensee = liveGenseeService.getLiveGenseeById(id);
            // 把返回的数据放到model中
            model.addObject("liveGensee", liveGensee);
        } catch (Exception e) {
            model.setViewName(setExceptionRequest(request, e));
            logger.error("AdminLiveGenseeController.toUpdateLiveGensee()--error", e);
        }
        return model;
    }
    /**
     * 更新展视互动直播
     */
    @RequestMapping("/liveGensee/update")
    @ResponseBody
    public Object updateLiveGensee(HttpServletRequest request,@ModelAttribute("liveGensee") LiveGensee liveGensee) {
        Map<String, Object> json = new HashMap<String, Object>(4);
        try {
            //subject是否存在 相同时会引起冲突
            LiveGensee queryLiveGensee=new LiveGensee();
            queryLiveGensee.setSubject(liveGensee.getSubject());
            //原来的subject 如果相同说明没修改
            String liveGenseeOldSubject = request.getParameter("liveGenseeOldSubject");
            if(!liveGenseeOldSubject.equals(liveGensee.getSubject())&&ObjectUtils.isNotNull(liveGenseeService.queryLiveGenseeList(queryLiveGensee))){
                json = setJson(false, "课堂主题已存在，修改后重试！", null);
                return json;
            }

            //获得 展视互动直播 配置
            Map<String,Object> datamap=(Map<String,Object>)websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.genseeLive.toString()).get(WebSiteProfileType.genseeLive.toString());
            if(ObjectUtils.isNull(datamap)){
                json = setJson(false, "请在后头配置展视互动直播信息后重试！", null);
                return json;
            }

            Map map = new HashMap();
            map.put("subject",liveGensee.getSubject() );
            map.put("teacherToken",liveGensee.getTeacherToken());
            map.put("studentToken",liveGensee.getStudentToken() );
            map.put("studentClientToken",liveGensee.getStudentClientToken() );
            map.put("startDate",DateUtils.formatDate(liveGensee.getStartDate(),"yyyy-MM-dd HH:mm:ss"));
            if(ObjectUtils.isNotNull(liveGensee.getInvalidDate())){
                map.put("invalidDate",DateUtils.formatDate(liveGensee.getInvalidDate(),"yyyy-MM-dd HH:mm:ss"));
            }
            map.put("assistantToken",liveGensee.getAssistantToken());
            map.put("speakerInfo",liveGensee.getSpeakerInfo());
            map.put("scheduleInfo ",liveGensee.getScheduleInfo());
            map.put("webJoin",liveGensee.getWebJoin());
            map.put("clientJoin",liveGensee.getClientJoin());
            map.put("description",liveGensee.getDescription());
            if(ObjectUtils.isNotNull(liveGensee.getDuration())){
                map.put("duration",liveGensee.getDuration()+"");
            }
            map.put("uiMode",liveGensee.getUiMode()+"");
            map.put("uiColor",liveGensee.getUiColor());
            map.put("scene",liveGensee.getScene()+"");
            map.put("uiWindow",liveGensee.getUiWindow()+"");
            map.put("uiVideo",liveGensee.getUiVideo()+"");
            map.put("upgrade",liveGensee.getUpgrade()+"");
            map.put("sec",liveGensee.getSec());
            map.put("realtime",liveGensee.getRealtime()+"");
            if(ObjectUtils.isNotNull(liveGensee.getMaxAttendees())){
                map.put("maxAttendees",liveGensee.getMaxAttendees()+"");
            }

            map.put("loginName",datamap.get("loginName"));
            map.put("password",datamap.get("password"));
            map.put("Id",liveGensee.getGenseeId());

            //接口调用访问路径规则： {站点域名}/integration/site/{接口路径}
            String result= HttpUtil.doPost(datamap.get("siteDomain")+"/integration/site"+"/training/room/modify", map);
            Map<String,String> resultMap= FastJsonUtil.json2StringMap(result);
            int code=Integer.parseInt(resultMap.get("code").toString());
            if(code==0){//成功
                // 修改展视互动直播
                liveGenseeService.updateLiveGensee(liveGensee);
                json = setJson(true,"", liveGensee);
            }else if(code==-1){//失败
                json = setJson(false, "创建失败，"+resultMap.get("message"), null);
            }
            else if(code==101){//
                json = setJson(false, "参数错误，"+resultMap.get("message"), null);
            }
            else if(code==102){//
                json = setJson(false, "参数转换错误 ，"+resultMap.get("message"), null);
            }
            else if(code==200){//
                json = setJson(false, "认证失败  ，"+resultMap.get("message"), null);
            }
            else if(code==201){//
                json = setJson(false, "口令过期  ，"+resultMap.get("message"), null);
            }
            else if(code==300){//
                json = setJson(false, "系统错误  ，"+resultMap.get("message"), null);
            }
            else if(code==500){//
                json = setJson(false, "业务错误 ，"+resultMap.get("message"), null);
            }
            else if(code==501){//
                json = setJson(false, "业务错误 – 数据不存在 ，"+resultMap.get("message"), null);
            }
            else if(code==502){//
                json = setJson(false, "业务错误 – 重复数据 ，"+resultMap.get("message"), null);
            }
            else if(code==600){//
                json = setJson(false, "接口被禁用，请联系管理员 ，"+resultMap.get("message"), null);
            }
        } catch (Exception e) {
            logger.error("AdminLiveGenseeController.updateLiveGensee()--error", e);
            json = this.setAjaxException(json);
        }
        return json;
    }

    /**
     * 查询展视互动直播分页列表
     */
    @RequestMapping("/liveGensee/list")
    public ModelAndView liveGenseeListPage(HttpServletRequest request,ModelAndView model,@ModelAttribute("liveGensee") LiveGensee liveGensee, @ModelAttribute("page") PageEntity page) {
        model.setViewName(getViewPath("/live/admin/livegensee/livegensee_list"));
        try {
            //按条件查询展视互动直播分页
            List<LiveGensee> liveGenseeList =  liveGenseeService.queryLiveGenseeListPage(liveGensee, page);
            //展视互动直播数据
            model.addObject("liveGenseeList",liveGenseeList);
            //分数数据
            model.addObject("page",page);
            //展视互动直播查询条件
            model.addObject("liveGensee",liveGensee);
        } catch (Exception e) {
            model.setViewName(setExceptionRequest(request, e));
            logger.error("AdminLiveGenseeController.liveGenseeListPage()--error", e);
        }
        return model;
    }

    /**
     * 根据章节id获取 展视互动直播
     */
    @RequestMapping("/ajax/getLiveGenseeByKpointid/{kpointId}")
    @ResponseBody
    public Map<String,Object> getLiveGenseeByKpointid(HttpServletRequest request, @PathVariable("kpointId") Long kpointId) {
        Map<String, Object> json = new HashMap<String, Object>(4);
        try {
            //展视互动直播
            LiveGensee liveGensee=new LiveGensee();
            liveGensee.setLiveId(kpointId);
            List<LiveGensee> liveGenseeList=liveGenseeService.queryLiveGenseeList(liveGensee);
            if(ObjectUtils.isNotNull(liveGenseeList)){
                json = setJson(true, null, liveGenseeList.get(0));
            }else {
                json = setJson(false, null, null);
            }

        } catch (Exception e) {
            logger.error("AdminLiveGenseeController.getLiveGenseeByKpointid()---error", e);
            json=this.setAjaxException(json);
        }
        return json;
    }
}


