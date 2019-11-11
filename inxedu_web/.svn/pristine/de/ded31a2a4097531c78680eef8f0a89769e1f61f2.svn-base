package com.inxedu.os.app.controller.course;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.inxedu.os.common.cache.CacheUtil;
import com.inxedu.os.common.constants.CacheConstans;
import com.inxedu.os.common.util.DateUtils;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.StringUtils;
import com.inxedu.os.edu.constants.enums.WebSiteProfileType;
import com.inxedu.os.edu.entity.kpoint.CourseKpoint;
import com.inxedu.os.edu.entity.kpoint.CourseKpointDto;
import com.inxedu.os.edu.entity.member.MemberType;
import com.inxedu.os.edu.entity.subject.QuerySubject;
import com.inxedu.os.edu.entity.subject.Subject;
import com.inxedu.os.edu.entity.teacher.QueryTeacher;
import com.inxedu.os.edu.entity.teacher.Teacher;
import com.inxedu.os.edu.service.course.CourseKpointService;
import com.inxedu.os.edu.service.livecourse.LiveCourseService;
import com.inxedu.os.edu.service.member.MemberTypeService;
import com.inxedu.os.edu.service.subject.SubjectService;
import com.inxedu.os.edu.service.teacher.TeacherService;
import com.inxedu.os.edu.service.website.WebsiteProfileService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.inxedu.os.common.entity.PageEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.edu.entity.course.Course;
import com.inxedu.os.edu.entity.course.CourseDto;
import com.inxedu.os.edu.entity.course.QueryCourse;
import com.inxedu.os.edu.service.course.CourseService;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/webapp")
public class AppCourseController extends BaseController{
	private static Logger logger=Logger.getLogger(AppCourseController.class);
	
