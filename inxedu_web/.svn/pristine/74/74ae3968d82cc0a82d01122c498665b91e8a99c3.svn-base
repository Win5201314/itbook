package com.inxedu.os.edu.controller.live;

import com.inxedu.os.common.cache.CacheUtil;
import com.inxedu.os.common.constants.CacheConstans;
import com.inxedu.os.common.constants.CommonConstants;
import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.util.*;
import com.inxedu.os.edu.constants.enums.WebSiteProfileType;
import com.inxedu.os.edu.entity.course.Course;
import com.inxedu.os.edu.entity.kpoint.CourseKpoint;
import com.inxedu.os.edu.entity.kpoint.CourseKpointDto;
import com.inxedu.os.edu.entity.livegensee.LiveGensee;
import com.inxedu.os.edu.entity.user.User;
import com.inxedu.os.edu.service.course.CourseKpointService;
import com.inxedu.os.edu.service.course.CourseService;
import com.inxedu.os.edu.service.livegensee.LiveGenseeService;
import com.inxedu.os.edu.service.order.OrderService;
import com.inxedu.os.edu.service.website.WebsiteProfileService;
import com.inxedu.os.edu.util.baijiayun.BajiayunUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

import static com.inxedu.os.common.util.SingletonLoginUtils.getLoginUserId;

/**
 * Live章节管理接口
 * @author www.inxedu.com
 */
