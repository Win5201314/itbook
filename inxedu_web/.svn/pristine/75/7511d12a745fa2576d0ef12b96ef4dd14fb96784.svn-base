package com.inxedu.os.edu.controller.video;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.edu.entity.video.Server;
import com.inxedu.os.edu.service.video.AdminStreamingService;
import com.inxedu.os.edu.service.website.WebsiteProfileService;
import com.inxedu.os.edu.util.httputil.HttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

import org.dom4j.*;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.*;



/**
 * 流媒体管理
 * @author ZJC
 */
@Controller
public class AdminStreamingController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(AdminStreamingController.class);

	@Autowired
	private WebsiteProfileService websiteProfileService;
    @Autowired
	private AdminStreamingService adminStreamingService;
    @InitBinder({"server"})
    public void initBinderSysUser(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("server.");
    }


    /**
     * 流媒体服务器管理
     * @return ModelAndView
     */
    @RequestMapping("/admin/video/servermanager")
    public ModelAndView serverManager(HttpServletRequest request) throws DocumentException {
        ModelAndView model = new ModelAndView();
        model.setViewName(getViewPath("/admin/video/server_list"));
        List<Server> list = adminStreamingService.getServerList();
        model.addObject("serverList",list);
        return model;
    }

    /**
     * 添加流媒体服务器
     * @return Object
     */
    @RequestMapping("/admin/video/createserver")
    @ResponseBody
    public Object createServer(@ModelAttribute("server") Server server) {
        Map<String,Object> json = new HashMap<String,Object>();
        try{
            server.setId(UUID.randomUUID().toString().replace("-",""));
            adminStreamingService.createServer(server);
            json = this.setJson(true, "流媒体服务器添加成功", null);
        }catch (Exception e) {
            this.setAjaxException(json);
            logger.error("createServer()--error",e);
        }
        return json;
    }

    /**
     * 删除流媒体服务器
     * @return Object
     */
    @RequestMapping("/admin/video/deleteserver/{serverId}")
    @ResponseBody
    public Object deleteServer(HttpServletRequest request, @PathVariable("serverId") String serverId) {
        Map<String,Object> json = new HashMap<String,Object>();
        try{
            adminStreamingService.deleteServer(serverId);
            json = this.setJson(true, "流媒体服务器删除成功", null);
        }catch (Exception e) {
            this.setAjaxException(json);
            logger.error("createServer()--error",e);
        }
        return json;
    }

    /**
     * 获取频道列表
     * @return ModelAndView
     */
    @RequestMapping("/admin/video/showchanlist")
    public ModelAndView showChanList(HttpServletRequest request) throws DocumentException {
        ModelAndView model = new ModelAndView();
        model.setViewName(getViewPath("/admin/video/stream_list"));
        String xml = HttpClient.sendHttpGet("http://127.0.0.1:6807/nmc?cmd=getallchanninfos&accesscode=sibore&retxml=true&");
        List<Map<String,String>> list = new ArrayList<>();
        Document document = DocumentHelper.parseText(xml);
        Element root = document.getRootElement();
        Iterator<Element> datas = root.element("liveinfos").elementIterator("info");
        while (datas.hasNext()){
            Element data = datas.next();
            Map<String,String> map = new HashMap<>();
            map.put("name",data.attributeValue("name"));
            map.put("recvratemul",data.attributeValue("recvratemul"));
            map.put("sendratemul",data.attributeValue("sendratemul"));
            map.put("recvratep2p",data.attributeValue("recvratep2p"));
            map.put("sendratep2p",data.attributeValue("sendratep2p"));
            map.put("memery",data.attributeValue("memery"));
            map.put("clients",data.attributeValue("clients"));
            map.put("usercount",data.attributeValue("usercount"));
            map.put("curnum",data.attributeValue("curnum"));
            map.put("maxsnnum",data.attributeValue("maxsnnum"));
            map.put("height",data.attributeValue("height"));
            map.put("width",data.attributeValue("width"));
            map.put("packetsize",data.attributeValue("packetsize"));
            map.put("bitrate",data.attributeValue("bitrate"));
            map.put("mediatype",data.attributeValue("mediatype"));
            map.put("statcode",data.attributeValue("statcode"));
            map.put("id",data.attributeValue("id"));
            list.add(map);
        }
        model.addObject("streamList",list);
        return model;
    }

    /**
     * 创建频道
     * @return Object
     */
    @RequestMapping("/admin/video/createchan")
    @ResponseBody
    public Object createChan(HttpServletRequest request) {
        Map<String, Object> json = null;
        try{
            HttpClient.sendHttpGet("http://127.0.0.1:6807/nmc?cmd=createlive&uid=nmc%40sibore%2Ecom&url=pushchannel&name=io&creator=nmc%40sibore%2Ecom&partner=140&type=WMV&ifpub=0&ismeeting=1&hostpassword=111&meetingpassword=909&desc=WMV&accesscode=sibore&");
            json=this.setJson(true, "创建成功！", "");
        }catch(Exception e){
            logger.error("signLive()----error", e);
            json=this.setJson(false, "系统繁忙,请稍后再试！", "");
        }
        return json;
    }

    /**
     * 删除频道
     * @return Object
     */
    @RequestMapping("/admin/video/deletechan/{streamId}")
    @ResponseBody
    public Object deleteChan(HttpServletRequest request, @PathVariable("streamId") int streamId) {
        Map<String, Object> json = null;
        try{
            HttpClient.sendHttpGet("http://127.0.0.1:6807/nmc?cmd=removelive&channelid="+streamId+"&accesscode=sibore");
            json=this.setJson(true, "删除成功！", "");
        }catch(Exception e){
            logger.error("signLive()----error", e);
            json=this.setJson(false, "系统繁忙,请稍后再试！", "");
        }
        return json;
    }