	@Autowired
	private CourseService courseService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private WebsiteProfileService websiteProfileService;
	@Autowired
	private MemberTypeService memberTypeService;
	@Autowired
	private LiveCourseService liveCourseService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private CourseKpointService courseKpointService;
	/**
	 * 课程列表
	 * queryCourse
	 * page
	 */
	@RequestMapping("/cou/list")
	@ResponseBody
	public Map<String, Object> showCourseList(HttpServletRequest request,@ModelAttribute("page") PageEntity page, @ModelAttribute("queryCourse") QueryCourse queryCourse){
		Map<String, Object> json=new HashMap<String, Object>();
		try{
			Map<String,Object> result = new HashedMap();
			// 页面传来的数据放到page中
			//page.setPageSize(12);
			//只查询上架的
			queryCourse.setIsavaliable(1);
			//查询 课程+套餐
			if (StringUtils.isEmpty(queryCourse.getSellType_cou_pag())){
				/*如果没有传sellType_cou_pag就取课程和套餐*/
				queryCourse.setSellType_cou_pag("true");
			}
			if(StringUtils.isEmpty(queryCourse.getOrder())){
				queryCourse.setOrder("BYGONE");//默认排序
			}
			// 搜索课程列表
			List<CourseDto> courseList = courseService.queryWebCourseListPage(queryCourse, page);
			result.put("courseList", courseList);
			// 查询选中专业
			QuerySubject querySubject = new QuerySubject();
			querySubject.setParentId(0);
			Subject subject = new Subject();
			subject.setSubjectId(queryCourse.getSubjectId());
			subject = subjectService.getSubjectBySubject(subject);
			/*获取专业筛选条件的信息*/
			result.put("currentSubject",subject);

			Map<String,Object> serviceSwitch = (Map<String,Object>)websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.serviceSwitch.toString()).get(WebSiteProfileType.serviceSwitch.toString());
			if ("ON".equals(serviceSwitch.get("member"))&&ObjectUtils.isNotNull(memberTypeService)){
				   /*会员类型集合*/
				List<MemberType> memberTypeList = memberTypeService.getMemberTypes();
				result.put("memberTypeList",memberTypeList);
			}

			result.put("page",page);

			result.put("queryCourse", queryCourse);

			  /*用于移动端展示的专业列表 查询的所有专业*/
			List<Subject> mobileSubjecs = subjectService.getSubjectList(new QuerySubject());
           	/*用于移动端 查询选择的subject的父级*/
			/*用于移动端*/
			result.put("mobileSubjecs",mobileSubjecs);
			json=this.setJson(true, "成功", result);
		}catch(Exception e){
			json=this.setJson(false, "异常", null);
			logger.error("showCourseList()--error",e);
		}
		return json;
	}
	/**
	 * 直播首页
	 */
	@RequestMapping("/front/showLivelist")
	@ResponseBody
	public Map<String, Object> liveIndex(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @ModelAttribute("queryCourse") QueryCourse queryCourse) {
		Map<String, Object> json=new HashMap<String, Object>();
		try {
			Map<String,Object> result = new HashedMap();
			//只查询上架的
			queryCourse.setIsavaliable(1);
			//查询 课程
			queryCourse.setSellType("LIVE");
			if(StringUtils.isEmpty(queryCourse.getOrder())){
				queryCourse.setOrder("BYGONE");//默认排序
			}
			queryCourse.setQueryTime(new Date());
			// 搜索课程列表
			List<Course> courseList = liveCourseService.queryWebLiveCourseListPage(queryCourse, page);
			result.put("courseList", courseList);

			// 查询所有1级专业
			QuerySubject querySubject = new QuerySubject();
			querySubject.setParentId(0);
			List<Subject> subjectList = subjectService.getSubjectList(querySubject);

			Subject subject = new Subject();
			subject.setSubjectId(queryCourse.getSubjectId());
			subject = subjectService.getSubjectBySubject(subject);
			/*获取专业筛选条件的信息*/
			result.put("currentSubject",subject);


			// 全部教师
			QueryTeacher query = new QueryTeacher();
			List<Teacher> teacherList =teacherService.queryTeacherList(query);
			result.put("page",page);
			result.put("queryCourse", queryCourse);
			result.put("teacherList", teacherList);
			Map<String,Object> serviceSwitch = (Map<String,Object>)websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.serviceSwitch.toString()).get(WebSiteProfileType.serviceSwitch.toString());
			if ("ON".equals(serviceSwitch.get("member"))&&ObjectUtils.isNotNull(memberTypeService)){
				/*会员类型集合*/
				List<MemberType> memberTypeList = memberTypeService.getMemberTypes();
				result.put("memberTypeList",memberTypeList);
			}
			/*用于移动端展示的专业列表 查询的所有专业*/
			List<Subject> mobileSubjecs = subjectService.getSubjectList(new QuerySubject());
           	/*用于移动端 查询选择的subject的父级*/
				/*用于移动端*/
			result.put("mobileSubjecs",mobileSubjecs);
			result.put("currentTeacher",teacherService.getTeacherById(queryCourse.getTeacherId()));
			json = this.setJson(true,"",result);
		} catch (Exception e) {
			json=this.setJson(false, "异常", null);
			logger.error("showLivelist()--error",e);
		}
		return json;
	}
	/**
	 * 课程详情
	 * courseId
	 */
	@RequestMapping("/front/couinfo")
	@ResponseBody
	public Map<String, Object> couinfo(HttpServletRequest request){
		Map<String, Object> json=new HashMap<String, Object>();
		try{
			String courseId=request.getParameter("courseId");
			if(courseId==null||courseId.trim().equals("")){
				json=this.setJson(true, "课程Id不能为空", null);
				return json;
			}
			// 查询课程详情
            Course course = courseService.queryCourseById(Integer.parseInt(courseId));
            json=this.setJson(true, "成功", course);
		}catch(Exception e){
			json=this.setJson(false, "异常", null);
			logger.error("couinfo()--error",e);
		}
		return json;
	}
	/**
	 * 首页免费课程
	 * count
	 */
	@RequestMapping("/front/freeCourse")
	@ResponseBody
	public Map<String, Object> freeCourse(HttpServletRequest request){
		Map<String, Object> json=new HashMap<String, Object>();
		try{
			int count = Integer.parseInt(request.getParameter("count"));
			PageEntity pageEntity = new PageEntity();
			pageEntity.setPageSize(count);
			QueryCourse queryCourse = new QueryCourse();
			/*set查询条件免费*/
			queryCourse.setOrder("FREE");
				/*set查询条件课程*/
			queryCourse.setSellType("COURSE");
			// 查询课程
			List<CourseDto>courseDtoList = courseService.queryWebCourseListPage(queryCourse,pageEntity);
			json=this.setJson(true, "成功", courseDtoList);
		}catch(Exception e){
			json=this.setJson(false, "异常", null);
			logger.error("freeCourse()--error",e);
		}
		return json;
	}
	/**
	 * 首页精品课程
	 */
	@RequestMapping("/front/recommenCourse")
	@ResponseBody
	public Map<String, Object> recommenCourse(HttpServletRequest request){
		Map<String, Object> json=new HashMap<String, Object>();
		try{
			String count = request.getParameter("count");
			// 获得所有推荐课程
			Map<String,List<CourseDto>> courseDtoList =  courseService.queryRecommenCourseList();
			List<CourseDto> courseDtoList1 = new ArrayList<>();
			for(int i=0;i<Integer.parseInt(count);i++){
				if (i<courseDtoList.get("recommen_32").size()){
					courseDtoList1.add(courseDtoList.get("recommen_32").get(i));
				}
			}
			json=this.setJson(true, "成功", courseDtoList1);
		}catch(Exception e){
			json=this.setJson(false, "异常", null);
			logger.error("recommenCourse()--error",e);
		}
		return json;
	}
	/**
	 * 首页热门课程
	 * count
	 */
	@RequestMapping("/front/topCourse")
	@ResponseBody
	public Map<String, Object> topCourse(HttpServletRequest request){
		Map<String, Object> json=new HashMap<String, Object>();
		try{
			int count = Integer.parseInt(request.getParameter("count"));
			PageEntity pageEntity = new PageEntity();
			pageEntity.setPageSize(count);
			QueryCourse queryCourse = new QueryCourse();
			/*set查询条件浏览量*/
			queryCourse.setOrder("FOLLOW");
			/*set查询条件课程*/
			queryCourse.setSellType("COURSE");
			// 获得所有推荐课程
			List<CourseDto> courseDtoList =  courseService.queryWebCourseListPage(queryCourse,pageEntity);
			json=this.setJson(true, "成功", courseDtoList);
		}catch(Exception e){
			json=this.setJson(false, "异常", null);
			logger.error("topCourse()--error",e);
		}
		return json;
	}
	/**
	 * 首页热门直播
	 * count
	 */
	@RequestMapping("/front/topLive")
	@ResponseBody
	public Map<String, Object> topLive(HttpServletRequest request){
		Map<String, Object> json=new HashMap<String, Object>();
		try{
			int count = Integer.parseInt(request.getParameter("count"));
			//近期直播章节
			CourseKpoint courseKpoint=new CourseKpoint();
			courseKpoint.setFileType("LIVE");//直播
			courseKpoint.setKpointType(1);//节点类型 0文件目录 1视频
			courseKpoint.setQueryOrder("near");//近期
			courseKpoint.setQueryLimitNum(8);//限制条数
			courseKpoint.setIsavaliable(1);
			//courseKpoint.setLiveBeginTime(DateUtils.parseToDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd"),"yyyy-MM-dd"));//包括今天之后的
				/*结束时间大于今天的  正在播放 和未开始的*/
			courseKpoint.setLiveEndTime(DateUtils.parseToDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd"),"yyyy-MM-dd"));
				/*去重查询直播最近开始的直播章节*/
			List<CourseKpointDto> courseKpointDtoList=courseKpointService.queryCourseNearestKpointList(courseKpoint);
				/*如果没有要开始的直播章节就查询最近结束的4个*/
			if (ObjectUtils.isNull(courseKpointDtoList)){
				courseKpoint.setLiveBeginTime(null);
				courseKpoint.setQueryOrder("overNear");//近期结束章节
				courseKpoint.setQueryLimitNum(count);//限制条数
				courseKpointDtoList=courseKpointService.queryCourseNearestKpointList(courseKpoint);
			}
			json=this.setJson(true, "成功", courseKpointDtoList);
		}catch(Exception e){
			json=this.setJson(false, "异常", null);
			logger.error("topLive()--error",e);
		}
		return json;
	}
}