@Controller
public class LiveKpointController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(LiveKpointController.class);
    @Autowired
    private CourseService courseService;
	@Autowired
	private CourseKpointService courseKpointService;
	@Autowired
	private OrderService trxorderService;
	@Autowired
	private WebsiteProfileService websiteProfileService;
	@Autowired
	private LiveGenseeService liveGenseeService;


	/**
	 * 直播 检查 播放
	 * @param request
	 * @param kpointId 课程节点id
	 * @return
	 */
	@RequestMapping("/front/ajax/livePlay")
	@ResponseBody
	public Object livePlay(HttpServletRequest request, HttpServletResponse response, @RequestParam("kpointId")int kpointId) throws IOException {
		Map<String, Object> json = null;
		try {
			int userId= getLoginUserId(request);
			CourseKpoint courseKpoint = courseKpointService.queryCourseKpointById(kpointId);
			if(userId<=0){
				json=this.setJson(false, "请登录后观看直播！", "");
			}

			// 当传入数据不正确时直接返回
			else if (ObjectUtils.isNull(courseKpoint)) {
				json=this.setJson(false, "直播章节不存在！", "");
			}
			// 判断节点不可以试听
			else{
				boolean isok1 = trxorderService.checkUserCursePay(userId,courseKpoint.getCourseId());
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(courseKpoint.getLiveBeginTime());
				calendar.add(Calendar.MINUTE, -15);//15分钟前
				if (!isok1) {// 判断该课程不可以观看
					json=this.setJson(false, "该直播为收费直播，请购买后操作。", "");
				}else if(new Date().before(calendar.getTime())){
					json=this.setJson(false, "直播未开始，您可在直播前15分钟内进入直播！", "");
				}/*else if(new Date().after(courseKpoint.getLiveEndTime())){
					json=this.setJson(false, "直播已结束！", "");
				}*/else{
					json=this.setJson(true,courseKpoint.getSupplier(), "/front/ajax/livePlayhtml?kpointId="+kpointId);
				}

				if ("APP".equals(courseKpoint.getOpenType())){
					json=this.setJson(true,"bajiayunApp", BajiayunUtil.roomUrl(request,courseKpoint.getVideoUrl(),courseKpoint.getOpenType(),"student",websiteProfileService));
				}
			}
		}catch (Exception e){
			logger.error("CourseKpointController.getKpointLink", e);
			json=this.setJson(false, "系统繁忙,请稍后再试！", "");
		}
		return json;
	}

	/**
	 * 直播 检查 播放
	 * @param request
	 * @param kpointId 课程节点id
	 * @return
	 */
	@RequestMapping("/front/ajax/livePlayhtml")
	public String livePlayHtml(HttpServletRequest request,  @RequestParam("kpointId")int kpointId) throws IOException {
		try {
			User user=SingletonLoginUtils.getLoginUser(request);
			CourseKpoint courseKpoint = courseKpointService.queryCourseKpointById(kpointId);
			if(ObjectUtils.isNull(user)){
				request.setAttribute("success",false);
				request.setAttribute("url","请登录后观看直播！");
				return getViewPath("/web/live/uc_play_live");
			}

			// 当传入数据不正确时直接返回
			else if (ObjectUtils.isNull(courseKpoint)) {
				request.setAttribute("success",false);
				request.setAttribute("url","直播章节不存在！");
				return getViewPath("/web/live/uc_play_live");
			}
			// 判断节点不可以试听
			else{
				boolean isok1 = trxorderService.checkUserCursePay(user.getUserId(),courseKpoint.getCourseId());
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(courseKpoint.getLiveBeginTime());
				calendar.add(Calendar.MINUTE, -15);//15分钟前
				if (!isok1) {// 判断该课程不可以观看
					request.setAttribute("success",false);
					request.setAttribute("url","该直播为收费直播，请购买后操作。！");
					return getViewPath("/web/live/uc_play_live");
				}else if(new Date().before(calendar.getTime())){
					request.setAttribute("success",false);
					request.setAttribute("url","直播未开始，您可在直播前15分钟内进入直播！");
					return getViewPath("/web/live/uc_play_live");
				}/*else if(new Date().after(courseKpoint.getLiveEndTime())){
					request.setAttribute("success",false);
					request.setAttribute("url","直播已结束！");
					return getViewPath("/web/live/uc_play_live");
				}*/else{
					String videoUrl=courseKpoint.getVideoUrl();
					//判断 直播供应商
					//展示互动
					if("gensee".equals(courseKpoint.getSupplier())){
						//生成 展示互动 认证K 用户id+展示互动直播id+当前时间戳
						String encodeStr=user.getUserId()+videoUrl.substring(videoUrl.indexOf("/site/s/")+8,videoUrl.indexOf("/site/s/")+16)+System.currentTimeMillis();
						String encoded = new BASE64Encoder().encode(encodeStr.getBytes());
						CacheUtil.set(CacheConstans.INDEX_LIVE_GENSEEK, encoded,15);//缓存15秒 ，过期后失效

						videoUrl+="?nickname="+user.getDisplayName()+"&uid="+1000000000+user.getUserId()+"&k="+encoded;
						//展视互动直播
						LiveGensee liveGensee=new LiveGensee();
						liveGensee.setLiveId(Long.valueOf(kpointId));
						List<LiveGensee> liveGenseeList=liveGenseeService.queryLiveGenseeList(liveGensee);
						if(ObjectUtils.isNotNull(liveGenseeList)) {
							liveGensee = liveGenseeList.get(0);
							videoUrl+="&token="+liveGensee.getStudentToken();
						}
					}
					//因酷云直播
					else if("inxedu_cloud".equals(courseKpoint.getSupplier())){
						//videoUrl  因酷云复制的 1，直播id（1234）   2，直播回放 （1324|qwerkladionfweoiis）(房间id|回放id)
						//直播链接地址
						//http://{{域名}}/glive/{{房间id}}?token={{验证key}}&nick={{昵称}}&uid={{用户id}}
						//生成 认证token（认证token可自定义）
						String authentication=user.getUserId()+""+System.currentTimeMillis();//用户id+当前时间戳
						//放入缓存，时间30秒（进入直播时请求，验证token是否存在）
						CacheUtil.set("authentication_"+authentication, authentication,30);
						//直播回放
						if(ObjectUtils.isNotNull(videoUrl.split("\\|"))&&videoUrl.split("\\|").length==2){
							videoUrl="http://finance.aodianyun.com/glive/"+videoUrl.split("\\|")[0]+"?recordId="+videoUrl.split("\\|")[1]+"&nick="+ user.getDisplayName()+"&token="+authentication+"&uid="+user.getUserId();
						}else {
							videoUrl="http://finance.aodianyun.com/glive/"+videoUrl.split("\\|")[0]+"?nick="+ user.getDisplayName()+"&token="+authentication+"&uid="+user.getUserId();
						}
					}
					/*如果是百家云*/
					else if ("baijiayun".equals(courseKpoint.getSupplier())){
						 videoUrl = BajiayunUtil.roomUrl(request,courseKpoint.getVideoUrl(),"WEB","student",websiteProfileService);
						/*查询房间转码信息*/
						SortedMap<String,String> message = new TreeMap<>();
						String roomId = courseKpoint.getVideoUrl();
						message.put("room_id",roomId);
						message.put("timestamp",new Date().getTime()/1000+"");
						message.put("sign",BajiayunUtil.createSign(message,websiteProfileService));
            			/*向白家云发送请求查看转码状态 roomMsg为返回信息*/
						String roomMsg = HttpClientUtil.doPost("http://api.baijiacloud.com/openapi/playback/getStatus",message,"utf-8");
						Map<String,Object> data = FastJsonUtil.json2Map(roomMsg);
						Map<String,Object> roomStatus = FastJsonUtil.json2Map(data.get("data").toString());
						if ("100".equals(roomStatus.get("status").toString())){
							videoUrl = "http://api.baijiacloud.com/web/playback/index?classid="+courseKpoint.getVideoUrl();
						}
					}
					//网站开关配置
					Map<String,Object> WebSwitch = (Map<String,Object>)websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.WebSwitch.toString()).get(WebSiteProfileType.WebSwitch.toString());
					request.setAttribute("WebSwitch",WebSwitch);

					request.setAttribute("success",true);
					request.setAttribute("url",videoUrl);
					return getViewPath("/web/live/uc_play_live");
				}
			}
		}catch (Exception e){
			logger.error("CourseKpointController.getKpointLink", e);
			return this.setExceptionRequest(request, e);
		}

	}

	/**
	 * 因酷云直播，观看认证接口（请求地址可以自定义，同时到因酷云个人中心修改直播防盗链）
	 * 请求会返回刚才生成token
	 *	必须返回errno 非0表示错误  0标识成功
	 */
	@RequestMapping("/api/auth/live")
	@ResponseBody
	public Object playCheck(HttpServletRequest request, @RequestParam("room_id")String room_id
			, @RequestParam("token")String token
			, @RequestParam("nick")String nick
			, @RequestParam("uid")String uid) {
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			if(ObjectUtils.isNotNull(CacheUtil.get("authentication_" + token))){
				json.put("errno", 0);//非0表示错误  0标识成功
				json.put("message", "success");
			}else{
				json.put("errno", 1);
				json.put("message", "播放认证失败");
				//自定义播放认证失败跳转链接
				json.put("redirect", CommonConstants.contextPath+"?msg="+ URLEncoder.encode("播放认证失败"));
			}
		} catch (Exception e) {
			logger.error("LiveKpointController.playCheck", e);
			json.put("errno", 1);
			json.put("message", "系统错误");
		}
		return json;
	}

	/**
	 * 因酷云直播 预览请求，写入播放验证code
	 * 注：请求地址不能修改
	 */
	@RequestMapping("/live/ajax/viewauthtoken")
	@ResponseBody
	public Object viewAuthToken(HttpServletRequest request,@RequestParam("authtoken")String authtoken) {
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			//放入缓存，时间30秒（进入直播时请求，验证token是否存在）
			CacheUtil.set("authentication_"+authtoken, authtoken,60);
			json= this.setJson(true, "操作成功", "");
		}catch (Exception e){
			json = this.setAjaxException(json);
			logger.error("LiveKpointController.viewAuthToken---error", e);
		}
		return json;
	}


	/**
	 * 根据直播id获得 跟现在 最近的一个直播播放
	 * @param request
	 * @param courseId 课程id
	 * @return
	 */
	@RequestMapping("/front/ajax/nowLivePlay")
	@ResponseBody
	public Object nowLivePlay(HttpServletRequest request, HttpServletResponse response,@RequestParam("courseId")int courseId) throws IOException{
		Map<String, Object> json = null;
		try {
			int userId= getLoginUserId(request);
			Course course = courseService.queryCourseById(courseId);
			if(userId<=0){
				json=this.setJson(false, "请登录后观看直播！", "");
			}
			// 当传入数据不正确时直接返回
			else if (ObjectUtils.isNull(course)) {
				json=this.setJson(false, "直播不存在！", "");
			}
			else{
				boolean isok1 = trxorderService.checkUserCursePay(userId,courseId);
				if (!isok1) {// 判断该课程不可以观看
					json=this.setJson(false, "该直播为收费直播，请购买后操作。", "");
				}else{
					CourseKpoint courseKpoint=new CourseKpoint();
					courseKpoint.setQueryLimitNum(1);
					courseKpoint.setCourseId(courseId);
					courseKpoint.setQueryOrder("near");//升序排序
					courseKpoint.setFileType("LIVE");
					courseKpoint.setIsavaliable(1);
					courseKpoint.setQueryLiveIngTime(new Date());
					List<CourseKpointDto> courseKpointDtoList=courseKpointService.queryKpointList(courseKpoint);
					if(ObjectUtils.isNotNull(courseKpointDtoList)){
						json=this.setJson(true, "", "/front/ajax/livePlayhtml?kpointId="+courseKpointDtoList.get(0).getKpointId());
					}else{
						json=this.setJson(false, "暂无直播中的课程，请查看直播课程表！", "");
					}
				}
			}
		}catch (Exception e){
			logger.error("CourseKpointController.getKpointLink", e);
			json=this.setJson(false, "系统繁忙,请稍后再试！", "");
		}
		return json;
	}

	/**
	 * 直播 展示互动K值认证
	 * @return
	 */
	@RequestMapping("/live/ajax/genseeIdentification")
	@ResponseBody
	public Object genseeIdentification(@RequestParam("k")String k) {
		try {
			if(StringUtils.isNotEmpty(k)&&ObjectUtils.isNotNull(CacheUtil.get(CacheConstans.INDEX_LIVE_GENSEEK))&&CacheUtil.get(CacheConstans.INDEX_LIVE_GENSEEK).toString().equals(k)){
				CacheUtil.remove(CacheConstans.INDEX_LIVE_GENSEEK);//移除缓存
				return "pass";
			}

		} catch (Exception e) {
			logger.error("genseeIdentification()", e);
		}
		return "fail";
	}
}