//    /**
//     * 获取中继频道信息
//     * @return ModelAndView
//     */
//    @RequestMapping("/stream/details/pushallrelaychannelinfo/ip/{ip}/port/{port}")
//    public Object pushallrelaychannelinfo(HttpServletRequest request, @PathVariable("ip") String ip,@PathVariable("port") String port) throws DocumentException {
//        try {
//            String json  = new String(readInputStream(request.getInputStream()), "UTF-8");
//            System.out.println(json);
//            String aaa = null;
//            JSONObject obj = new JSONObject(json);
//            String relaynum = obj.getString("relaynum");
//            JSONArray jArr = obj.getJSONArray("relay_list") == null ? null : obj.getJSONArray("relay_list");
//            List<Map> list = new ArrayList<>();
//            for(int i = 0; i<jArr.length(); i++){
//                JSONObject obj1 = jArr.getJSONObject(i);
//                Map<String,String> map = new HashMap<>();
//                map.put("chann_id",obj1.getString("chann_id"));
//                map.put("relay_progress",obj1.getString("relay_progress"));
//                map.put("relay_state",obj1.getString("relay_state"));
//                list.add(map);
//            }
//            int i = 1;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


    /**
     * 获取服务器信息
     * @return ModelAndView
     */
    @RequestMapping("/stream/details/pushchannelliveinfo/ip/{ip}/port/{port}")
    public void pushchannelliveinfo(HttpServletRequest request, @PathVariable("ip") String ip,@PathVariable("port") String port) throws DocumentException {
        try {
            String json  = new String(readInputStream(request.getInputStream()), "UTF-8");
            JSONObject obj = new JSONObject(json);
            String channum = obj.getString("channum");
            String servip = obj.getString("servip");
            String tatalrtmpbandwidth = obj.getString("tatalrtmpbandwidth");
            JSONArray jArr = obj.getJSONArray("infolist");
            List<Map<String,String>> list = new ArrayList<>();
            for(int i = 0; i<jArr.length(); i++){
                JSONObject obj1 = jArr.getJSONObject(i);
                Map<String,String> map = new HashMap<>();
                map.put("asf",obj1.getString("asf"));
                map.put("channid",obj1.getString("channid"));
                map.put("rtmp",obj1.getString("rtmp"));
                map.put("rtmp_bandwidth",obj1.getString("rtmp_bandwidth"));
                map.put("rtmp_clients",obj1.getString("rtmp_clients"));
                map.put("rtmp_have",obj1.getString("rtmp_have"));
                map.put("ip",servip);
                list.add(map);
            }
            adminStreamingService.getChanDetail(list);
            int i = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        boolean var3 = false;

        int len;
        while((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }

        outSteam.close();
        inStream.close();
        return outSteam.toByteArray();
    }
}
