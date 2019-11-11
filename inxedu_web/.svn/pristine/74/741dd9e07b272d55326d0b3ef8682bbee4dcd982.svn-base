package com.inxedu.os.edu.controller.course;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inxedu.os.common.cache.CacheUtil;
import com.inxedu.os.common.constants.CommonConstants;
import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.util.FastJsonUtil;
import com.inxedu.os.common.util.HttpClientUtil;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.SingletonLoginUtils;
import com.inxedu.os.common.util.StringUtils;
import com.inxedu.os.edu.constants.enums.WebSiteProfileType;
import com.inxedu.os.edu.entity.course.Course;
import com.inxedu.os.edu.entity.course.CourseStudyhistory;
import com.inxedu.os.edu.entity.kpoint.CourseKpoint;
import com.inxedu.os.edu.service.course.CourseKpointService;
import com.inxedu.os.edu.service.course.CourseService;
import com.inxedu.os.edu.service.course.CourseStudyhistoryService;
import com.inxedu.os.edu.service.order.OrderService;
import com.inxedu.os.edu.service.website.WebsiteProfileService;
import com.inxedu.os.edu.util.baijiayun.BajiayunUtil;
import com.inxedu.os.edu.util.inxeduvideo.InxeduVideo;

/**
 * CourseKpoint管理接口 User: qinggang.liu Date: 2014-05-27
 * @author www.inxedu.com
 */
