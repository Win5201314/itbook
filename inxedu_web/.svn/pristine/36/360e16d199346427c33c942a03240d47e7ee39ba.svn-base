package com.inxedu.os.edu.controller.course;



import com.inxedu.os.common.constants.CommonConstants;
import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.sysLog.SystemLog;
import com.inxedu.os.common.util.*;
import com.inxedu.os.edu.constants.enums.WebSiteProfileType;
import com.inxedu.os.edu.entity.course.Course;
import com.inxedu.os.edu.entity.course.CourseKpointAtlas;
import com.inxedu.os.edu.entity.kpoint.CourseKpoint;
import com.inxedu.os.edu.entity.kpoint.CourseKpointDto;
import com.inxedu.os.edu.service.course.CourseKpointAtlasService;
import com.inxedu.os.edu.service.course.CourseKpointService;
import com.inxedu.os.edu.service.course.CourseService;
import com.inxedu.os.edu.service.website.WebsiteProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.util.*;

/**
 * CourseKpoint管理接口
 * @author www.inxedu.com
 */
@Controller
@RequestMapping("/admin")
public class AdminKpointController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(AdminKpointController.class);

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("courseKpoint")
    public void initBinderCourseKpoint(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("courseKpoint.");
    }

    @Autowired
    private CourseKpointService courseKpointService;
    @Autowired
	private WebsiteProfileService websiteProfileService;
	@Autowired
	private CourseKpointAtlasService courseKpointAtlasService;
	@Autowired
	private CourseService courseService;

    
    @RequestMapping("/kpoint/updateparentid/{parentId}/{kpointId}")
	@ResponseBody
    public Map<String,Object> updateKpointParentId(@PathVariable("parentId") int parentId,@PathVariable("kpointId") int kpointId){
    	Map<String,Object> json = new HashMap<String,Object>();
    	try{
 			if(parentId!=0&&courseKpointService.queryCourseKpointById(kpointId).getKpointType()==0){
				json = this.setJson(false, "文件目录不能移动到其他目录下", null);
			}else{
				courseKpointService.updateKpointParentId(kpointId, parentId);
				json = this.setJson(true, null, null);
			}

    	}catch (Exception e) {
    		this.setAjaxException(json);
			logger.error("updateKpointParentId()---error",e);
		}
    	return json;
    }
   /* 移动节点后更改节点排序*/
	@RequestMapping("/kpoint/updSort")
	@ResponseBody
	@SystemLog(type="update",operation="节点排序")
	public Map<String,Object> updSort(HttpServletRequest request){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			String nodes = request.getParameter("nodes");
			String[] nodesInfo = nodes.split(",");
			for (int i=0;i<nodesInfo.length;i++){
				int kpointId = Integer.parseInt(nodesInfo[i]);
				CourseKpoint courseKpoint = courseKpointService.queryCourseKpointById(kpointId);
				courseKpoint.setSort(i+1);
				courseKpointService.updateKpointSort(courseKpoint);
			}
		}catch (Exception e) {
			this.setAjaxException(json);
			logger.error("updSort()---error",e);
		}
		return json;
	}
    /**
     * 删除视频节点
     */
    @RequestMapping("/kpoint/deletekpoint/{kpointIds}")
    @ResponseBody
	@SystemLog(type="del",operation="删除视频节点")
    public Map<String,Object> deleteKpoint(@PathVariable("kpointIds") String kpointIds){
    	Map<String,Object> json = new HashMap<String,Object>();
    	try{
    		courseKpointService.deleteKpointByIds(kpointIds);
    		json = this.setJson(true, null, null);
		}catch (Exception e) {
    		this.setAjaxException(json);
			logger.error("deleteKpoint()---error",e);
		}
    	return json;
    }
    
    /**
     * 修改视频节点
     */
    @RequestMapping("/kpoint/updateKpoint")
    @ResponseBody
	@SystemLog(type="update",operation="视频节点信息")
    public Map<String,Object> updateKpoint(HttpServletRequest request,@ModelAttribute("courseKpoint") CourseKpoint courseKpoint){
    	Map<String,Object> json = new HashMap<String,Object>();
    	try{
			//课后作业
			if(courseKpoint.getFileType().equals("EXERCISES")){
				String examType=request.getParameter("examType");
				//试卷
				if(examType.equals("0")){
					//保存试卷id
					courseKpoint.setVideoUrl(request.getParameter("examPaperId"));
				}else{
					Course course = courseService.queryCourseById(courseKpointService.queryCourseKpointById(courseKpoint.getKpointId()).getCourseId());
					//所有试题id
					String examQuestionIds=request.getParameter("examQuestionIds");
					Map map = new HashMap();
					map.put("examQuestionIds", examQuestionIds);
					map.put("courseName", course.getCourseName());
					map.put("kpointName", courseKpoint.getName());
					String result=HttpUtil.doPost(CommonConstants.contextPath + "/api/ajax/webCourseAddPaper", map);
					Map<String,Object> resultMap = FastJsonUtil.json2Map(result);
					if((Boolean) (resultMap.get("success"))){
						//保存试卷id
						courseKpoint.setVideoUrl(resultMap.get("entity").toString());
						courseKpoint.setContent(resultMap.get("message").toString());
					}else{
						courseKpoint.setVideoUrl("");
						courseKpoint.setContent("");
					}
				}
			}
			courseKpoint.setVideoUrl(courseKpoint.getVideoUrl().trim());
    		courseKpointService.updateKpoint(courseKpoint);
			json = this.setJson(true, null, courseKpoint);
    	}catch (Exception e) {
    		this.setAjaxException(json);
			logger.error("updateKpoint()---error",e);
		}
    	return json;
    }
    
    /**
     * 通过过ID，查询视频详情
     * @param kpointId 视频ID
     */
    @RequestMapping("/kpoint/getkpoint/{kpointId}")
    @ResponseBody
    public Map<String,Object> queryCourseKpointById(@PathVariable("kpointId") int kpointId){
    	Map<String,Object> json = new HashMap<String,Object>();
    	try{
    		CourseKpointDto kpoint = courseKpointService.queryCourseKpointById(kpointId);

			//图片集和pdf课节将查询关联的图片集信息
			if(StringUtils.equals(kpoint.getFileType(),"ATLAS")||StringUtils.equals(kpoint.getFileType(), "PDF")){
				CourseKpointAtlas courseKpointAtlas= courseKpointAtlasService.queryKpointAtlasByKpointId(Long.valueOf(kpoint.getKpointId()));
				if (ObjectUtils.isNotNull(courseKpointAtlas)&&StringUtils.isNotEmpty(courseKpointAtlas.getUrl())) {
					//将图片路径截取存入图片集集合
					String atlasUrlArr[] = courseKpointAtlas.getUrl().split(",");
					String atlasTBUrlArr[] = courseKpointAtlas.getUrlThumbnail().split(",");
					List<CourseKpointAtlas> atlasList = new ArrayList<>();
					for (int i=0;i<atlasUrlArr.length;i++) {
						if(StringUtils.isNotEmpty(atlasUrlArr[i])){
							courseKpointAtlas = new CourseKpointAtlas();
							courseKpointAtlas.setKpointId(Long.valueOf(kpoint.getKpointId()));
							courseKpointAtlas.setUrl(atlasUrlArr[i]);
							courseKpointAtlas.setUrlThumbnail(atlasTBUrlArr[i]);
							atlasList.add(courseKpointAtlas);
						}
					}
					kpoint.setKpointAtlasesList(atlasList);
				}
			}

			if(kpoint.getFileType().equals("LIVE")&& ObjectUtils.isNotNull(kpoint.getLiveBeginTime()) &&ObjectUtils.isNotNull(kpoint.getLiveEndTime())){
				String beginDateStr=DateUtils.formatDate(kpoint.getLiveBeginTime(),"yyyy-MM-dd HH:mm:ss");
				String endDateStr=DateUtils.formatDate(kpoint.getLiveEndTime(),"yyyy-MM-dd HH:mm:ss");
				json = this.setJson(true, beginDateStr+"|"+endDateStr, kpoint);
				return json;
			}
    		json = this.setJson(true, null, kpoint);
    	}catch (Exception e) {
			this.setAjaxException(json);
			logger.error("queryCourseKpointById()--error",e);
		}
    	return json;
    }
    
    /**
     * 创建节点视频
     */
    @RequestMapping("/kpoint/addkpoint")
    @ResponseBody
	@SystemLog(type="add",operation="添加视频节点")
    public Map<String,Object> addKpoint(@ModelAttribute("courseKpoint") CourseKpoint courseKpoint){
    	Map<String,Object> json = new HashMap<String,Object>();
    	try{
    		courseKpoint.setAddTime(new Date());
    		courseKpoint.setFree(2);
    		courseKpointService.addCourseKpoint(courseKpoint);
    		json = this.setJson(true, null, courseKpoint);

		}catch (Exception e) {
    		this.setAjaxException(json);
			logger.error("addKpoint()--error",e);
		}
    	return json;
    }

    /**
     * 课程的视频列表
     */
    @RequestMapping("/kpoint/list/{courseId}")
    public ModelAndView showKpointList(HttpServletRequest request, @PathVariable("courseId") int courseId) {
    	ModelAndView model = new ModelAndView();
    	try {
    		model.setViewName(getViewPath("/admin/kpoint/kpoint_list"));// 章节列表
    		List<CourseKpoint> kpointList = courseKpointService.queryCourseKpointByCourseId(courseId);
			/*for(CourseKpoint courseKpoint:kpointList){
				courseKpoint.setContent("");
			}*/
    		model.addObject("kpointList", FastJsonUtil.obj2JsonString(kpointList));
    		model.addObject("courseId", courseId);

			Course course=courseService.queryCourseById(courseId);
			//获得课程类型 课程类型：COURSE(课程) LIVE(直播)
			model.addObject("sellType", course.getSellType());
		} catch (Exception e) {
            logger.error("showKpointList()---error", e);
            model.setViewName(this.setExceptionRequest(request, e));
        }
        return model;
    }
    
    /**
     *  生成cc视频参数按照 THQS 算法处理
     */
    @RequestMapping("/ajax/kpoint/ccVideoTHQSData")
    @ResponseBody
    public Object ccVideoTHQSData(HttpServletRequest request){
    	Map<String,Object> json = new HashMap<String,Object>();
    	try{
    		//cc视频配置
    		Map<String,Object> ccConfigMap=(Map<String,Object>)websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.cc.toString()).get("cc");
    		//上传视频名
    		String filename=request.getParameter("filename");
    		filename=URLEncoder.encode(filename,"UTF-8");
    		String infoStr="description="+"inxedu_upload_"+filename//视频描述
					+"&tag=null"//视频标签 	可选
					+"&title="+"inxedu_upload_"+filename//视频标题
					+"&userid="+ ccConfigMap.get("ccappID")//用户ID 	必选
					+"&time="+System.currentTimeMillis()/1000L//当前时间的 Unix  时间戳
					;
			String infoStrSalt=infoStr+"&salt="+ ccConfigMap.get("ccappKEY");// Spark API Key 值
																																	   
			String hash=MD5.getMD5(infoStrSalt);
			infoStr+="&hash="+hash;
    		json = this.setJson(true, infoStr, null);
    	}catch (Exception e) {
    		this.setAjaxException(json);
			logger.error("deleteKpoint()---error",e);
			json = this.setJson(false, "", null);
		}
    	return json;
    }


}