@Controller
public class CourseKpointController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(CourseKpointController.class);

	// 课程播放
	private static final String getKopintHtml = getViewPath("/web/course/videocode");

	private static final String kpointAudioAjax=getViewPath("/web/playCourse/kpoint_audio_ajax");//音频加载地址
	private static final String playAudioAjax=getViewPath("/web/playCourse/play_audio_ajax");//加载播放大厅音频地址
	private static final String playAudioAjaxMobile=getViewPath("/web/playCourse/play_audio_ajax_mobile");//手机 加载播放大厅音频地址
	private static final String playAtlasAjax=getViewPath("/web/playCourse/play_atlas_ajax");//加载播放大厅图片集
	private static final String playTxtAjax=getViewPath("/web/playCourse/play_txt_ajax");//加载播放大厅文本

	@Autowired
	private OrderService trxorderService;

	@Autowired
	private CourseKpointService courseKpointService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private WebsiteProfileService websiteProfileService;
	@Autowired
	private CourseStudyhistoryService courseStudyhistoryService;

	/**
	 * 课程章节 检查 播放
	 * @param request
	 * @param kpointId 课程节点id
	 * @param courseId 课程id
	 * @return
	 */
	@RequestMapping("/front/ajax/checkKpoint")
	public String checkKpoint(HttpServletRequest request, HttpServletResponse response,@RequestParam("kpointId")int kpointId, @RequestParam("courseId") int courseId, Model model) throws IOException{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		try {
			CourseKpoint courseKpoint = courseKpointService.queryCourseKpointById(kpointId);
			request.setAttribute("courseKpoint",courseKpoint);
			request.setAttribute("user",SingletonLoginUtils.getLoginUser(request));
			// 当传入数据不正确时直接返回
			if (ObjectUtils.isNull(courseKpoint)) {
				out.println("<script>var noCover=true; dialog dialog('提示','参数错误！',1);</script>");
				return null;
			}
			// 判断节点不可以试听
			if (courseKpoint.getFree() == 2) {
				boolean isok1 = trxorderService.checkUserCursePay(SingletonLoginUtils.getLoginUserId(request),courseId);
				if (!isok1) {// 判断该课程不可以观看
					courseKpoint.setVideoUrl("");
					out.println("<script>var noCover=true; dialog('提示','该课程为收费课程，请购买后操作。',1);</script>");
					return null;
				}
			}
			//playFromType 1 课程详情 2 播放大厅
			int playFromType=Integer.parseInt(request.getParameter("playFromType"));
			if(playFromType==1&&( courseKpoint.getFileType().equals("TXT")||courseKpoint.getFileType().equals("ATLAS")||courseKpoint.getFileType().equals("PDF")||courseKpoint.getFileType().equals("AUDIO") )){
				courseKpoint.setVideoUrl("");
				out.println("<script>var noCover=true; dialog('提示','文档、文本格式、图片集、音频等格式暂不支持试听!',1);</script>");
				return null;
			}
			//判断是否为手机浏览器
			boolean isMoblie = SingletonLoginUtils.JudgeIsMoblie(request);
			model.addAttribute("isMoblie", isMoblie);
			//章节详情
			model.addAttribute("courseKpoint",courseKpoint);
			//查询课程信息
			Course course= courseService.queryCourseById(courseKpoint.getCourseId());
			model.addAttribute("course",course);

			//视频
			if(courseKpoint.getFileType().equals("VIDEO")){
				// 视频url
				String videourl = courseKpoint.getVideoUrl();
				// 播放类型
				String videotype = courseKpoint.getVideoType();
				//查询inxeduVideo的key
				if(videotype.equalsIgnoreCase(WebSiteProfileType.inxeduVideo.toString())){
					Map<String,Object> map=(Map<String,Object>)websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.inxeduVideo.toString()).get(WebSiteProfileType.inxeduVideo.toString());

					String play_url = "http://vod.baofengcloud.com/" + map.get("UserId") + "/player/cloud.swf";

					if(StringUtils.isEmpty(map.get("UserId")+"")||StringUtils.isEmpty(map.get("SecretKey")+"")||StringUtils.isEmpty(map.get("AccessKey")+"")){
						out.println("<script>var noCover=true; dialog('播放提示','请购买因酷云视频，后台配置后重试!',1);</script>");
						return null;
					}

					String url = "servicetype=1&uid="+map.get("UserId")+"&fid="+videourl;
					play_url += "?" + url;
					String token = InxeduVideo.createPlayToken(videourl,map.get("SecretKey").toString(),map.get("AccessKey").toString());
					play_url += "&tk=" + token;
					play_url += "&auto=" + 1;
					videourl=play_url;
				}
				/*如果是百家云点播*/
				if(videotype.equalsIgnoreCase(WebSiteProfileType.baijiayun.toString())){
					SortedMap<String,String> map = new TreeMap<>();
					map.put("video_id",courseKpoint.getVideoUrl());
					map.put("timestamp",new Date().getTime()/1000+"");
					map.put("sign", BajiayunUtil.createSign(map,websiteProfileService));
					/*获取视频token*/
					String roomMsg = HttpClientUtil.doPost("http://api.baijiacloud.com/openapi/video/getPlayerToken",map,"utf-8");
					Map<String,Object> tokenMap = FastJsonUtil.json2Map(roomMsg);
					if (Integer.parseInt(tokenMap.get("code")+"")==0){
						Map<String,Object> token  = FastJsonUtil.json2Map(tokenMap.get("data").toString());
						videourl= token.get("token")+"";
					}else {
						out.println("<script>var noCover=true; dialog('播放提示','因酷云视频地址错误，请检查修改后重试!',1);</script>");
					}
				}
				//查询INXEDUCLOUD的用户id
				if(videotype.equalsIgnoreCase(WebSiteProfileType.inxeduCloud.toString())){
					Map<String,Object> map=(Map<String,Object>)websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.inxeduCloud.toString()).get(WebSiteProfileType.inxeduCloud.toString());

					if(StringUtils.isEmpty(map.get("UserId").toString())){
						out.println("<script>var noCover=true; dialog('播放提示','请购买因酷云视频，后台配置后重试!',1);</script>");
						return null;
					}
					String videoArr[]= videourl.split("\\|");
					String configArr[]= map.get("UserId").toString().split("\\|");
					if(ObjectUtils.isNotNull(videoArr)&&videoArr.length>=3){
						String play_url="http://"+configArr[0]+".long-vod.cdn.aodianyun.com/u/"+configArr[0]+"/m3u8/adaptive/"+videoArr[0]+".m3u8";
						model.addAttribute("configArr",  map.get("UserId"));
						// 当前视频的解密key 加入 cookie
						//WebUtils.setCookie(response, "inxeduCloud_drmcode", videourl.split("\\|")[1], 1);
						// 当前视频的解密key 加入 缓存
						CacheUtil.set("temp_inxeduCloud_drmcode"+videoArr[2], videoArr[1],60);
						videourl=play_url;

						// 查询是否 有播放记录
						CourseStudyhistory tempHistory =  new CourseStudyhistory();
						tempHistory.setUserId(Long.valueOf(SingletonLoginUtils.getLoginUserId(request)));
						tempHistory.setCourseId(Long.valueOf(courseKpoint.getCourseId()));
						tempHistory.setKpointId(Long.valueOf(courseKpoint.getKpointId()));
						List<CourseStudyhistory> courseStudyhistoryList = courseStudyhistoryService.getCourseStudyhistoryList(tempHistory);
						if (ObjectUtils.isNotNull(courseStudyhistoryList)) {
							tempHistory = courseStudyhistoryList.get(0);
							//获取上次 播放时间
							model.addAttribute("lastPlayTime", tempHistory.getDataback().split(",")[0].split("\\|")[0]);
						}
					}else {
						out.println("<script>var noCover=true; dialog('播放提示','因酷云视频地址错误，请检查修改后重试!',1);</script>");
						return null;
					}

				}
				//查询cc的appId key
				if(videotype.equalsIgnoreCase(WebSiteProfileType.cc.toString())){//如果cc
					Map<String,Object> map=websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.cc.toString());
					model.addAttribute("ccwebsitemap", map);
				}
				//查询乐视的appId key
				/*if(videotype.equalsIgnoreCase(WebSiteProfileType.letv.toString())){//如果乐视
					Map<String,Object>	lmap=websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.letv.toString());
					model.addAttribute("websiteLetvmap", lmap);
				}*/

				// 放入数据
				model.addAttribute("videourl", videourl);
				model.addAttribute("videotype", videotype);
				return getKopintHtml;
			}
			//音频
			else if(courseKpoint.getFileType().equals("AUDIO")){
				//手机加载播放大厅音频
				if(isMoblie){
					return playAudioAjaxMobile;
				}
				if(playFromType==1){
					//加载课程详情音频
					return kpointAudioAjax;
					//return playAudioAjaxMobile;
				}
				//加载播放大厅音频
				return playAudioAjax;
			}
			//文本
			else if(courseKpoint.getFileType().equals("TXT")){
				return playTxtAjax;
			}
			//图片集 pdf
			else if(courseKpoint.getFileType().equals("ATLAS")||courseKpoint.getFileType().equals("PDF")){
				//手机
				if(isMoblie){
					out.println("<script>var noCover=true; window.location.href=\"/mobile/ajax/mobileatlas?kpointId="+courseKpoint.getKpointId()+"\"</script>");
					return null;
				}
				return playAtlasAjax;
			}
			//课后作业
			else if(courseKpoint.getFileType().equals("EXERCISES")){
				//课后作业
				if(StringUtils.isNotEmpty(courseKpoint.getVideoUrl())) {
					out.println("<script>var noCover=true; videotype=\""+courseKpoint.getVideoType()+"\"; window.open(\" "+ CommonConstants.contextPath+"/paper/toExamPaper/"+courseKpoint.getVideoUrl()+"\");</script>");
					return null;
				}else{
					out.println("<script>var noCover=true; dialog('提示','该课程章节未添加课后作业。',1);</script>");
					return null;
				}

			}
		}catch (Exception e){
			logger.error("CourseKpointController.getKpointLink", e);
			out.println("<script>var noCover=true; dialog('提示','系统繁忙,请稍后再试！',1);</script>");
		}
		return null;
	}

	/**
	 * 点播 drm 解密key 获取
	 */
	@RequestMapping("/api/ajax/drmkey")
	@ResponseBody
	public String playCheck(HttpServletRequest request) {
		//return WebUtils.getCookie(request, "inxeduCloud_drmcode");
		return CacheUtil.get("temp_inxeduCloud_drmcode" + request.getParameter("ucode"))+"";
	}


	/**
	 * 因酷云视频预览 请求，写入播放验证code
	 * 注：请求地址不能修改
	 */
	@RequestMapping("/video/ajax/codewritein")
	@ResponseBody
	public Object codeWritein(HttpServletRequest request,@RequestParam("videoCode")String videoCode) {
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			// 当前视频的解密key 加入 缓存
			CacheUtil.set("inxeduCloud_drmcode"+videoCode.split("\\|")[2], videoCode.split("\\|")[1],60);
			json= this.setJson(true, "操作成功", "");
		}catch (Exception e){
			json = this.setAjaxException(json);
			logger.error("CourseKpointController.codeWritein---error", e);
		}
		return json;
	}

	/**
	 * 手机图片集页面
	 */
	@RequestMapping("/mobile/ajax/mobileatlas")
	public String mobileLoadAtlas(HttpServletRequest request,@RequestParam("kpointId") int kpointId) {
		try {
			// 查询节点
			CourseKpoint courseKpoint = courseKpointService.queryCourseKpointById(kpointId);
			// 当传入数据不正确时直接返回
			if (ObjectUtils.isNull(courseKpoint)) {
				request.setAttribute("message","课程章节不存在");
			}else if (ObjectUtils.isNull(courseKpoint)||!trxorderService.checkUserCursePay(SingletonLoginUtils.getLoginUserId(request),courseKpoint.getCourseId())){
				request.setAttribute("message","你没有权限观看此章节图片集，请购买后观看");
			}else{
				request.setAttribute("kpointAtlasesList", FastJsonUtil.obj2JsonString(courseKpoint.getKpointAtlasesList()));
				request.setAttribute("courseKpoint",courseKpoint);
			}
		} catch (Exception e) {
			logger.error("CourseKpointController.loadAtlas---error", e);
			return setExceptionRequest(request, e);
		}
		//图片集列表
		return getViewPath("/web/playCourse/play_atlas_mobile");
	}
}